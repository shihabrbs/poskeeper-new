package com.portal.terminalbd.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.portal.terminalbd.R;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.ExpenseCategory;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.ModelAddProduct;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.model.TokenNo;
import com.portal.terminalbd.model.TransactionMethod;
import com.portal.terminalbd.model.Vendor;
import com.portal.terminalbd.network.APIClient;
import com.portal.terminalbd.network.ApiInterface;
import com.portal.terminalbd.network.PaymentCards;
import com.portal.terminalbd.network.RetrofitApiClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupActivity extends AppCompatActivity {

    ArrayList<String> shoptypelist;

  /*  @BindView(R.id.edit_setup_mobile)
    EditText mobile;

    @BindView(R.id.edit_setup_unique_key)
    EditText uniqueKey;*/

    @BindView(R.id.edit_setup_mobile)
    TextInputEditText mobile;

    @BindView(R.id.edit_setup_unique_key)
    TextInputEditText uniqueKey;


    
    /*@BindView(R.id.checkBox)
    CheckBox conditioncheckBox;*/

    @BindView(R.id.button_setup)
    Button mybtn;
    @BindView(R.id.tv_languagee)
    TextView tvLanguage;

//    @BindView(R.id.edit_setup_device_id)
//    EditText deviceId;

    CompositeDisposable compositeDisposable;

    ProgressDialog progressDialog;

    Realm realm;

    boolean languageBoolean = true;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);

        String language = PreferenceManager.getLanguage(SetupActivity.this);

        if (language.equals("en")){
            tvLanguage.setText("বাংলা");
        }else {
            tvLanguage.setText("English");
        }

        tvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (languageBoolean){
                    PreferenceManager.setLanguage(SetupActivity.this,"bn");
                    PreferenceManager.setCurrency(SetupActivity.this,"৳");
                    tvLanguage.setText("English");
                    languageBoolean = false;
                }else {
                    PreferenceManager.setLanguage(SetupActivity.this,"en");
                    PreferenceManager.setCurrency(SetupActivity.this,"$");
                    tvLanguage.setText("বাংলা");
                    languageBoolean = true;
                }
            }
        });

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        shoptypelist = new ArrayList<>();

        apiInterface.getShopType().enqueue(new Callback<List<ModelAddProduct>>() {
            @Override
            public void onResponse(Call<List<ModelAddProduct>> call, Response<List<ModelAddProduct>> response) {

               try {
                   for (int i=0;i<response.body().size();i++){

                       // shoptypelist.add(response.body().get(i).getShop_type());
                       shoptypelist.add(i,response.body().get(i).getShop_type());
                   }
               }catch (Exception e){

               }
            }

            @Override
            public void onFailure(Call<List<ModelAddProduct>> call, Throwable t) {

            }
        });



        compositeDisposable = new CompositeDisposable();


        /*
        initialize realm database
         */
        realm = Realm.getDefaultInstance();

        progressDialog = new ProgressDialog(SetupActivity.this);
        progressDialog.setTitle("Syncing");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);


        ((CheckBox)findViewById(R.id.checkBoxPassword)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                {

                    // hide password
                    uniqueKey.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    uniqueKey.setSelection(uniqueKey.length());
                }else
                {
                    // show password
                    uniqueKey.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    uniqueKey.setSelection(uniqueKey.length());
                }
            }
        });

        /*TextView textView = findViewById(R.id.termsandcondition);
        String text = "By using this app, you agree to the Terms and Condition";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //Toast.makeText(SetupActivity.this, "one", Toast.LENGTH_SHORT).show();
                showTermsDialog();

            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        ss.setSpan(clickableSpan1, 35,55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new BackgroundColorSpan(Color.WHITE), 35,55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 35,55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        conditioncheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conditioncheckBox.isChecked()){
                    DrawableCompat.setTint(mybtn.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.orenge));
                }else{

                    DrawableCompat.setTint(mybtn.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.btn_disable));
                }
            }
        });*/

    }


    /* Terms and condition dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(SetupActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_terms, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(SetupActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }


    @OnClick(R.id.createnewbusiness)
    void creatBusiness(){
        Intent intent = new Intent(SetupActivity.this, SignupActivity.class);
        intent.putExtra("mylist", shoptypelist);
        startActivity(intent);
    }


    @OnClick(R.id.button_setup)
    void setup()
    {
        
        /*if (conditioncheckBox.isChecked()){*/


            
            String mobileTxt = mobile.getText().toString();
            String uniqueKeyTxt = uniqueKey.getText().toString();
//        String deviceIdTxt = deviceId.getText().toString();
            String deviceIdTxt = getDeviceUniqueID(SetupActivity.this);

     /*   final String usernameTxt = mobile.getText().toString();
        final String passwordTxt = uniqueKey.getText().toString();*/

            if (TextUtils.isEmpty(mobileTxt))
            {
                mobile.setError("Please input your mobile no.");
                mobile.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(uniqueKeyTxt))
            {
                uniqueKey.setError("Please input your unique key");
                uniqueKey.requestFocus();
                return;
            }

//        if (TextUtils.isEmpty(deviceIdTxt))
//        {
//            deviceId.setError("Please input your device id.");
//            deviceId.requestFocus();
//            return;
//        }

            progressDialog.show();


            compositeDisposable.add(APIClient.getInstance().setup(mobileTxt,uniqueKeyTxt,deviceIdTxt)
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
                                    PreferenceManager.setSecretKey(SetupActivity.this, setupResponse.body().getUniqueCode());
                                    PreferenceManager.setDeviceId(SetupActivity.this, setupResponse.body().getDeviceId());
                                    PreferenceManager.setHasBackup(SetupActivity.this, true);

                                    syncSystemUser();
                                }else
                                {
                                    syncSystemUser();
                                }
                            }else
                            {
                                progressDialog.hide();
                                Toast.makeText(SetupActivity.this, "Setup information incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressDialog.hide();
                            Toast.makeText(SetupActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }));
        /*}else {
            Toast.makeText(this, "Please Check the Terms & Condition.", Toast.LENGTH_SHORT).show();
        }*/

        

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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                startActivity(new Intent(SetupActivity.this,LoginActivity.class));
                                finish();

                            }else
                            {
//                                syncDIMS();
                                progressDialog.hide();
                                startActivity(new Intent(SetupActivity.this,LoginActivity.class));
                                finish();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

//    public void syncDIMS()
//    {
//        progressDialog.setMessage("Syncing Dims...");
//
//        realm.beginTransaction();
//        realm.delete(DIMSItem.class);
//        realm.commitTransaction();
//
//        compositeDisposable.add(APIClient.getInstance().getDIMS(PreferenceManager.getSecretKey(this))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribeWith(new DisposableObserver<Response<List<DIMSItem>>>() {
//                    @Override
//                    public void onNext(final Response<List<DIMSItem>> response) {
//
//                        if (response.isSuccessful())
//                        {
//
//                            if (response.body()!=null && response.body().size()>0)
//                            {
//
//                                 /*
//                                    start realm for save data
//                                     */
//                                realm.beginTransaction();
//
//                                /*
//                                get each data
//                                 */
//                                for (int i = 0 ; i<response.body().size(); i++) {
//
//
//                                    DIMSItem dims = realm.createObject(DIMSItem.class);;
//                                    dims.setBrand(response.body().get(i).getBrand());
//                                    dims.setGenericName(response.body().get(i).getGenericName());
//                                    dims.setGlobalId(response.body().get(i).getGlobalId());
//                                    dims.setMedicineForm(response.body().get(i).getMedicineForm());
//                                    dims.setMedicineId(response.body().get(i).getMedicineId());
//                                    dims.setMedicineName(response.body().get(i).getMedicineName());
//                                    dims.setSalesPrice(response.body().get(i).getSalesPrice());
//                                    dims.setStrength(response.body().get(i).getStrength());
//                                    dims.setUseFor(response.body().get(i).getUseFor());
//
//                                }
//
//                                realm.commitTransaction();
//
//                                progressDialog.hide();
//                                startActivity(new Intent(SetupActivity.this,LoginActivity.class));
//                                finish();
//
//
//                            }else
//                            {
//                                progressDialog.hide();
//                                startActivity(new Intent(SetupActivity.this,LoginActivity.class));
//                                finish();
//                            }
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        progressDialog.hide();
//                        Toast.makeText(SetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                }));
//    }

    public String getDeviceUniqueID(Activity activity) {
        String device_unique_id;
        device_unique_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return device_unique_id;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onStart() {
        super.onStart();


        if (PreferenceManager.isHasBackup(this))
        {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }
    }


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
