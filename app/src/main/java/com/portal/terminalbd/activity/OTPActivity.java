package com.portal.terminalbd.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.portal.terminalbd.R;
import com.portal.terminalbd.fragments.SettingsFragment;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.ExpenseCategory;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.model.TokenNo;
import com.portal.terminalbd.model.TransactionMethod;
import com.portal.terminalbd.model.Vendor;
import com.portal.terminalbd.network.APIClient;
import com.portal.terminalbd.network.PaymentCards;
import com.portal.terminalbd.utils.OTPReceiver;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {
    TextView counter;
    PinView editTextotp;
    Button verifybtn, resendBtn;
    CompositeDisposable compositeDisposable;
    String otp;
    String phone;
    String uniqueKey;

    ProgressDialog progressDialog;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        requestPermission();

        counter = findViewById(R.id.tv_counter);
        editTextotp = findViewById(R.id.edtOtp);
        verifybtn = findViewById(R.id.verifyBtn);
        resendBtn = findViewById(R.id.btn_resend);
        compositeDisposable = new CompositeDisposable();
        counters();

        Intent intent = getIntent();
        otp = intent.getStringExtra("otpcode");
        phone = intent.getStringExtra("phone");
        uniqueKey = intent.getStringExtra("uniqueKey");

               /*
        initialize realm database
         */
        realm = Realm.getDefaultInstance();




        new OTPReceiver().setEditText_otp(editTextotp);

        editTextotp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable str) {
                if (str.toString().equals(otp)) {
                    counter.setVisibility(View.GONE);
                    resendBtn.setVisibility(View.GONE);
                    verifybtn.setClickable(true);
                    verifybtn.setBackgroundColor(getResources().getColor(R.color.orenge));
                    verifybtn.setTextColor(getResources().getColor(R.color.white));

                    getSetup();

                } else {
                    verifybtn.setClickable(false);
                    verifybtn.setTextColor(Color.parseColor("#000000"));
                    verifybtn.setBackgroundColor(getResources().getColor(R.color.grey_20));
                }
            }
        });






    }

    private void getSetup() {

        progressDialog = new ProgressDialog(OTPActivity.this);
        progressDialog.setTitle("Syncing");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);


        String deviceIdTxt = "555511889";


        progressDialog.show();


        compositeDisposable.add(APIClient.getInstance().setup(phone,uniqueKey,deviceIdTxt)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Response<Setup>>() {
                    @Override
                    public void onSuccess(final Response<Setup> setupResponse) {

                        if (setupResponse.isSuccessful())
                        {
                            if (setupResponse!=null) {

                                realm.beginTransaction();
                                realm.delete(Setup.class);
                                realm.commitTransaction();

                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        realm.copyToRealmOrUpdate(setupResponse.body());
                                    }
                                });
                                PreferenceManager.setSecretKey(OTPActivity.this, setupResponse.body().getUniqueCode());
                                PreferenceManager.setDeviceId(OTPActivity.this, setupResponse.body().getDeviceId());
                                PreferenceManager.setHasBackup(OTPActivity.this, true);
                                PreferenceManager.setLogin(OTPActivity.this, true);

                                syncSystemUser();
                            }else
                            {
                                syncSystemUser();
                            }
                        }else
                        {
                            progressDialog.hide();
                            Toast.makeText(OTPActivity.this, "Setup information incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));





    }
private void resend() {

    compositeDisposable.add(APIClient.getInstance().otpLogin(phone)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(new DisposableSingleObserver<Response<Setup>>() {
                @Override
                public void onSuccess(final Response<Setup> response) {

                    try {

                        otp =  ""+response.body().getOtp();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(Throwable e) {

                    Toast.makeText(OTPActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }));

    }


    public void btn_back(View view) {
    }

    public void btnverify(View view) {
        Intent intent = new Intent(OTPActivity.this,ProductActivity.class);
        startActivity(intent);
    }

    public void signUpbtn(View view) {
    }
    private void counters() {
        counter.setVisibility(View.VISIBLE);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                String mmsecound = String.valueOf(millisUntilFinished / 1000);

                String mmsecound2 = String.valueOf(millisUntilFinished / 1000);
                String first = getString(R.string.text_otp_resend);
                String secoundd = getString(R.string.text_otp_secound);


                counter.setText(first + " " + mmsecound2 + " " + secoundd);

            }

            public void onFinish() {
                counter.setVisibility(View.GONE);
                resendBtn.setVisibility(View.VISIBLE);
                /*Vibrator v = (Vibrator) OTPActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 1000 milliseconds
                v.vibrate(1000);*/
                // resendBtn.setVisibility(View.VISIBLE);
            }

        }.start();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(OTPActivity.this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(OTPActivity.this, new String[]{
                    Manifest.permission.RECEIVE_SMS
            }, 100);

        }
    }


    public void btn_resend(View view) {
        counters();
        resend();
    }

      public void syncSystemUser()
    {
        progressDialog.setMessage("Syncing System User....");
        realm.beginTransaction();
        realm.delete(SystemUser.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getSystemUser(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<SystemUser>>>() {
                    @Override
                    public void onNext(final Response<List<SystemUser>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {

                                 /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    SystemUser systemUser = realm.createObject(SystemUser.class);
                                    systemUser.setUserId(response.body().get(i).getUserId());
                                    systemUser.setEmail(response.body().get(i).getEmail());
                                    systemUser.setFullName(response.body().get(i).getFullName());
                                    systemUser.setPassword(response.body().get(i).getPassword());
                                    systemUser.setRoles(response.body().get(i).getRoles());
                                    systemUser.setUsername(response.body().get(i).getUsername());


                                }

                                realm.commitTransaction();

                                syncAnonymousCustomer();


                            }else
                            {
                                syncAnonymousCustomer();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncAnonymousCustomer()
    {
        progressDialog.setMessage("Syncing Anonymous Cusomer...");

        realm.beginTransaction();
        realm.delete(AnonymousCustomer.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getAnonymousCustomer(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<AnonymousCustomer>>>() {
                    @Override
                    public void onNext(final Response<List<AnonymousCustomer>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {

                                  /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {

                                    AnonymousCustomer anonymousCustomer = realm.createObject(AnonymousCustomer.class);;
                                    anonymousCustomer.setCustomerId(response.body().get(i).getCustomerId());
                                    anonymousCustomer.setGlobalId(response.body().get(i).getGlobalId());
                                    anonymousCustomer.setMobile(response.body().get(i).getMobile());
                                    anonymousCustomer.setName(response.body().get(i).getName());

                                }

                                realm.commitTransaction();

                                syncCategory();
                            }else
                            {

                                syncCategory();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncCategory()
    {
        progressDialog.setMessage("Syncing Categoty...");

        realm.beginTransaction();
        realm.delete(Category.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getCategory(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();

                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    Category category = realm.createObject(Category.class);;
                                    category.setCategoryId(response.body().get(i).getCategoryId());
                                    category.setName(response.body().get(i).getName());
                                    category.setSlug(response.body().get(i).getSlug());


                                }

                                realm.commitTransaction();


                                syncTokenNo();

                            }else
                            {
                                syncTokenNo();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncTokenNo()
    {
        progressDialog.setMessage("Syncing Token No...");

        realm.beginTransaction();
        realm.delete(TokenNo.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getTokenNo(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<TokenNo>>>() {
                    @Override
                    public void onNext(final Response<List<TokenNo>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();

                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    TokenNo tokenNo = realm.createObject(TokenNo.class);
                                    tokenNo.setTokenId(response.body().get(i).getTokenId());
                                    tokenNo.setTokenName(response.body().get(i).getTokenName());

                                }

                                realm.commitTransaction();


                                syncStockItem();

                            }else
                            {

                                syncStockItem();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncStockItem()
    {
        progressDialog.setMessage("Syncing Stock Item...");

        realm.beginTransaction();
        realm.delete(StockItem.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getStockItem(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<StockItem>>>() {
                    @Override
                    public void onNext(final Response<List<StockItem>> listResponse) {

                        if (listResponse.isSuccessful())
                        {

                            if (listResponse.body()!=null && listResponse.body().size()>0)
                            {
                                realm.beginTransaction();

                                for (int i = 0 ; i<listResponse.body().size(); i++) {

                                    StockItem stockItem = realm.createObject(StockItem.class);
                                    stockItem.setCount(i);
                                    stockItem.setCategoryId(listResponse.body().get(i).getCategoryId());
                                    stockItem.setCategoryName(listResponse.body().get(i).getCategoryName());
                                    stockItem.setGlobalId(listResponse.body().get(i).getGlobalId());
                                    stockItem.setItemId(listResponse.body().get(i).getItemId());
                                    stockItem.setName(listResponse.body().get(i).getName());
                                    stockItem.setPrintName(listResponse.body().get(i).getPrintName());
                                    stockItem.setPurchasePrice(listResponse.body().get(i).getPurchasePrice());
                                    stockItem.setSalesPrice(listResponse.body().get(i).getSalesPrice());
                                    stockItem.setQuantity(listResponse.body().get(i).getQuantity());
                                    stockItem.setUnit(listResponse.body().get(i).getUnit());
                                    stockItem.setUnitId(listResponse.body().get(i).getUnitId());
                                    stockItem.setPrintHidden(listResponse.body().get(i).getPrintHidden());

                                    if (TextUtils.isEmpty(listResponse.body().get(i).getImagePath())) {

//                                        DownloadImage task = new DownloadImage();
//                                        byte[] result = null;
//                                        try {
//                                            result = task.execute("http://alexabd.com/default.png").get();
//                                            stockItem.setImage(result);
//                                        } catch (InterruptedException | ExecutionException e) {
//                                            e.printStackTrace();
//                                        }

                                        stockItem.setImage(null);
                                    }else
                                    {

                                        DownloadImage task = new DownloadImage();
                                        byte[] result = null;
                                        try {
                                            result = task.execute("http://"+listResponse.body().get(i).getImagePath()).get();
                                            stockItem.setImage(result);
                                        } catch (InterruptedException | ExecutionException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }

                                realm.commitTransaction();

                                syncTransactionMethod();
                            }else
                            {
                                syncTransactionMethod();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncTransactionMethod()
    {
        progressDialog.setMessage("Syncing Transaction Method...");

        realm.beginTransaction();
        realm.delete(TransactionMethod.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getTransactionMethod(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<TransactionMethod>>>() {
                    @Override
                    public void onNext(final Response<List<TransactionMethod>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {

                                 /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {

                                    TransactionMethod transactionMethod = realm.createObject(TransactionMethod.class);;
                                    transactionMethod.setItemId(response.body().get(i).getItemId());
                                    transactionMethod.setName(response.body().get(i).getName());

                                }

                                realm.commitTransaction();

                                syncBankAccount();

                            }else
                            {
                                syncBankAccount();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncBankAccount()
    {
        progressDialog.setMessage("Syncing Bank Account...");

        realm.beginTransaction();
        realm.delete(BankAccount.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getOnlineBankAccount(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<BankAccount>>>() {
                    @Override
                    public void onNext(final Response<List<BankAccount>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();

                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    BankAccount bankAccount = realm.createObject(BankAccount.class);;
                                    bankAccount.setGlobalId(response.body().get(i).getGlobalId());
                                    bankAccount.setName(response.body().get(i).getName());
                                    bankAccount.setItemId(response.body().get(i).getItemId());
                                    bankAccount.setService_charge(response.body().get(i).getService_charge());

                                }

                                realm.commitTransaction();

                                syncMobileAccount();


                            }else
                            {
                                syncMobileAccount();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncMobileAccount()
    {
        progressDialog.setMessage("Syncing Mobile Account...");

        realm.beginTransaction();
        realm.delete(MobileAccount.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getMobileAccount(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<MobileAccount>>>() {
                    @Override
                    public void onNext(final Response<List<MobileAccount>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                    /*
                                    start realm for save data
                                     */

                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    MobileAccount mobileAccount = realm.createObject(MobileAccount.class);;
                                    mobileAccount.setGlobalId(response.body().get(i).getGlobalId());
                                    mobileAccount.setName(response.body().get(i).getName());
                                    mobileAccount.setItemId(response.body().get(i).getItemId());
                                    mobileAccount.setService_charge(response.body().get(i).getService_charge());

                                }

                                realm.commitTransaction();

                                syncVendor();

                            }else
                            {
                                syncVendor();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncVendor()
    {

        progressDialog.setMessage("Syncing Vendor...");

        realm.beginTransaction();
        realm.delete(Vendor.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getVendor(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Vendor>>>() {
                    @Override
                    public void onNext(final Response<List<Vendor>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {

                                 /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {

                                    Vendor vendor = realm.createObject(Vendor.class);
                                    vendor.setName(response.body().get(i).getName());
                                    vendor.setVendorId(response.body().get(i).getVendorId());

                                }

                                realm.commitTransaction();

                                syncExpenseCategory();


                            }else
                            {
                                syncExpenseCategory();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncExpenseCategory()
    {

        progressDialog.setMessage("Syncing Expense Category...");

        realm.beginTransaction();
        realm.delete(ExpenseCategory.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getExpenseCategory(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ExpenseCategory>>>() {
                    @Override
                    public void onNext(final Response<List<ExpenseCategory>> response) {

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {

                                 /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();

                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {



                                    ExpenseCategory expenseCategory = realm.createObject(ExpenseCategory.class);;
                                    expenseCategory.setName(response.body().get(i).getName());
                                    expenseCategory.setCategoryId(response.body().get(i).getCategoryId());
                                    expenseCategory.setGlobalId(response.body().get(i).getGlobalId());
                                    expenseCategory.setSlug(response.body().get(i).getSlug());

                                }

                                realm.commitTransaction();

                                syncPaymentCard();


                            }else
                            {
                                syncPaymentCard();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncPaymentCard()
    {

        progressDialog.setMessage("Syncing Payment Card...");

        realm.beginTransaction();
        realm.delete(PaymentCards.class);
        realm.commitTransaction();



        compositeDisposable.add(APIClient.getInstance().getPaymentCards(PreferenceManager.getSecretKey(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<PaymentCards>>>() {
                    @Override
                    public void onNext(final Response<List<PaymentCards>> response) {

                        if (response.isSuccessful())
                        {
                            if (response.body()!=null && response.body().size()>0)
                            {
                                /*
                                start realm for save data
                                */

                                realm.beginTransaction();

                                /*
                                get each data
                                 */

                                for (int i = 0 ; i<response.body().size(); i++) {

                                    PaymentCards paymentCard = realm.createObject(PaymentCards.class);;
                                    paymentCard.setItemId(response.body().get(i).getItemId());
                                    paymentCard.setName(response.body().get(i).getName());

                                }

                                realm.commitTransaction();

//                                syncDIMS();

                                progressDialog.hide();



                                progressDialog.setTitle("Syncing");
                                progressDialog.setMessage("Please Wait...");

                                progressDialog.show();

                                SystemUser user = realm
                                        .where(SystemUser.class)
                                        .beginGroup()
                                        .equalTo("username",phone)
                                        .and()
                                        .equalTo("password",otp)
                                        .endGroup()
                                        .findFirst();

                                if (user!=null)
                                {
                                    if (TextUtils.isEmpty(user.getRoles()))
                                    {
                                        progressDialog.hide();
                                        Toast.makeText(getApplicationContext(), "You don't have any permission", Toast.LENGTH_SHORT).show();

                                    }else {
                                        //save user information
                                        PreferenceManager.setUserId(OTPActivity.this, String.valueOf(user.getUserId()));
                                        PreferenceManager.setUserName(OTPActivity.this, String.valueOf(user.getFullName()));
                                        PreferenceManager.setRoles(OTPActivity.this, user.getRoles());
                                        PreferenceManager.setLogin(OTPActivity.this, true);


                                        progressDialog.hide();
                                        startActivity(new Intent(OTPActivity.this, DashboardActivity.class));
                                        finish();

                                    }


                                }else
                                {
                                    progressDialog.hide();

                                }


                            }else
                            {
//                                syncDIMS();
                                progressDialog.setTitle("Syncing");
                                progressDialog.setMessage("Please Wait...");

                                progressDialog.show();

                                SystemUser user = realm
                                        .where(SystemUser.class)
                                        .beginGroup()
                                        .equalTo("username",phone)
                                        .and()
                                        .equalTo("password",otp)
                                        .endGroup()
                                        .findFirst();

                                if (user!=null)
                                {
                                    if (TextUtils.isEmpty(user.getRoles()))
                                    {
                                        progressDialog.hide();
                                        Toast.makeText(getApplicationContext(), "You don't have any permission", Toast.LENGTH_SHORT).show();

                                    }else {
                                        //save user information
                                        PreferenceManager.setUserId(OTPActivity.this, String.valueOf(user.getUserId()));
                                        PreferenceManager.setUserName(OTPActivity.this, String.valueOf(user.getFullName()));
                                        PreferenceManager.setRoles(OTPActivity.this, user.getRoles());
                                        PreferenceManager.setLogin(OTPActivity.this, true);


                                        progressDialog.hide();
                                        startActivity(new Intent(OTPActivity.this, DashboardActivity.class));
                                        finish();

                                    }


                                }else
                                {
                                    progressDialog.hide();

                                }

                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

   /* public void clickEdit(View view) {

        editTextotp.requestFocus();
        editTextotp.postDelayed(new Runnable(){
                                   @Override public void run(){
                                       InputMethodManager keyboard=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                       keyboard.showSoftInput(editTextotp,0);
                                   }
                               }
                    ,200);

    }*/

    public class DownloadImage extends AsyncTask<String, Void, byte[]>
    {


        @Override
        protected byte[] doInBackground(String... imageurls) {

            try {

                URL imageUrl = new URL(imageurls[0]);
                URLConnection ucon = imageUrl.openConnection();

                InputStream is = ucon.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int read = 0;

                while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, read);
                }

                baos.flush();

                return  baos.toByteArray();

            } catch (Exception e) {
                Log.d("ImageManager", "Error: " + e.toString());
                return null;
            }
        }
    }

}