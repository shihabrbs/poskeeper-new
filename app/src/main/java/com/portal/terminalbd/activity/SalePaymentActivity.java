package com.portal.terminalbd.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import androidx.core.content.ContextCompat;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.rt.printerlibrary.bean.PrinterStatusBean;
import com.rt.printerlibrary.bean.UsbConfigBean;
import com.rt.printerlibrary.cmd.Cmd;
import com.rt.printerlibrary.cmd.EscFactory;
import com.rt.printerlibrary.connect.PrinterInterface;
import com.rt.printerlibrary.enumerate.CommonEnum;
import com.rt.printerlibrary.enumerate.ConnectStateEnum;
import com.rt.printerlibrary.factory.cmd.CmdFactory;
import com.rt.printerlibrary.factory.connect.PIFactory;
import com.rt.printerlibrary.factory.connect.UsbFactory;
import com.rt.printerlibrary.factory.printer.PrinterFactory;
import com.rt.printerlibrary.factory.printer.ThermalPrinterFactory;
import com.rt.printerlibrary.observer.PrinterObserver;
import com.rt.printerlibrary.observer.PrinterObserverManager;
import com.rt.printerlibrary.printer.RTPrinter;
import com.rt.printerlibrary.utils.PrintStatusCmd;
import com.rt.printerlibrary.utils.PrinterStatusPareseUtils;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.CustomerSuggestionAdapter;
import com.portal.terminalbd.adapter.SalesItemAdapter;
import com.portal.terminalbd.app.TerminalApplication;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.SaleItem;
import com.portal.terminalbd.model.SalesHistory;
import com.portal.terminalbd.model.SalesItemHistory;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.model.TokenNo;
import com.portal.terminalbd.network.APIClient;
import com.portal.terminalbd.network.PaymentCards;
import com.portal.terminalbd.utils.BaseEnum;
import com.portal.terminalbd.utils.Common;
import com.portal.terminalbd.utils.UsbDeviceChooseDialog;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class SalePaymentActivity extends AppCompatActivity implements SalesItemclickEvent,Runnable, PrinterObserver {

    ValueAnimator mAnimator;
    ValueAnimator cAnimator;
    boolean isShowing = false;
    boolean isShowingCustomer = false;



    RealmResults<SaleItem> salesItemList;
    Setup setup;
    List<AnonymousCustomer> customerList;
    List<MobileAccount> mobileAccounts;
    List<BankAccount> bankAccounts;
    RealmResults<SystemUser> users;
    RealmResults<PaymentCards> paymentCards;
    List<PaymentCards> paymentCardsList;

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
    EditText discount_et;

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


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.print_status)
            TextView print_status;

    @BindView(R.id.scroll)
    NestedScrollView scroll;

    @BindView(R.id.floating_layout)
    CardView floating_layout;

    @BindView(R.id.floating_total_tk)
    TextView floating_total_tk;

    int total;
    int totalVat;
    int totalPP;
    int totalProfit;
    int total_item =  0;

    int serial=0;
    String items="";

    String pharmacyName="";
    String locationName="";
    String addressName="";
    String pharmacyMobileName="";

    String anoCustomerName="";
    String anoCustomerMobile="";
    int anoCustomerId;


    int discountAmount;
    int discountTotal;
    int returnAmount;
    int dueAmount;
    int memoNo;

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

    private Menu menu;

    /*
    **************************Printer connect code***********************************
     */
    protected static final String TAG = "TAG";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;


    BluetoothAdapter mBluetoothAdapter;
    private UUID applicationUUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog mBluetoothConnectProgressDialog;
    private BluetoothSocket mBluetoothSocket;
    BluetoothDevice mBluetoothDevice;
    Thread mBlutoothConnectThread;

    StockItem stock;

    String itemPrintName="";


    RealmChangeListener<RealmResults<SaleItem>> realmListener = new RealmChangeListener<RealmResults<SaleItem>>() {
        @Override
        public void onChange(RealmResults<SaleItem> saleItems) {

            checkSaleLayoutVisibility();
            adapter.notifyDataSetChanged();
        }
    };

    boolean isBluetoothOff = false;

    private List<TokenNo> tokenNoList;
    private List<Integer> tokenIds;
    private String tokenNos = "";

    private List<SalesHistory> salesHistoryList;
    private List<SalesItemHistory> salesItemHistoryList;

    CompositeDisposable compositeDisposable;
    int totalSize;
    int saleItemTotalSize;

    ProgressDialog progressDialog;

    int grandTotal;

    /*Usb Printer variable*/
    private String[] NEED_PERMISSION = {
            Manifest.permission.CAMERA
    };
    private List<String> NO_PERMISSION = new ArrayList<String>();
    private static final int REQUEST_CAMERA = 0;

    private ProgressDialog pb_connect;

    @BaseEnum.ConnectType
    private int checkedConType = BaseEnum.CON_USB;
    private RTPrinter rtPrinter = null;
    private PrinterFactory printerFactory;
    private final String SP_KEY_IP = "ip";
    private final String SP_KEY_PORT = "port";
    private Object configObj;
    private ArrayList<PrinterInterface> printerInterfaceArrayList = new ArrayList<>();
    private PrinterInterface curPrinterInterface = null;
    private BroadcastReceiver broadcastReceiver;//USB Attach-Deattached Receiver
    private int iprintTimes=0;
    private boolean isReceiverRegistered = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_payment);
        setContentView(R.layout.activity_sale_payment);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));


        realm = Realm.getDefaultInstance();

        compositeDisposable = new CompositeDisposable();

        progressDialog = new ProgressDialog(SalePaymentActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);

//        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy h:mm a");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy h:mm a");
        currentDateandTime = sdf.format(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createdDate = format.format(new Date());

        customerList = new ArrayList<>();
        salesHistoryList = new ArrayList<>();
        salesItemHistoryList = new ArrayList<>();

        anonymousCustomer = new AnonymousCustomer();

        salesItemList = realm.where(SaleItem.class).findAll();

        salesItemList.addChangeListener(realmListener);

        memoNo = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        receivedTk.requestFocus();

        floating_layout.setVisibility(View.GONE);


        //show hide floating layout

//        scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                if (scrollY > oldScrollY) {
//                    Log.i(TAG, "Scroll DOWN");
//
//                    floating_layout.setVisibility(View.VISIBLE);
//
//                }
//                if (scrollY < oldScrollY) {
//                    Log.i(TAG, "Scroll UP");
//                }
//
//                if (scrollY == 0) {
//                    Log.i(TAG, "TOP SCROLL");
//
//                    floating_layout.setVisibility(View.GONE);
//                }
//
//                if (scrollY == ( v.getMeasuredHeight() - v.getChildAt(0).getMeasuredHeight() )) {
//                    Log.i(TAG, "BOTTOM SCROLL");
//                }
//            }
//        });

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


        //         Add onPreDrawListener
        customer_info_layout.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        customer_info_layout.getViewTreeObserver()
                                .removeOnPreDrawListener(this);
                        customer_info_layout.setVisibility(View.GONE);

                        final int widthSpec =     View.MeasureSpec.makeMeasureSpec(
                                0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec
                                .makeMeasureSpec(0,
                                        View.MeasureSpec.UNSPECIFIED);
                        customer_info_layout.measure(widthSpec, heightSpec);

                        cAnimator = customerInfoAnimator(0,
                                customer_info_layout.getMeasuredHeight());
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


        /*
        show customer info
         */
        customCustomer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    cAnimator.start();

                    customer_search.setText("");
                    anoCustomerName = "";
                    anoCustomerMobile = "";
                    anoCustomerId = 0;
                }
            }
        });

        //set adapter
        adapter = new SalesItemAdapter(SalePaymentActivity.this,salesItemList,this);
        saleItemRv.setLayoutManager(new LinearLayoutManager(SalePaymentActivity.this,LinearLayoutManager.VERTICAL,false));
        saleItemRv.addItemDecoration(new DividerItemDecoration(SalePaymentActivity.this,DividerItemDecoration.VERTICAL));
        saleItemRv.setAdapter(adapter);

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
                new ArrayAdapter<>(SalePaymentActivity.this
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
                new ArrayAdapter<>(SalePaymentActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);

        /*
        sale by adapter
         */

        users = realm.where(SystemUser.class).findAll();

        List<SystemUser> systemUserList = realm.copyFromRealm(users);

        String[] systemUsers = new String[systemUserList.size()];
        for (int i=0;i<systemUserList.size();i++)
        {
            systemUsers[i] = systemUserList.get(i).getUsername();

        }

        ArrayAdapter<String> systemUsersAdapter =
                new ArrayAdapter<>(SalePaymentActivity.this
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

        ArrayAdapter<String> paymentCardsAdapter =
                new ArrayAdapter<>(SalePaymentActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,cards);

        payment_card_spinner.setAdapter(paymentCardsAdapter);

        /*
        set customer search
         */
        customer_search.setAdapter(new CustomerSuggestionAdapter(this,customerList));

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

                        if (TextUtils.isEmpty(vatRegNo)) {

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
                            if (Integer.parseInt(receivedTk.getText().toString()) >= grandTotal) {
                                dueAmount = 0;
                                returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - grandTotal);
                                sale_return_tk.setText("৳ " + returnAmount);
                                returnText.setText("Return Tk");

                            } else {
                                returnAmount = 0;
                                returnText.setText("Due Tk");
                                dueAmount = (int) (grandTotal - Integer.parseInt(receivedTk.getText().toString()));
                                sale_return_tk.setText("৳ " + dueAmount);
                            }
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

                        if (TextUtils.isEmpty(vatRegNo)) {
                            sale_return_tk.setText(String.valueOf(total));
                        }else
                        {
                            sale_return_tk.setText(String.valueOf(grandTotal));
                        }

                    }else
                    {
                        sale_return_tk.setText(String.valueOf(discountTotal));
                    }

                    }

            }
        });

        /*
        ********************************Printer connect code here***********************************
         */

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();



        ListPairedDevices();



        /*
        Pharmacy Information
         */
        setup = realm.where(Setup.class).findFirst();

        pharmacyName = setup.getName();
        locationName = setup.getLocationName();

        addressName = setup.getAddress();
        pharmacyMobileName = setup.getMobile();

        siteName = setup.getWebsite();

        /*
        vat details
         */
        vatRegNo = setup.getVatRegNo();
        vatPercentage = setup.getVatPercentage();


        /*
        Token no
         */

        tokenIds = new ArrayList<>();

        if (PreferenceManager.getModule(SalePaymentActivity.this).equalsIgnoreCase("restaurant"))
        {
            if (getIntent().getExtras()!=null)
            {
                Type type = new TypeToken<List<TokenNo>>(){}.getType();
                tokenNoList = new Gson().fromJson(getIntent().getExtras().getString("TOKEN"),type);
            }

            if (tokenNoList.size()>0)
            {
                for (TokenNo token :
                        tokenNoList) {
                    tokenIds.add(token.getTokenId());
                }

                String str = tokenIds.toString();
                tokenNos = str.replaceAll("[\\[\\]]", "");

                Log.i("TOKEN", tokenNos);
            }
        }

        calculatePrice();

        /*
         ******************************** Usb Printer connect code here***********************************
         */
        pb_connect = new ProgressDialog(SalePaymentActivity.this);
        pb_connect.setMessage("Connecting...");
        CheckAllPermission();
        init();


    }

    public void checkSaleLayoutVisibility()
    {
        if (salesItemList.size()>0) {
            calculatePrice();
            checkDiscount();
            checkReturn();
        }else
        {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
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

    @OnClick(R.id.save_btn)
    void save_btn()
    {
                           new AlertDialog.Builder(SalePaymentActivity.this).setMessage("Are you sure want to save?")
                           .setTitle("Alert!!")
                           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i) {

                                   if (PreferenceManager.getOnlineOfflineMode(SalePaymentActivity.this).equalsIgnoreCase("Online")) {
                                       if (Common.isNetworkAvailable(SalePaymentActivity.this)) {

                                           syncSales();

                                       } else {

                                           Toast.makeText(SalePaymentActivity.this, "You are in online mode.Please Check your network connection", Toast.LENGTH_SHORT).show();
                                       }
                                   }else if (PreferenceManager.getOnlineOfflineMode(SalePaymentActivity.this).equalsIgnoreCase("Offline"))
                                   {
                                       saveSaleAndRemove();
                                   }
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

    @OnClick(R.id.pos_print_btn)
    void onPosPrint()
    {

        if(print_status.getText().equals("Off"))
        {
            if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("usb"))
            {
                showUSBDeviceChooseDialog();

            }else
            {
                            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(SalePaymentActivity.this, "Message1", Toast.LENGTH_SHORT).show();
            } else {
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(
                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent,
                            REQUEST_ENABLE_BT);
                } else {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(SalePaymentActivity.this,
                            DeviceListActivity.class);
                    startActivityForResult(connectIntent,
                            REQUEST_CONNECT_DEVICE);

                }
            }
            }


        }
        else if(print_status.getText().equals("Connected"))
        {
            new AlertDialog.Builder(SalePaymentActivity.this).setMessage("Are you sure want to print?")
                    .setTitle("Alert!!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            if (PreferenceManager.getOnlineOfflineMode(SalePaymentActivity.this).equalsIgnoreCase("Online")) {
                                if (Common.isNetworkAvailable(SalePaymentActivity.this)) {

                                    if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("80mm"))
                                    {
                                        print80mm();
                                        syncSales();

                                    }else if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("50mm"))
                                    {
                                        print50mm();
                                        syncSales();
                                    }else if(PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("usb"))
                                    {
                                        printUsb();
                                        syncSales();
                                    }


                                } else {

                                    Toast.makeText(SalePaymentActivity.this, "You are in online mode.Please Check your network connection", Toast.LENGTH_SHORT).show();
                                }
                            }else if (PreferenceManager.getOnlineOfflineMode(SalePaymentActivity.this).equalsIgnoreCase("Offline"))
                            {
                                if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("80mm"))
                                {
                                    print80mm();
                                    saveSaleAndRemove();

                                }else if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("50mm"))
                                {
                                    print50mm();
                                    saveSaleAndRemove();
                                }else if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("usb"))
                                {
                                    printUsb();
                                    saveSaleAndRemove();
                                }

                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();

        }else
        {
            if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("usb")) {
                doConnect();
            }
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

            totalProfit = total-totalPP;
            total_item = salesItemList.size();

            sale_grand_total_tk.setText("৳ "+String.valueOf(grandTotal)+" ("+total_item+")");
            sale_total_profit.setText("৳ "+String.valueOf(totalProfit));
            sale_total_tk.setText("৳ "+String.valueOf(total));
            sale_discount_tk.setText("");
            sale_return_tk.setText("৳ "+grandTotal);
            floating_total_tk.setText("৳ "+grandTotal);


        }else
        {
            totalProfit = total-totalPP;
            total_item = salesItemList.size();

            sale_grand_total_tk.setText("৳ "+String.valueOf(grandTotal)+" ("+total_item+")");
            sale_total_profit.setText("৳ "+String.valueOf(totalProfit));
            sale_total_tk.setText("৳ "+String.valueOf(total));
            sale_discount_tk.setText("");
            sale_return_tk.setText("৳ "+total);
            floating_total_tk.setText("৳ "+total);
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

                if (TextUtils.isEmpty(vatRegNo)) {

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
                    if (Integer.parseInt(receivedTk.getText().toString()) >= grandTotal) {
                        dueAmount = 0;
                        returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - grandTotal);
                        sale_return_tk.setText("৳ " + returnAmount);
                        returnText.setText("Return Tk");

                    } else {
                        returnAmount = 0;
                        returnText.setText("Due Tk");
                        dueAmount = (int) (grandTotal - Integer.parseInt(receivedTk.getText().toString()));
                        sale_return_tk.setText("৳ " + dueAmount);
                    }
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

                if (TextUtils.isEmpty(vatRegNo)) {
                    sale_return_tk.setText(String.valueOf(total));
                }else
                {
                    sale_return_tk.setText(String.valueOf(grandTotal));
                }

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

        salesItemList.removeAllChangeListeners();

        realm.beginTransaction();

        SalesHistory salesHistory = realm.createObject(SalesHistory.class, UUID.randomUUID().toString());
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
        salesHistory.setCreatedBy(Integer.parseInt(PreferenceManager.getUserId(SalePaymentActivity.this)));

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
            salesHistory.setDiscountCalculation(Integer.parseInt(discount_et.getText().toString()));

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
        salesHistory.setInvoiceId(String.valueOf(memoNo));
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

//        realm.commitTransaction();

//        realm.beginTransaction();

        for (int i=0; i<salesItemList.size();i++)
        {

            SalesItemHistory salesItemHistory =
                    realm.createObject(SalesItemHistory.class,UUID.randomUUID().toString());
            salesItemHistory.setQuantity(Integer.parseInt(salesItemList.get(i).getSaleQuantity()));
            salesItemHistory.setSalesId(memoNo);
            salesItemHistory.setStockId(Integer.parseInt(String.valueOf(salesItemList.get(i).getSaleStockId())));
            salesItemHistory.setSubTotal(Integer.valueOf(Double.valueOf(salesItemList.get(i).getSaleSubTotal()).intValue()));
            salesItemHistory.setUnitPrice(Double.valueOf(salesItemList.get(i).getSaleMrpPrice()));

        }
        realm.delete(SaleItem.class);
        realm.commitTransaction();
        realm.close();

//        realm.beginTransaction();
//        realm.commitTransaction();
//

        Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    public void syncSales() {

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

        progressDialog.show();

        SalesHistory salesHistory = new SalesHistory();
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
        salesHistory.setCreatedBy(Integer.parseInt(PreferenceManager.getUserId(SalePaymentActivity.this)));

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
            salesHistory.setDiscountCalculation(Integer.parseInt(discount_et.getText().toString()));

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

            if (!TextUtils.isEmpty(vatRegNo))
            {
                salesHistory.setTotal(Integer.parseInt(String.valueOf(grandTotal)));
            }else
            {
                salesHistory.setTotal(Integer.parseInt(String.valueOf(total)));
            }

        }

        salesHistory.setDue(Integer.parseInt(String.valueOf(dueAmount)));
        salesHistory.setInvoiceId(String.valueOf(memoNo));
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


        salesHistoryList.add(salesHistory);


        for (int i=0; i<salesItemList.size();i++)
        {

            SalesItemHistory salesItemHistory =
                    new SalesItemHistory();
            salesItemHistory.setQuantity(Integer.parseInt(salesItemList.get(i).getSaleQuantity()));
            salesItemHistory.setSalesId(memoNo);
            salesItemHistory.setStockId(Integer.parseInt(String.valueOf(salesItemList.get(i).getSaleStockId())));
            salesItemHistory.setSubTotal(Integer.valueOf(Double.valueOf(salesItemList.get(i).getSaleSubTotal()).intValue()));
            salesItemHistory.setUnitPrice(Double.valueOf(salesItemList.get(i).getSaleMrpPrice()));

            salesItemHistoryList.add(salesItemHistory);

        }


        totalSize = salesHistoryList.size();
        saleItemTotalSize = salesItemHistoryList.size();


        if (totalSize>0) {

            compositeDisposable.addAll(
                    APIClient.getInstance().syncSales(PreferenceManager.getDeviceId(SalePaymentActivity.this)
                            , PreferenceManager.getSecretKey(SalePaymentActivity.this), new Gson().toJson(salesHistoryList)
                            ,totalSize
                            ,new Gson().toJson(salesItemHistoryList)
                            ,saleItemTotalSize)
                            .delay(2, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
                                @Override
                                public void onNext(final Response<ResponseBody> response) {

                                    progressDialog.dismiss();

                                    if (response.isSuccessful()) {

                                        realm.beginTransaction();
                                        realm.delete(SaleItem.class);
                                        realm.commitTransaction();

                                        Toast.makeText(SalePaymentActivity.this, "Successfully saved", Toast.LENGTH_SHORT).show();

                                        onBackPressed();


                                    } else {
                                        progressDialog.dismiss();
                                        saveSaleAndRemove();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    progressDialog.dismiss();
                                    saveSaleAndRemove();
                                }

                                @Override
                                public void onComplete() {

                                }
                            }));


        }else
        {
            progressDialog.dismiss();
            Toast.makeText(SalePaymentActivity.this, "Nothing to sync.Please add some sales first", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.print_status,menu);

        this.menu = menu;

        if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("usb")) {
            connectPrinter();
        }

        return super.onCreateOptionsMenu(menu);
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

    /*
    ************************************Printer connect code here********************************************
     */

    @Override
    protected void onDestroy() {
        realm.close();
        try {
            if (mBluetoothSocket != null)
                mBluetoothSocket.close();

        } catch (Exception e) {
            Log.e("Tag", "Exe ", e);
        }
        super.onDestroy();
    }


    public void onActivityResult(int mRequestCode, int mResultCode,
                                 Intent mDataIntent) {
        super.onActivityResult(mRequestCode, mResultCode, mDataIntent);

        switch (mRequestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (mResultCode == Activity.RESULT_OK) {
                    Bundle mExtra = mDataIntent.getExtras();
                    String mDeviceAddress = mExtra.getString("DeviceAddress");
                    Log.v(TAG, "Coming incoming address " + mDeviceAddress);

                    isBluetoothOff = true;

                    mBluetoothDevice = mBluetoothAdapter
                            .getRemoteDevice(mDeviceAddress);
                    mBluetoothConnectProgressDialog = ProgressDialog.show(this,
                            "Connecting...", mBluetoothDevice.getName() + " : "
                                    + mBluetoothDevice.getAddress(), true, false);

                    mBlutoothConnectThread = new Thread(this);
                    mBlutoothConnectThread.start();
                }
                break;

            case REQUEST_ENABLE_BT:
                if (mResultCode == Activity.RESULT_OK) {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(SalePaymentActivity.this,
                            DeviceListActivity.class);
                    startActivityForResult(connectIntent, REQUEST_CONNECT_DEVICE);
                } else {
                    Toast.makeText(SalePaymentActivity.this, "Message", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void ListPairedDevices() {
        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter
                .getBondedDevices();
        if (mPairedDevices.size() > 0) {
            for (BluetoothDevice mDevice : mPairedDevices) {
                Log.v(TAG, "PairedDevices: " + mDevice.getName() + "  "
                        + mDevice.getAddress());

                mBluetoothAdapter.cancelDiscovery();

                String mDeviceAddress = mDevice.getAddress();

                mBluetoothDevice = mBluetoothAdapter
                        .getRemoteDevice(mDeviceAddress);
            }

            mBlutoothConnectThread = new Thread(this);
            mBlutoothConnectThread.start();
        }
    }

    public void run() {
        try {

            synchronized(SalePaymentActivity.this) {
                mBluetoothSocket = mBluetoothDevice
                        .createInsecureRfcommSocketToServiceRecord(applicationUUID);
                mBluetoothAdapter.cancelDiscovery();
                mBluetoothSocket.connect();
                mHandler.sendEmptyMessage(0);
            }

        } catch (IOException eConnectException) {
            Log.d(TAG, "CouldNotConnectToSocket", eConnectException);
            closeSocket(mBluetoothSocket);
            return;
        }
    }

    private void closeSocket(BluetoothSocket nOpenSocket) {
        try {
            nOpenSocket.close();
            Log.d(TAG, "SocketClosed");
        } catch (IOException ex) {
            Log.d(TAG, "CouldNotCloseSocket");
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (isBluetoothOff) {
                mBluetoothConnectProgressDialog.dismiss();
            }

            SharedPreferences pre = getSharedPreferences("BT_NAME",MODE_PRIVATE);
            pre.edit().putString("bluetooth_connected",mBluetoothDevice.getAddress()).apply();

            print_status.setText("");
            print_status.setTextColor(Color.RED);
            print_status.setText("Connected");
            print_status.setTextColor(Color.rgb(97, 170, 74));
            menu.getItem(0).setIcon(ContextCompat.getDrawable(SalePaymentActivity.this, R.drawable.ic_print_connected));

        }
    };

    /*
    * 80mm printing method
    * */
    public void print80mm(){

        for (int i=0;i<salesItemList.size();i++)
        {
            serial++;

            stock = realm
                    .where(StockItem.class)
                    .equalTo("itemId",Integer.parseInt(salesItemList.get(i)
                            .getSaleStockId()))
                    .findFirst();

            if (TextUtils.isEmpty(stock.getPrintHidden()))
            {
                if (stock.getPrintName().length()>35) {

                    itemPrintName = stock.getPrintName().substring(0,35);

                }else
                {
                    itemPrintName = stock.getPrintName();
                }

            }else
            {
                itemPrintName = "Item-1";
            }

            items+=String.format("%1$-47s %2$-8s %3$-1s",
                    serial+". "+itemPrintName,
                    salesItemList.get(i).getSaleQuantity(),
                    salesItemList.get(i).getSaleSubTotal())+"\n";
        }

        Log.d("Loop","DATA"+items);


        Thread t = new Thread() {
            public void run() {
                try {
                    OutputStream os = mBluetoothSocket
                            .getOutputStream();
                    String header5 = "";
                    String header = "";
                    String address = "";
                    String he = "";
                    String header2 = "";
                    String BILL = "";
                    String vio = "";
                    String header3 = "";
                    String mvdtail = "";
                    String header4 = "" ;
                    String offname = "";
                    String copy = "";
                    String site = "";


                    header5 = header5+pharmacyName+"\n";

                    if (!TextUtils.isEmpty(addressName)) {
                        address = address+addressName + "\n";
                    }

                    if (!TextUtils.isEmpty(locationName)) {
                        he = he+locationName + "\n";
                    }
                    he = he+pharmacyMobileName+"\n";


                    if (!TextUtils.isEmpty(vatRegNo))
                    {
                        he = he +"Vat Regi No:"+vatRegNo+"\n\n";
                    }else
                    {
                        he=he+"\n";
                    }

                    if (!TextUtils.isEmpty(anoCustomerName) && !TextUtils.isEmpty(anoCustomerMobile)) {
                        header = header+String.format("%1$-30s %2$-1s", "Bill No:"+memoNo, "Sales By:"+sale_by_adapter.getSelectedItem())+"\n";
                        header = header+String.format("%1$-30s %2$-1s", "Date:"+currentDateandTime, "Pay Mode:"+paymentType)+"\n";
                        header = header+String.format("%1$-30s %2$-1s", "Customer:"+anoCustomerName, "Mobile:"+anoCustomerMobile)+"\n";
                        header = header+"\n";

                    }else
                    {
                        header = header+String.format("%1$-30s %2$-1s", "Bill No:"+memoNo, "Sales By:"+sale_by_adapter.getSelectedItem())+"\n";
                        header = header+String.format("%1$-30s %2$-1s", "Date:"+currentDateandTime, "Pay Mode:"+paymentType)+"\n";

                        header = header+"\n";
                    }

                    header2=String.format("%1$-47s %2$-8s %3$-1s", "Item Name", "Qnt", "Amount")+"\n";
                    header2 = header2 + "----------------------------------------------------------------\n";
                    vio = items;
                    vio = vio + "----------------------------------------------------------------\n";

                    header3 =              "Sub Total:                                      Tk.      "+total+"\n";

                    if (!TextUtils.isEmpty(vatRegNo))
                    {
                        mvdtail =           "(-)                                             Tk.      "+discountAmount+"\n";
                        mvdtail = mvdtail + "Vat:                                            Tk.      "+totalVat + "\n";
                        mvdtail = mvdtail + "----------------------------------------------------------------\n";
                    }else
                    {
                        mvdtail =           "(-)                                             Tk.      "+discountAmount+"\n";
                        mvdtail = mvdtail + "----------------------------------------------------------------\n";
                    }

                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        mvdtail = mvdtail + "Net Payable:                                    Tk.      "+discountTotal + "\n";
                        mvdtail = mvdtail + "Paid:                                           Tk.      "+receivedTk.getText().toString() + "\n";
                        mvdtail = mvdtail + "Due:                                            Tk.      "+dueAmount + "\n";
                        mvdtail = mvdtail + "Return:                                         Tk.      "+returnAmount + "\n\n";
                    }else
                    {
                        if (TextUtils.isEmpty(vatRegNo)) {
                            mvdtail = mvdtail + "Net Payable:                                    Tk.      " + total + "\n";
                            mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                            mvdtail = mvdtail + "Due:                                            Tk.      " + dueAmount + "\n";
                            mvdtail = mvdtail + "Return:                                         Tk.      " + returnAmount + "\n\n";
                        }else
                        {
                            mvdtail = mvdtail + "Net Payable:                                    Tk.      " + grandTotal + "\n";
                            mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                            mvdtail = mvdtail + "Due:                                            Tk.      " + dueAmount + "\n";
                            mvdtail = mvdtail + "Return:                                         Tk.      " + returnAmount + "\n\n";
                        }
                    }


                    site ="** Visit "+siteName+" **\n";
                    copy ="Development By www.terminalbd.com\n\n\n\n\n\n\n\n\n\n";


                    os.write(Common.bold);
                    os.write(Common.ESC_ALIGN_CENTER);
                    os.write(header5.getBytes());
                    os.write(Common.bold_cancel);
                    os.write(Common.text_small_size);
                    os.write(Common.center);
                    os.write(he.getBytes());
                    os.write(Common.left);
                    os.write(header.getBytes());
                    os.write(BILL.getBytes());
                    os.write(Common.left);
                    os.write(header2.getBytes());
                    os.write(vio.getBytes());
                    os.write(Common.left);
                    os.write(header3.getBytes());
                    os.write(Common.left);
                    os.write(mvdtail.getBytes());
                    os.write(Common.center);
                    os.write(header4.getBytes());
                    os.write(Common.center);
                    os.write(offname.getBytes());
                    os.write(Common.center);
                    if (!TextUtils.isEmpty(siteName)) {
                        os.write(site.getBytes());
                    }
                    os.write(Common.center);
                    os.write(copy.getBytes());


                } catch (Exception e) {
                    Log.e("PrintActivity", "Exe ", e);
                }
            }
        };
        t.start();
    }

    /*
     * 50mm printing method
     * */
    public void print50mm(){

        for (int i=0;i<salesItemList.size();i++)
        {
            serial++;

            stock = realm
                    .where(StockItem.class)
                    .equalTo("itemId",Integer.parseInt(salesItemList.get(i)
                            .getSaleStockId()))
                    .findFirst();

            if (TextUtils.isEmpty(stock.getPrintHidden()))
            {
                if (stock.getPrintName().length()>20) {

                    itemPrintName = stock.getPrintName().substring(0,20);

                }else
                {
                    itemPrintName = stock.getPrintName();
                }

            }else
            {
                itemPrintName = "Item-1";
            }

            items+=String.format("%1$-27s %2$-8s %3$-1s",
                    serial+". "+itemPrintName,
                    salesItemList.get(i).getSaleQuantity(),
                    salesItemList.get(i).getSaleSubTotal())+"\n";
        }

        Log.d("Loop","DATA"+items);


        Thread t = new Thread() {
            public void run() {
                try {
                    OutputStream os = mBluetoothSocket
                            .getOutputStream();
                    String header5 = "";
                    String header = "";
                    String he = "";
                    String header2 = "";
                    String BILL = "";
                    String vio = "";
                    String header3 = "";
                    String mvdtail = "";
                    String header4 = "" ;
                    String offname = "";
                    String copy = "";

                    String site = "";


                    header5 = header5+pharmacyName+"\n";

                    if (!TextUtils.isEmpty(locationName)) {
                        he = he+locationName + "\n";
                    }
                    he = he+pharmacyMobileName+"\n";

                    if (!TextUtils.isEmpty(vatRegNo))
                    {
                        he = he +"Vat Regi No:"+vatRegNo+"\n\n";
                    }else
                    {
                        he=he+"\n";
                    }

                    if (!TextUtils.isEmpty(anoCustomerName) && !TextUtils.isEmpty(anoCustomerMobile)) {
                        header = header+String.format("%1$-21s %2$-1s", "Bill No:"+memoNo, "Sales By:"+sale_by_adapter.getSelectedItem())+"\n";
                        header = header+String.format("%1$-27s %2$-1s", "Date:"+currentDateandTime, "\nPay Mode:"+paymentType)+"\n";
                        header = header+String.format("%1$-27s %2$-1s", "Customer:"+anoCustomerName, "Mobile:"+anoCustomerMobile)+"\n";
                        header = header+"\n";

                    }else
                    {
                        header = header+String.format("%1$-21s %2$-1s", "Bill No:"+memoNo, "Sales By:"+sale_by_adapter.getSelectedItem())+"\n";
                        header = header+String.format("%1$-20s %2$-1s", "Date:"+currentDateandTime, "\nPay Mode: "+paymentType)+"\n";

                        header = header+"\n";
                    }

                    header2=String.format("%1$-26s %2$-8s %3$-1s", "Item Name", "Qnt", "Amount")+"\n";
                    header2 = header2 + "------------------------------------------\n";
                    vio = items;
                    vio = vio + "------------------------------------------\n";

                    header3 =               "Sub Total:                  Tk.      "+total+"\n";

                    if (!TextUtils.isEmpty(vatRegNo))
                    {
                        mvdtail =           "(-)                          Tk.      "+discountAmount+"\n";
                        mvdtail = mvdtail + "Vat:                         Tk.      "+totalVat + "\n";
                        mvdtail = mvdtail + "------------------------------------------\n";
                    }else
                    {
                        mvdtail =           "(-)                         Tk.      "+discountAmount+"\n";
                        mvdtail = mvdtail + "------------------------------------------\n";
                    }

                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        mvdtail = mvdtail + "Net Payable:                Tk.      "+discountTotal + "\n";
                        mvdtail = mvdtail + "Paid:                       Tk.      "+receivedTk.getText().toString() + "\n";
                        mvdtail = mvdtail + "Due:                        Tk.      "+dueAmount + "\n";
                        mvdtail = mvdtail + "Return:                     Tk.      "+returnAmount + "\n\n";
                    }else
                    {
                        if (TextUtils.isEmpty(vatRegNo)) {
                            mvdtail = mvdtail + "Net Payable:                Tk.      " + total + "\n";
                            mvdtail = mvdtail + "Paid:                       Tk.      " + receivedTk.getText().toString() + "\n";
                            mvdtail = mvdtail + "Due:                        Tk.      " + dueAmount + "\n";
                            mvdtail = mvdtail + "Return:                     Tk.      " + returnAmount + "\n\n";
                        }else
                        {
                            mvdtail = mvdtail + "Net Payable:                Tk.      " + grandTotal + "\n";
                            mvdtail = mvdtail + "Paid:                       Tk.      " + receivedTk.getText().toString() + "\n";
                            mvdtail = mvdtail + "Due:                        Tk.      " + dueAmount + "\n";
                            mvdtail = mvdtail + "Return:                     Tk.      " + returnAmount + "\n\n";
                        }
                    }

                    site ="** Visit "+siteName+" **\n";
                    copy ="Development By www.terminalbd.com\n\n\n\n";


                    os.write(Common.bold);
                    os.write(Common.ESC_ALIGN_CENTER);
                    os.write(header5.getBytes());
                    os.write(Common.bold_cancel);
                    os.write(Common.text_small_size);
                    os.write(Common.center);
                    os.write(he.getBytes());
                    os.write(Common.left);
                    os.write(header.getBytes());
                    os.write(BILL.getBytes());
                    os.write(Common.left);
                    os.write(header2.getBytes());
                    os.write(vio.getBytes());
                    os.write(Common.left);
                    os.write(header3.getBytes());
                    os.write(Common.left);
                    os.write(mvdtail.getBytes());
                    os.write(Common.center);
                    os.write(header4.getBytes());
                    os.write(Common.center);
                    os.write(offname.getBytes());
                    os.write(Common.center);
                    if (!TextUtils.isEmpty(siteName)) {
                        os.write(site.getBytes());
                    }
                    os.write(Common.center);
                    os.write(copy.getBytes());


                } catch (Exception e) {
                    Log.e("PrintActivity", "Exe ", e);
                }
            }
        };
        t.start();
    }


    /*
     * Usb printing method
     * */
    public void printUsb(){

        serial = 0;
        items = "";

        CmdFactory cmdFactory = new EscFactory();
        Cmd cmd = cmdFactory.create();

        for (int i=0;i<salesItemList.size();i++)
        {
            serial++;

            stock = realm
                    .where(StockItem.class)
                    .equalTo("itemId",Integer.parseInt(salesItemList.get(i)
                            .getSaleStockId()))
                    .findFirst();

            if (TextUtils.isEmpty(stock.getPrintHidden()))
            {
                if (stock.getPrintName().length()>35) {

                    itemPrintName = stock.getPrintName().substring(0,35);

                }else
                {
                    itemPrintName = stock.getPrintName();
                }

            }else
            {
                itemPrintName = "Item-1";
            }

            items+=String.format("%1$-47s %2$-8s %3$-1s",
                    serial+". "+itemPrintName,
                    salesItemList.get(i).getSaleQuantity(),
                    salesItemList.get(i).getSaleSubTotal())+"\n";
        }

        Log.d("Loop","DATA"+items);

                    String header5 = "";
                    String header = "";
                    String he = "";
                    String header2 = "";
                    String BILL = "";
                    String vio = "";
                    String header3 = "";
                    String mvdtail = "";
                    String header4 = "" ;
                    String offname = "";
                    String copy = "";
                    String site = "";


                    header5 = header5+pharmacyName+"\n";

                    if (!TextUtils.isEmpty(locationName)) {
                        he = he+locationName + "\n";
                    }
                    he = he+pharmacyMobileName+"\n";

                    if (!TextUtils.isEmpty(vatRegNo))
                    {
                        he = he +"Vat Regi No:"+vatRegNo+"\n\n";
                    }else
                    {
                        he=he+"\n";
                    }

                    if (!TextUtils.isEmpty(anoCustomerName) && !TextUtils.isEmpty(anoCustomerMobile)) {
                        header = header+String.format("%1$-40s %2$-1s", "Bill No:"+memoNo, "Sales By:"+sale_by_adapter.getSelectedItem())+"\n";
                        header = header+String.format("%1$-40s %2$-1s", "Date:"+currentDateandTime, "Pay Mode:"+paymentType)+"\n";
                        header = header+String.format("%1$-40s %2$-1s", "Customer:"+anoCustomerName, "Mobile:"+anoCustomerMobile)+"\n";
                        header = header+"\n";

                    }else
                    {
                        header = header+String.format("%1$-40s %2$-1s", "Bill No:"+memoNo, "Sales By:"+sale_by_adapter.getSelectedItem())+"\n";
                        header = header+String.format("%1$-40s %2$-1s", "Date:"+currentDateandTime, "Pay Mode:"+paymentType)+"\n";

                        header = header+"\n";
                    }

                    header2=String.format("%1$-47s %2$-8s %3$-1s", "Item Name", "Qnt", "Amount")+"\n";
                    header2 = header2 + "----------------------------------------------------------------\n";
                    vio = items;
                    vio = vio + "----------------------------------------------------------------\n";

                    header3 =              "Sub Total:                                      Tk.      "+total+"\n";

                    if (!TextUtils.isEmpty(vatRegNo))
                    {
                        mvdtail =           "(-)                                             Tk.      "+discountAmount+"\n";
                        mvdtail = mvdtail + "Vat:                                            Tk.      "+totalVat + "\n";
                        mvdtail = mvdtail + "----------------------------------------------------------------\n";
                    }else
                    {
                        mvdtail =           "(-)                                             Tk.      "+discountAmount+"\n";
                        mvdtail = mvdtail + "----------------------------------------------------------------\n";
                    }

                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        mvdtail = mvdtail + "Net Payable:                                    Tk.      "+discountTotal + "\n";
                        mvdtail = mvdtail + "Paid:                                           Tk.      "+receivedTk.getText().toString() + "\n";
                        mvdtail = mvdtail + "Due:                                            Tk.      "+dueAmount + "\n";
                        mvdtail = mvdtail + "Return:                                         Tk.      "+returnAmount + "\n\n";
                    }else
                    {
                        if (TextUtils.isEmpty(vatRegNo)) {
                            mvdtail = mvdtail + "Net Payable:                                    Tk.      " + total + "\n";
                            mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                            mvdtail = mvdtail + "Due:                                            Tk.      " + dueAmount + "\n";
                            mvdtail = mvdtail + "Return:                                         Tk.      " + returnAmount + "\n\n";
                        }else
                        {
                            mvdtail = mvdtail + "Net Payable:                                    Tk.      " + grandTotal + "\n";
                            mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                            mvdtail = mvdtail + "Due:                                            Tk.      " + dueAmount + "\n";
                            mvdtail = mvdtail + "Return:                                         Tk.      " + returnAmount + "\n\n";
                        }
                    }

                    site ="** Visit "+siteName+" **\n";
                    copy ="Development By www.terminalbd.com\n\n\n\n\n\n";


                    cmd.append(Common.bold);
                    cmd.append(Common.ESC_ALIGN_CENTER);
                    cmd.append(header5.getBytes());
                    cmd.append(Common.bold_cancel);
                    cmd.append(Common.text_small_size);
                    cmd.append(Common.center);
                    cmd.append(he.getBytes());
                    cmd.append(Common.left);
                    cmd.append(header.getBytes());
                    cmd.append(BILL.getBytes());
                    cmd.append(Common.left);
                    cmd.append(header2.getBytes());
                    cmd.append(vio.getBytes());
                    cmd.append(Common.left);
                    cmd.append(header3.getBytes());
                    cmd.append(Common.left);
                    cmd.append(mvdtail.getBytes());
                    cmd.append(Common.center);
                    cmd.append(header4.getBytes());
                    cmd.append(Common.center);
                    cmd.append(offname.getBytes());
                    cmd.append(Common.center);
                    if (!TextUtils.isEmpty(siteName)) {
                        cmd.append(site.getBytes());
                    }
                    cmd.append(Common.center);
                    cmd.append(copy.getBytes());
                    cmd.append(cmd.getAllCutCmd());

                    rtPrinter.writeMsgAsync(cmd.getAppendCmds());
                    cmd.clear();


    }


    /* Usb printer initialize*/
    public void init() {

        if (PreferenceManager.getPrinterType(SalePaymentActivity.this).equalsIgnoreCase("usb")) {
            //初始化为针打printer
            TerminalApplication.instance.setCurrentCmdType(BaseEnum.CMD_ESC);
            printerFactory = new ThermalPrinterFactory();
            rtPrinter = printerFactory.create();
            rtPrinter.setPrinterInterface(curPrinterInterface);
            PrinterObserverManager.getInstance().add(this);//添加连接状态监听

            initBroadcast();
        }

    }

    private void connectPrinter()
    {
        printerInterfaceArrayList = TerminalApplication.getInstance().getPrinterInterfaceArrayList();
        if (printerInterfaceArrayList.size()>0) {
            PrinterInterface printerInter = printerInterfaceArrayList.get(0);
            rtPrinter.setPrinterInterface(printerInter);//设置连接方式 Connection port settings
            print_status.setTag(BaseEnum.HAS_DEVICE);
            curPrinterInterface = printerInter;
            try {
                rtPrinter.connect(printerInter.getConfigObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
            TerminalApplication.instance.setRtPrinter(rtPrinter);

            //  BaseApplication.getInstance().setRtPrinter(rtPrinter);//设置全局RTPrinter
            if (printerInter.getConnectState() == ConnectStateEnum.Connected) {

                        try {
                            print_status.setTextColor(Color.RED);
                            print_status.setText("Connected");
                            print_status.setTextColor(Color.rgb(97, 170, 74));
                            menu.getItem(0).setIcon(ContextCompat.getDrawable(SalePaymentActivity.this, R.drawable.ic_print_connected));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

            } else {
                Toast.makeText(SalePaymentActivity.this, "Can't connect.Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(SalePaymentActivity.this, "No Printer Added", Toast.LENGTH_SHORT).show();
        }
    }

    private void initBroadcast() {
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub
                String action = intent.getAction();
                if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                    //   ToastUtil.show(context,"接收到断开信息");
                    if (TerminalApplication.getInstance().getCurrentConnectType() == BaseEnum.CON_USB) {
                        doDisConnect();//断开USB连接， Disconnect USB connection.
                    }
                }
                if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                    //    ToastUtil.show(context,"插入USB");
                    Toast.makeText(SalePaymentActivity.this, "Printer attached", Toast.LENGTH_SHORT).show();
                    connectPrinter();
                }
            }

        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(broadcastReceiver, intentFilter);
        isReceiverRegistered=true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isReceiverRegistered) {
            unregisterReceiver(broadcastReceiver);
            isReceiverRegistered=false;
        }
    }

    private void doDisConnect() {

        if (Integer.parseInt(print_status.getTag().toString()) == BaseEnum.NO_DEVICE) {//未选择设备
//            showAlertDialog(getString(R.string.main_discon_click_repeatedly));
            return;
        }

        if (rtPrinter != null && rtPrinter.getPrinterInterface() != null) {
            rtPrinter.disConnect();
            // DisconnectByATDISC();
        }
        print_status.setText("Off");
        print_status.setTag(BaseEnum.NO_DEVICE);
        print_status.setTextColor(getResources().getColor(R.color.grey));
        menu.getItem(0).setIcon(ContextCompat.getDrawable(SalePaymentActivity.this, R.drawable.ic_print_disconnect));
    }

    /*check permission for usb print*/
    private void CheckAllPermission() {
        NO_PERMISSION.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < NEED_PERMISSION.length; i++) {
                if (checkSelfPermission(NEED_PERMISSION[i]) != PackageManager.PERMISSION_GRANTED) {
                    NO_PERMISSION.add(NEED_PERMISSION[i]);
                }
            }
            if (NO_PERMISSION.size() == 0) {
                recordVideo();
            } else {
                requestPermissions(NO_PERMISSION.toArray(new String[NO_PERMISSION.size()]), REQUEST_CAMERA);
            }
        } else {
            recordVideo();
        }

    }

    private void recordVideo() {
        Log.d("MainActivity", "有权限了");
    }

    /**
     * usb设备选择
     */
    private void showUSBDeviceChooseDialog() {
        final UsbDeviceChooseDialog usbDeviceChooseDialog = new UsbDeviceChooseDialog();
        usbDeviceChooseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UsbDevice mUsbDevice = (UsbDevice) parent.getAdapter().getItem(position);
                PendingIntent mPermissionIntent = PendingIntent.getBroadcast(
                        SalePaymentActivity.this,
                        0,
                        new Intent(SalePaymentActivity.this.getApplicationInfo().packageName),
                        0);
                print_status.setText(getString(R.string.adapter_usbdevice) + mUsbDevice.getDeviceId()); //+ (position + 1));
                configObj = new UsbConfigBean(TerminalApplication.getInstance(), mUsbDevice, mPermissionIntent);
                print_status.setTag(BaseEnum.HAS_DEVICE);
                isConfigPrintEnable(configObj);

                usbDeviceChooseDialog.dismiss();
            }
        });
        usbDeviceChooseDialog.show(getFragmentManager(), null);
    }

    private void isConfigPrintEnable(Object configObj) {
        if (isInConnectList(configObj)) {
            doConnect();
        }
    }


    private boolean isInConnectList(Object configObj) {
        boolean isInList = false;
        for (int i = 0; i < printerInterfaceArrayList.size(); i++) {
            PrinterInterface printerInterface = printerInterfaceArrayList.get(i);
            if (configObj.toString().equals(printerInterface.getConfigObject().toString())) {
                if (printerInterface.getConnectState() == ConnectStateEnum.Connected) {
                    isInList = true;
                    break;
                }
            }
        }
        return isInList;
    }

    private void doConnect() {
        if (Integer.parseInt(print_status.getTag().toString()) == BaseEnum.NO_DEVICE) {//未选择设备
            showAlertDialog(getString(R.string.main_pls_choose_device));
            return;
        }
        pb_connect.show();
        switch (checkedConType) {
            case BaseEnum.CON_USB:
                UsbConfigBean usbConfigBean = (UsbConfigBean) configObj;
                connectUSB(usbConfigBean);
                break;
            default:
                pb_connect.dismiss();
                break;
        }

    }

    private void connectUSB(UsbConfigBean usbConfigBean) {
        UsbManager mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        PIFactory piFactory = new UsbFactory();
        PrinterInterface printerInterface = piFactory.create();
        printerInterface.setConfigObject(usbConfigBean);
        rtPrinter.setPrinterInterface(printerInterface);

        if (mUsbManager.hasPermission(usbConfigBean.usbDevice)) {
            try {
                rtPrinter.connect(usbConfigBean);
                TerminalApplication.instance.setRtPrinter(rtPrinter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pb_connect.dismiss();
            mUsbManager.requestPermission(usbConfigBean.usbDevice, usbConfigBean.pendingIntent);
        }

    }

    public void showAlertDialog(final String msg){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SalePaymentActivity.this);
                dialog.setTitle(R.string.dialog_tip);
                dialog.setMessage(msg);
                dialog.setNegativeButton(R.string.dialog_back, null);
                dialog.show();
            }
        });
    }


    @Override
    public void printerObserverCallback(final PrinterInterface printerInterface, final int state) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pb_connect.dismiss();
                switch (state) {
                    case CommonEnum.CONNECT_STATE_SUCCESS:
                        showToast(printerInterface.getConfigObject().toString()+" "+ getString(R.string.main_connected));
                        print_status.setTag(BaseEnum.HAS_DEVICE);
                        curPrinterInterface = printerInterface;//设置为当前连接， set current Printer Interface
                        printerInterfaceArrayList.add(printerInterface);//多连接-添加到已连接列表
                        TerminalApplication.getInstance().setPrinterInterfaceArrayList(printerInterfaceArrayList);
                        rtPrinter.setPrinterInterface(printerInterface);

                        print_status.setTextColor(Color.RED);
                        print_status.setText("Connected");
                        print_status.setTextColor(Color.rgb(97, 170, 74));
                        menu.getItem(0).setIcon(ContextCompat.getDrawable(SalePaymentActivity.this, R.drawable.ic_print_connected));

                        break;
                    case CommonEnum.CONNECT_STATE_INTERRUPTED:
                        if (printerInterface != null && printerInterface.getConfigObject() != null) {
                            showToast(printerInterface.getConfigObject().toString() + " "+getString(R.string.main_disconnect));
                        } else {
                            showToast(getString(R.string.main_disconnect));
                        }

                        curPrinterInterface = null;
                        printerInterfaceArrayList.remove(printerInterface);//多连接-从已连接列表中移除


                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void printerReadMsgCallback(PrinterInterface printerInterface, final byte[] bytes) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PrinterStatusBean StatusBean = PrinterStatusPareseUtils.parsePrinterStatusResult(bytes);
                if (StatusBean.printStatusCmd== PrintStatusCmd.cmd_PrintFinish){
                    if (StatusBean.blPrintSucc){
                        Log.e("mydebug","print ok");
                        showToast("Print finished");
                    } else
                    {
                        showToast(PrinterStatusPareseUtils.getPrinterStatusStr(StatusBean));

                    }


                } else  if (StatusBean.printStatusCmd== PrintStatusCmd.cmd_Normal){
                    showToast("print status："+PrinterStatusPareseUtils.getPrinterStatusStr(StatusBean));

                }
            }
        });

    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent(SalePaymentActivity.this,SalesActivity.class);
        startActivity(returnIntent);
        finish();
    }


    @Override
    public void onClick(String clickname) {

    }
}
