package com.portal.terminalbd.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.CategoryAdapter;
import com.portal.terminalbd.adapter.NewPurchaseProductListAdapter;
import com.portal.terminalbd.adapter.ProductGridAdapter;
import com.portal.terminalbd.adapter.ProductListAdapter;
import com.portal.terminalbd.adapter.PurchaseItemAdapter;
import com.portal.terminalbd.adapter.SalesItemAdapter;
import com.portal.terminalbd.adapter.TokenAdapter;
import com.portal.terminalbd.app.TerminalApplication;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.interfaces.ItemDeleteClickListener;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.CustomerLedeger;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.PurchaseHistory;
import com.portal.terminalbd.model.PurchaseItem;
import com.portal.terminalbd.model.PurchaseItemHistory;
import com.portal.terminalbd.model.SaleItem;
import com.portal.terminalbd.model.SalesHistory;
import com.portal.terminalbd.model.SalesItemHistory;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.model.TokenNo;
import com.portal.terminalbd.model.Vendor;
import com.portal.terminalbd.network.APIClient;
import com.portal.terminalbd.network.PaymentCards;
import com.portal.terminalbd.utils.BaseEnum;
import com.portal.terminalbd.utils.Common;
import com.portal.terminalbd.utils.PrefixEditText;
import com.portal.terminalbd.utils.UsbDeviceChooseDialog;
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

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

public class NewPurchaseActivity extends AppCompatActivity implements SalesItemclickEvent, ItemDeleteClickListener {
    @BindView(R.id.menubtn)
    ImageView menu_btn;
    List<Vendor> vendorList;
    String search_item = "";

    ProgressDialog progressDialog;

    /*@BindView(R.id.recycerviewCartItem)
    RecyclerView recycerviewCartItem;*/

    int paymentReceiveMobileNoID;
    @BindView(R.id.cart_ItemRV)
    RecyclerView cartItemRV;

    @BindView(R.id.paymentitemRv)
    RecyclerView paymentitemRv;

    @BindView(R.id.cartempty)
    TextView cartempty;

    @BindView(R.id.closesearchbr)
    ImageView closesearchbr;

    @BindView(R.id.addNewCustomer)
    ImageView addNewCustomer;

    @BindView(R.id.lineview)
    View lineview;

    List<AnonymousCustomer> customerList;
    List<BankAccount> bankAccounts;


    @BindView(R.id.customer_layout)
    LinearLayout customer_layout;

    @BindView(R.id.payment_receive_spinner)
    Spinner payment_receive_spinner;
    @BindView(R.id.payment_card_bank_spinner)
    Spinner payment_card_bank_spinner;


    @BindView(R.id.sale_layout)
    RelativeLayout sale_layout;


    @BindView(R.id.search_layout)
    LinearLayout search_layout;


    @BindView(R.id.title_layout)
    LinearLayout title_layout;


    TextView sale_total_tk;


    @BindView(R.id.searchbarLayout)
    ConstraintLayout search_bar_Layout;


    @BindView(R.id.searchedittext)
    EditText searchEdittext;

    @BindView(R.id.editTextSearch)
    EditText editTextSearch;

    @BindView(R.id.cartlayout)
    ConstraintLayout cartlayout;

    @BindView(R.id.clearbtn)
    ImageView clearbtn;

    @BindView(R.id.productqty)
    TextView productqty;

    @BindView(R.id.emptytext)
    TextView emptytext;

    @BindView(R.id.recyclerViewSearchSuggestion)
    RecyclerView recyclerViewSearchSuggestion;

    @BindView(R.id.searchSuggestionrecyclerView)
    RecyclerView searchSuggestionrecyclerView;


    @BindView(R.id.sale_return_tk)
    EditText sale_return_tk;

    List<StockItem> stockItemList;
    Realm realm;

    RealmResults<PurchaseItem> salesItemList;

    PurchaseItemAdapter adapter;

    SalesItemAdapter salesItemAdapter;

    NewPurchaseProductListAdapter adapterSuggestion;

    ProductListAdapter productListAdapter;

    ProductGridAdapter productGridAdapter;


    StockItem stockItem;

    int discountAmount = 0;
    int discountTotal = 0;
    double returnAmount;
    double dueAmount;
    int memoNo;

    int totalVat;
    int totalPP;
    int totalProfit;
    int grandTotal;

    int total;
    int total_item = 0;
    Setup setup;

    boolean showcartitem = false;
    boolean customerlistshow = false;
    int paymentCardType;
    int paymentBankAccount;
    String paymentCardNo;

    String vatRegNo = "";
    String vatPercentage = "";


    double discountpercent = 0;
    double paymentamount = 0;
    double dueamount = 0;
    @BindView(R.id.due_tk)
    TextView percent;

    @BindView(R.id.sale_total_tk)
    TextView sale_grand_total_tk;
    @BindView(R.id.receivedTk)
    EditText receivedTk;


    RealmResults<Category> categoryRealmResults;
    private List<Category> categoryList;
    List<MobileAccount> mobileAccounts;

    private CategoryAdapter categoryAdapter;

    RealmResults<TokenNo> tokenNos;
    private String tokenNoss = "";
    private List<TokenNo> tokenNoList;

    RealmResults<PaymentCards> paymentCards;
    List<PaymentCards> paymentCardsList;

    private TokenAdapter tokenAdapter;

    String sales_mode;

    BottomSheetBehavior bottomSheetBehavior;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.list_show_btn)
    FloatingActionButton list_show_btn;

    @BindView(R.id.bottom_sheet)
    LinearLayout bottom_sheet;

    @BindView(R.id.linearLayout4)
    LinearLayout paymentlayout;


    @BindView(R.id.searchback)
    ImageView search_Back;


    @BindView(R.id.cart_layout)
    RelativeLayout cartlayoutbg;


    @BindView(R.id.customerSpinner)
    Spinner customerSpinner;

    @BindView(R.id.mobile_bank_layout)
    LinearLayout mobileBankLayout;

    @BindView(R.id.mobile_payment_form)
    LinearLayout mobilePaymentForm;

    boolean mobilebanklayoutshow = false;

    @BindView(R.id.bank_paynement_layout)
    LinearLayout bankPaynementLayout;


    @BindView(R.id.pos_print_btn)
    Button posPrintBtn;

    @BindView(R.id.bank_payment_form)
    LinearLayout bankPaymentForm;
    boolean banklayoutshow = false;

    @BindView(R.id.cash_payment_layout)
    LinearLayout cashPaymentLayout;
    boolean cashlayoutshow = false;

    ArrayList<String> printmessageList = new ArrayList<>();
    ArrayList<String> customerListsingle = new ArrayList<>();

    int page = 1;
    int limit = 50;
    int cur_limit = 50;
    private boolean isLoading = false;
    private int currentItems, totalItems, scrollOutitems = 0;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;


    private RealmResults<StockItem> stockItemRealmResults;
    private List<StockItem> searchStockItemList;


    RealmResults<SystemUser> users;

    /*
     **************************Printer connect code***********************************
     */
    String systemUsers;
    int systemUsersId;

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

    int serial = 0;

    String itemPrintName = "";

    private Object configObj;


    boolean isBluetoothOff = false;

    private List<Integer> tokenIds;

    private List<SalesHistory> salesHistoryList;
    private List<SalesItemHistory> salesItemHistoryList;

    int totalSize;
    int saleItemTotalSize;

    String currentDateandTime = "";
    String createdDate;
    int paymentMobileID;
    String paymentMobileNo = "";
    String paymentTransactionId = "";
    String paymentReceiveMobileNo;

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
    private ArrayList<PrinterInterface> printerInterfaceArrayList = new ArrayList<>();
    private PrinterInterface curPrinterInterface = null;
    private BroadcastReceiver broadcastReceiver;//USB Attach-Deattached Receiver
    private int iprintTimes = 0;
    private boolean isReceiverRegistered = false;
    String items = "";

    String pharmacyName = "";
    String locationName = "";
    String addressName = "";
    String pharmacyMobileName = "";
    String siteName = "";

    String print_status = "on";

    String anoCustomerName = "";
    String anoCustomerMobile = "";
    int anoCustomerId;
    String paymentType = "cash";
    String printMessage = "";
    String discountType = "";
    RealmChangeListener<RealmResults<SaleItem>> realmListener = new RealmChangeListener<RealmResults<SaleItem>>() {
        @Override
        public void onChange(RealmResults<SaleItem> saleItems) {

            checkSaleLayoutVisibility();
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String language = PreferenceManager.getLanguage(NewPurchaseActivity.this);
        setLocale(language);
        loadLocale();
        setContentView(R.layout.activity_new_purchase);


        ButterKnife.bind(this);


        initpayment();

        //        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy h:mm a");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy h:mm a");
        currentDateandTime = sdf.format(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createdDate = format.format(new Date());

        memoNo = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

      /*  setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, null));

        toolbar_title.setText("Add Sales");*/

        realm = Realm.getDefaultInstance();

        progressDialog = new ProgressDialog(NewPurchaseActivity.this);
        progressDialog.setTitle("Syncing");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        vendorList = new ArrayList<>();
        customerList = new ArrayList<>();


        getCustomerSpinner();

        //  calculatePrice();


        searchStockItemList = new ArrayList<>();
        searchStockItemList.clear();
        stockItemRealmResults = realm.where(StockItem.class).findAll();
        searchStockItemList.addAll(realm.copyFromRealm(stockItemRealmResults));


        adapterSuggestion = new NewPurchaseProductListAdapter(this, stockItemList, this);

        searchSuggestionrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchSuggestionrecyclerView.setAdapter(adapterSuggestion);
        adapterSuggestion.notifyDataSetChanged();


        stockItemList = new ArrayList<>();
        salesItemList = realm.where(PurchaseItem.class).findAll();

        salesItemList.addChangeListener(new RealmChangeListener<RealmResults<PurchaseItem>>() {
            @Override
            public void onChange(RealmResults<PurchaseItem> saleItems) {
                checkSaleLayoutVisibility();
                adapter.notifyDataSetChanged();
            }
        });


        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        stockItem = new StockItem();


        /*
        category setup
         */
        categoryList = new ArrayList<>();
        categoryRealmResults = realm.where(Category.class).findAll();

        categoryList.addAll(realm.copyFromRealm(categoryRealmResults));


        /*
         ********************************Printer connect code here***********************************
         */


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        /*
        token no data getting
         */
        tokenNoList = new ArrayList<>();
        tokenNos = realm.where(TokenNo.class).findAll();
        tokenNoList.addAll(realm.copyFromRealm(tokenNos));


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


        closesearchbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cartlayoutbg.setVisibility(View.VISIBLE);
                editTextSearch.setText("");
                editTextSearch.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextSearch, InputMethodManager.SHOW_IMPLICIT);

            }
        });



          /*
        mobile accounts adapter
         */
        RealmResults<MobileAccount> mobile = realm.where(MobileAccount.class).findAll();

        mobileAccounts = realm.copyFromRealm(mobile);

        String[] accounts = new String[mobileAccounts.size()];
        for (int i = 0; i < mobileAccounts.size(); i++) {
            accounts[i] = mobileAccounts.get(i).getName();

        }

        ArrayAdapter<String> mobileAccountAdapter =
                new ArrayAdapter<>(NewPurchaseActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , accounts);

        payment_receive_spinner.setAdapter(mobileAccountAdapter);


        /*
        payment bank adapter
         */

        RealmResults<BankAccount> banks = realm.where(BankAccount.class).findAll();

        bankAccounts = realm.copyFromRealm(banks);

        String[] bank = new String[bankAccounts.size()];
        for (int i = 0; i < bankAccounts.size(); i++) {
            bank[i] = bankAccounts.get(i).getName();

        }

        ArrayAdapter<String> bankAccountAdapter =
                new ArrayAdapter<>(NewPurchaseActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);


         /*
        sale by adapter
         */

        users = realm.where(SystemUser.class).findAll();

        List<SystemUser> systemUserList = realm.copyFromRealm(users);

        systemUsers = systemUserList.get(0).getUsername();
        systemUsersId = systemUserList.get(0).getUserId();




 /*
        Token no
         */

        tokenIds = new ArrayList<>();

        if (PreferenceManager.getModule(NewPurchaseActivity.this).equalsIgnoreCase("restaurant")) {
            if (getIntent().getExtras() != null) {
                Type type = new TypeToken<List<TokenNo>>() {
                }.getType();
                tokenNoList = new Gson().fromJson(getIntent().getExtras().getString("TOKEN"), type);
            }

            if (tokenNoList.size() > 0) {
                for (TokenNo token :
                        tokenNoList) {
                    tokenIds.add(token.getTokenId());
                }

                String str = tokenIds.toString();
                tokenNoss = str.replaceAll("[\\[\\]]", "");

                Log.i("TOKEN", tokenNoss);
            }
        }


        search_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_bar_Layout.setVisibility(View.GONE);
                clearbtn.setVisibility(View.GONE);
                hideSoftKeyboard(view);
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEdittext.setText("");
            }
        });


        searchEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                /*if (searchEdittext.length()>1){


                }*/
                final List<StockItem> filteredModelList = filter(stockItemList, "" + s);
                adapterSuggestion.setFilter(filteredModelList);

                //product_filter(s.toString());


            }
        });


        editTextSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                sale_layout.setVisibility(View.GONE);
                cartlayout.setVisibility(View.GONE);
                editTextSearch.setFocusableInTouchMode(true);
                editTextSearch.setFocusable(true);
                editTextSearch.requestFocus();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
                return false;
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {

                    cartlayout.setVisibility(View.GONE);
                    cartlayoutbg.setVisibility(View.GONE);
                    closesearchbr.setVisibility(View.VISIBLE);
                    sale_layout.setVisibility(View.GONE);
                    searchSuggestionrecyclerView.setVisibility(View.VISIBLE);

                    final List<StockItem> filteredModelList = filter(stockItemList, "" + s.toString());
                    adapterSuggestion.setFilter(filteredModelList);

                } else {
                    cartlayout.setVisibility(View.VISIBLE);
                    sale_layout.setVisibility(View.VISIBLE);
                    closesearchbr.setVisibility(View.GONE);
                    searchSuggestionrecyclerView.setVisibility(View.GONE);
                }
            }
        });


        productqty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                cartlayoutbg.setVisibility(View.VISIBLE);
                cartlayout.setVisibility(View.VISIBLE);
                sale_layout.setVisibility(View.VISIBLE);

                searchSuggestionrecyclerView.setVisibility(View.GONE);
                sale_return_tk.setText("" + total);
                editTextSearch.setText("");
                hideSoftKeyboard(view);
            }
        });


        adapter = new PurchaseItemAdapter(NewPurchaseActivity.this, salesItemList,this);
        cartItemRV.setLayoutManager(new LinearLayoutManager(NewPurchaseActivity.this, LinearLayoutManager.VERTICAL, false));
        cartItemRV.addItemDecoration(new DividerItemDecoration(NewPurchaseActivity.this, DividerItemDecoration.VERTICAL));
        cartItemRV.setAdapter(adapter);


        adapter = new PurchaseItemAdapter(NewPurchaseActivity.this, salesItemList,this);
        paymentitemRv.setLayoutManager(new LinearLayoutManager(NewPurchaseActivity.this, LinearLayoutManager.VERTICAL, false));
        paymentitemRv.addItemDecoration(new DividerItemDecoration(NewPurchaseActivity.this, DividerItemDecoration.VERTICAL));
        paymentitemRv.setAdapter(adapter);


        if (PreferenceManager.getModule(NewPurchaseActivity.this).equalsIgnoreCase("restaurant")) {
             /*
        token no adapter
         */


        } else {

        }

        /*
        getting sales mode
         */
        sales_mode = PreferenceManager.getSalesMode(NewPurchaseActivity.this);


        /*
        check sales layout
         */
        if (sales_mode.equalsIgnoreCase("Search")) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    stockItemList.clear();
                    RealmResults<StockItem> oldStockItem = realm.where(StockItem.class).findAll();
                    stockItemList.addAll(realm.copyFromRealm(oldStockItem));

                }
            });


            search_layout.setVisibility(View.VISIBLE);
            list_show_btn.hide();

        } else if (sales_mode.equalsIgnoreCase("List")) {

            loadData(0, 50);


            search_layout.setVisibility(View.GONE);


        } else if (sales_mode.equalsIgnoreCase("Grid")) {
            loadData(0, 50);


            search_layout.setVisibility(View.GONE);
            title_layout.setVisibility(View.GONE);


        }

        checkSaleLayoutVisibility();

        sale_grand_total_tk.setText("৳ " + total);

    }


    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings",MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocale(language);
    }


    private void initpayment() {


        addNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTermsDialog();
            }
        });


        cashPaymentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankPaymentForm.setVisibility(View.GONE);
                mobilePaymentForm.setVisibility(View.GONE);
                mobilebanklayoutshow = false;
                banklayoutshow = false;
                cashPaymentLayout.setBackgroundResource(R.drawable.red_border_style);
                bankPaynementLayout.setBackgroundResource(R.drawable.white_border);
                mobileBankLayout.setBackgroundResource(R.drawable.white_border);
                paymentType = "Cash";
            }
        });
        mobileBankLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentType = "Mobile";
                if (mobilebanklayoutshow == false) {
                    mobileBankLayout.setBackgroundResource(R.drawable.red_border_style);
                    bankPaynementLayout.setBackgroundResource(R.drawable.white_border);
                    cashPaymentLayout.setBackgroundResource(R.drawable.white_border);
                    mobilePaymentForm.setVisibility(View.VISIBLE);
                    bankPaymentForm.setVisibility(View.GONE);
                    mobilebanklayoutshow = true;
                    banklayoutshow = false;
                } else {
                    mobilePaymentForm.setVisibility(View.GONE);

                    mobilebanklayoutshow = false;
                }
            }
        });

        bankPaynementLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentType = "Bank";
                if (banklayoutshow == false) {
                    bankPaymentForm.setVisibility(View.VISIBLE);
                    mobilePaymentForm.setVisibility(View.GONE);
                    bankPaynementLayout.setBackgroundResource(R.drawable.red_border_style);
                    mobileBankLayout.setBackgroundResource(R.drawable.white_border);
                    cashPaymentLayout.setBackgroundResource(R.drawable.white_border);
                    banklayoutshow = true;
                    mobilebanklayoutshow = false;
                } else {
                    bankPaymentForm.setVisibility(View.GONE);
                    banklayoutshow = false;
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


                    returnAmount = (Double.parseDouble(receivedTk.getText().toString()));
                    paymentamount = (Double.parseDouble(receivedTk.getText().toString()));


                    double totalpayment = paymentamount + dueamount;

                    double discounttaka = total - totalpayment;


                    discountpercent = (discounttaka * 100) / total;
                    String formatedata = String.format("%.2f", discountpercent);
                    percent.setText(formatedata + "%");


                }
                else if(TextUtils.isEmpty(receivedTk.getText().toString())){
                    paymentamount = 0;
                    double totalpayment = paymentamount + dueamount;

                    double discounttaka = total - totalpayment;


                    discountpercent = (discounttaka * 100) / total;
                    String formatedata = String.format("%.2f", discountpercent);
                    percent.setText(formatedata + "%");
                }
                else if (TextUtils.isEmpty(receivedTk.getText().toString()) && TextUtils.isEmpty(sale_return_tk.getText().toString())){

                    percent.setText("0%");

                }

            }
        });

        sale_return_tk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                return false;
            }
        });

        sale_return_tk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!TextUtils.isEmpty(sale_return_tk.getText().toString())) {


                    dueAmount = (Double.parseDouble(sale_return_tk.getText().toString()));
                    dueamount = (Double.parseDouble(sale_return_tk.getText().toString()));

                    double totalpayment = paymentamount + dueamount;

                    double discounttaka = total - totalpayment;


                    discountpercent = (discounttaka * 100) / total;
                    String formatedata = String.format("%.2f", discountpercent);
                    percent.setText(formatedata + "%");


                }else if(TextUtils.isEmpty(sale_return_tk.getText().toString())){
                    dueamount = 0;
                    double totalpayment = paymentamount + dueamount;

                    double discounttaka = total - totalpayment;


                    discountpercent = (discounttaka * 100) / total;
                    String formatedata = String.format("%.2f", discountpercent);
                    percent.setText(formatedata + "%");
                }
                else if (TextUtils.isEmpty(receivedTk.getText().toString()) && TextUtils.isEmpty(sale_return_tk.getText().toString())){

                    percent.setText("0%");
                    dueamount = 0;



                }

            }
        });


    }


    private void getCustomerSpinner() {



        /*
       vendor adapter
         */
        RealmResults<Vendor> vendors = realm.where(Vendor.class).findAll();

        vendorList.addAll(realm.copyFromRealm(vendors));

        String[] vendorNames = new String[vendorList.size()];
        for (int i = 0; i < vendorList.size(); i++) {
            vendorNames[i] = vendorList.get(i).getName();

        }

        ArrayAdapter<String> vendorAdapter =
                new ArrayAdapter<>(NewPurchaseActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , vendorNames);

        customerSpinner.setAdapter(vendorAdapter);





/*
        customerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                //  String valu = adapterView.getItemAtPosition(i).toString();
                // category_id = categoryArrayList.get(i).getName();

                if (pos == 0) {

                } else {
                    try {
                        anoCustomerName = customerList.get(pos).getName();
                        anoCustomerMobile = customerList.get(pos).getMobile();
                    } catch (Exception e) {

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

    }


    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplication().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        final MenuItem item = menu.findItem(R.id.ic_search);
        final MenuItem item2 = menu.findItem(R.id.ic_addicons);
        final MenuItem item3 = menu.findItem(R.id.ic_syncicons);

        item.setVisible(false);
        // item2.setVisible(false);


        item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               /* LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

                TextView toastText = layout.findViewById(R.id.toasttext);
                toastText.setText("Sync Stock Item Successfully");

                final Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                syncStock();
                toast.show();*/


                Toast.makeText(NewPurchaseActivity.this, "sync", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }


    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        // MenuInflater inflater = popup.getMenuInflater();
        popup.inflate(R.menu.popupmenu);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ic_customeradd:
                        showTermsDialog();
                        break;
                    case R.id.ic_addicons:
                        Toast.makeText(NewPurchaseActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ic_syncicons:
                        Toast.makeText(NewPurchaseActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ic_sales:
                        Toast.makeText(NewPurchaseActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                }
                //Toast.makeText(NewPurchaseActivity.this, ""+menuItem, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        popup.show();

    }

    private void showTermsDialog() {


        LayoutInflater layoutInflater = LayoutInflater.from(NewPurchaseActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_create_vendor, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(NewPurchaseActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView close = view.findViewById(R.id.close);
        ImageView resetBtn = view.findViewById(R.id.imageView12);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        ConstraintLayout back = view.findViewById(R.id.btn_back);
        EditText name = view.findViewById(R.id.nameid);
        EditText mobile = view.findViewById(R.id.mobileid);
        EditText email = view.findViewById(R.id.emailid);
        EditText obalance = view.findViewById(R.id.openingid);
        EditText address = view.findViewById(R.id.descriptionid);
        String userid = "" + PreferenceManager.getUserId(NewPurchaseActivity.this);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

                TextView toastText = layout.findViewById(R.id.toasttext);
                toastText.setText("Create Customer Successfully");

                final Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();*/
                progressDialog.show();
                createCustomer(name.getText().toString(), mobile.getText().toString(), address.getText().toString(), email.getText().toString(), obalance.getText().toString(), userid);

 /*   realm.beginTransaction();
                                AnonymousCustomer anonymousCustomer = new AnonymousCustomer();
                                anonymousCustomer.setName("" + name.getText().toString());
                                anonymousCustomer.setMobile("" + mobile.getText().toString());


                                realm.insertOrUpdate(anonymousCustomer);
                                realm.commitTransaction();*/


                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }

    private void createCustomer(String name, String mobile, String address, String email, String openingbalance, String userid) {

        progressDialog.setMessage("Syncing System Customer....");

        compositeDisposable.addAll(APIClient.getInstance().createCustomer(PreferenceManager.getSecretKey(NewPurchaseActivity.this),
                name, mobile, address, email, openingbalance, userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<AnonymousCustomer>>() {
                    @Override
                    public void onNext(final Response<AnonymousCustomer> response) {

                        if (response.body().getStatus().equals("success")) {
                            syncAnonymousCustomer();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }


    public void syncAnonymousCustomer() {
        realm.beginTransaction();
        realm.delete(AnonymousCustomer.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getAnonymousCustomer(PreferenceManager.getSecretKey(getApplicationContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<AnonymousCustomer>>>() {
                    @Override
                    public void onNext(final Response<List<AnonymousCustomer>> response) {


                        if (response.isSuccessful()) {


                            if (response.body() != null && response.body().size() > 0) {
                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0; i < response.body().size(); i++) {


                                    AnonymousCustomer anonymousCustomer = realm.createObject(AnonymousCustomer.class);
                                    ;
                                    anonymousCustomer.setCustomerId(response.body().get(i).getCustomerId());
                                    anonymousCustomer.setGlobalId(response.body().get(i).getGlobalId());
                                    anonymousCustomer.setMobile(response.body().get(i).getMobile());
                                    anonymousCustomer.setName(response.body().get(i).getName());
                                    anonymousCustomer.setAddress(response.body().get(i).getAddress());
                                    anonymousCustomer.setOpeningBalance(response.body().get(i).getOpeningBalance());
                                    anonymousCustomer.setEmail(response.body().get(i).getEmail());


                                }

                                realm.commitTransaction();
                            }

                            getCustomerSpinner();
                            progressDialog.hide();

                        } else {

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


    private void syncStock() {


        realm.beginTransaction();
        realm.delete(StockItem.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getStockItem(PreferenceManager.getSecretKey(getApplicationContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<StockItem>>>() {
                    @Override
                    public void onNext(final Response<List<StockItem>> listResponse) {

                        // progressDialog.dismiss();

                        if (listResponse.isSuccessful()) {

                            if (listResponse.body() != null && listResponse.body().size() > 0) {

                                realm.beginTransaction();

                                for (int i = 0; i < listResponse.body().size(); i++) {

                                    StockItem stockItem = realm.createObject(StockItem.class);
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

                                        stockItem.setImage(null);
                                    } else {

                                    }

                                }

                                realm.commitTransaction();

                            }


                            /*stockItemList = new ArrayList<>();

                            stockItems = realm.where(StockItem.class).findAll();

                            stockItemList.addAll(realm.copyFromRealm(stockItems));


                            adapter = new StockItemAdapter(getApplicationContext(), stockItemList);

                            stock_item_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                            //  stock_item_rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                            stock_item_rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();*/

                        } else {


                           /* stockItemList = new ArrayList<>();

                            stockItems = realm.where(StockItem.class).findAll();

                            stockItemList.addAll(realm.copyFromRealm(stockItems));


                            adapter = new StockItemAdapter(getApplicationContext(), stockItemList);

                            stock_item_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                            //  stock_item_rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                            stock_item_rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();*/
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));


    }

    public void checkSaleLayoutVisibility() {
        if (salesItemList.size() > 0) {


            if (sales_mode.equalsIgnoreCase("List")
                    || sales_mode.equalsIgnoreCase("Grid")) {
//                list_show_btn.setVisibility(View.VISIBLE);
            }

            // sale_layout.setVisibility(View.VISIBLE);
            title_layout.setVisibility(View.VISIBLE);
            lineview.setVisibility(View.VISIBLE);
            cartempty.setVisibility(View.GONE);


            calculatePrice();
        } else {

//            list_show_btn.setVisibility(View.GONE);
            //  sale_layout.setVisibility(View.GONE);
            title_layout.setVisibility(View.GONE);
            paymentlayout.setVisibility(View.GONE);
            lineview.setVisibility(View.GONE);
            productqty.setText("0");
            cartempty.setVisibility(View.VISIBLE);

        }
    }


    @OnClick(R.id.sale_layout)
    void sale_layout() {

        cartlayoutbg.setVisibility(View.VISIBLE);
        calculatePrices();

        cartlayoutbg.setVisibility(View.VISIBLE);


    }


    @OnClick(R.id.save_btn)
    void save_btn() {

        if (!TextUtils.isEmpty(receivedTk.getText().toString()) || !TextUtils.isEmpty(sale_return_tk.getText().toString())) {

            new AlertDialog.Builder(NewPurchaseActivity.this).setMessage("Are you sure want to save?")
                    .setTitle("Alert!!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (PreferenceManager.getOnlineOfflineMode(NewPurchaseActivity.this).equalsIgnoreCase("Online")) {
                                if (Common.isNetworkAvailable(NewPurchaseActivity.this)) {

                                    savePurchaseAndRemove();

                                } else {

                                    Toast.makeText(NewPurchaseActivity.this, "You are in online mode.Please Check your network connection", Toast.LENGTH_SHORT).show();
                                }
                            } else if (PreferenceManager.getOnlineOfflineMode(NewPurchaseActivity.this).equalsIgnoreCase("Offline")) {
                                savePurchaseAndRemove();
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

        } else {
            Toast.makeText(this, "Input at lest one Field", Toast.LENGTH_SHORT).show();

        }

    }


    @OnClick(R.id.pos_print_btn)
    void onPurchase() {

        if (!TextUtils.isEmpty(receivedTk.getText().toString()) || !TextUtils.isEmpty(sale_return_tk.getText().toString())) {

            new AlertDialog.Builder(NewPurchaseActivity.this).setMessage("Are you sure want to save?")
                    .setTitle("Alert!!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (PreferenceManager.getOnlineOfflineMode(NewPurchaseActivity.this).equalsIgnoreCase("Online")) {
                                if (Common.isNetworkAvailable(NewPurchaseActivity.this)) {

                                    savePurchaseAndRemove();

                                } else {

                                    Toast.makeText(NewPurchaseActivity.this, "You are in online mode.Please Check your network connection", Toast.LENGTH_SHORT).show();
                                }
                            } else if (PreferenceManager.getOnlineOfflineMode(NewPurchaseActivity.this).equalsIgnoreCase("Offline")) {
                                savePurchaseAndRemove();
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

        } else {
            Toast.makeText(this, "Input at lest one Field", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.backbtn)
    void backbtn() {
        onBackPressed();
    }


    public void calculatePrices() {
        setup = realm.where(Setup.class).findFirst();
        total = 0;
        totalProfit = 0;
        totalPP = 0;

        for (PurchaseItem item : salesItemList) {
            total += Math.round(Double.valueOf(item.getPurchaseMrpPrice()) * Double.valueOf(item.getPurchaseQuantity()));
            totalPP += Math.round(Double.valueOf(item.getPurchasePpPrice()) * Double.valueOf(item.getPurchaseQuantity()));

        }

        if (!TextUtils.isEmpty(vatRegNo)) {


            totalVat = (int) Math.round((total * Double.valueOf(setup.getVatPercentage()) / 100));
            grandTotal = total + totalVat;

            // sale_vat_tk.setText(totalVat + "");

            totalProfit = total - totalPP;
            total_item = salesItemList.size();

           /* sale_grand_total_tk.setText("৳ " + String.valueOf(grandTotal) + " (" + total_item + ")");
            sale_total_profit.setText("৳ " + String.valueOf(totalProfit));
            sale_total_tk.setText("৳ " + String.valueOf(total));
            sale_discount_tk.setText("");*/
          //  sale_return_tk.setText("৳ " + grandTotal);
            //  floating_total_tk.setText("৳ " + grandTotal);


        } else {
            totalProfit = total - totalPP;
            total_item = salesItemList.size();

           /* sale_grand_total_tk.setText("৳ " + String.valueOf(grandTotal) + " (" + total_item + ")");
            sale_total_profit.setText("৳ " + String.valueOf(totalProfit));
            sale_total_tk.setText("৳ " + String.valueOf(total));
            sale_discount_tk.setText("");*/
            sale_return_tk.setText("৳ " + total);
            /*  floating_total_tk.setText("৳ " + total);*/
        }


    }

    public void calculatePrice() {
        total = 0;
        totalProfit = 0;
        totalPP = 0;

        for (PurchaseItem item : salesItemList) {
            total += Math.round(Double.valueOf(item.getPurchaseMrpPrice()) * Double.valueOf(item.getPurchaseQuantity()));
            totalPP += Math.round(Double.valueOf(item.getPurchasePpPrice()) * Double.valueOf(item.getPurchaseQuantity()));
        }

        total_item = salesItemList.size();

        totalProfit = total - totalPP;

        if (total_item > 0) {
            emptytext.setVisibility(View.GONE);
            productqty.setVisibility(View.VISIBLE);
            paymentlayout.setVisibility(View.VISIBLE);
            paymentitemRv.setVisibility(View.VISIBLE);
            title_layout.setVisibility(View.VISIBLE);
        } else {
            cartlayoutbg.setVisibility(View.VISIBLE);
            emptytext.setVisibility(View.VISIBLE);
            productqty.setVisibility(View.VISIBLE);
            paymentitemRv.setVisibility(View.GONE);
            title_layout.setVisibility(View.GONE);
            paymentlayout.setVisibility(View.GONE);
        }
        productqty.setText(String.valueOf(total_item));
        sale_grand_total_tk.setText("৳ " + total);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
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


    private List<StockItem> filter(List<StockItem> stocks, String query) {
        query = query.toLowerCase();
        final List<StockItem> filteredModelList = new ArrayList<>();
        for (StockItem stock : stocks) {
            final String text = stock.getName().toLowerCase() + " " + stock.getCategoryName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(stock);
            }
        }
        return filteredModelList;
    }


    private void loadData(int start, int limit) {
        RealmResults<StockItem> oldStockItem = realm.where(StockItem.class).between("count", start, limit).findAll();
        stockItemList.addAll(realm.copyFromRealm(oldStockItem));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 101:
                if (resultCode == RESULT_CANCELED) {
                    Intent intent = getIntent();
                    startActivity(intent);
                    finish();
                }
                break;

            case 102:
                if (resultCode == RESULT_OK) {
                    String code = data.getStringExtra("code");
                    StockItem item = realm.where(StockItem.class).equalTo("unitId", code).findFirst();

                    if (item != null) {
                        realm.beginTransaction();
                        SaleItem saleItem = realm.createObject(SaleItem.class, UUID.randomUUID().toString());
                        saleItem.setSaleStockId(String.valueOf(item.getItemId()));
                        saleItem.setSaleItemName(item.getName());
                        saleItem.setSalePpPrice(String.valueOf(item.getPurchasePrice()));
                        saleItem.setSaleMrpPrice(String.valueOf(item.getSalesPrice()));
                        saleItem.setSaleQuantity(String.valueOf(1));
                        int sub_total = (int) Math.round(item.getSalesPrice() * Double.valueOf(1));
                        saleItem.setSaleSubTotal(String.valueOf(sub_total));
                        realm.commitTransaction();

                        sale_layout.setVisibility(View.VISIBLE);

                        calculatePrice();

                        if (sales_mode.equalsIgnoreCase("Search")) {
                            adapter.notifyDataSetChanged();
                        } else if (sales_mode.equalsIgnoreCase("List")) {
                            productListAdapter.notifyDataSetChanged();
                        } else if (sales_mode.equalsIgnoreCase("Grid")) {
                            productGridAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(this, "Product not found !", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }


    public void savePurchaseAndRemove() {

        if (TextUtils.isEmpty(receivedTk.getText().toString()) && TextUtils.isEmpty(sale_return_tk.getText().toString())) {
            receivedTk.setError("Required");
            receivedTk.requestFocus();
            return;
        }


        /*
        save saled details
         */
        realm.beginTransaction();


        PurchaseHistory purchaseHistory = realm.createObject(PurchaseHistory.class, UUID.randomUUID().toString());
        if (paymentType.equals("cash")) {

            purchaseHistory.setTransactionMethod(paymentType);

        } else if (paymentType.equals("mobile")) {

            int selecPos = payment_receive_spinner.getSelectedItemPosition();
            paymentReceiveMobileNoID = mobileAccounts.get(selecPos).getItemId();
            purchaseHistory.setTransactionMethod(paymentType);
            purchaseHistory.setMobileBankAccount(paymentReceiveMobileNoID);

        } else if (paymentType.equals("bank")) {

            int pos = payment_card_bank_spinner.getSelectedItemPosition();
            paymentBankAccount = bankAccounts.get(pos).getItemId();
            purchaseHistory.setBankAccount(paymentBankAccount);
        }

        purchaseHistory.setCreated(createdDate);


        purchaseHistory.setDiscount(0);
        purchaseHistory.setDiscountCulculation(0);
        purchaseHistory.setDiscountType("");
        purchaseHistory.setTotal(total);


        purchaseHistory.setDue(Integer.parseInt(sale_return_tk.getText().toString()));
        purchaseHistory.setPayment(Integer.parseInt(receivedTk.getText().toString()));
        purchaseHistory.setInvoiceId(String.valueOf(memoNo));

        int position = customerSpinner.getSelectedItemPosition();
        int vendorId = vendorList.get(position).getVendorId();
        purchaseHistory.setVendorId(vendorId);
        purchaseHistory.setSubTotal(total);
        purchaseHistory.setVat(0);
        purchaseHistory.setReceivedBy(Integer.parseInt(PreferenceManager.getUserId(NewPurchaseActivity.this)));

        realm.commitTransaction();

        realm.beginTransaction();

        for (int i = 0; i < salesItemList.size(); i++) {

            PurchaseItemHistory salesItemHistory =
                    realm.createObject(PurchaseItemHistory.class, UUID.randomUUID().toString());
            salesItemHistory.setQuantity(Integer.parseInt(salesItemList.get(i).getPurchaseQuantity()));
            salesItemHistory.setPurchaseId(memoNo);
            salesItemHistory.setStockId(Integer.parseInt(salesItemList.get(i).getPurchaseStockId()));
            try {
                salesItemHistory.setSubTotal(Integer.parseInt("" + salesItemList.get(i).getPurchaseSubTotal()));
            } catch (Exception e) {

            }
            salesItemHistory.setSalesPrice(Double.valueOf(salesItemList.get(i).getPurchaseMrpPrice()));
            salesItemHistory.setPurchasePrice(Double.valueOf(salesItemList.get(i).getPurchasePpPrice()));

        }

        realm.commitTransaction();

        realm.beginTransaction();
        realm.delete(PurchaseItem.class);
        realm.commitTransaction();

        Toast.makeText(this, "Save Successfull", Toast.LENGTH_SHORT).show();
    }


    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Boolean response) {


        if (response) {

            editTextSearch.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editTextSearch, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onClickNotify(Boolean response) {

    }

    @Override
    public void onClick(String clickname) {
        if (clickname.equals("click")){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
    }
}