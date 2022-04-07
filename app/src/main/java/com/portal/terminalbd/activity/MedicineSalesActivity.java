package com.portal.terminalbd.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.CategoryAdapter;
import com.portal.terminalbd.adapter.ProductGridAdapter;
import com.portal.terminalbd.adapter.ProductListAdapter;
import com.portal.terminalbd.adapter.SalesItemAdapter;
import com.portal.terminalbd.adapter.SuggestionAdapter;
import com.portal.terminalbd.adapter.TokenAdapter;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.interfaces.ItemClickListener;
import com.portal.terminalbd.interfaces.ItemDeleteClickListener;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.portal.terminalbd.model.Category;
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

public class MedicineSalesActivity extends  AppCompatActivity implements SalesItemclickEvent,ItemDeleteClickListener, ItemClickListener{
    @BindView(R.id.med_search)
    ClearableAutoCompleteTextView search;



    @BindView(R.id.menubtn)
    ImageView menu_btn;

    String search_item = "";



    @BindView(R.id.sales_history_rv)
    RecyclerView saleItemRv;



    @BindView(R.id.cart_ItemRV)
    RecyclerView cartItemRV;

    @BindView(R.id.cartempty)
    TextView cartempty;

    @BindView(R.id.closesearchbr)
    ImageView closesearchbr;

    @BindView(R.id.productqty)
    TextView productqty;

    @BindView(R.id.category_rv)
    RecyclerView category_rv;

    @BindView(R.id.product_rv)
    RecyclerView product_rv;

    @BindView(R.id.token_no_rv)
    RecyclerView token_no_rv;

    @BindView(R.id.total_item_count)
    TextView total_item_count;

    @BindView(R.id.grand_total_text)
    TextView grand_total_text;

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






    @BindView(R.id.editTextSearch)
    EditText editTextSearch;

    @BindView(R.id.cartlayout)
    ConstraintLayout cartlayout;



    @BindView(R.id.searchSuggestionrecyclerView)
    RecyclerView searchSuggestionrecyclerView;






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





    /*
    toolbar
     */
  /*  @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;*/

    RealmResults<Category> categoryRealmResults;
    private List<Category> categoryList;

    private CategoryAdapter categoryAdapter;

    RealmResults<TokenNo> tokenNos;
    private List<TokenNo> tokenNoList;

    private TokenAdapter tokenAdapter;

    String sales_mode;

    BottomSheetBehavior bottomSheetBehavior;

    CompositeDisposable compositeDisposable;




    @BindView(R.id.list_grid_search)
    EditText list_grid_search;

    @BindView(R.id.tokenLayout)
    LinearLayout tokenLayout;



    @BindView(R.id.searchopentv)
    TextView searchOpentv;








    boolean mobilebanklayoutshow = false;



    boolean banklayoutshow = false;


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
        setContentView(R.layout.activity_medicine_sales);


        ButterKnife.bind(this);

        

        realm = Realm.getDefaultInstance();

        /* check barcode scanner mode */
        if (PreferenceManager.getBarcodeMode(MedicineSalesActivity.this).equalsIgnoreCase("On")) {
            barcode_scan.setVisibility(View.VISIBLE);
            barcode_scan_list_grid.setVisibility(View.VISIBLE);
        } else if (PreferenceManager.getBarcodeMode(MedicineSalesActivity.this).equalsIgnoreCase("Off")) {
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
                editTextSearch.setText("");
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

                    search_item = String.valueOf(stockItem.getName());

                    Toast.makeText(MedicineSalesActivity.this, "" + search_item, Toast.LENGTH_SHORT).show();

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


                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

            }
        });








        search.setOnClearListener(new ClearableAutoCompleteTextView.OnClearListener() {
            @Override
            public void onClear() {

                search.setText("");

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

                if (editTextSearch.length() > 0) {

                    cartlayout.setVisibility(View.GONE);
                    closesearchbr.setVisibility(View.VISIBLE);
                    searchSuggestionrecyclerView.setVisibility(View.VISIBLE);

                } else {
                    cartlayout.setVisibility(View.VISIBLE);
                    closesearchbr.setVisibility(View.GONE);
                    searchSuggestionrecyclerView.setVisibility(View.GONE);
                }
                final List<StockItem> filteredModelList = filter(stockItemList, "" + s);
                adapterSuggestion.setFilter(filteredModelList);


            }
        });



        adapter = new SalesItemAdapter(MedicineSalesActivity.this, salesItemList,this);
        cartItemRV.setLayoutManager(new LinearLayoutManager(MedicineSalesActivity.this, LinearLayoutManager.VERTICAL, false));
        cartItemRV.addItemDecoration(new DividerItemDecoration(MedicineSalesActivity.this, DividerItemDecoration.VERTICAL));
        cartItemRV.setAdapter(adapter);





        if (PreferenceManager.getModule(MedicineSalesActivity.this).equalsIgnoreCase("restaurant")) {
             /*
        token no adapter
         */

            tokenAdapter = new TokenAdapter(MedicineSalesActivity.this, tokenNoList);
            token_no_rv.setLayoutManager(new LinearLayoutManager(MedicineSalesActivity.this, LinearLayoutManager.HORIZONTAL, false));
            token_no_rv.addItemDecoration(new DividerItemDecoration(MedicineSalesActivity.this, DividerItemDecoration.HORIZONTAL));
            token_no_rv.setAdapter(tokenAdapter);

            tokenLayout.setVisibility(View.VISIBLE);
        } else {
            tokenLayout.setVisibility(View.GONE);
        }

        /*
        getting sales mode
         */
        sales_mode = PreferenceManager.getSalesMode(MedicineSalesActivity.this);


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


        }

        checkSaleLayoutVisibility();






    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplication().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
                        Toast.makeText(MedicineSalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ic_syncicons:
                        Toast.makeText(MedicineSalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ic_sales:
                        Toast.makeText(MedicineSalesActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                }
                //Toast.makeText(MedicineSalesActivity.this, ""+menuItem, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        popup.show();

    }

    private void showTermsDialog() {


        LayoutInflater layoutInflater = LayoutInflater.from(MedicineSalesActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_create_customer, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MedicineSalesActivity.this);

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




    public void checkSaleLayoutVisibility() {
        if (salesItemList.size() > 0) {
            if (sales_mode.equalsIgnoreCase("List")
                    || sales_mode.equalsIgnoreCase("Grid")) {

            }

            sale_layout.setVisibility(View.VISIBLE);
            title_layout.setVisibility(View.VISIBLE);
            cartempty.setVisibility(View.GONE);



        } else {


            sale_layout.setVisibility(View.GONE);
            title_layout.setVisibility(View.GONE);
            productqty.setText("0");
            cartempty.setVisibility(View.VISIBLE);

        }
    }





    @OnClick(R.id.productqty)
    void productqty() {
       /* cartlayoutbg.setVisibility(View.VISIBLE);

        //set cart item adapter
        salesItemAdapter = new SalesItemAdapter(SalesActivity.this, salesItemList);
        saleItemRview.setLayoutManager(new LinearLayoutManager(SalesActivity.this, LinearLayoutManager.VERTICAL, false));
        saleItemRview.addItemDecoration(new DividerItemDecoration(SalesActivity.this, DividerItemDecoration.VERTICAL));
        saleItemRview.setAdapter(salesItemAdapter);*/
        Toast.makeText(this, "Payment Page", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.backbtn)
    void backbtn() {
        onBackPressed();
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

        startActivityForResult(new Intent(MedicineSalesActivity.this, BarcodeScannerActivity.class), 102);
    }

    @OnClick(R.id.barcode_scan_list_grid)
    public void onBarcodeScanList() {

        startActivityForResult(new Intent(MedicineSalesActivity.this, BarcodeScannerActivity.class), 102);
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
