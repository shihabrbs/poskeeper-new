package com.portal.terminalbd.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
import com.portal.terminalbd.adapter.CustomerSuggestionAdapter;
import com.portal.terminalbd.adapter.SalesItemAdapter;
import com.portal.terminalbd.adapter.SuggestionAdapter;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.SaleItem;
import com.portal.terminalbd.model.SalesHistory;
import com.portal.terminalbd.model.SalesItemHistory;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.network.PaymentCards;
import com.portal.terminalbd.utils.ClearableAutoCompleteTextView;
import com.portal.terminalbd.utils.PrefixEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class SalesEditActivity extends AppCompatActivity implements SalesItemclickEvent {

    ValueAnimator mAnimator;
    ValueAnimator cAnimator;
    boolean isShowing = false;
    boolean isShowingCustomer = false;


    RealmResults<SaleItem> salesItemList;

    RealmResults<SalesItemHistory> saleItems;

    StockItem stockItem;

    StockItem stockItem2;

    SalesHistory salesHistory;

    List<SaleItem> existSalesList;


    Setup setup;
    List<AnonymousCustomer> customerList;

    List<MobileAccount> mobileAccounts;
    ArrayAdapter<String> mobileAccountAdapter;

    List<BankAccount> bankAccounts;
    ArrayAdapter<String> bankAccountAdapter;

    List<SystemUser> systemUserList;
    RealmResults<SystemUser> users;
    ArrayAdapter<String> systemUsersAdapter;

    RealmResults<PaymentCards> paymentCards;

    List<PaymentCards> paymentCardsList;
    ArrayAdapter<String> paymentCardsAdapter;

    AnonymousCustomer anonymousCustomer;

    SalesItemAdapter adapter;

    @BindView(R.id.saleItemsLayout)
    LinearLayout saleItemsLayout;

    @BindView(R.id.showSaleItem)
    RelativeLayout showSaleItem;

    @BindView(R.id.sales_history_rv)
    RecyclerView saleItemRv;

    @BindView(R.id.sale_total_tk)
    TextView sale_total_tk;

    @BindView(R.id.sale_discount_tk)
    TextView sale_discount_tk;

    @BindView(R.id.sale_vat_tk)
    TextView sale_vat_tk;

    /*
    add new item
     */
    @BindView(R.id.med_search)
    ClearableAutoCompleteTextView search;

    @BindView(R.id.sales_mrp)
    EditText sales_mrp;

    @BindView(R.id.sales_quantity)
    EditText sales_quantity;

    @BindView(R.id.add_new_item_layout)
    LinearLayout add_new_item_layout;

    @BindView(R.id.add_new_item)
    Button add_new_item;

    boolean isAddLayoutShow=false;


    @BindView(R.id.sale_grand_total_tk)
    TextView sale_grand_total_tk;

    @BindView(R.id.sale_total_profit)
    TextView sale_total_profit;

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

    @BindView(R.id.customCustomer_btn)
    ImageView customCustomer_btn;

    @BindView(R.id.mobile_payment_form)
    LinearLayout mobile_payment_form;

    @BindView(R.id.bank_payment_form)
    LinearLayout bank_payment_form;

    @BindView(R.id.custom_customer_info_layout)
    LinearLayout customer_info_layout;

    @BindView(R.id.discount_et)
    PrefixEditText discount_et;

    @BindView(R.id.discount_switch)
    Switch discount_switch;

    @BindView(R.id.payment_mobile_no)
    EditText payment_mobile_no_et;
    @BindView(R.id.payment_transaction_id)
    EditText payment_transaction_id;

    @BindView(R.id.payment_receive_spinner)
    Spinner payment_receive_spinner;

    @BindView(R.id.payment_card_no)
    EditText payment_card_no_et;
    @BindView(R.id.payment_card_spinner)
    Spinner payment_card_spinner;
    @BindView(R.id.payment_card_bank_spinner)
    Spinner payment_card_bank_spinner;

    @BindView(R.id.sale_by_adapter)
    Spinner sale_by_adapter;

    @BindView(R.id.customer_search)
    AutoCompleteTextView customer_search;

    @BindView(R.id.returnText)
    TextView returnText;
    @BindView(R.id.sale_return_tk)
    TextView sale_return_tk;
    @BindView(R.id.receivedTk)
    EditText receivedTk;

    @BindView(R.id.customerName_et)
    EditText customerName_et;

    @BindView(R.id.customerMobile_et)
    EditText customerMobile_et;

    @BindView(R.id.scroll)
    NestedScrollView scroll;

    @BindView(R.id.floating_layout)
    CardView floating_layout;

    @BindView(R.id.floating_total_tk)
    TextView floating_total_tk;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;



    int total;
    int totalVat;
    int totalPP;
    int totalProfit;
    int total_item =  0;

    String pharmacyName="";
    String locationName="";
    String pharmacyMobileName="";

    String anoCustomerName="";
    String anoCustomerMobile="";
    int anoCustomerId;


    int discountAmount;
    int discountTotal;
    int returnAmount;
    int dueAmount;
    String invoiceId;

    int paymentMobileID;

    String paymentType="cash";
    String paymentReceiveMobileNo;
    String paymentMobileNo="";
    String paymentTransactionId="";
    String paymentCardNo;

    String vatRegNo = "";
    String vatPercentage = "";

    String siteName = "";

    int paymentCardType;
    int paymentBankAccount;

    String discountType="";

    String createdDate;

    int index = 0;

    String currentDateandTime="";


    Realm realm;
    List<StockItem> stockItemList;


    RealmChangeListener<RealmResults<SaleItem>> realmListener = new RealmChangeListener<RealmResults<SaleItem>>() {
        @Override
        public void onChange(RealmResults<SaleItem> saleItems) {

            checkSaleLayoutVisibility();
            adapter.notifyDataSetChanged();
        }
    };


    List<SaleItem> importSaleItemList;

    String tokenNos="";
    private int grandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_edit);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));

        toolbar_title.setText("Sales Edit");

        realm = Realm.getDefaultInstance();

//        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy h:mm a");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy h:mm a");
        currentDateandTime = sdf.format(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createdDate = format.format(new Date());

        customerList = new ArrayList<>();

        anonymousCustomer = new AnonymousCustomer();

        existSalesList = new ArrayList<>();

        importSaleItemList = new ArrayList<>();

        if (getIntent().getExtras()!=null)
        {
            invoiceId = getIntent().getExtras().getString("INVOICE");
        }

        salesHistory = realm.where(SalesHistory.class).equalTo("invoiceId",invoiceId).findFirst();
        saleItems = realm.where(SalesItemHistory.class).equalTo("salesId",Integer.parseInt(invoiceId)).findAll();

        for (int i=0;i<saleItems.size();i++) {

            SaleItem saleItem = new SaleItem();
            stockItem = realm.where(StockItem.class).equalTo("itemId",saleItems.get(i).getStockId()).findFirst();
            saleItem.setSaleItemName(stockItem.getName());
            saleItem.setSalePpPrice(String.valueOf(stockItem.getPurchasePrice()));
            saleItem.setSaleStockId(String.valueOf(saleItems.get(i).getStockId()));
            saleItem.setSaleMrpPrice(String.valueOf(saleItems.get(i).getUnitPrice()));
            saleItem.setSaleSubTotal(String.valueOf(saleItems.get(i).getSubTotal()));
            saleItem.setSaleQuantity(String.valueOf(saleItems.get(i).getQuantity()));

            existSalesList.add(saleItem);

        }

        realm.beginTransaction();
        realm.insert(existSalesList);
        realm.commitTransaction();

        salesItemList = realm.where(SaleItem.class).findAll();

        salesItemList.addChangeListener(realmListener);

        receivedTk.requestFocus();
        receivedTk.setFocusableInTouchMode(true);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(receivedTk, InputMethodManager.SHOW_FORCED);

        floating_layout.setVisibility(View.GONE);

        /*
        add new item
         */

        stockItemList = new ArrayList<>();

        stockItem2 = new StockItem();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                stockItemList.clear();
                RealmResults<StockItem> oldStockItem = realm.where(StockItem.class).findAll();
                stockItemList.addAll(realm.copyFromRealm(oldStockItem));

            }
        });

        /**
         * setup serach function here
         */
        search.setAdapter(new SuggestionAdapter(this,stockItemList));

        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof StockItem){
                    stockItem2=(StockItem) item;
                    sales_mrp.setText(String.valueOf(stockItem2.getSalesPrice()));
                    sales_quantity.requestFocus();
                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.showClearButton();
            }
        });

        search.setOnClearListener(new ClearableAutoCompleteTextView.OnClearListener() {
            @Override
            public void onClear() {

                search.setText("");
                sales_mrp.setText("");
                sales_quantity.setText("");

            }
        });


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



        customer_info_layout.setVisibility(View.GONE);

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
        adapter = new SalesItemAdapter(SalesEditActivity.this,salesItemList,this);
        saleItemRv.setLayoutManager(new LinearLayoutManager(SalesEditActivity.this,LinearLayoutManager.VERTICAL,false));
        saleItemRv.addItemDecoration(new DividerItemDecoration(SalesEditActivity.this,DividerItemDecoration.VERTICAL));
        saleItemRv.setAdapter(adapter);

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
                        if (!TextUtils.isEmpty(vatRegNo))
                        {
                            totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                            discountTotal = discountTotal+totalVat;
                            sale_vat_tk.setText(totalVat+"");
                        }
                        sale_grand_total_tk.setText("৳ "+ discountTotal);
                        floating_total_tk.setText("৳ "+ discountTotal);
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
                        if (!TextUtils.isEmpty(vatRegNo))
                        {
                            totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                            discountTotal = discountTotal+totalVat;
                            sale_vat_tk.setText(totalVat+"");
                        }
                        sale_grand_total_tk.setText("৳ " + discountTotal);
                        floating_total_tk.setText("৳ " + discountTotal);
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
                            if (!TextUtils.isEmpty(vatRegNo))
                            {
                                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                                discountTotal = discountTotal+totalVat;
                                sale_vat_tk.setText(totalVat+"");
                            }
                            sale_grand_total_tk.setText("৳ "+ discountTotal);
                            floating_total_tk.setText("৳ "+ discountTotal);
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
                            if (!TextUtils.isEmpty(vatRegNo))
                            {
                                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                                discountTotal = discountTotal+totalVat;
                                sale_vat_tk.setText(totalVat+"");
                            }
                            sale_grand_total_tk.setText("৳ " + discountTotal);
                            floating_total_tk.setText("৳ " + discountTotal);
                            sale_discount_tk.setText("৳ " + discountAmount);
                            sale_return_tk.setText("৳ "+discountTotal);
                        }else
                        {
                            discount_et.setError("Please input amount of discount");
                        }

                    }

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
//                            floating_total_tk.setText("৳ "+ discountTotal);
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
//                            floating_total_tk.setText("৳ " + discountTotal);
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
                            if (!TextUtils.isEmpty(vatRegNo))
                            {
                                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                                discountTotal = discountTotal+totalVat;
                                sale_vat_tk.setText(totalVat+"");
                            }
                            sale_grand_total_tk.setText("৳ "+ discountTotal);
                            floating_total_tk.setText("৳ "+ discountTotal);
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
                            if (!TextUtils.isEmpty(vatRegNo))
                            {
                                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                                discountTotal = discountTotal+totalVat;
                                sale_vat_tk.setText(totalVat+"");
                            }
                            sale_grand_total_tk.setText("৳ " + discountTotal);
                            floating_total_tk.setText("৳ " + discountTotal);
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

        mobileAccountAdapter =
                new ArrayAdapter<>(SalesEditActivity.this
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

        bankAccountAdapter =
                new ArrayAdapter<>(SalesEditActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);

        /*
        sale by adapter
         */

        users = realm.where(SystemUser.class).findAll();

        systemUserList = realm.copyFromRealm(users);

        String[] systemUsers = new String[systemUserList.size()];
        for (int i=0;i<systemUserList.size();i++)
        {
            systemUsers[i] = systemUserList.get(i).getUsername();

        }

        systemUsersAdapter =
                new ArrayAdapter<>(SalesEditActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,systemUsers);

        sale_by_adapter.setAdapter(systemUsersAdapter);

        /*
        customerlist
         */
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                customerList.clear();
                RealmResults<AnonymousCustomer> customers = realm.where(AnonymousCustomer.class).findAll();
                customerList.addAll(realm.copyFromRealm(customers));

            }
        });

         /*
        payment cards adapter
         */

        paymentCards = realm.where(PaymentCards.class).findAll();

        paymentCardsList = realm.copyFromRealm(paymentCards);

        String[] cards = new String[paymentCardsList.size()];
        for (int i=0;i<paymentCardsList.size();i++)
        {
            cards[i] = paymentCardsList.get(i).getName();

        }

        paymentCardsAdapter =
                new ArrayAdapter<>(SalesEditActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,cards);

        payment_card_spinner.setAdapter(paymentCardsAdapter);

        /*
        set customer search
         */
        customer_search.setAdapter(new CustomerSuggestionAdapter(SalesEditActivity.this,customerList));

        customer_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof AnonymousCustomer){
                    anonymousCustomer=(AnonymousCustomer) item;
                    anoCustomerName = anonymousCustomer.getName();
                    anoCustomerMobile = anonymousCustomer.getMobile();
                    anoCustomerId = anonymousCustomer.getCustomerId();

                }
            }
        });

        receivedTk.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (view.isFocused())
                {
                    floating_layout.setVisibility(View.VISIBLE);
                }else
                {
                    floating_layout.setVisibility(View.GONE);
                }
            }
        });

        receivedTk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

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


        /*
        Pharmacy Information
         */
        setup = realm.where(Setup.class).findFirst();

        pharmacyName = setup.getName();
        locationName = setup.getLocationName();
        pharmacyMobileName = setup.getMobile();

        siteName = setup.getWebsite();

        /*
        vat details
         */
        vatRegNo = setup.getVatRegNo();
        vatPercentage = setup.getVatPercentage();

        setData();

    }

    public void checkSaleLayoutVisibility()
    {
        if (salesItemList.size()>0) {
            calculatePrice();
            checkDiscount();
            checkReturn();
        }
        else
        {
            Intent intent = new Intent(SalesEditActivity.this, SalesDetailsActivity.class);
            intent.putExtra("INVOICE",invoiceId);
            startActivity(intent);
            this.finish();
        }
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
        paymentType="mobile";

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
//
//        index = 1;
//        paymentType="mobile";
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
        paymentType="bank";

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

    @OnClick(R.id.add_btn)
    void add_btn()
    {
        if (TextUtils.isEmpty(search.getText().toString()))
        {
            search.setError("Please add a product");
            search.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(sales_mrp.getText().toString()))
        {
            sales_mrp.setError("Mrp cannot empty");
            sales_mrp.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(sales_quantity.getText().toString()))
        {
            sales_quantity.setError("Please enter quantity");
            sales_quantity.requestFocus();
            return;
        }



        realm.beginTransaction();
        SaleItem saleItem = realm.createObject(SaleItem.class, UUID.randomUUID().toString());
        saleItem.setSaleStockId(String.valueOf(stockItem2.getItemId()));
        saleItem.setSaleItemName(search.getText().toString().trim());
        saleItem.setSalePpPrice(String.valueOf(stockItem2.getPurchasePrice()));
        saleItem.setSaleMrpPrice(sales_mrp.getText().toString());
        saleItem.setSaleQuantity(sales_quantity.getText().toString());
        double sub_total = Math.round(Double.valueOf(sales_mrp.getText().toString())*Double.valueOf(sales_quantity.getText().toString()));
        saleItem.setSaleSubTotal(String.format("%.0f",sub_total));
        realm.commitTransaction();

        search.setText("");
        sales_mrp.setText("");
        sales_quantity.setText("");

        search.requestFocus();

    }

    @OnClick(R.id.add_new_item)
    void onAddNewBtn()
    {
        if (isAddLayoutShow)
        {
            isAddLayoutShow = false;
            add_new_item_layout.setVisibility(View.GONE);
            add_new_item.setText("Add Item");
            add_new_item.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            add_new_item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add,0,0,0);
        }else
        {
            isAddLayoutShow = true;
            add_new_item_layout.setVisibility(View.VISIBLE);
            add_new_item.setText("Close");
            add_new_item.setBackgroundColor(getResources().getColor(R.color.receive_tk_bg));
            add_new_item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_close,0,0,0);
        }
    }

    @OnClick(R.id.save_btn)
    void save_btn()
    {
        new AlertDialog.Builder(SalesEditActivity.this).setMessage("Are you sure want to save?")
                .setTitle("Alert!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        saveSaleAndRemove();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }

    @OnClick(R.id.cancel_btn)
            void onCancelBtn()
    {
        onBackPressed();
    }

    @OnClick(R.id.customCustomer_btn)
    void onCustomerBtnClick()
    {
         /*
        show customer info
         */

        if (isShowingCustomer)
        {
            customCustomer_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_user));

            isShowingCustomer = false;

            int finalHeight = customer_info_layout.getHeight();

            ValueAnimator cAnimator = customerInfoAnimator(finalHeight, 0);

            cAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    // Height=0, but it set visibility to GONE
                    customer_info_layout.setVisibility(View.GONE);
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
            cAnimator.start();


        }else
        {

            customCustomer_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
            isShowingCustomer = true;
            customer_info_layout.setVisibility(View.VISIBLE);

            final int widthSpec =     View.MeasureSpec.makeMeasureSpec(
                    0, View.MeasureSpec.UNSPECIFIED);
            final int heightSpec = View.MeasureSpec
                    .makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED);
            customer_info_layout.measure(widthSpec, heightSpec);

            cAnimator = customerInfoAnimator(0,
                    customer_info_layout.getMeasuredHeight());

            cAnimator.start();

            customer_search.setText("");
        }

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

    private ValueAnimator customerInfoAnimator(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new     ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = customer_info_layout
                        .getLayoutParams();
                layoutParams.height = value;
                customer_info_layout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    public void calculatePrice()
    {
        total = 0;
        totalProfit = 0;
        totalPP = 0;

        for (SaleItem item : salesItemList) {
            total += Math.round(Double.valueOf(item.getSaleMrpPrice()) * Double.valueOf(item.getSaleQuantity()));
            totalPP += Math.round(Double.valueOf(item.getSalePpPrice()) * Double.valueOf(item.getSaleQuantity()));

        }

        if (!TextUtils.isEmpty(vatRegNo))
        {
            if (!TextUtils.isEmpty(discount_et.getText().toString()))
            {
                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                grandTotal = discountTotal + totalVat;

            }else
            {
                totalVat = (int) Math.round((total * Double.valueOf(setup.getVatPercentage()) / 100));
                grandTotal = total + totalVat;
            }

            sale_vat_tk.setText(totalVat+"");

        }

        totalProfit = total-totalPP;
        total_item = salesItemList.size();

        sale_grand_total_tk.setText("৳ "+String.valueOf(total)+" ("+total_item+")");
        sale_total_profit.setText("৳ "+String.valueOf(totalProfit));
        sale_total_tk.setText("৳ "+String.valueOf(total));
        sale_discount_tk.setText("");
        sale_return_tk.setText("৳ "+total);
        floating_total_tk.setText("৳ "+total);


    }

    public void checkDiscount()
    {
//        if (!TextUtils.isEmpty(discount_et.getText().toString()))
//        {
//            if (discount_switch.isChecked())
//            {
//
//                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
//                    discountAmount = (int) Math.round ((total * Double.valueOf(discount_et.getText().toString()) / 100 ));
//                    discountTotal = total- discountAmount;
//                    sale_grand_total_tk.setText("৳ "+ discountTotal);
//                    floating_total_tk.setText("৳ "+ discountTotal);
//                    sale_discount_tk.setText("৳ "+discountAmount);
//                    sale_return_tk.setText("৳ "+discountTotal);
//
//                }else
//                {
//                    calculatePrice();
//                    discount_et.setError("Please input discount percent");
//                    discount_et.clearFocus();
//                }
//
//            }else
//            {
//                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
//                    discountAmount = Integer.parseInt(discount_et.getText().toString());
//                    discountTotal = total - discountAmount;
//                    sale_grand_total_tk.setText("৳ " + discountTotal);
//                    floating_total_tk.setText("৳ " + discountTotal);
//                    sale_discount_tk.setText("৳ " + discount_et.getText().toString());
//                    sale_return_tk.setText("৳ "+discountTotal);
//                }else
//                {
//                    calculatePrice();
//                    discount_et.setError("Please input amount of discount");
//                    discount_et.clearFocus();
//                }
//
//            }
//        }
        if (!TextUtils.isEmpty(discount_et.getText().toString()))
        {
            if (discount_switch.isChecked())
            {

                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                    discountAmount = (int) Math.round ((total * Double.valueOf(discount_et.getText().toString()) / 100 ));
                    discountTotal = total- discountAmount;
                    if (!TextUtils.isEmpty(vatRegNo))
                    {
                        totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                        discountTotal = discountTotal+totalVat;
                        sale_vat_tk.setText(totalVat+"");
                    }
                    sale_grand_total_tk.setText("৳ "+ discountTotal);
                    floating_total_tk.setText("৳ "+ discountTotal);
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
                    if (!TextUtils.isEmpty(vatRegNo))
                    {
                        totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                        discountTotal = discountTotal+totalVat;
                        sale_vat_tk.setText(totalVat+"");
                    }
                    sale_grand_total_tk.setText("৳ " + discountTotal);
                    floating_total_tk.setText("৳ " + discountTotal);
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

    public void saveSaleAndRemove() {

        /*
        save saled details
         */

        if (TextUtils.isEmpty(receivedTk.getText().toString())) {
            receivedTk.setError("Required");
            receivedTk.requestFocus();
            return;
        }

        if (isShowingCustomer) {
            if (TextUtils.isEmpty(customerName_et.getText().toString())) {
                customerName_et.setError("Please input customer name");
                customerName_et.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(customerMobile_et.getText().toString())) {
                customerMobile_et.setError("Please input customer mobile");
                customerMobile_et.requestFocus();
                return;
            }
        }

        realm.beginTransaction();

        if (index == 0) {
            paymentType = "cash";
            salesHistory.setTransactionMethod(paymentType);
            salesHistory.setPaymentMobile("");
            salesHistory.setTransactionId("");
            salesHistory.setMobileBankAccount(0);
            salesHistory.setPaymentCard(0);
            salesHistory.setPaymentCardNo("");
            salesHistory.setBankAccount(0);

        } else if (index == 1) {
                paymentType = "mobile";
                int paymentPos = payment_receive_spinner.getSelectedItemPosition();
                paymentMobileID = mobileAccounts.get(paymentPos).getItemId();
                paymentMobileNo = payment_mobile_no_et.getText().toString();
                paymentTransactionId = payment_transaction_id.getText().toString();
                paymentReceiveMobileNo = String.valueOf(payment_receive_spinner.getSelectedItem());
                salesHistory.setTransactionMethod(paymentType);
                salesHistory.setPaymentMobile(paymentMobileNo);
                salesHistory.setTransactionId(paymentTransactionId);
                salesHistory.setMobileBankAccount(paymentMobileID);
                salesHistory.setPaymentCard(0);
                salesHistory.setPaymentCardNo("");
                salesHistory.setBankAccount(0);

            } else if (index == 2) {
                    paymentType = "bank";
                    int posi = payment_card_spinner.getSelectedItemPosition();
                    paymentCardType = paymentCardsList.get(posi).getItemId();
                    paymentCardNo = payment_card_no_et.getText().toString();
                    int position = payment_card_bank_spinner.getSelectedItemPosition();
                    paymentBankAccount = bankAccounts.get(position).getItemId();
                    salesHistory.setPaymentCard(paymentCardType);
                    salesHistory.setPaymentCardNo(paymentCardNo);
                    salesHistory.setBankAccount(paymentBankAccount);
                    salesHistory.setTransactionMethod(paymentType);
                    salesHistory.setPaymentMobile("");
                    salesHistory.setTransactionId("");
                    salesHistory.setMobileBankAccount(0);

                }

        salesHistory.setCreated(createdDate);
        salesHistory.setCreatedBy(Integer.parseInt(PreferenceManager.getUserId(SalesEditActivity.this)));

        if (isShowingCustomer)
        {
            anoCustomerId = 0;
            anoCustomerMobile = customerMobile_et.getText().toString();
            anoCustomerName = customerName_et.getText().toString();
            salesHistory.setCustomerId(0);
            salesHistory.setCustomerMobile(anoCustomerMobile);
            salesHistory.setCustomerName(anoCustomerName);
        }else {
            salesHistory.setCustomerId(anoCustomerId);
            salesHistory.setCustomerMobile(anoCustomerMobile);
            salesHistory.setCustomerName(anoCustomerName);
        }


        if (!TextUtils.isEmpty(discount_et.getText().toString())) {

            salesHistory.setDiscount(Integer.parseInt(String.valueOf(discountAmount)));
            salesHistory.setDiscountCalculation( Integer.parseInt(discount_et.getText().toString()));

            if (discount_switch.isChecked()) {
                discountType = "percentage";
            } else {
                discountType = "flat";
            }

            salesHistory.setDiscountType(discountType);
            salesHistory.setTotal(Integer.parseInt(String.valueOf(discountTotal)));

        }else
        {

            salesHistory.setDiscount(0);
            salesHistory.setDiscountCalculation(0);
            salesHistory.setDiscountType("");
            salesHistory.setTotal(Integer.parseInt(String.valueOf(total)));
        }

        salesHistory.setDue(Integer.parseInt(String.valueOf(dueAmount)));
        salesHistory.setInvoiceId(String.valueOf(invoiceId));
        salesHistory.setReceive(Integer.parseInt(receivedTk.getText().toString()));
        int pos = sale_by_adapter.getSelectedItemPosition();
        salesHistory.setSalesBy(Integer.parseInt(String.valueOf(users.get(pos).getUserId())));
        salesHistory.setSubTotal(Integer.parseInt(String.valueOf(total)));

        if (TextUtils.isEmpty(vatRegNo)) {

            salesHistory.setVat(0);
        }else
        {
            salesHistory.setVat(totalVat);
        }

        salesHistory.setSlipNo("");
        salesHistory.setTokenNo(tokenNos);
        salesHistory.setDiscountCoupon(0);
        salesHistory.setRemark("");

        realm.commitTransaction();



        realm.beginTransaction();
        saleItems = realm.where(SalesItemHistory.class).equalTo("salesId",Integer.parseInt(invoiceId)).findAll();
        saleItems.deleteAllFromRealm();
        realm.commitTransaction();

        realm.beginTransaction();

        for (int i=0; i<salesItemList.size();i++)
        {

//            saleItems.get(i).setQuantity(Integer.parseInt(salesItemList.get(i).getSaleQuantity()));
//            saleItems.get(i).setSalesId(Integer.parseInt(invoiceId));
//            saleItems.get(i).setStockId(Integer.parseInt(String.valueOf(salesItemList.get(i).getSaleStockId())));
//            saleItems.get(i).setSubTotal(Integer.valueOf(Double.valueOf(salesItemList.get(i).getSaleSubTotal()).intValue()));
//            saleItems.get(i).setUnitPrice(Double.valueOf(salesItemList.get(i).getSaleMrpPrice()));
            SalesItemHistory salesItemHistory =
                    realm.createObject(SalesItemHistory.class,UUID.randomUUID().toString());
            salesItemHistory.setQuantity(Integer.parseInt(salesItemList.get(i).getSaleQuantity()));
            salesItemHistory.setSalesId(Integer.parseInt(invoiceId));
            salesItemHistory.setStockId(Integer.parseInt(String.valueOf(salesItemList.get(i).getSaleStockId())));
            salesItemHistory.setSubTotal(Integer.valueOf(Double.valueOf(salesItemList.get(i).getSaleSubTotal()).intValue()));
            salesItemHistory.setUnitPrice(Double.valueOf(salesItemList.get(i).getSaleMrpPrice()));

        }

        realm.commitTransaction();

        realm.beginTransaction();
        realm.delete(SaleItem.class);
        realm.commitTransaction();

        Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            destroy();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    public void destroy() {

        realm.beginTransaction();
        realm.delete(SaleItem.class);
        realm.commitTransaction();
        realm.close();
    }


    public void setData()
    {

        if (salesHistory.getTransactionMethod().equalsIgnoreCase("cash"))
        {
            cashPayment();

        }else if (salesHistory.getTransactionMethod().equalsIgnoreCase("mobile"))
        {
            bkashPayment();
            paymentMobileNo = salesHistory.getPaymentMobile();
            payment_mobile_no_et.setText(paymentMobileNo);
            paymentTransactionId = salesHistory.getTransactionId();
            payment_transaction_id.setText(paymentTransactionId);
            paymentMobileID = salesHistory.getMobileBankAccount();

            MobileAccount mobileAccountRealmQuery = realm.where(MobileAccount.class).equalTo("itemId",paymentMobileID).findFirst();
            payment_receive_spinner.setSelection(mobileAccountAdapter.getPosition(mobileAccountRealmQuery.getName()));

        }else
        {
            visaPayment();
            paymentCardType = salesHistory.getPaymentCard();
            paymentCardNo = salesHistory.getPaymentCardNo();
            paymentBankAccount = salesHistory.getBankAccount();

            payment_card_no_et.setText(paymentCardNo);

            PaymentCards paymentCardsRealmQuery = realm.where(PaymentCards.class).equalTo("itemId",paymentCardType).findFirst();
            payment_card_spinner.setSelection(paymentCardsAdapter.getPosition(paymentCardsRealmQuery.getName()));

            BankAccount bankAccountRealmQuery = realm.where(BankAccount.class).equalTo("itemId",paymentBankAccount).findFirst();
            payment_card_bank_spinner.setSelection(bankAccountAdapter.getPosition(bankAccountRealmQuery.getName()));


        }

        if (salesHistory.getCustomerId()==0 && !TextUtils.isEmpty(salesHistory.getCustomerName()))
        {
            isShowingCustomer=false;
            onCustomerBtnClick();
            anoCustomerName = salesHistory.getCustomerName();
            anoCustomerMobile = salesHistory.getCustomerMobile();
            anoCustomerId = salesHistory.getCustomerId();
            customerName_et.setText(anoCustomerName);
            customerMobile_et.setText(anoCustomerMobile);

        }else
        {
            anoCustomerName = salesHistory.getCustomerName();
            anoCustomerMobile = salesHistory.getCustomerMobile();
            anoCustomerId = salesHistory.getCustomerId();
            customer_search.setText(anoCustomerName);
        }

        discountType = salesHistory.getDiscountType();

        if (!TextUtils.isEmpty(discountType))
        {

            discountTotal = salesHistory.getTotal();
            discountAmount = salesHistory.getDiscount();
            discount_et.setText(String.valueOf(salesHistory.getDiscountCalculation()));
            sale_grand_total_tk.setText("৳ "+ discountTotal);
            floating_total_tk.setText("৳ "+ discountTotal);
            sale_discount_tk.setText("৳ "+discountAmount);

            if (discountType.equalsIgnoreCase("percentage"))
            {
                discount_switch.setChecked(true);
                discount_switch.setText("%");
                discount_et.setTag("% ");

            }else
            {
                discount_switch.setChecked(false);
                discount_switch.setText("Tk");
                discount_et.setTag("৳ ");
            }

        }

        receivedTk.setText(String.valueOf(salesHistory.getReceive()));

        dueAmount = salesHistory.getDue();
        if (dueAmount==0) {

            returnText.setText("Return Tk");
            if (salesHistory.getReceive()>salesHistory.getTotal()) {
                sale_return_tk.setText("৳ " + (salesHistory.getReceive() - salesHistory.getTotal()));
            }else
            {
                sale_return_tk.setText("৳ " + (salesHistory.getTotal()-salesHistory.getReceive()));
            }


        }else
        {
            returnText.setText("Due Tk");
            sale_return_tk.setText("৳ " + dueAmount);

        }

        totalVat = salesHistory.getVat();

        SystemUser systemUserRealmQuery = realm.where(SystemUser.class).equalTo("userId",salesHistory.getSalesBy()).findFirst();
        sale_by_adapter.setSelection(systemUsersAdapter.getPosition(systemUserRealmQuery.getUsername()));

        tokenNos = salesHistory.getTokenNo();

    }

    @Override
    public void onBackPressed() {
        destroy();
    }

    @Override
    public void onClick(String clickname) {

    }
}
