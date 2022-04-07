package com.portal.terminalbd.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;

import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.PurchaseItemAdapter;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.PurchaseItem;
import com.portal.terminalbd.model.PurchaseItemHistory;
import com.portal.terminalbd.model.PurchaseHistory;
import com.portal.terminalbd.model.Vendor;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class PurchasePaymentActivity extends AppCompatActivity implements SalesItemclickEvent {

    @BindView(R.id.choose_date)
    LinearLayout choose_date;

    @BindView(R.id.date)
    TextView date;

    ValueAnimator mAnimator;
    boolean isShowing = false;

    RealmResults<PurchaseItem> purchaseItemList;
    List<AnonymousCustomer> customerList;
    AnonymousCustomer anonymousCustomer;

    PurchaseItemAdapter adapter;

    @BindView(R.id.saleItemsLayout)
    LinearLayout saleItemsLayout;

    @BindView(R.id.showSaleItem)
    RelativeLayout showSaleItem;

    @BindView(R.id.purchase_history_rv)
    RecyclerView purchase_history_rv;

    @BindView(R.id.sale_total_tk)
    TextView sale_total_tk;

    @BindView(R.id.sale_discount_tk)
    TextView sale_discount_tk;


    @BindView(R.id.sale_grand_total_tk)
    TextView sale_grand_total_tk;


    @BindView(R.id.cashPayment)
    TextView cashPayment;

    @BindView(R.id.arrow_icon)
    ImageView arrow_icon;

    @BindView(R.id.bkashPayment)
    TextView bkashPayment;

//    @BindView(R.id.rocketPayment)
//    ImageView rocketPayment;

    @BindView(R.id.visaPayment)
    TextView visaPayment;

    @BindView(R.id.mobile_payment_form)
    LinearLayout mobile_payment_form;

    @BindView(R.id.bank_payment_form)
    LinearLayout bank_payment_form;

    @BindView(R.id.commision_mode_spinner)
    Spinner commision_mode_spinner;

    @BindView(R.id.vendor_spinner)
    Spinner vendor_spinner;

    @BindView(R.id.discount_et)
    EditText discount_et;

    @BindView(R.id.discount_switch)
    Switch discount_switch;

    @BindView(R.id.payment_receive_spinner)
    Spinner payment_receive_spinner;

    @BindView(R.id.payment_card_bank_spinner)
    Spinner payment_card_bank_spinner;

    @BindView(R.id.returnText)
    TextView returnText;
    @BindView(R.id.sale_return_tk)
    TextView sale_return_tk;
    @BindView(R.id.receivedTk)
    EditText receivedTk;

    int total;
    int total_item =  0;
    int discountAmount;
    int discountTotal;
    int index = 0;

    int returnAmount;
    int dueAmount;

    int memoNo;

    String paymentType;
    int paymentReceiveMobileNoID;
    int paymentBankAccount;

    String discountType;

    Realm realm;

    RealmChangeListener<RealmResults<PurchaseItem>> realmListener = new RealmChangeListener<RealmResults<PurchaseItem>>() {
        @Override
        public void onChange(RealmResults<PurchaseItem> saleItems) {

            checkPurchasLayoutVisibility();
            adapter.notifyDataSetChanged();
        }
    };


    String[] commisionMode = {"Manual","Vendor"};

    List<Vendor> vendorList;

    List<MobileAccount> mobileAccounts;

    List<BankAccount> bankAccounts;

    String createdDate;

    /*
    toolbar
     */
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_payment);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));

        toolbar_title.setText("Payment");


        customerList = new ArrayList<>();
        vendorList = new ArrayList<>();

        anonymousCustomer = new AnonymousCustomer();

        purchaseItemList = realm.where(PurchaseItem.class).findAll();

        memoNo = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        createdDate = format.format(new Date());



        purchaseItemList.addChangeListener(realmListener);

//         Add onPreDrawListener
        saleItemsLayout.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        saleItemsLayout.getViewTreeObserver()
                                .removeOnPreDrawListener(this);
                        saleItemsLayout.setVisibility(View.GONE);

                        final int widthSpec =     View.MeasureSpec.makeMeasureSpec(
                                0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec
                                .makeMeasureSpec(0,
                                        View.MeasureSpec.UNSPECIFIED);
                        saleItemsLayout.measure(widthSpec, heightSpec);

                        mAnimator = slideAnimator(0,
                                saleItemsLayout.getMeasuredHeight());
                        return true;
                    }
                });

        showSaleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isShowing)
                {
                    arrow_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down));

                    isShowing = false;

                    int finalHeight = saleItemsLayout.getHeight();

                    ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

                    mAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationEnd(Animator animator) {
                            // Height=0, but it set visibility to GONE
                            saleItemsLayout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationStart(Animator animator) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                    mAnimator.start();


                }else
                {
                    arrow_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_up));

                    isShowing = true;
                    saleItemsLayout.setVisibility(View.VISIBLE);
                    mAnimator.start();
                }
            }
        });

        //set adapter
        adapter = new PurchaseItemAdapter(PurchasePaymentActivity.this,purchaseItemList,this);
        purchase_history_rv.setLayoutManager(new LinearLayoutManager(PurchasePaymentActivity.this,LinearLayoutManager.VERTICAL,false));
        purchase_history_rv.addItemDecoration(new DividerItemDecoration(PurchasePaymentActivity.this,DividerItemDecoration.VERTICAL));
        purchase_history_rv.setAdapter(adapter);

        calculatePrice();

        discount_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (discount_switch.isChecked())
                {
                    discount_switch.setText("%");
                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        discountAmount = (int) Math.round((total * Double.valueOf(discount_et.getText().toString()) / 100));
                        discountTotal = total - discountAmount;
                        sale_grand_total_tk.setText("৳ "+String.valueOf(discountTotal));
                        sale_discount_tk.setText("৳ "+discountAmount);
                        sale_return_tk.setText("৳ "+discountTotal);

                    }else
                    {
                        discount_et.setError("Please input discount percent");
                        discount_et.clearFocus();

                    }
                }else
                {
                    discount_switch.setText("Tk");
                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        discountAmount = Integer.parseInt(discount_et.getText().toString());
                        discountTotal = total - discountAmount;
                        sale_grand_total_tk.setText("৳ " + discountTotal);
                        sale_discount_tk.setText("৳ " + discount_et.getText().toString());
                        sale_return_tk.setText("৳ "+discountTotal);
                    }else
                    {
                        discount_et.setError("Please input amount of discount");
                        discount_et.clearFocus();
                    }
                }
            }
        });
        discount_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    handled = true;

                    if (discount_switch.isChecked())
                    {

                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                            discountAmount = (int) Math.round(( total * Double.valueOf(discount_et.getText().toString()) / 100));
                            discountTotal = total-discountAmount;
                            sale_grand_total_tk.setText("৳ "+ discountTotal);
                            sale_discount_tk.setText("৳ "+discountAmount);
                            sale_return_tk.setText("৳ "+discountTotal);

                        }else
                        {
                            discount_et.setError("Please input discount percent");
                        }

                    }else
                    {
                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                            discountAmount = Integer.parseInt(discount_et.getText().toString());
                            discountTotal = total - discountAmount;
                            sale_grand_total_tk.setText("৳ " + discountTotal);
                            sale_discount_tk.setText("৳ " + discountAmount);
                            sale_return_tk.setText("৳ "+discountTotal);
                        }else
                        {
                            discount_et.setError("Please input amount of discount");
                        }

                    }

                    checkReturn();
                }

                return handled;
            }
        });

        discount_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

//                if (discount_et.getText().toString()!=null)
//                {
//                    if (discount_switch.isChecked())
//                    {
//
//                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
//                            discountAmount = (int) Math.round ((total * Double.valueOf(discount_et.getText().toString()) / 100 ));
//                            discountTotal = total- discountAmount;
//                            sale_grand_total_tk.setText("৳ "+ discountTotal);
//                            sale_discount_tk.setText("৳ "+discountAmount);
//                            sale_return_tk.setText("৳ "+discountTotal);
//
//                        }else
//                        {
//                            calculatePrice();
//                            discount_et.setError("Please input discount percent");
//                            discount_et.clearFocus();
//                        }
//
//                    }else
//                    {
//                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
//                            discountAmount = Integer.parseInt(discount_et.getText().toString());
//                            discountTotal = total - discountAmount;
//                            sale_grand_total_tk.setText("৳ " + discountTotal);
//                            sale_discount_tk.setText("৳ " + discount_et.getText().toString());
//                            sale_return_tk.setText("৳ "+discountTotal);
//                        }else
//                        {
//                            calculatePrice();
//                            discount_et.setError("Please input amount of discount");
//                            discount_et.clearFocus();
//                        }
//
//                    }
//                }

                if (!TextUtils.isEmpty(discount_et.getText().toString()))
                {
                    if (discount_switch.isChecked())
                    {

                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                            discountAmount = (int) Math.round ((total * Double.valueOf(discount_et.getText().toString()) / 100 ));
                            discountTotal = total- discountAmount;
                            sale_grand_total_tk.setText("৳ "+ discountTotal);
                            sale_discount_tk.setText("৳ "+discountAmount);
                        }else
                        {
                            calculatePrice();
                            discount_et.setError("Please input discount percent");
                            discount_et.clearFocus();
                        }

                    }else
                    {
                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                            discountAmount = Integer.parseInt(discount_et.getText().toString());
                            discountTotal = total - discountAmount;
                            sale_grand_total_tk.setText("৳ " + discountTotal);
                            sale_discount_tk.setText("৳ " + discount_et.getText().toString());
                        }else
                        {
                            calculatePrice();
                            discount_et.setError("Please input amount of discount");
                            discount_et.clearFocus();
                        }

                    }
                }

                checkReturn();
            }
        });

        cashPayment.setBackgroundColor(getResources().getColor(R.color.cash_background));
        bkashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        visaPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));

        /*
        mobile accounts adapter
         */
        RealmResults<MobileAccount> mobile = realm.where(MobileAccount.class).findAll();

        mobileAccounts = realm.copyFromRealm(mobile);

        String[] accounts = new String[mobileAccounts.size()];
        for (int i=0;i<mobileAccounts.size();i++)
        {
            accounts[i] = mobileAccounts.get(i).getName();

        }

        ArrayAdapter<String> mobileAccountAdapter =
                new ArrayAdapter<>(PurchasePaymentActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,accounts);

        payment_receive_spinner.setAdapter(mobileAccountAdapter);



        /*
        payment bank adapter
         */

        RealmResults<BankAccount> banks = realm.where(BankAccount.class).findAll();

        bankAccounts = realm.copyFromRealm(banks);

        String[] bank = new String[bankAccounts.size()];
        for (int i=0;i<bankAccounts.size();i++)
        {
            bank[i] = bankAccounts.get(i).getName();

        }

        ArrayAdapter<String> bankAccountAdapter =
                new ArrayAdapter<>(PurchasePaymentActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);

        /*
        Commision mode adapter
         */
        ArrayAdapter<String> commisionAdapter =
                new ArrayAdapter<>(PurchasePaymentActivity.this
                ,android.R.layout.simple_spinner_dropdown_item
                ,commisionMode);

        commision_mode_spinner.setAdapter(commisionAdapter);

        /*
       vendor adapter
         */
        RealmResults<Vendor> vendors = realm.where(Vendor.class).findAll();

        vendorList.addAll(realm.copyFromRealm(vendors));

        String[] vendorNames = new String[vendorList.size()];
        for (int i=0;i<vendorList.size();i++)
        {
            vendorNames[i] = vendorList.get(i).getName();

        }

        ArrayAdapter<String> vendorAdapter =
                new ArrayAdapter<>(PurchasePaymentActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,vendorNames);

        vendor_spinner.setAdapter(vendorAdapter);



        receivedTk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

//                if (!TextUtils.isEmpty(receivedTk.getText().toString()))
//                {
//                    if (Integer.parseInt(receivedTk.getText().toString())>=total)
//                    {
//                        dueAmount=0;
//                        returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString())-total);
//                        sale_return_tk.setText("৳"+ returnAmount);
//                        returnText.setText("Return Tk");
//
//                    }else
//                    {
//                        returnAmount = 0;
//                        returnText.setText("Due Tk");
//                        dueAmount = (int) (total - Integer.parseInt(receivedTk.getText().toString()));
//                        sale_return_tk.setText("৳"+ dueAmount);
//                    }
//                }else
//                {
//                    returnText.setText("Return Tk");
//                    sale_return_tk.setText("0");
//                }

                if (!TextUtils.isEmpty(receivedTk.getText().toString())) {

                    if (TextUtils.isEmpty(discount_et.getText().toString())) {

                        if (Integer.parseInt(receivedTk.getText().toString()) >= total) {
                            dueAmount = 0;
                            returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - total);
                            sale_return_tk.setText("৳ " + returnAmount);
                            returnText.setText("Return Tk");

                        } else {
                            returnAmount = 0;
                            returnText.setText("Due Tk");
                            dueAmount = (int) (total - Integer.parseInt(receivedTk.getText().toString()));
                            sale_return_tk.setText("৳ " + dueAmount);
                        }
                    }else
                    {
                        if (Integer.parseInt(receivedTk.getText().toString()) >= discountTotal) {
                            dueAmount = 0;
                            returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - discountTotal);
                            sale_return_tk.setText("৳ " + returnAmount);
                            returnText.setText("Return Tk");

                        } else {
                            returnAmount = 0;
                            returnText.setText("Due Tk");
                            dueAmount = (int) (discountTotal - Integer.parseInt(receivedTk.getText().toString()));
                            sale_return_tk.setText("৳ " + dueAmount);
                        }
                    }

                } else {
                    returnText.setText("Due Tk");

                    if (TextUtils.isEmpty(discount_et.getText().toString())) {
                        sale_return_tk.setText(String.valueOf(total));

                    }else
                    {
                        sale_return_tk.setText(String.valueOf(discountTotal));
                    }

                }
            }
        });
    }

    public void checkPurchasLayoutVisibility()
    {
        if (purchaseItemList.size()>0) {
            calculatePrice();
            checkDiscount();
            checkReturn();

        }else
        {
            onBackPressed();
        }
    }


    public void checkDiscount()
    {
        if (!TextUtils.isEmpty(discount_et.getText().toString()))
        {
            if (discount_switch.isChecked())
            {

                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                    discountAmount = (int) Math.round ((total * Double.valueOf(discount_et.getText().toString()) / 100 ));
                    discountTotal = total- discountAmount;
                    sale_grand_total_tk.setText("৳ "+ discountTotal);
                    sale_discount_tk.setText("৳ "+discountAmount);

                }else
                {
                    calculatePrice();
                    discount_et.setError("Please input discount percent");
                    discount_et.clearFocus();
                }

            }else
            {
                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                    discountAmount = Integer.parseInt(discount_et.getText().toString());
                    discountTotal = total - discountAmount;
                    sale_grand_total_tk.setText("৳ " + discountTotal);
                    sale_discount_tk.setText("৳ " + discount_et.getText().toString());
                }else
                {
                    calculatePrice();
                    discount_et.setError("Please input amount of discount");
                    discount_et.clearFocus();
                }

            }
        }
    }

    public void checkReturn()
    {
        if (!TextUtils.isEmpty(receivedTk.getText().toString())) {

            if (TextUtils.isEmpty(discount_et.getText().toString())) {

                if (Integer.parseInt(receivedTk.getText().toString()) >= total) {
                    dueAmount = 0;
                    returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - total);
                    sale_return_tk.setText("৳ " + returnAmount);
                    returnText.setText("Return Tk");

                } else {
                    returnAmount = 0;
                    returnText.setText("Due Tk");
                    dueAmount = (int) (total - Integer.parseInt(receivedTk.getText().toString()));
                    sale_return_tk.setText("৳ " + dueAmount);
                }
            }else
            {
                if (Integer.parseInt(receivedTk.getText().toString()) >= discountTotal) {
                    dueAmount = 0;
                    returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - discountTotal);
                    sale_return_tk.setText("৳ " + returnAmount);
                    returnText.setText("Return Tk");

                } else {
                    returnAmount = 0;
                    returnText.setText("Due Tk");
                    dueAmount = (int) (discountTotal - Integer.parseInt(receivedTk.getText().toString()));
                    sale_return_tk.setText("৳ " + dueAmount);
                }
            }

        } else {

            returnText.setText("Due Tk");

            if (TextUtils.isEmpty(discount_et.getText().toString())) {
                sale_return_tk.setText(String.valueOf(total));

            }else
            {
                sale_return_tk.setText(String.valueOf(discountTotal));
            }

        }
    }

    @OnClick(R.id.save_btn)
    void onSaveBtn()
    {
        savePurchaseAndRemove();
    }

    public void savePurchaseAndRemove()
    {

        if (TextUtils.isEmpty(receivedTk.getText().toString()))
        {
            receivedTk.setError("Required");
            receivedTk.requestFocus();
            return;
        }


        /*
        save saled details
         */
        realm.beginTransaction();


        PurchaseHistory purchaseHistory = realm.createObject(PurchaseHistory.class,UUID.randomUUID().toString());
        if (index==0)
        {
            paymentType = "cash";
            purchaseHistory.setTransactionMethod(paymentType);

        }else if (index==1)
        {
            paymentType = "mobile";
            int selecPos = payment_receive_spinner.getSelectedItemPosition();
            paymentReceiveMobileNoID = mobileAccounts.get(selecPos).getItemId();
            purchaseHistory.setTransactionMethod(paymentType);
            purchaseHistory.setMobileBankAccount(paymentReceiveMobileNoID);

        }
//        else if (index==2)
//        {
//            paymentType = "mobile";
//            purchaseHistory.setTransactionMethod(paymentType);
//            purchaseHistory.setMobileBankAccount(paymentReceiveMobileNoID);
//
//        }
        else if (index==2)
        {
            paymentType = "bank";
            int pos = payment_card_bank_spinner.getSelectedItemPosition();
            paymentBankAccount = bankAccounts.get(pos).getItemId();
            purchaseHistory.setBankAccount(paymentBankAccount);
        }

        purchaseHistory.setCreated(createdDate);

        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
            purchaseHistory.setDiscount(discountAmount);
            purchaseHistory.setDiscountCulculation(Integer.parseInt(discount_et.getText().toString()));
            if (discount_switch.isChecked()) {
                discountType = "Percentage";
            } else {
                discountType = "Flat";
            }
            purchaseHistory.setDiscountType(discountType);
            purchaseHistory.setTotal(discountTotal);

        }else
        {
            purchaseHistory.setDiscount(0);
            purchaseHistory.setDiscountCulculation(0);
            purchaseHistory.setDiscountType("");
            purchaseHistory.setTotal(total);

        }
        purchaseHistory.setDue(dueAmount);
        purchaseHistory.setPayment(Integer.parseInt(receivedTk.getText().toString()));
        purchaseHistory.setInvoiceId(String.valueOf(memoNo));

        int position = vendor_spinner.getSelectedItemPosition();
        int vendorId = vendorList.get(position).getVendorId();
        purchaseHistory.setVendorId(vendorId);
        purchaseHistory.setSubTotal(total);
        purchaseHistory.setVat(0);
        purchaseHistory.setReceivedBy(Integer.parseInt(PreferenceManager.getUserId(PurchasePaymentActivity.this)));

        realm.commitTransaction();

        realm.beginTransaction();

        for (int i=0; i<purchaseItemList.size();i++)
        {

            PurchaseItemHistory salesItemHistory =
                    realm.createObject(PurchaseItemHistory.class,UUID.randomUUID().toString());
            salesItemHistory.setQuantity(Integer.parseInt(purchaseItemList.get(i).getPurchaseQuantity()));
            salesItemHistory.setPurchaseId(memoNo);
            salesItemHistory.setStockId(Integer.parseInt(purchaseItemList.get(i).getPurchaseStockId()));
            salesItemHistory.setSubTotal(Integer.parseInt(purchaseItemList.get(i).getPurchaseSubTotal()));
            salesItemHistory.setSalesPrice(Double.valueOf(purchaseItemList.get(i).getPurchaseMrpPrice()));
            salesItemHistory.setPurchasePrice(Double.valueOf(purchaseItemList.get(i).getPurchasePpPrice()));

        }

        realm.commitTransaction();

        realm.beginTransaction();
        realm.delete(PurchaseItem.class);
        realm.commitTransaction();

        Toast.makeText(this, "Save Successfull", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bkashPayment)
    void bkashPayment()
    {
        cashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        bkashPayment.setBackgroundColor(getResources().getColor(R.color.bkash_background));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        visaPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));

        mobile_payment_form.setVisibility(View.VISIBLE);
        bank_payment_form.setVisibility(View.GONE);

        index = 1;

    }


//    @OnClick(R.id.rocketPayment)
//    void rocketPayment()
//    {
//        cashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        bkashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.rocket_background));
//        visaPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//
//        mobile_payment_form.setVisibility(View.VISIBLE);
//        bank_payment_form.setVisibility(View.GONE);
//        index = 2;
//
//    }

    @OnClick(R.id.visaPayment)
    void visaPayment()
    {
        cashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        bkashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        visaPayment.setBackgroundColor(getResources().getColor(R.color.rocket_background));

        mobile_payment_form.setVisibility(View.GONE);
        bank_payment_form.setVisibility(View.VISIBLE);
        index = 2;

    }

    @OnClick(R.id.cashPayment)
    void cashPayment()
    {
        cashPayment.setBackgroundColor(getResources().getColor(R.color.cash_background));
        bkashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        visaPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));

        mobile_payment_form.setVisibility(View.GONE);
        bank_payment_form.setVisibility(View.GONE);
        index = 0;

    }

    private ValueAnimator slideAnimator(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new     ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = saleItemsLayout
                        .getLayoutParams();
                layoutParams.height = value;
                saleItemsLayout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    public void calculatePrice()
    {
        total = 0;

        for (PurchaseItem item : purchaseItemList) {
            total += Math.round(Double.valueOf(item.getPurchasePpPrice()) * Double.valueOf(item.getPurchaseQuantity()));

        }

        total_item = purchaseItemList.size();

        sale_grand_total_tk.setText("৳ "+total+" ("+total_item+")");
        sale_total_tk.setText("৳ "+total);
        sale_discount_tk.setText("");
        sale_return_tk.setText(String.valueOf(total));
    }

    @OnClick(R.id.choose_date)
    void choose_date()
    {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth));
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setTitle("Choose Date");
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onClick(String clickname) {

    }
}
