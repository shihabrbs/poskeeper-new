package com.portal.terminalbd.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
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

import com.google.gson.Gson;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.CategoryAdapter;
import com.portal.terminalbd.adapter.ProductGridAdapter;
import com.portal.terminalbd.adapter.ProductListAdapter;
import com.portal.terminalbd.adapter.SalesItemAdapter;
import com.portal.terminalbd.adapter.StockItemAdapter;
import com.portal.terminalbd.adapter.SuggestionAdapter;
import com.portal.terminalbd.adapter.TokenAdapter;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.interfaces.ItemClickListener;
import com.portal.terminalbd.interfaces.ItemDeleteClickListener;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.ModelCategory;
import com.portal.terminalbd.model.ModelCreateProduct;
import com.portal.terminalbd.model.ModelUnit;
import com.portal.terminalbd.model.SaleItem;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.model.TokenNo;
import com.portal.terminalbd.network.APIClient;
import com.portal.terminalbd.utils.ClearableAutoCompleteTextView;
import com.portal.terminalbd.utils.PrefixEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity implements SalesItemclickEvent,ItemDeleteClickListener, ItemClickListener {

    @BindView(R.id.med_search)
    ClearableAutoCompleteTextView search;

    @BindView(R.id.sales_mrp)
    EditText sales_mrp;

    @BindView(R.id.menubtn)
    ImageView menu_btn;

    String search_item = "";

    @BindView(R.id.sales_quantity)
    EditText sales_quantity;

    @BindView(R.id.sales_history_rv)
    RecyclerView saleItemRv;

    /*@BindView(R.id.recycerviewCartItem)
    RecyclerView recycerviewCartItem;*/



    @BindView(R.id.cart_ItemRV)
    RecyclerView cartItemRV;

    @BindView(R.id.cartempty)
    TextView cartempty;

    @BindView(R.id.closesearchbr)
    ImageView closesearchbr;



    @BindView(R.id.category_rv)
    RecyclerView category_rv;

    @BindView(R.id.product_rv)
    RecyclerView product_rv;






    @BindView(R.id.sale_layout)
    RelativeLayout sale_layout;


    @BindView(R.id.search_layout)
    LinearLayout search_layout;

    @BindView(R.id.list_grid_layout)
    LinearLayout list_grid_layout;

    @BindView(R.id.title_layout)
    LinearLayout title_layout;

    @BindView(R.id.category_all)
    TextView category_all;

    @BindView(R.id.barcode_scan)
    Button barcode_scan;

    @BindView(R.id.barcode_scan_list_grid)
    Button barcode_scan_list_grid;

    @BindView(R.id.searchbarLayout)
    ConstraintLayout search_bar_Layout;

    @BindView(R.id.itemtitlelayout)
    LinearLayout itemtitlelayout;

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

    @BindView(R.id.recyclerViewSearchSuggestion)
    RecyclerView recyclerViewSearchSuggestion;

    @BindView(R.id.searchSuggestionrecyclerView)
    RecyclerView searchSuggestionrecyclerView;





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

    @BindView(R.id.sale_total_tk)
    TextView sale_grand_total_tk;
    @BindView(R.id.receivedTk)
    EditText receivedTk;
    @BindView(R.id.return_due_text)
    TextView return_due_text;



    RealmResults<Category> categoryRealmResults;
    private List<Category> categoryList;

    private CategoryAdapter categoryAdapter;

    RealmResults<TokenNo> tokenNos;
    private List<TokenNo> tokenNoList;

    private TokenAdapter tokenAdapter;

    String sales_mode;

    BottomSheetBehavior bottomSheetBehavior;

    CompositeDisposable compositeDisposable;

    @BindView(R.id.list_show_btn)
    FloatingActionButton list_show_btn;

    @BindView(R.id.bottom_sheet)
    LinearLayout bottom_sheet;

    @BindView(R.id.list_grid_search)
    EditText list_grid_search;


    @BindView(R.id.searchback)
    ImageView search_Back;

    @BindView(R.id.searchopentv)
    TextView searchOpentv;

    @BindView(R.id.cart_layout)
    ConstraintLayout cartlayoutbg;



    @BindView(R.id.printMessage)
    Spinner printMessageSpinner;

    @BindView(R.id.mobile_bank_layout)
    LinearLayout mobileBankLayout;

    @BindView(R.id.mobile_payment_form)
    LinearLayout mobilePaymentForm;

    boolean mobilebanklayoutshow = false;

    @BindView(R.id.bank_paynement_layout)
    LinearLayout bankPaynementLayout;

    @BindView(R.id.bank_payment_form)
    LinearLayout bankPaymentForm;
    boolean banklayoutshow = false;

    @BindView(R.id.cash_payment_layout)
    LinearLayout cashPaymentLayout;
    boolean cashlayoutshow = false;

    ArrayList<String> printmessageList = new ArrayList<>();

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        ButterKnife.bind(this);

        getDiscountSpinner();
        getPrintMessageSpinner();

        initpayment();


      /*  setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, null));

        toolbar_title.setText("Add Sales");*/

        realm = Realm.getDefaultInstance();

        /* check barcode scanner mode */
        if (PreferenceManager.getBarcodeMode(SalesActivity.this).equalsIgnoreCase("On")) {
            barcode_scan.setVisibility(View.VISIBLE);
            barcode_scan_list_grid.setVisibility(View.VISIBLE);
        } else if (PreferenceManager.getBarcodeMode(SalesActivity.this).equalsIgnoreCase("Off")) {
            barcode_scan.setVisibility(View.GONE);
            barcode_scan_list_grid.setVisibility(View.GONE);
        }

        searchStockItemList = new ArrayList<>();
        searchStockItemList.clear();
        stockItemRealmResults = realm.where(StockItem.class).findAll();
        searchStockItemList.addAll(realm.copyFromRealm(stockItemRealmResults));


        adapterSuggestion = new ProductListAdapter(this, stockItemList,this);

        searchSuggestionrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchSuggestionrecyclerView.setAdapter(adapterSuggestion);
        adapterSuggestion.notifyDataSetChanged();


        /*
        bottom sheet
         */
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


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
        token no data getting
         */
        tokenNoList = new ArrayList<>();
        tokenNos = realm.where(TokenNo.class).findAll();
        tokenNoList.addAll(realm.copyFromRealm(tokenNos));


        closesearchbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  editTextSearch.setText("");
                hideSoftKeyboard(view);
            }
        });

        //search click
        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {


                Object item = parent.getItemAtPosition(position);
                if (item instanceof StockItem) {
                    stockItem = (StockItem) item;
                    sales_mrp.setText(String.valueOf(stockItem.getSalesPrice()));
                    search_item = String.valueOf(stockItem.getName());
                    sales_quantity.requestFocus();
                    Toast.makeText(SalesActivity.this, "" + search_item, Toast.LENGTH_SHORT).show();

                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.showClearButton();

            }
        });


        searchOpentv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_bar_Layout.setVisibility(View.VISIBLE);
                clearbtn.setVisibility(View.VISIBLE);
                searchEdittext.setFocusableInTouchMode(true);
                searchEdittext.setFocusable(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                searchEdittext.requestFocus();
            }
        });

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

        search.setOnClearListener(new ClearableAutoCompleteTextView.OnClearListener() {
            @Override
            public void onClear() {

                search.setText("");
                sales_mrp.setText("");
                sales_quantity.setText("");

            }
        });

        editTextSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                sale_layout.setVisibility(View.GONE);
                cartlayout.setVisibility(View.GONE);
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

               if(editTextSearch.length() == 0){

               }{
                    if (editTextSearch.length() > 0) {
                        closesearchbr.setVisibility(View.VISIBLE);
                        searchSuggestionrecyclerView.setVisibility(View.VISIBLE);

                    } else {
                        sale_layout.setVisibility(View.VISIBLE);
                        cartlayout.setVisibility(View.VISIBLE);
                        closesearchbr.setVisibility(View.GONE);
                        searchSuggestionrecyclerView.setVisibility(View.GONE);
                    }
                    final List<StockItem> filteredModelList = filter(stockItemList, "" + s);
                    adapterSuggestion.setFilter(filteredModelList);
                }


            }
        });


        productqty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SalesActivity.this, "Click", Toast.LENGTH_SHORT).show();

              //  cartlayoutbg.setVisibility(View.VISIBLE);
                sale_layout.setVisibility(View.VISIBLE);
                cartlayout.setVisibility(View.VISIBLE);
                searchSuggestionrecyclerView.setVisibility(View.GONE);
                hideSoftKeyboard(view);
            }
        });




        adapter = new SalesItemAdapter(SalesActivity.this, salesItemList,this);
        cartItemRV.setLayoutManager(new LinearLayoutManager(SalesActivity.this, LinearLayoutManager.VERTICAL, false));
        cartItemRV.addItemDecoration(new DividerItemDecoration(SalesActivity.this, DividerItemDecoration.VERTICAL));
        cartItemRV.setAdapter(adapter);









        if (PreferenceManager.getModule(SalesActivity.this).equalsIgnoreCase("restaurant")) {
             /*
        token no adapter
         */


        } else {

        }

        /*
        getting sales mode
         */
        sales_mode = PreferenceManager.getSalesMode(SalesActivity.this);


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

            /**
             * setup serach function here
             */
            search.setAdapter(new SuggestionAdapter(this, stockItemList));


            list_grid_layout.setVisibility(View.GONE);
            search_layout.setVisibility(View.VISIBLE);
            list_show_btn.hide();

        } else if (sales_mode.equalsIgnoreCase("List")) {

            loadData(0, 50);



            /*
        category adapter
         */
            categoryAdapter = new CategoryAdapter(SalesActivity.this, categoryList);
            category_rv.setLayoutManager(new LinearLayoutManager(SalesActivity.this, LinearLayoutManager.HORIZONTAL, false));
            category_rv.addItemDecoration(new DividerItemDecoration(SalesActivity.this, DividerItemDecoration.HORIZONTAL));
            category_rv.setAdapter(categoryAdapter);

            categoryAdapter.SetItemClickListener(this);




         /*
        list stock item adapter
         */
            linearLayoutManager = new LinearLayoutManager(SalesActivity.this, LinearLayoutManager.VERTICAL, false);
            productListAdapter = new ProductListAdapter(SalesActivity.this, stockItemList,this);
            product_rv.setLayoutManager(linearLayoutManager);
            //product_rv.addItemDecoration(new DividerItemDecoration(SalesActivity.this, DividerItemDecoration.VERTICAL));
            product_rv.setAdapter(productListAdapter);

//        productListAdapter.setFilter(stockItemList);

            search_layout.setVisibility(View.GONE);
            list_grid_layout.setVisibility(View.VISIBLE);


            category_all.setBackground(getResources().getDrawable(R.drawable.selected_category_background));
            category_all.setTextColor(Color.WHITE);

        } else if (sales_mode.equalsIgnoreCase("Grid")) {
            loadData(0, 50);




            /*
        category adapter
         */
            categoryAdapter = new CategoryAdapter(SalesActivity.this, categoryList);
            category_rv.setLayoutManager(new LinearLayoutManager(SalesActivity.this, LinearLayoutManager.HORIZONTAL, false));
            category_rv.addItemDecoration(new DividerItemDecoration(SalesActivity.this, DividerItemDecoration.HORIZONTAL));
            category_rv.setAdapter(categoryAdapter);

            categoryAdapter.SetItemClickListener(this);

             /*
        Grid stock item adapter
         */
            gridLayoutManager = new GridLayoutManager(SalesActivity.this, 3);
            productGridAdapter = new ProductGridAdapter(SalesActivity.this, stockItemList);
            product_rv.setLayoutManager(gridLayoutManager);
            product_rv.addItemDecoration(new DividerItemDecoration(SalesActivity.this, DividerItemDecoration.VERTICAL));
            product_rv.addItemDecoration(new DividerItemDecoration(SalesActivity.this, DividerItemDecoration.HORIZONTAL));
            product_rv.setAdapter(productGridAdapter);

//            productGridAdapter.setFilter(stockItemList);


            search_layout.setVisibility(View.GONE);
            title_layout.setVisibility(View.GONE);
            list_grid_layout.setVisibility(View.VISIBLE);


            category_all.setBackground(getResources().getDrawable(R.drawable.selected_category_background));
            category_all.setTextColor(Color.WHITE);

        }

        checkSaleLayoutVisibility();

        list_grid_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() > 0) {

                    if (sales_mode.equalsIgnoreCase("List")) {
                        final List<StockItem> filteredModelList = filter(searchStockItemList, charSequence.toString());
                        productListAdapter.setFilter(filteredModelList);


                    } else if (sales_mode.equalsIgnoreCase("Grid")) {
                        final List<StockItem> filteredModelList = filter(searchStockItemList, charSequence.toString());
                        productGridAdapter.setFilter(filteredModelList);

                    }
                } else {
                    if (sales_mode.equalsIgnoreCase("List")) {
                        productListAdapter.setFilter(stockItemList);

                    } else if (sales_mode.equalsIgnoreCase("Grid")) {
                        productGridAdapter.setFilter(stockItemList);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        product_rv.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isLoading = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (sales_mode.equalsIgnoreCase("List")) {
                    currentItems = linearLayoutManager.getChildCount();
                    totalItems = linearLayoutManager.getItemCount();
                    scrollOutitems = linearLayoutManager.findFirstVisibleItemPosition();

                } else {
                    currentItems = gridLayoutManager.getChildCount();
                    totalItems = gridLayoutManager.getItemCount();
                    scrollOutitems = gridLayoutManager.findFirstVisibleItemPosition();
                }


                if (isLoading && (currentItems + scrollOutitems == totalItems)) {

//                    loadMoreProgressbar.setVisibility(View.VISIBLE);
                    page = page + limit;
                    cur_limit = cur_limit + limit;

                    loadData(page, cur_limit);

                    isLoading = false;

                    if (sales_mode.equalsIgnoreCase("List")) {
                        productListAdapter.notifyDataSetChanged();
                    } else {
                        productGridAdapter.notifyDataSetChanged();
                    }

                }
            }
        });


    }

    private void initpayment() {

        TextView sale_total_tk = findViewById(R.id.sale_total_tk);

        Switch discount_switch = findViewById(R.id.discount_switch);

        if (!TextUtils.isEmpty(discount_et.getText().toString())) {
            discountAmount = Integer.parseInt(discount_et.getText().toString());
            discountTotal = total - discountAmount;
            sale_return_tk.setText("৳ "+discountTotal);
        }

        discount_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (discount_switch.isChecked())
                {

                    discount_et.setHint("%");
                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        discountAmount = (int) Math.round((total * Double.valueOf(discount_et.getText().toString()) / 100));
                        discountTotal = total - discountAmount;
                        if (!TextUtils.isEmpty(vatRegNo))
                        {
                            totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                            discountTotal = discountTotal+totalVat;
                            sale_vat_tk.setText(totalVat+"");
                        }
                        sale_discount_tk.setText("৳ "+discountAmount);
                        sale_return_tk.setText("৳ "+discountTotal);

                    }else
                    {
                        discount_et.setError("Please input discount percent");
                        discount_et.clearFocus();

                    }
                }else
                {
                    discount_et.setHint("TK");
                    if (!TextUtils.isEmpty(discount_et.getText().toString())) {
                        discountAmount = Integer.parseInt(discount_et.getText().toString());
                        discountTotal = total - discountAmount;
                        if (!TextUtils.isEmpty(vatRegNo))
                        {
                            totalVat = (int) Math.round((discountTotal * Double.valueOf(setup.getVatPercentage()) / 100));
                            discountTotal = discountTotal+totalVat;
                            sale_vat_tk.setText(totalVat+"");
                        }
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

                            sale_discount_tk.setText("৳ " + discountAmount);
                        }else
                        {
                            discount_et.setError("Please input amount of discount");
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
                    sale_return_tk.setText("৳ "+discountTotal);
                }

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
                            //sale_grand_total_tk.setText("৳ "+ discountTotal);
                            sale_discount_tk.setText("৳ "+discountAmount);

                        }else
                        {
                            calculatePrices();
                            discount_et.setError("Please input discount percent");
                            discount_et.setText("");
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
                            //sale_grand_total_tk.setText("৳ " + discountTotal);
                            sale_discount_tk.setText("৳ " + discount_et.getText().toString());
                        }else
                        {
                            calculatePrices();
                            discount_et.setError("Please input amount of discount");
                            discount_et.clearFocus();
                            discount_et.setText("");
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

            }
        });
        mobileBankLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobilebanklayoutshow == false) {
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
                if (banklayoutshow == false) {
                    bankPaymentForm.setVisibility(View.VISIBLE);
                    mobilePaymentForm.setVisibility(View.GONE);
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

                    if (TextUtils.isEmpty(discount_et.getText().toString())) {

                        if (TextUtils.isEmpty(vatRegNo)) {

                            if (Integer.parseInt(receivedTk.getText().toString()) >= total) {
                                dueAmount = 0;
                                returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - total);
                                sale_return_tk.setText("৳ " + returnAmount);
                                return_due_text.setText("Return Tk");

                            } else {
                                returnAmount = 0;
                                return_due_text.setText("Due Tk");
                                dueAmount = (int) (total - Integer.parseInt(receivedTk.getText().toString()));
                                sale_return_tk.setText("৳ " + dueAmount);
                            }

                        }else
                        {
                            if (Integer.parseInt(receivedTk.getText().toString()) >= grandTotal) {
                                dueAmount = 0;
                                returnAmount = (int) (Integer.parseInt(receivedTk.getText().toString()) - grandTotal);
                                sale_return_tk.setText("৳ " + returnAmount);
                                return_due_text.setText("Return Tk");

                            } else {
                                returnAmount = 0;
                                return_due_text.setText("Due Tk");
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
                            return_due_text.setText("Return Tk");

                        } else {
                            returnAmount = 0;
                            return_due_text.setText("Due Tk");
                            dueAmount = (int) (discountTotal - Integer.parseInt(receivedTk.getText().toString()));
                            sale_return_tk.setText("৳ " + dueAmount);
                        }
                    }

                } else {
                    return_due_text.setText("Due Tk");

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


    }

    private void getPrintMessageSpinner() {


        printmessageList.add("Print Message");
        printmessageList.add("Message 1");
        printmessageList.add("Message 2");


        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, printmessageList);

        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        printMessageSpinner.setAdapter(adapter2);


        printMessageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  String valu = adapterView.getItemAtPosition(i).toString();
                // category_id = categoryArrayList.get(i).getName();


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

    private void getDiscountSpinner() {

        ArrayList<String> number = new ArrayList<>();
        number.add("Dis(%)");
        number.add("1");
        number.add("2");
        number.add("3");
        number.add("4");
        number.add("6");
        number.add("7");
        number.add("8");
        number.add("9");
        number.add("10");
        Spinner spinnerDiscount = findViewById(R.id.spinnerid);

        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, number);

        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerDiscount.setAdapter(adapter2);


        spinnerDiscount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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


                Toast.makeText(SalesActivity.this, "sync", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ic_syncicons:
                        Toast.makeText(SalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ic_sales:
                        Toast.makeText(SalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                }
                //Toast.makeText(SalesActivity.this, ""+menuItem, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        popup.show();

    }

    private void showTermsDialog() {


        LayoutInflater layoutInflater = LayoutInflater.from(SalesActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_create_customer, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(SalesActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView close = view.findViewById(R.id.close);
        ImageView resetBtn = view.findViewById(R.id.imageView12);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        ConstraintLayout back = view.findViewById(R.id.btn_back);
        Spinner spinner = view.findViewById(R.id.spinnercatid);
        Spinner spinnerUnit = view.findViewById(R.id.spinnerunitid);
        EditText name = view.findViewById(R.id.nameid);
        EditText mrp = view.findViewById(R.id.mrpid);
        EditText pp = view.findViewById(R.id.ppid);
        EditText brand = view.findViewById(R.id.Brandid);
        EditText miniQty = view.findViewById(R.id.minqtyid);
        EditText openingQty = view.findViewById(R.id.openingqtyid);
        EditText description = view.findViewById(R.id.descriptionid);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

                TextView toastText = layout.findViewById(R.id.toasttext);
                toastText.setText("Create Customer Successfully");

                final Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

/*
                ModelCreateProduct modelCreateProduct = new ModelCreateProduct();
                modelCreateProduct.setName("" + name.getText());
                modelCreateProduct.setCategory("" + category_id);
                modelCreateProduct.setBrandName("" + brand.getText());
                modelCreateProduct.setPrice("" + mrp.getText());
                modelCreateProduct.setUnit("" + utility_id);
                modelCreateProduct.setOpeningQuantity("" + openingQty.getText());
                modelCreateProduct.setMinQuantity("" + miniQty.getText());
                //  stockItemList.add(new StockItem(100,"",""+utility_id,""+name.getText(),Integer.parseInt(""+miniQty.getText()),Double.parseDouble(""+mrp.getText())));
                createStockProduct(modelCreateProduct, "" + PreferenceManager.getSecretKey(getApplicationContext()));*/
            }
        });


        alertDialog.show();
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
            itemtitlelayout.setVisibility(View.GONE);
           // sale_layout.setVisibility(View.VISIBLE);
            title_layout.setVisibility(View.VISIBLE);
            cartempty.setVisibility(View.GONE);


            calculatePrice();
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//            list_show_btn.setVisibility(View.GONE);
          //  sale_layout.setVisibility(View.GONE);
            title_layout.setVisibility(View.GONE);
            productqty.setText("0");
            cartempty.setVisibility(View.VISIBLE);

        }
    }


    @OnClick(R.id.list_show_btn)
    void onShowBtn() {
        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        } else {

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }


    @OnClick(R.id.sale_layout)
    void sale_layout() {

        cartlayoutbg.setVisibility(View.VISIBLE);
        calculatePrices();
        checkDiscount();

        cartlayoutbg.setVisibility(View.VISIBLE);



    }

    private void checkDiscount() {
        setup = realm.where(Setup.class).findFirst();
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
                  //  sale_grand_total_tk.setText("৳ "+ discountTotal);
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
                  //  sale_grand_total_tk.setText("৳ " + discountTotal);
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



    @OnClick(R.id.backbtn)
    void backbtn() {
        onBackPressed();
    }



    @OnClick(R.id.add_btn)
    void add_btn() {


        if (TextUtils.isEmpty(search.getText().toString())) {
            search.setError("Please add a product");
            search.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(sales_mrp.getText().toString())) {
            sales_mrp.setError("Mrp cannot empty");
            sales_mrp.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(sales_quantity.getText().toString())) {
            sales_quantity.setError("Please enter quantity");
            sales_quantity.requestFocus();
            return;
        }

        if (search_item == "") {

            search.setError("Please Select Valid Product");


        } else {

            realm.beginTransaction();
            SaleItem saleItem = realm.createObject(SaleItem.class, UUID.randomUUID().toString());
            saleItem.setSaleStockId(String.valueOf(stockItem.getItemId()));
            saleItem.setSaleItemName(search.getText().toString().trim());
            saleItem.setSalePpPrice(String.valueOf(stockItem.getPurchasePrice()));
            saleItem.setSaleMrpPrice(sales_mrp.getText().toString());
            saleItem.setSaleQuantity(sales_quantity.getText().toString());
            int sub_total = (int) Math.round(Double.valueOf(sales_mrp.getText().toString()) * Double.valueOf(sales_quantity.getText().toString()));
            saleItem.setSaleSubTotal(String.valueOf(sub_total));
            realm.commitTransaction();

            sale_layout.setVisibility(View.VISIBLE);

            calculatePrice();


            search.setText("");
            sales_mrp.setText("");
            sales_quantity.setText("");

            search.requestFocus();

            adapter.notifyDataSetChanged();

            search_item = "";
        }


    }


    @OnClick(R.id.category_all)
    void onAllCategory() {

        loadData(0, 50);

        if (sales_mode.equalsIgnoreCase("List")) {
            productListAdapter.setFilter(stockItemList);
            productListAdapter.notifyDataSetChanged();

        } else if (sales_mode.equalsIgnoreCase("Grid")) {
            productGridAdapter.setFilter(stockItemList);
            productGridAdapter.notifyDataSetChanged();

        }

        category_all.setBackground(getResources().getDrawable(R.drawable.selected_category_background));
        category_all.setTextColor(Color.WHITE);
        categoryAdapter.SetPosition(-1);
        categoryAdapter.notifyDataSetChanged();
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

        for (SaleItem item : salesItemList) {
            total += Math.round(Double.valueOf(item.getSaleMrpPrice()) * Double.valueOf(item.getSaleQuantity()));

        }

        total_item = salesItemList.size();




        if (total_item > 0) {
            productqty.setVisibility(View.VISIBLE);
        } else {
            productqty.setVisibility(View.VISIBLE);
        }
        productqty.setText(String.valueOf(total_item));
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

    @Override
    public void onClick(View view, final int position) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                stockItemList.clear();
                RealmResults<StockItem> oldStockItem = realm.
                        where(StockItem.class)
                        .beginGroup()
                        .equalTo("categoryId", categoryList.get(position).getCategoryId())
                        .endGroup()
                        .findAll();
                stockItemList.addAll(realm.copyFromRealm(oldStockItem));

            }
        });

        if (sales_mode.equalsIgnoreCase("List")) {
            productListAdapter.setFilter(stockItemList);
            productListAdapter.notifyDataSetChanged();

        } else if (sales_mode.equalsIgnoreCase("Grid")) {
            productGridAdapter.setFilter(stockItemList);
            productGridAdapter.notifyDataSetChanged();

        }


        category_all.setBackground(getResources().getDrawable(R.drawable.edit_text_round_corner_border));
        category_all.setTextColor(Color.BLACK);
        categoryAdapter.SetPosition(position);
        categoryAdapter.notifyDataSetChanged();

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

    @OnClick(R.id.barcode_scan)
    public void onBarcodeScan() {

        startActivityForResult(new Intent(SalesActivity.this, BarcodeScannerActivity.class), 102);
    }

    @OnClick(R.id.barcode_scan_list_grid)
    public void onBarcodeScanList() {

        startActivityForResult(new Intent(SalesActivity.this, BarcodeScannerActivity.class), 102);
    }

    @Override
    public void onClick(Boolean response) {

    }

    @Override
    public void onClickNotify(Boolean response) {

    }

    @Override
    public void onClick(String clickname) {

    }
}
