package com.portal.terminalbd.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
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
import com.portal.terminalbd.adapter.ProductGridAdapter;
import com.portal.terminalbd.adapter.ProductListAdapter;
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

public class SearchSalesActivity extends AppCompatActivity implements SalesItemclickEvent, ItemDeleteClickListener, Runnable, PrinterObserver {


    @BindView(R.id.menubtn)
    ImageView menu_btn;

    String search_item = "";

    ProgressDialog progressDialog;

    /*@BindView(R.id.recycerviewCartItem)
    RecyclerView recycerviewCartItem;*/


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

    @BindView(R.id.printerid)
    TextView print_status;

    List<AnonymousCustomer> customerList;
    List<BankAccount> bankAccounts;


    @BindView(R.id.customer_layout)
    LinearLayout customer_layout;

    @BindView(R.id.payment_receive_spinner)
    Spinner payment_receive_spinner;
    @BindView(R.id.payment_card_bank_spinner)
    Spinner payment_card_bank_spinner;
    @BindView(R.id.payment_card_spinner)
    Spinner payment_card_spinner;


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

    @BindView(R.id.payment_mobile_no)
    EditText payment_mobile_no;
    @BindView(R.id.payment_card_no)
    EditText payment_card_no;

    @BindView(R.id.payment_transaction_id)
    EditText payment_transaction_id;

    @BindView(R.id.sale_return_tk)
    TextView sale_return_tk;

    List<StockItem> stockItemList;
    Realm realm;

    RealmResults<SaleItem> salesItemList;

    SalesItemAdapter adapter;

    SalesItemAdapter salesItemAdapter;

    ProductListAdapter adapterSuggestion;

    ProductListAdapter productListAdapter;

    ProductGridAdapter productGridAdapter;


    StockItem stockItem;

    int discountAmount;
    int discountTotal;
    int returnAmount;
    int dueAmount;
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
    @BindView(R.id.discount_et)
    PrefixEditText discount_et;
    @BindView(R.id.discount_switch)
    Switch discount_switch;
    @BindView(R.id.sale_vat_tk)
    TextView sale_vat_tk;
    @BindView(R.id.sale_discount_tk)
    TextView sale_discount_tk;

    @BindView(R.id.sale_total_profit)
    TextView sale_total_profit;

    @BindView(R.id.sale_total_tk)
    TextView sale_grand_total_tk;
    @BindView(R.id.receivedTk)
    EditText receivedTk;
    @BindView(R.id.return_due_text)
    TextView return_due_text;


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

    @BindView(R.id.customer_btn)
    ImageView customer_btn;

    @BindView(R.id.printMessage)
    Spinner printMessageSpinner;

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
    String salesByUser;

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
        String language = PreferenceManager.getLanguage(SearchSalesActivity.this);
        setLocale(language);
        loadLocale();
        setContentView(R.layout.activity_search_sales);

        // sale_total_tk = findViewById(R.id.sale_total_tk);

        salesByUser = ""+PreferenceManager.getUserName(SearchSalesActivity.this);

        ButterKnife.bind(this);


        getPrintMessageSpinner();

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

        progressDialog = new ProgressDialog(SearchSalesActivity.this);
        progressDialog.setTitle("Syncing");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        customerList = new ArrayList<>();


        getCustomerSpinner();

        //  calculatePrice();


        searchStockItemList = new ArrayList<>();
        searchStockItemList.clear();
        stockItemRealmResults = realm.where(StockItem.class).findAll();
        searchStockItemList.addAll(realm.copyFromRealm(stockItemRealmResults));


        adapterSuggestion = new ProductListAdapter(this, stockItemList, this);

        searchSuggestionrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchSuggestionrecyclerView.setAdapter(adapterSuggestion);
        adapterSuggestion.notifyDataSetChanged();


        stockItemList = new ArrayList<>();
        salesItemList = realm.where(SaleItem.class).findAll();

        salesItemList.addChangeListener(new RealmChangeListener<RealmResults<SaleItem>>() {
            @Override
            public void onChange(RealmResults<SaleItem> saleItems) {
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

        ListPairedDevices();

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
                cartlayoutbg.setVisibility(View.VISIBLE);
                cartlayout.setVisibility(View.VISIBLE);
                sale_layout.setVisibility(View.VISIBLE);
                editTextSearch.setText("");
                //  hideSoftKeyboard(view);
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
                new ArrayAdapter<>(SearchSalesActivity.this
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
                new ArrayAdapter<>(SearchSalesActivity.this
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

        if (PreferenceManager.getModule(SearchSalesActivity.this).equalsIgnoreCase("restaurant")) {
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
                paymentlayout.setVisibility(View.GONE);
                sale_layout.setVisibility(View.GONE);
                cartlayout.setVisibility(View.GONE);
                editTextSearch.setFocusableInTouchMode(true);
                editTextSearch.setFocusable(true);
                editTextSearch.requestFocus();

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
                paymentlayout.setVisibility(View.VISIBLE);
                hideSoftKeyboard(view);
            }
        });


        adapter = new SalesItemAdapter(SearchSalesActivity.this, salesItemList, this);
        cartItemRV.setLayoutManager(new LinearLayoutManager(SearchSalesActivity.this, LinearLayoutManager.VERTICAL, false));
        cartItemRV.addItemDecoration(new DividerItemDecoration(SearchSalesActivity.this, DividerItemDecoration.VERTICAL));
        cartItemRV.setAdapter(adapter);


        adapter = new SalesItemAdapter(SearchSalesActivity.this, salesItemList, this);
        paymentitemRv.setLayoutManager(new LinearLayoutManager(SearchSalesActivity.this, LinearLayoutManager.VERTICAL, false));
        paymentitemRv.addItemDecoration(new DividerItemDecoration(SearchSalesActivity.this, DividerItemDecoration.VERTICAL));
        paymentitemRv.setAdapter(adapter);


        if (PreferenceManager.getModule(SearchSalesActivity.this).equalsIgnoreCase("restaurant")) {
             /*
        token no adapter
         */


        } else {

        }

        /*
        getting sales mode
         */
        sales_mode = PreferenceManager.getSalesMode(SearchSalesActivity.this);


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


             /*
        payment cards adapter
         */

            paymentCards = realm.where(PaymentCards.class).findAll();

            paymentCardsList = realm.copyFromRealm(paymentCards);

            String[] cards = new String[paymentCardsList.size()];
            for (int i = 0; i < paymentCardsList.size(); i++) {
                cards[i] = paymentCardsList.get(i).getName();

            }

            ArrayAdapter<String> paymentCardsAdapter =
                    new ArrayAdapter<>(SearchSalesActivity.this
                            , android.R.layout.simple_spinner_dropdown_item
                            , cards);

            payment_card_spinner.setAdapter(paymentCardsAdapter);


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


        sale_return_tk.setText("৳ " + total);
        sale_grand_total_tk.setText("৳ " + total);

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }


    private void initpayment() {


        Switch discount_switch = findViewById(R.id.discount_switch);


        addNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTermsDialog();
            }
        });

        customer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customerlistshow == false) {
                    customer_layout.setVisibility(View.VISIBLE);
                    customerlistshow = true;
                } else {
                    customer_layout.setVisibility(View.GONE);
                    customerlistshow = false;
                }

            }
        });

        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
            discountAmount = Integer.parseInt(discount_et.getText().toString());
            discountTotal = total - discountAmount;
            sale_return_tk.setText("৳ " + discountTotal);
        }

        discount_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (discount_switch.isChecked()) {

                    discount_et.setHint("Percent");
                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        discountAmount = (int) Math.round((total * Double.valueOf(discount_et.getText().toString()) / 100));
                        discountTotal = total - discountAmount;
                        if (!TextUtils.isEmpty(vatRegNo)) {
                            totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                            discountTotal = discountTotal + totalVat;
                            sale_vat_tk.setText(totalVat + "");
                        }
                        sale_discount_tk.setText("৳ " + discountAmount);
                        sale_return_tk.setText("৳ " + discountTotal);

                    } else {
                        // discount_et.setError("Please input discount percent");
                        discount_et.clearFocus();

                    }
                } else {
                    discount_et.setHint("Flat");
                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        discountAmount = Integer.parseInt(discount_et.getText().toString());
                        discountTotal = total - discountAmount;
                        if (!TextUtils.isEmpty(vatRegNo)) {
                            totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                            discountTotal = discountTotal + totalVat;
                            sale_vat_tk.setText(totalVat + "");
                        }
                        sale_discount_tk.setText("৳ " + discount_et.getText().toString());
                        sale_return_tk.setText("৳ " + discountTotal);
                    } else {
                        //discount_et.setError("Please input amount of discount");
                        discount_et.clearFocus();
                    }
                }
            }
        });

        discount_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    handled = true;

                    if (discount_switch.isChecked()) {

                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                            discountAmount = (int) Math.round((total * Double.valueOf(discount_et.getText().toString()) / 100));
                            discountTotal = total - discountAmount;
                            if (!TextUtils.isEmpty(vatRegNo)) {
                                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                                discountTotal = discountTotal + totalVat;
                                sale_vat_tk.setText(totalVat + "");
                            }
                            sale_grand_total_tk.setText("৳ " + discountTotal);
                            sale_discount_tk.setText("৳ " + discountAmount);

                        } else {
                            // discount_et.setError("Please input discount percent");
                        }

                    } else {
                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                            discountAmount = Integer.parseInt(discount_et.getText().toString());
                            discountTotal = total - discountAmount;
                            if (!TextUtils.isEmpty(vatRegNo)) {
                                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                                discountTotal = discountTotal + totalVat;
                                sale_vat_tk.setText(totalVat + "");
                            }
                            sale_grand_total_tk.setText("৳ " + discountTotal);

                            sale_discount_tk.setText("৳ " + discountAmount);
                        } else {
                            //  discount_et.setError("Please input amount of discount");
                        }

                    }

                    //checkReturn();

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
                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                    discountAmount = Integer.parseInt(discount_et.getText().toString());
                    discountTotal = total - discountAmount;
                    sale_return_tk.setText("৳ " + discountTotal);
                } else {
                    sale_discount_tk.setText("৳ " + 0);
                    sale_return_tk.setText("৳ " + total);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                    if (discount_switch.isChecked()) {

                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                            discountAmount = (int) Math.round((total * Double.valueOf(discount_et.getText().toString()) / 100));
                            discountTotal = total - discountAmount;
                            if (!TextUtils.isEmpty(vatRegNo)) {
                                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                                discountTotal = discountTotal + totalVat;
                                sale_vat_tk.setText(totalVat + "");
                            }
                            //sale_grand_total_tk.setText("৳ "+ discountTotal);
                            sale_discount_tk.setText("৳ " + discountAmount);

                        } else {
                            calculatePrices();
                            discount_et.setError("Please input discount percent");
                            sale_discount_tk.setText("৳ " + 0);
                            sale_return_tk.setText("৳ " + total);
                            discount_et.setText("");
                            discount_et.clearFocus();
                        }

                    } else {
                        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                            discountAmount = Integer.parseInt(discount_et.getText().toString());
                            discountTotal = total - discountAmount;
                            if (!TextUtils.isEmpty(vatRegNo)) {
                                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                                discountTotal = discountTotal + totalVat;
                                sale_vat_tk.setText(totalVat + "");
                            }
                            //sale_grand_total_tk.setText("৳ " + discountTotal);
                            sale_discount_tk.setText("৳ " + discount_et.getText().toString());
                        } else {
                            calculatePrices();
                            discount_et.setError("Please input amount of discount");
                            discount_et.clearFocus();
                            discount_et.setText("");
                            sale_discount_tk.setText("৳ " + 0);
                            sale_return_tk.setText("৳ " + total);
                        }

                    }
                }

                // checkReturn();
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
                    sale_grand_total_tk.setText("৳ " + total);

                    if (TextUtils.isEmpty(discount_et.getText().toString())) {

                        if (TextUtils.isEmpty(vatRegNo)) {

                            if (Integer.parseInt(receivedTk.getText().toString()) >= total) {
                                dueAmount = 0;
                                returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - total);
                                sale_return_tk.setText("৳ " + returnAmount);
                                return_due_text.setText("" + getResources().getString(R.string.string_return));

                            } else {
                                returnAmount = 0;
                                return_due_text.setText("" + getResources().getString(R.string.string_due));
                                dueAmount = (int) (total - Integer.parseInt(receivedTk.getText().toString()));
                                sale_return_tk.setText("৳ " + dueAmount);
                            }

                        } else {
                            if (Integer.parseInt(receivedTk.getText().toString()) >= grandTotal) {
                                dueAmount = 0;
                                returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - grandTotal);
                                sale_return_tk.setText("৳ " + returnAmount);
                                return_due_text.setText("" + getResources().getString(R.string.string_return));

                            } else {
                                returnAmount = 0;
                                return_due_text.setText("" + getResources().getString(R.string.string_due));
                                dueAmount = (int) (grandTotal - Integer.parseInt(receivedTk.getText().toString()));
                                sale_return_tk.setText("৳ " + dueAmount);
                            }
                        }
                    } else {
                        if (Integer.parseInt(receivedTk.getText().toString()) >= discountTotal) {
                            dueAmount = 0;
                            returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - discountTotal);
                            sale_return_tk.setText("৳ " + returnAmount);
                            return_due_text.setText("" + getResources().getString(R.string.string_return));

                        } else {
                            returnAmount = 0;
                            return_due_text.setText("" + getResources().getString(R.string.string_due));
                            dueAmount = (int) (discountTotal - Integer.parseInt(receivedTk.getText().toString()));
                            sale_return_tk.setText("৳ " + dueAmount);
                        }
                    }

                } else {
                    return_due_text.setText("" + getResources().getString(R.string.string_due));

                    if (TextUtils.isEmpty(discount_et.getText().toString())) {

                        if (TextUtils.isEmpty(vatRegNo)) {
                            sale_return_tk.setText("৳ " + total);
                        } else {
                            sale_return_tk.setText("৳ " + grandTotal);
                        }

                    } else {
                        sale_return_tk.setText("৳ " + discountTotal);
                    }

                }

            }
        });


    }

    private void getPrintMessageSpinner() {
        printmessageList.add("" + getResources().getString(R.string.string_printmessage));
        getprintMessage();


        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, printmessageList);

        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        printMessageSpinner.setAdapter(adapter2);


        printMessageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String valu = adapterView.getItemAtPosition(pos).toString();
                // category_id = categoryArrayList.get(i).getName();

                if (pos != 0) {
                    printMessage = adapterView.getItemAtPosition(pos).toString();
                } else {
                    printMessage = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getprintMessage() {

        // + PreferenceManager.getSecretKey(getApplicationContext())
        compositeDisposable.add(APIClient.getInstance().getPrintMessage("1582309825")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<CustomerLedeger>>>() {
                    @Override
                    public void onNext(final Response<List<CustomerLedeger>> response) {
                        try {
                            for (int i = 0; i < response.body().size(); i++) {
                                printmessageList.add(response.body().get(i).getName());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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

    private void getCustomerSpinner() {


          /*
        customerlist
         */
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                customerList.clear();
                RealmResults<AnonymousCustomer> customers = realm.where(AnonymousCustomer.class).findAll();
                customerList.addAll(realm.copyFromRealm(customers));
                for (int i = 0; i < customerList.size(); i++) {
                    customerListsingle.add(customerList.get(i).getMobile() + " - " + customerList.get(i).getName());
                }

            }
        });


        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, customerListsingle);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customerSpinner.setAdapter(adapter);


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
        });

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


                Toast.makeText(SearchSalesActivity.this, "sync", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SearchSalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ic_syncicons:
                        Toast.makeText(SearchSalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ic_sales:
                        Toast.makeText(SearchSalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                }
                //Toast.makeText(SearchSalesActivity.this, ""+menuItem, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        popup.show();

    }

    private void showTermsDialog() {


        LayoutInflater layoutInflater = LayoutInflater.from(SearchSalesActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_create_customer, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(SearchSalesActivity.this);

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
        String userid = "" + PreferenceManager.getUserId(SearchSalesActivity.this);

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

        compositeDisposable.addAll(APIClient.getInstance().createCustomer(PreferenceManager.getSecretKey(SearchSalesActivity.this),
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
        checkDiscount();

        cartlayoutbg.setVisibility(View.VISIBLE);


    }


    @OnClick(R.id.save_btn)
    void save_btn() {
        new AlertDialog.Builder(SearchSalesActivity.this).setMessage("Are you sure want to save?")
                .setTitle("Alert!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (PreferenceManager.getOnlineOfflineMode(SearchSalesActivity.this).equalsIgnoreCase("Online")) {
                            if (Common.isNetworkAvailable(SearchSalesActivity.this)) {

                                syncSales();

                            } else {

                                Toast.makeText(SearchSalesActivity.this, "You are in online mode.Please Check your network connection", Toast.LENGTH_SHORT).show();
                            }
                        } else if (PreferenceManager.getOnlineOfflineMode(SearchSalesActivity.this).equalsIgnoreCase("Offline")) {
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
    void onPosPrint() {


        if (print_status.getText().equals("Off")) {

            // posPrintBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_print_red, 0, 0, 0);
            if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("usb")) {
                showUSBDeviceChooseDialog();

            } else {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter == null) {
                    Toast.makeText(SearchSalesActivity.this, "Message1", Toast.LENGTH_SHORT).show();
                } else {
                    if (!mBluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(
                                BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent,
                                REQUEST_ENABLE_BT);
                    } else {
                        ListPairedDevices();
                        Intent connectIntent = new Intent(SearchSalesActivity.this,
                                DeviceListActivity.class);
                        startActivityForResult(connectIntent,
                                REQUEST_CONNECT_DEVICE);

                    }
                }
            }


        }
       /* else if (!print_status.getText().equals("Connected")) {
            posPrintBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_print_red, 0, 0, 0);
        }*/
        else if (print_status.getText().equals("Connected")) {
            new AlertDialog.Builder(SearchSalesActivity.this).setMessage("Are you sure want to print?")
                    .setTitle("Alert!!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (TextUtils.isEmpty(receivedTk.getText().toString())) {
                                sale_grand_total_tk.setText("৳ " + total);

                                if (TextUtils.isEmpty(discount_et.getText().toString())) {

                                    if (TextUtils.isEmpty(vatRegNo)) {


                                        dueAmount = 0;
                                        returnAmount = (int) (total - total);
                                        sale_return_tk.setText("৳ " + returnAmount);
                                        return_due_text.setText("Return Tk");
                                        saveSaleAndRemove();

                                    } else {

                                        dueAmount = 0;
                                        returnAmount = (int) (total - grandTotal);
                                        sale_return_tk.setText("৳ " + returnAmount);
                                        return_due_text.setText("Return Tk");
                                        saveSaleAndRemove();

                                    }
                                } else {

                                    dueAmount = 0;
                                    returnAmount = (int) (discountTotal - discountTotal);
                                    sale_return_tk.setText("৳ " + returnAmount);
                                    return_due_text.setText("Return Tk");


                                }

                            }


                            if (PreferenceManager.getOnlineOfflineMode(SearchSalesActivity.this).equalsIgnoreCase("Online")) {
                                if (Common.isNetworkAvailable(SearchSalesActivity.this)) {

                                    if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("80mm")) {
                                        print80mm();
                                        syncSales();

                                    } else if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("50mm")) {
                                        print50mm();
                                        syncSales();
                                    } else if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("usb")) {
                                        printUsb();
                                        syncSales();
                                    }


                                } else {

                                    Toast.makeText(SearchSalesActivity.this, "You are in online mode.Please Check your network connection", Toast.LENGTH_SHORT).show();
                                }
                            } else if (PreferenceManager.getOnlineOfflineMode(SearchSalesActivity.this).equalsIgnoreCase("Offline")) {
                                if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("80mm")) {
                                    print80mm();
                                    saveSaleAndRemove();

                                } else if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("50mm")) {
                                    print50mm();
                                    saveSaleAndRemove();
                                } else if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("usb")) {
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

        } else {
            if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("usb")) {
                doConnect();
            }
        }
    }


    public void syncSales() {


        if (TextUtils.isEmpty(receivedTk.getText().toString())) {
            receivedTk.setError("Required");
            receivedTk.requestFocus();
            return;
        }


        progressDialog.show();

        SalesHistory salesHistory = new SalesHistory();
        if (banklayoutshow == false && mobilebanklayoutshow == false) {
            paymentType = "cash";
            salesHistory.setTransactionMethod(paymentType);
            salesHistory.setPaymentMobile("");
            salesHistory.setTransactionId("");
            salesHistory.setMobileBankAccount(0);
            salesHistory.setPaymentCard(0);
            salesHistory.setPaymentCardNo("");
            salesHistory.setBankAccount(0);

        } else if (mobilebanklayoutshow) {
            paymentType = "mobile";
            int paymentPos = payment_receive_spinner.getSelectedItemPosition();
            paymentMobileID = mobileAccounts.get(paymentPos).getItemId();
            paymentMobileNo = payment_mobile_no.getText().toString();
            paymentTransactionId = payment_transaction_id.getText().toString();
            paymentReceiveMobileNo = String.valueOf(payment_receive_spinner.getSelectedItem());
            salesHistory.setTransactionMethod(paymentType);
            salesHistory.setPaymentMobile(paymentMobileNo);
            salesHistory.setTransactionId(paymentTransactionId);
            salesHistory.setMobileBankAccount(paymentMobileID);

            salesHistory.setPaymentCard(0);
            salesHistory.setPaymentCardNo("");
            salesHistory.setBankAccount(0);

        } else if (banklayoutshow) {
            paymentType = "bank";
            int posi = payment_card_spinner.getSelectedItemPosition();
            paymentCardType = paymentCardsList.get(posi).getItemId();
            paymentCardNo = payment_card_no.getText().toString();
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
        salesHistory.setCreatedBy(Integer.parseInt(PreferenceManager.getUserId(SearchSalesActivity.this)));

        if (customerlistshow) {
            anoCustomerId = 0;
            salesHistory.setCustomerId(0);
            salesHistory.setCustomerMobile(anoCustomerMobile);
            salesHistory.setCustomerName(anoCustomerName);
        } else {
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

        } else {

            salesHistory.setDiscount(0);
            salesHistory.setDiscountCalculation(0);
            salesHistory.setDiscountType("");

            if (!TextUtils.isEmpty(vatRegNo)) {
                salesHistory.setTotal(Integer.parseInt(String.valueOf(grandTotal)));
            } else {
                salesHistory.setTotal(Integer.parseInt(String.valueOf(total)));
            }

        }

        salesHistory.setDue(Integer.parseInt(String.valueOf(dueAmount)));
        salesHistory.setInvoiceId(String.valueOf(memoNo));
        salesHistory.setReceive(Integer.parseInt(receivedTk.getText().toString()));
        salesHistory.setSalesBy(systemUsersId);
        salesHistory.setSubTotal(Integer.parseInt(String.valueOf(total)));

        if (TextUtils.isEmpty(vatRegNo)) {
            salesHistory.setVat(0);
        } else {
            salesHistory.setVat(totalVat);
        }

        salesHistory.setSlipNo("");
        salesHistory.setTokenNo(tokenNoss);
        salesHistory.setDiscountCoupon(0);
        salesHistory.setRemark("");


        salesHistoryList.add(salesHistory);


        for (int i = 0; i < salesItemList.size(); i++) {

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


        if (totalSize > 0) {

            compositeDisposable.addAll(
                    APIClient.getInstance().syncSales(PreferenceManager.getDeviceId(SearchSalesActivity.this)
                            , PreferenceManager.getSecretKey(SearchSalesActivity.this), new Gson().toJson(salesHistoryList)
                            , totalSize
                            , new Gson().toJson(salesItemHistoryList)
                            , saleItemTotalSize)
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

                                        Toast.makeText(SearchSalesActivity.this, "Successfully saved", Toast.LENGTH_SHORT).show();

                                      //  onBackPressed();


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


        } else {
            progressDialog.dismiss();
            Toast.makeText(SearchSalesActivity.this, "Nothing to sync.Please add some sales first", Toast.LENGTH_LONG).show();
        }

    }

    private void showUSBDeviceChooseDialog() {
        final UsbDeviceChooseDialog usbDeviceChooseDialog = new UsbDeviceChooseDialog();
        usbDeviceChooseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UsbDevice mUsbDevice = (UsbDevice) parent.getAdapter().getItem(position);
                PendingIntent mPermissionIntent = PendingIntent.getBroadcast(
                        SearchSalesActivity.this,
                        0,
                        new Intent(SearchSalesActivity.this.getApplicationInfo().packageName),
                        0);
                print_status.setText(getString(R.string.adapter_usbdevice) + mUsbDevice.getDeviceId()); //+ (position + 1));
                configObj = new UsbConfigBean(TerminalApplication.getInstance(), mUsbDevice, mPermissionIntent);
                //  print_status.setTag(BaseEnum.HAS_DEVICE);
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
        if (Integer.parseInt(print_status.toString()) == BaseEnum.NO_DEVICE) {//未选择设备
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

    public void showAlertDialog(final String msg) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SearchSalesActivity.this);
                dialog.setTitle(R.string.dialog_tip);
                dialog.setMessage(msg);
                dialog.setNegativeButton(R.string.dialog_back, null);
                dialog.show();
            }
        });
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


    private void ListPairedDevices() {
        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter
                .getBondedDevices();
        if (mPairedDevices.size() > 0) {
            if (PreferenceManager.getLastPairedDeviceSocketAddrId(SearchSalesActivity.this) != null) {
                mBluetoothAdapter.cancelDiscovery();

                String mDeviceAddress = PreferenceManager.getLastPairedDeviceSocketAddrId(SearchSalesActivity.this);

                Log.i("Paired device", mDeviceAddress);

                mBluetoothDevice = mBluetoothAdapter
                        .getRemoteDevice(mDeviceAddress);
            } else {
                for (BluetoothDevice mDevice : mPairedDevices) {
                    Log.v(TAG, "PairedDevices: " + mDevice.getName() + "  "
                            + mDevice.getAddress());

                    mBluetoothAdapter.cancelDiscovery();

                    String mDeviceAddress = mDevice.getAddress();

                    Log.i("Paired device", mDeviceAddress);

                    mBluetoothDevice = mBluetoothAdapter
                            .getRemoteDevice(mDeviceAddress);
                }
            }

            mBlutoothConnectThread = new Thread(this);
            mBlutoothConnectThread.start();
        }
    }

    public void saveSaleAndRemove() {


        if (TextUtils.isEmpty(receivedTk.getText().toString())) {
            receivedTk.setError("Required");
            receivedTk.requestFocus();
            return;
        }


        salesItemList.removeAllChangeListeners();

        realm.beginTransaction();

        SalesHistory salesHistory = realm.createObject(SalesHistory.class, UUID.randomUUID().toString());
        if (banklayoutshow == false && mobilebanklayoutshow == false) {
            paymentType = "cash";
            salesHistory.setTransactionMethod(paymentType);
            salesHistory.setPaymentMobile("");
            salesHistory.setTransactionId("");
            salesHistory.setMobileBankAccount(0);
            salesHistory.setPaymentCard(0);
            salesHistory.setPaymentCardNo("");
            salesHistory.setBankAccount(0);

        } else if (mobilebanklayoutshow) {
            paymentType = "mobile";
            int paymentPos = payment_receive_spinner.getSelectedItemPosition();
            paymentMobileID = mobileAccounts.get(paymentPos).getItemId();
            paymentMobileNo = payment_mobile_no.getText().toString();
            paymentTransactionId = payment_transaction_id.getText().toString();
            paymentReceiveMobileNo = String.valueOf(payment_receive_spinner.getSelectedItem());
            salesHistory.setTransactionMethod(paymentType);
            salesHistory.setPaymentMobile(paymentMobileNo);
            salesHistory.setTransactionId(paymentTransactionId);
            salesHistory.setMobileBankAccount(paymentMobileID);

            salesHistory.setPaymentCard(0);
            salesHistory.setPaymentCardNo("");
            salesHistory.setBankAccount(0);

        } else if (banklayoutshow) {
            paymentType = "bank";
            int posi = payment_card_spinner.getSelectedItemPosition();
            paymentCardType = paymentCardsList.get(posi).getItemId();
            paymentCardNo = payment_card_no.getText().toString();
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
        salesHistory.setCreatedBy(Integer.parseInt(PreferenceManager.getUserId(SearchSalesActivity.this)));

        if (customerlistshow) {
            anoCustomerId = 0;
            salesHistory.setCustomerId(0);
            salesHistory.setCustomerMobile(anoCustomerMobile);
            salesHistory.setCustomerName(anoCustomerName);
        } else {
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

        } else {

            salesHistory.setDiscount(0);
            salesHistory.setDiscountCalculation(0);
            salesHistory.setDiscountType("");
            salesHistory.setTotal(Integer.parseInt(String.valueOf(total)));
        }

        salesHistory.setDue(Integer.parseInt(String.valueOf(dueAmount)));
        salesHistory.setInvoiceId(String.valueOf(memoNo));
        salesHistory.setReceive(Integer.parseInt(receivedTk.getText().toString()));

        salesHistory.setSalesBy(systemUsersId);
        salesHistory.setSubTotal(Integer.parseInt(String.valueOf(total)));

        if (TextUtils.isEmpty(vatRegNo)) {
            salesHistory.setVat(0);
        } else {
            salesHistory.setVat(totalVat);
        }

        salesHistory.setSlipNo("");
        salesHistory.setTokenNo(tokenNoss);
        salesHistory.setDiscountCoupon(0);
        salesHistory.setRemark("");

//        realm.commitTransaction();

//        realm.beginTransaction();

        for (int i = 0; i < salesItemList.size(); i++) {

            SalesItemHistory salesItemHistory =
                    realm.createObject(SalesItemHistory.class, UUID.randomUUID().toString());
            salesItemHistory.setQuantity(Integer.parseInt(salesItemList.get(i).getSaleQuantity()));
            salesItemHistory.setSalesId(memoNo);
            salesItemHistory.setStockId(Integer.parseInt(String.valueOf(salesItemList.get(i).getSaleStockId())));
            salesItemHistory.setSubTotal(Integer.valueOf(Double.valueOf(salesItemList.get(i).getSaleSubTotal()).intValue()));
            salesItemHistory.setUnitPrice(Double.valueOf(salesItemList.get(i).getSaleMrpPrice()));

        }
        realm.delete(SaleItem.class);
        realm.commitTransaction();
        realm.close();
        adapter.notifyDataSetChanged();

        calculatePrice();
        cartempty.setVisibility(View.GONE);
        //productqty.setText("0");
      /*  sale_return_tk.setText("৳ 0");
        sale_return_tk.setText("৳ 0");*/
        receivedTk.setText(null);
        Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
       // onBackPressed();

    }

    public void run() {

        try {

            synchronized (SearchSalesActivity.this) {
                mBluetoothSocket = mBluetoothDevice
                        .createInsecureRfcommSocketToServiceRecord(applicationUUID);
                mBluetoothAdapter.cancelDiscovery();
                mBluetoothSocket.connect();
                Log.i("Connected device", mBluetoothDevice.getAddress());
                mHandler.sendEmptyMessage(0);
                PreferenceManager.setLastPairedDeviceSocketAddre(SearchSalesActivity.this, mBluetoothDevice.getAddress());
            }

        } catch (IOException eConnectException) {
            Log.d(TAG, "CouldNotConnectToSocket", eConnectException);
            closeSocket(mBluetoothSocket);
            return;
        }
        /*try {

            synchronized (SearchSalesActivity.this) {
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
        }*/
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

            SharedPreferences pre = getSharedPreferences("BT_NAME", MODE_PRIVATE);
            pre.edit().putString("bluetooth_connected", mBluetoothDevice.getAddress()).apply();

            print_status.setText("");
            print_status.setText("Connected");

            //     menu.getItem(0).setIcon(ContextCompat.getDrawable(SearchSalesActivity.this, R.drawable.ic_print_connected));
            PreferenceManager.setPrinterConnection(SearchSalesActivity.this, "Connected");

        }
    };


    /*
     * 80mm printing method
     * */
    public void print80mm() {

        for (int i = 0; i < salesItemList.size(); i++) {
            serial++;

            stock = realm
                    .where(StockItem.class)
                    .equalTo("itemId", Integer.parseInt(salesItemList.get(i)
                            .getSaleStockId()))
                    .findFirst();

            if (TextUtils.isEmpty(stock.getPrintHidden())) {
                if (stock.getPrintName().length() > 35) {

                    itemPrintName = stock.getPrintName().substring(0, 35);

                } else {
                    itemPrintName = stock.getPrintName();
                }

            } else {
                itemPrintName = "Item-1";
            }

            items += String.format("%1$-47s %2$-8s %3$-1s",
                    serial + ". " + itemPrintName,
                    salesItemList.get(i).getSaleQuantity(),
                    salesItemList.get(i).getSaleSubTotal()) + "\n";
        }

        Log.d("Loop", "DATA" + items);


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
                    String header4 = "";
                    String offname = "";
                    String copy = "";
                    String message = "";
                    String site = "";


                    header5 = header5 + pharmacyName + "\n";

                   /* if (!TextUtils.isEmpty(addressName)) {
                        address = address+addressName + "\n";
                    }*/

                    if (!TextUtils.isEmpty(addressName)) {
                        he = he + addressName + "\n";
                    }
                    he = he + pharmacyMobileName + "\n";


                    if (!TextUtils.isEmpty(vatRegNo)) {
                        he = he + "Vat Regi No:" + vatRegNo + "\n\n";
                    } else {
                        he = he + "\n";
                    }

                    if (!TextUtils.isEmpty(anoCustomerName) && !TextUtils.isEmpty(anoCustomerMobile)) {
                        header = header + String.format("%1$-30s %2$-1s", "Bill No:" + memoNo, "Sales By:" + salesByUser) + "\n";
                        header = header + String.format("%1$-30s %2$-1s", "Date:" + currentDateandTime, "Pay Mode:" + paymentType) + "\n";
                        header = header + String.format("%1$-30s %2$-1s", "Customer:" + anoCustomerName, "\nMobile:" + anoCustomerMobile) + "\n";
                        header = header + "\n";

                    } else {
                        header = header + String.format("%1$-30s %2$-1s", "Bill No:" + memoNo, "Sales By:" + salesByUser) + "\n";
                        header = header + String.format("%1$-30s %2$-1s", "Date:" + currentDateandTime, "Pay Mode:" + paymentType) + "\n";

                        header = header + "\n";
                    }

                    header2 = String.format("%1$-47s %2$-8s %3$-1s", "Item Name", "Qnt", "Amount") + "\n";
                    header2 = header2 + "----------------------------------------------------------------\n";
                    vio = items;
                    vio = vio + "----------------------------------------------------------------\n";

                    header3 = "Sub Total:                                      Tk.      " + total + "\n";

                    if (!TextUtils.isEmpty(vatRegNo)) {
                        mvdtail = "Discount:                                             Tk.      " + discountAmount + "\n";
                        mvdtail = mvdtail + "Vat:                                            Tk.      " + totalVat + "\n";
                        mvdtail = mvdtail + "----------------------------------------------------------------\n";
                    } else {
                        mvdtail = "Discount:                                             Tk.      " + discountAmount + "\n";
                        mvdtail = mvdtail + "----------------------------------------------------------------\n";
                    }

                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        mvdtail = mvdtail + "Net Payable:                                    Tk.      " + discountTotal + "\n";
                        if (TextUtils.isEmpty(receivedTk.getText().toString())) {
                            mvdtail = mvdtail + "Paid:                                           Tk.      " + discountTotal + "\n";
                        } else {
                            mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                        }

                        if (dueAmount > 0) {
                            mvdtail = mvdtail + "Due:                        Tk.      " + dueAmount + "\n";
                        } else {
                            mvdtail = mvdtail + "Return:                     Tk.      " + returnAmount + "\n\n";
                        }
                    } else {
                        if (TextUtils.isEmpty(vatRegNo)) {
                            mvdtail = mvdtail + "Net Payable:                                    Tk.      " + total + "\n";

                            if (TextUtils.isEmpty(receivedTk.getText().toString())) {
                                mvdtail = mvdtail + "Paid:                                           Tk.      " + total + "\n";
                            } else {
                                mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                            }

                            if (dueAmount > 0) {
                                mvdtail = mvdtail + "Due:                        Tk.      " + dueAmount + "\n";
                            } else {
                                mvdtail = mvdtail + "Return:                     Tk.      " + returnAmount + "\n\n";
                            }
                        } else {
                            mvdtail = mvdtail + "Net Payable:                                    Tk.      " + grandTotal + "\n";

                            if (TextUtils.isEmpty(receivedTk.getText().toString())) {
                                mvdtail = mvdtail + "Paid:                                           Tk.      " + grandTotal + "\n";
                            } else {
                                mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                            }

                            if (dueAmount > 0) {
                                mvdtail = mvdtail + "Due:                        Tk.      " + dueAmount + "\n";
                            } else {
                                mvdtail = mvdtail + "Return:                     Tk.      " + returnAmount + "\n\n";
                            }
                        }
                    }


                    site = "\n** Visit " + siteName + " **\n";
                    message = "** " + printMessage + " **\n";
                    copy = "Development By www.terminalbd.com\n\n\n\n\n";


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
                    if (!printMessage.equals("")) {
                        os.write(message.getBytes());
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
    public void print50mm() {

        for (int i = 0; i < salesItemList.size(); i++) {
            serial++;

            stock = realm
                    .where(StockItem.class)
                    .equalTo("itemId", Integer.parseInt(salesItemList.get(i)
                            .getSaleStockId()))
                    .findFirst();

            if (TextUtils.isEmpty(stock.getPrintHidden())) {
                if (stock.getPrintName().length() > 20) {

                    itemPrintName = stock.getPrintName().substring(0, 20);

                } else {
                    itemPrintName = stock.getPrintName();
                }

            } else {
                itemPrintName = "Item-1";
            }

            items += String.format("%1$-27s %2$-8s %3$-1s",
                    serial + ". " + itemPrintName,
                    salesItemList.get(i).getSaleQuantity(),
                    salesItemList.get(i).getSaleSubTotal()) + "\n";
        }

        Log.d("Loop", "DATA" + items);


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
                    String header4 = "";
                    String offname = "";
                    String copy = "";
                    String message = "";
                    String site = "";


                    header5 = header5 + pharmacyName + "\n";

                    if (!TextUtils.isEmpty(addressName)) {
                        he = he + addressName + "\n";
                    }
                    he = he + pharmacyMobileName + "\n";

                    if (!TextUtils.isEmpty(vatRegNo)) {
                        he = he + "Vat Regi No:" + vatRegNo + "\n\n";
                    } else {
                        he = he + "\n";
                    }

                    if (!TextUtils.isEmpty(anoCustomerName) && !TextUtils.isEmpty(anoCustomerMobile)) {
                        header = header + String.format("%1$-21s %2$-1s", "Bill No:" + memoNo, "\nSales By:" + salesByUser) + "\n";
                        header = header + String.format("%1$-27s %2$-1s", "Date:" + currentDateandTime, "\nPay Mode:" + paymentType) + "\n";
                        header = header + String.format("%1$-27s %2$-1s", "Customer:" + anoCustomerName, "\nMobile:" + anoCustomerMobile) + "\n";
                        header = header + "\n";

                    } else {
                        header = header + String.format("%1$-21s %2$-1s", "Bill No:" + memoNo, "\nSales By:" + salesByUser) + "\n";
                        header = header + String.format("%1$-20s %2$-1s", "Date:" + currentDateandTime, "\nPay Mode: " + paymentType) + "\n";

                        header = header + "\n";
                    }

                    header2 = String.format("%1$-26s %2$-8s %3$-1s", "Item Name", "Qnt", "Amount") + "\n";
                    header2 = header2 + "------------------------------------------\n";
                    vio = items;
                    vio = vio + "------------------------------------------\n";

                    header3 = "Sub Total:                  Tk.      " + total + "\n";

                    if (!TextUtils.isEmpty(vatRegNo)) {
                        mvdtail = "Discount:                    Tk.      " + discountAmount + "\n";
                        mvdtail = mvdtail + "Vat:                         Tk.      " + totalVat + "\n";
                        mvdtail = mvdtail + "------------------------------------------\n";
                    } else {
                        mvdtail = "Discount:                   Tk.      " + discountAmount + "\n";
                        mvdtail = mvdtail + "------------------------------------------\n";
                    }

                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        mvdtail = mvdtail + "Net Payable:                Tk.      " + discountTotal + "\n";

                        if (TextUtils.isEmpty(receivedTk.getText().toString())) {
                            mvdtail = mvdtail + "Paid:                       Tk.      " + discountTotal + "\n";
                        } else {
                            mvdtail = mvdtail + "Paid:                       Tk.      " + receivedTk.getText().toString() + "\n";
                        }

                        if (dueAmount > 0) {
                            mvdtail = mvdtail + "Due:                        Tk.      " + dueAmount + "\n";
                        } else {
                            mvdtail = mvdtail + "Return:                     Tk.      " + returnAmount + "\n\n";
                        }
                    } else {
                        if (TextUtils.isEmpty(vatRegNo)) {
                            mvdtail = mvdtail + "Net Payable:                Tk.      " + total + "\n";

                            if (TextUtils.isEmpty(receivedTk.getText().toString())) {
                                mvdtail = mvdtail + "Paid:                       Tk.      " + total + "\n";
                            } else {
                                mvdtail = mvdtail + "Paid:                       Tk.      " + receivedTk.getText().toString() + "\n";
                            }

                            if (dueAmount > 0) {
                                mvdtail = mvdtail + "Due:                        Tk.      " + dueAmount + "\n";
                            } else {
                                mvdtail = mvdtail + "Return:                     Tk.      " + returnAmount + "\n\n";
                            }

                        } else {
                            mvdtail = mvdtail + "Net Payable:                Tk.      " + grandTotal + "\n";

                            if (TextUtils.isEmpty(receivedTk.getText().toString())) {
                                mvdtail = mvdtail + "Paid:                       Tk.      " + grandTotal + "\n";
                            } else {
                                mvdtail = mvdtail + "Paid:                       Tk.      " + receivedTk.getText().toString() + "\n";
                            }

                            if (dueAmount > 0) {
                                mvdtail = mvdtail + "Due:                        Tk.      " + dueAmount + "\n";
                            } else {
                                mvdtail = mvdtail + "Return:                     Tk.      " + returnAmount + "\n\n";
                            }

                        }
                    }

                    site = "** Visit " + siteName + " **\n";
                    message = "** " + printMessage + " **\n";
                    copy = "Development By www.terminalbd.com\n\n\n\n";


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
                    if (!printMessage.equals("")) {
                        os.write(message.getBytes());
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
    public void printUsb() {

        serial = 0;
        items = "";

        CmdFactory cmdFactory = new EscFactory();
        Cmd cmd = cmdFactory.create();

        for (int i = 0; i < salesItemList.size(); i++) {
            serial++;

            stock = realm
                    .where(StockItem.class)
                    .equalTo("itemId", Integer.parseInt(salesItemList.get(i)
                            .getSaleStockId()))
                    .findFirst();

            if (TextUtils.isEmpty(stock.getPrintHidden())) {
                if (stock.getPrintName().length() > 35) {

                    itemPrintName = stock.getPrintName().substring(0, 35);

                } else {
                    itemPrintName = stock.getPrintName();
                }

            } else {
                itemPrintName = "Item-1";
            }

            items += String.format("%1$-47s %2$-8s %3$-1s",
                    serial + ". " + itemPrintName,
                    salesItemList.get(i).getSaleQuantity(),
                    salesItemList.get(i).getSaleSubTotal()) + "\n";
        }

        Log.d("Loop", "DATA" + items);

        String header5 = "";
        String header = "";
        String he = "";
        String header2 = "";
        String BILL = "";
        String vio = "";
        String header3 = "";
        String mvdtail = "";
        String header4 = "";
        String offname = "";
        String copy = "";
        String site = "";


        header5 = header5 + pharmacyName + "\n";

        if (!TextUtils.isEmpty(locationName)) {
            he = he + locationName + "\n";
        }
        he = he + pharmacyMobileName + "\n";

        if (!TextUtils.isEmpty(vatRegNo)) {
            he = he + "Vat Regi No:" + vatRegNo + "\n\n";
        } else {
            he = he + "\n";
        }

        if (!TextUtils.isEmpty(anoCustomerName) && !TextUtils.isEmpty(anoCustomerMobile)) {
            header = header + String.format("%1$-40s %2$-1s", "Bill No:" + memoNo, "Sales By:" + salesByUser) + "\n";
            header = header + String.format("%1$-40s %2$-1s", "Date:" + currentDateandTime, "Pay Mode:" + paymentType) + "\n";
            header = header + String.format("%1$-40s %2$-1s", "Customer:" + anoCustomerName, "Mobile:" + anoCustomerMobile) + "\n";
            header = header + "\n";

        } else {
            header = header + String.format("%1$-40s %2$-1s", "Bill No:" + memoNo, "Sales By:" + salesByUser) + "\n";
            header = header + String.format("%1$-40s %2$-1s", "Date:" + currentDateandTime, "Pay Mode:" + paymentType) + "\n";

            header = header + "\n";
        }

        header2 = String.format("%1$-47s %2$-8s %3$-1s", "Item Name", "Qnt", "Amount") + "\n";
        header2 = header2 + "----------------------------------------------------------------\n";
        vio = items;
        vio = vio + "----------------------------------------------------------------\n";

        header3 = "Sub Total:                                      Tk.      " + total + "\n";

        if (!TextUtils.isEmpty(vatRegNo)) {
            mvdtail = "(-)                                             Tk.      " + discountAmount + "\n";
            mvdtail = mvdtail + "Vat:                                            Tk.      " + totalVat + "\n";
            mvdtail = mvdtail + "----------------------------------------------------------------\n";
        } else {
            mvdtail = "(-)                                             Tk.      " + discountAmount + "\n";
            mvdtail = mvdtail + "----------------------------------------------------------------\n";
        }

        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
            mvdtail = mvdtail + "Net Payable:                                    Tk.      " + discountTotal + "\n";
            mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
            mvdtail = mvdtail + "Due:                                            Tk.      " + dueAmount + "\n";
            mvdtail = mvdtail + "Return:                                         Tk.      " + returnAmount + "\n\n";
        } else {
            if (TextUtils.isEmpty(vatRegNo)) {
                mvdtail = mvdtail + "Net Payable:                                    Tk.      " + total + "\n";
                mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                mvdtail = mvdtail + "Due:                                            Tk.      " + dueAmount + "\n";
                mvdtail = mvdtail + "Return:                                         Tk.      " + returnAmount + "\n\n";
            } else {
                mvdtail = mvdtail + "Net Payable:                                    Tk.      " + grandTotal + "\n";
                mvdtail = mvdtail + "Paid:                                           Tk.      " + receivedTk.getText().toString() + "\n";
                mvdtail = mvdtail + "Due:                                            Tk.      " + dueAmount + "\n";
                mvdtail = mvdtail + "Return:                                         Tk.      " + returnAmount + "\n\n";
            }
        }

        site = "** Visit " + siteName + " **\n";
        copy = "Development By www.terminalbd.com\n\n\n\n\n\n";


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

        if (PreferenceManager.getPrinterType(SearchSalesActivity.this).equalsIgnoreCase("usb")) {
            //初始化为针打printer
            TerminalApplication.instance.setCurrentCmdType(BaseEnum.CMD_ESC);
            printerFactory = new ThermalPrinterFactory();
            rtPrinter = printerFactory.create();
            rtPrinter.setPrinterInterface(curPrinterInterface);
            PrinterObserverManager.getInstance().add(this);//添加连接状态监听

            initBroadcast();
        }

    }

    private void connectPrinter() {
        printerInterfaceArrayList = TerminalApplication.getInstance().getPrinterInterfaceArrayList();
        if (printerInterfaceArrayList.size() > 0) {
            PrinterInterface printerInter = printerInterfaceArrayList.get(0);
            rtPrinter.setPrinterInterface(printerInter);//设置连接方式 Connection port settings
            //  print_status.setTag(BaseEnum.HAS_DEVICE);
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

                    print_status.setText("Connected");

                    //  menu.getItem(0).setIcon(ContextCompat.getDrawable(SalePaymentActivity.this, R.drawable.ic_print_connected));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(SearchSalesActivity.this, "Can't connect.Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(SearchSalesActivity.this, "No Printer Added", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(SearchSalesActivity.this, "Printer attached", Toast.LENGTH_SHORT).show();
                    connectPrinter();
                }
            }

        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(broadcastReceiver, intentFilter);
        isReceiverRegistered = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isReceiverRegistered) {
            unregisterReceiver(broadcastReceiver);
            isReceiverRegistered = false;
        }
    }

    private void doDisConnect() {

        if (Integer.parseInt(print_status.toString()) == BaseEnum.NO_DEVICE) {//未选择设备
//            showAlertDialog(getString(R.string.main_discon_click_repeatedly));
            return;
        }

        if (rtPrinter != null && rtPrinter.getPrinterInterface() != null) {
            rtPrinter.disConnect();
            // DisconnectByATDISC();
        }
        print_status.setText("Connected");
        //   menu.getItem(0).setIcon(ContextCompat.getDrawable(SalePaymentActivity.this, R.drawable.ic_print_disconnect));
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
                //   recordVideo();
            } else {
                requestPermissions(NO_PERMISSION.toArray(new String[NO_PERMISSION.size()]), REQUEST_CAMERA);
            }
        } else {
            // recordVideo();
        }

    }

    private void checkDiscount() {
        setup = realm.where(Setup.class).findFirst();
        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
            if (discount_switch.isChecked()) {

                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                    discountAmount = (int) Math.round((total * Double.valueOf(discount_et.getText().toString()) / 100));
                    discountTotal = total - discountAmount;
                    if (!TextUtils.isEmpty(vatRegNo)) {
                        totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                        discountTotal = discountTotal + totalVat;
                        sale_vat_tk.setText(totalVat + "");
                    }
                    //  sale_grand_total_tk.setText("৳ "+ discountTotal);
                    sale_discount_tk.setText("৳ " + discountAmount);

                } else {
                    calculatePrice();
                    discount_et.setError("Please input discount percent");
                    discount_et.clearFocus();
                }

            } else {
                if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                    discountAmount = Integer.parseInt(discount_et.getText().toString());
                    discountTotal = total - discountAmount;
                    if (!TextUtils.isEmpty(vatRegNo)) {
                        totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                        discountTotal = discountTotal + totalVat;
                        sale_vat_tk.setText(totalVat + "");
                    }
                    //  sale_grand_total_tk.setText("৳ " + discountTotal);
                    sale_discount_tk.setText("৳ " + discount_et.getText().toString());
                } else {
                    calculatePrice();
                    discount_et.setError("Please input amount of discount");
                    discount_et.clearFocus();
                }

            }
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

        for (SaleItem item : salesItemList) {
            total += Math.round(Double.valueOf(item.getSaleMrpPrice()) * Double.valueOf(item.getSaleQuantity()));
            totalPP += Math.round(Double.valueOf(item.getSalePpPrice()) * Double.valueOf(item.getSaleQuantity()));

        }

        if (!TextUtils.isEmpty(vatRegNo)) {

            if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                grandTotal = discountTotal + totalVat;

            } else {
                totalVat = (int) Math.round((total * Double.valueOf(setup.getVatPercentage()) / 100));
                grandTotal = total + totalVat;
            }

            // sale_vat_tk.setText(totalVat + "");

            totalProfit = total - totalPP;
            total_item = salesItemList.size();

           /* sale_grand_total_tk.setText("৳ " + String.valueOf(grandTotal) + " (" + total_item + ")");
            sale_total_profit.setText("৳ " + String.valueOf(totalProfit));
            sale_total_tk.setText("৳ " + String.valueOf(total));
            sale_discount_tk.setText("");*/
            sale_return_tk.setText("৳ " + grandTotal);
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

        for (SaleItem item : salesItemList) {
            total += Math.round(Double.valueOf(item.getSaleMrpPrice()) * Double.valueOf(item.getSaleQuantity()));
            totalPP += Math.round(Double.valueOf(item.getSalePpPrice()) * Double.valueOf(item.getSaleQuantity()));
        }

        total_item = salesItemList.size();

        totalProfit = total - totalPP;

        if (total_item > 0) {
            emptytext.setVisibility(View.GONE);
            productqty.setVisibility(View.VISIBLE);
            paymentlayout.setVisibility(View.VISIBLE);
            paymentitemRv.setVisibility(View.GONE);
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
        sale_return_tk.setText("৳ " + total);
        sale_grand_total_tk.setText("৳ " + total);
        sale_total_profit.setText("৳ " + String.valueOf(totalProfit));
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
        PreferenceManager.setLastPairedDeviceSocketAddre(SearchSalesActivity.this, null);
        realm.close();
        try {
            if (mBluetoothSocket != null)
                mBluetoothSocket.close();

        } catch (Exception e) {
            Log.e("Tag", "Exe ", e);
        }
        super.onDestroy();
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
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle mExtra = data.getExtras();
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
                if (resultCode == Activity.RESULT_OK) {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(SearchSalesActivity.this,
                            DeviceListActivity.class);
                    startActivityForResult(connectIntent, REQUEST_CONNECT_DEVICE);
                } else {
                    Toast.makeText(SearchSalesActivity.this, "Message", Toast.LENGTH_SHORT).show();
                }
                break;

      /*      case 101:
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
                break;*/
        }

    }


    @Override
    public void printerObserverCallback(final PrinterInterface printerInterface, final int state) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pb_connect.dismiss();
                switch (state) {
                    case CommonEnum.CONNECT_STATE_SUCCESS:
                        showToast(printerInterface.getConfigObject().toString() + " " + getString(R.string.main_connected));
                        // print_status.setTag(BaseEnum.HAS_DEVICE);
                        curPrinterInterface = printerInterface;//设置为当前连接， set current Printer Interface
                        printerInterfaceArrayList.add(printerInterface);//多连接-添加到已连接列表
                        TerminalApplication.getInstance().setPrinterInterfaceArrayList(printerInterfaceArrayList);
                        rtPrinter.setPrinterInterface(printerInterface);

                        print_status.setText("Connected");
                        // menu.getItem(0).setIcon(ContextCompat.getDrawable(SalePaymentActivity.this, R.drawable.ic_print_connected));

                        break;
                    case CommonEnum.CONNECT_STATE_INTERRUPTED:
                        if (printerInterface != null && printerInterface.getConfigObject() != null) {
                            showToast(printerInterface.getConfigObject().toString() + " " + getString(R.string.main_disconnect));
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
                if (StatusBean.printStatusCmd == PrintStatusCmd.cmd_PrintFinish) {
                    if (StatusBean.blPrintSucc) {
                        Log.e("mydebug", "print ok");
                        showToast("Print finished");
                    } else {
                        showToast(PrinterStatusPareseUtils.getPrinterStatusStr(StatusBean));

                    }


                } else if (StatusBean.printStatusCmd == PrintStatusCmd.cmd_Normal) {
                    showToast("print status：" + PrinterStatusPareseUtils.getPrinterStatusStr(StatusBean));

                }
            }
        });

    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Boolean response) {


        if (response) {
            editTextSearch.setText("");

            cartlayoutbg.setVisibility(View.VISIBLE);
            cartlayout.setVisibility(View.VISIBLE);
            sale_layout.setVisibility(View.VISIBLE);

            editTextSearch.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editTextSearch, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onClickNotify(Boolean response) {
        if (response) {

          /*  adapter = new SalesItemAdapter(SearchSalesActivity.this, salesItemList);
            cartItemRV.setLayoutManager(new LinearLayoutManager(SearchSalesActivity.this, LinearLayoutManager.VERTICAL, false));
            cartItemRV.addItemDecoration(new DividerItemDecoration(SearchSalesActivity.this, DividerItemDecoration.VERTICAL));
          */


          /*  adapter = new SalesItemAdapter(SearchSalesActivity.this, salesItemList);
            paymentitemRv.setLayoutManager(new LinearLayoutManager(SearchSalesActivity.this, LinearLayoutManager.VERTICAL, false));
            paymentitemRv.addItemDecoration(new DividerItemDecoration(SearchSalesActivity.this, DividerItemDecoration.VERTICAL));
          */
            cartItemRV.setAdapter(adapter);
            paymentitemRv.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onClick(String clickname) {


        if (clickname.equals("visible")) {
            paymentlayout.setVisibility(View.VISIBLE);
            receivedTk.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editTextSearch, InputMethodManager.SHOW_IMPLICIT);
        } else {
            paymentlayout.setVisibility(View.GONE);
        }

    }


}