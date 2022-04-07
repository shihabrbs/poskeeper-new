package com.portal.terminalbd.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.StockItemAdapter;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.interfaces.ItemDeleteClickListener;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.ModelCategory;
import com.portal.terminalbd.model.ModelCreateProduct;
import com.portal.terminalbd.model.ModelUnit;
import com.portal.terminalbd.model.PurchaseItem;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.network.APIClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;

public class StockActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ItemDeleteClickListener {
    private String filePath;
    private static final int PICK_PHOTO = 1958;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    @BindView(R.id.stock_item_rv)
    RecyclerView stock_item_rv;
    ArrayList<ModelUnit> unitArrayList = new ArrayList<>();
    ArrayList unitNameList = new ArrayList<>();
    String utility_id;

    ArrayList<ModelCategory> categoryArrayList = new ArrayList<>();
    ArrayList categoryNameList = new ArrayList<>();
    String category_id;

    StockItemAdapter adapter;

    Realm realm;

    RealmResults<StockItem> stockItems;
    RealmResults<PurchaseItem> salesItemList;
    List<StockItem> stockItemList;

    RealmResults<Category> vendors;

    List<Category> pCategoryList;

    boolean isShowing = false;
    boolean isExportShowing = false;

    boolean isSearchShow = false;
    boolean isFilterShow = false;

    Animation rotation;

    CompositeDisposable compositeDisposable;
    ProgressDialog progressDialog;
    EditText searchtextview;
    ImageView closeSearch;
    CardView constraintLayoutSearch;
    /*
  toolbar
   */
/*    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    */


    @BindView(R.id.filterbtn)
    ImageView filter;

   /* @BindView(R.id.view)
    View searchfilterDivider;*/

    @BindView(R.id.filtersearchbtn)
    Button filtersearchbutton;

    @BindView(R.id.outlinedTextField4)
    TextInputLayout edittextstart;

    @BindView(R.id.outlinedTextField5)
    TextInputLayout edittextend;

    @BindView(R.id.menubtn)
    ImageView menubtn;



    @BindView(R.id.spinnercat)
    Spinner categoryspinner;

    @BindView(R.id.spinnerbrand)
    Spinner brandspinner;

    @BindView(R.id.spinnermode)
    Spinner modespinner;

    @BindView(R.id.fab)
    FloatingActionButton fabb;

    @BindView(R.id.filter_layoutt)
    ConstraintLayout filter_layoutt;

    @BindView(R.id.tv_cartno)
    TextView tv_cartno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String language = PreferenceManager.getLanguage(StockActivity.this);
        setLocale(language);
        loadLocale();
        setContentView(R.layout.activity_stock);


        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        searchtextview = findViewById(R.id.editTextSearch);
        closeSearch = findViewById(R.id.closesearchbr);
        constraintLayoutSearch = findViewById(R.id.constraintLayoutsearch);
        compositeDisposable = new CompositeDisposable();
        ModelCategory modelCategory = new ModelCategory();
        modelCategory.setCategoryId(100);
        modelCategory.setName(""+getResources().getString(R.string.string_selectacat));
        categoryArrayList.add(modelCategory);

        ModelUnit modelUnit = new ModelUnit();
        modelUnit.setUnitId(100);
        String name = getResources().getString(R.string.string_selectaunit);
        modelUnit.setName(""+name);
        unitArrayList.add(modelUnit);

        getUnits();
        getCategory();

/*        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, null));

        toolbar_title.setText(R.string.stocktitle);
        toolbar_title.setTextColor(R.color.colorPrimary);*/


        stockItemList = new ArrayList<>();

        stockItems = realm.where(StockItem.class).findAll();
        salesItemList = realm.where(PurchaseItem.class).findAll();

        stockItemList.addAll(realm.copyFromRealm(stockItems));

        if (stockItemList.size() >0){
            tv_cartno.setText(""+salesItemList.size());
        }




        Log.i("TOTAL", "" + stockItemList.size());
        // Toast.makeText(this, "Total: "+stockItemList.size(), Toast.LENGTH_SHORT).show();

        adapter = new StockItemAdapter(this, stockItemList,this);

        stock_item_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //  stock_item_rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        stock_item_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        stock_item_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                if (dy > 5) { // scrolling down
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fabb.setVisibility(View.VISIBLE);
                        }
                    }, 4000); // delay of 2 seconds before hiding the fab

                } else if (dy < 0) { // scrolling up

                   fabb.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) { // No scrolling
                    new Handler().postDelayed(new Runnable() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void run() {
                          //  fabb.setVisibility(View.GONE);
                        }
                    }, 2000); // delay of 2 seconds before hiding the fab
                }

            }
        });



        fabb.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                //stock_item_rv.scrollToPosition(0);
                stock_item_rv.smoothScrollToPosition(0);
                fabb.setVisibility(View.GONE);
            }
        });


        filtersearchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StockActivity.this, "Filter", Toast.LENGTH_SHORT).show();
               /* edittextstart.setVisibility(View.GONE);
                edittextend.setVisibility(View.GONE);
                categoryspinner.setVisibility(View.GONE);
                brandspinner.setVisibility(View.GONE);
                modespinner.setVisibility(View.GONE);
                filtersearchbutton.setVisibility(View.GONE);*/
                filter_layoutt.setVisibility(View.GONE);
                isSearchShow = false;
                isFilterShow = false;
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFilterShow == false) {
                   /* searchfilterDivider.setVisibility(View.VISIBLE);
                    filtersearchbutton.setVisibility(View.VISIBLE);
                   */
                    filter_layoutt.setVisibility(View.VISIBLE);
                    isFilterShow = true;
                } else {
                   /* searchfilterDivider.setVisibility(View.GONE);
                    filtersearchbutton.setVisibility(View.GONE);
                   */
                    filter_layoutt.setVisibility(View.GONE);

                    isFilterShow = false;
                }


            }
        });


        searchtextview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //closeSearch.setVisibility(View.VISIBLE);


                return false;
            }
        });


        closeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch.setVisibility(View.GONE);
                searchtextview.setText("");

                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchtextview.getWindowToken(), 0);
            }
        });


        searchtextview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

               /* if (searchtextview.length()<4 && searchtextview.length()>0){
                    searchtextview.setError("aro dao");
                }
*/
                //product_filter(s.toString());
                if (s.length()>0){
                    closeSearch.setVisibility(View.VISIBLE);
                }else {
                    closeSearch.setVisibility(View.GONE);
                }

                final List<StockItem> filteredModelList = filter(stockItemList, "" + s);
                adapter.setFilter(filteredModelList);
            }
        });

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });


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

    private void getCategory() {
        compositeDisposable.add(APIClient.getInstance().getCategories("" + PreferenceManager.getSecretKey(getApplicationContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelCategory>>>() {
                    @Override
                    public void onNext(final Response<List<ModelCategory>> response) {

                        try {
                            categoryArrayList.addAll(response.body());
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


    private void getUnits() {

        compositeDisposable.add(APIClient.getInstance().getUnit("" + PreferenceManager.getSecretKey(getApplicationContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelUnit>>>() {
                    @Override
                    public void onNext(final Response<List<ModelUnit>> response) {

                        try {
                            unitArrayList.addAll(response.body());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        // Toast.makeText(StockActivity.this, ""+unitArrayList.get(10).getName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        final List<StockItem> filteredModelList = filter(stockItemList, newText);
        adapter.setFilter(filteredModelList);

        return true;
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        final MenuItem item = menu.findItem(R.id.ic_search);
        final MenuItem item2 = menu.findItem(R.id.ic_addicons);
        final MenuItem item3 = menu.findItem(R.id.ic_syncicons);
       *//* final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        ImageView icon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        icon.setColorFilter(Color.WHITE);

        ImageView searchClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        searchClose.setColorFilter(Color.WHITE);

        EditText editText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        editText.setHintTextColor(getResources().getColor(R.color.white));
        editText.setTextColor(getResources().getColor(R.color.white));

        searchView.setOnQueryTextListener(this);*//*

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showTermsDialog();
                return false;
            }
        });

        item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

                TextView toastText = layout.findViewById(R.id.toasttext);
                toastText.setText("Sync Stock Item Successfully");

                final Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP, 0, 50);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                syncStock();
                toast.show();
                //Toast.makeText(StockActivity.this, "Sync Stock Item", Toast.LENGTH_SHORT).show();
               *//* Tools.snackInfo_Listener((Activity) getApplicationContext(), "Sync Stock Item Successfully", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });*//*

                return false;
            }
        });

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (isSearchShow == false) {
                    searchlayoutmain.setVisibility(View.VISIBLE);
                    constraintLayoutSearch.setVisibility(View.VISIBLE);
                    filter.setVisibility(View.VISIBLE);
                    isSearchShow = true;
                } else {
                    searchlayoutmain.setVisibility(View.GONE);
                    constraintLayoutSearch.setVisibility(View.GONE);
                    searchfilterDivider.setVisibility(View.GONE);
                    filtersearchbutton.setVisibility(View.GONE);

                    edittextstart.setVisibility(View.GONE);
                    edittextend.setVisibility(View.GONE);
                    categoryspinner.setVisibility(View.GONE);
                    brandspinner.setVisibility(View.GONE);
                    modespinner.setVisibility(View.GONE);
                    isSearchShow = false;
                }

                return false;
            }
        });
       *//* MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        adapter.setFilter(stockItemList);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });*//*

        return super.onCreateOptionsMenu(menu);
    }*/

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


                            stockItemList = new ArrayList<>();

                            stockItems = realm.where(StockItem.class).findAll();

                            stockItemList.addAll(realm.copyFromRealm(stockItems));


                            adapter = new StockItemAdapter(getApplicationContext(), stockItemList,StockActivity.this);

                            stock_item_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                            //  stock_item_rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                            stock_item_rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } else {


                            stockItemList = new ArrayList<>();

                            stockItems = realm.where(StockItem.class).findAll();

                            stockItemList.addAll(realm.copyFromRealm(stockItems));


                            adapter = new StockItemAdapter(getApplicationContext(), stockItemList,StockActivity.this);

                            stock_item_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                            //  stock_item_rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                            stock_item_rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
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

    private void showTermsDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(StockActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_medicine_product, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(StockActivity.this);
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


        //spinner category dropdown

        for (ModelCategory helpers : categoryArrayList) {

            categoryNameList.add(helpers.getName());

        }

        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, categoryNameList);

        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter2);

        try {
            category_id = categoryArrayList.get(0).getName();
        } catch (Exception e) {

        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  String valu = adapterView.getItemAtPosition(i).toString();
                category_id = categoryArrayList.get(i).getName();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        for (ModelUnit helper : unitArrayList) {

            unitNameList.add(helper.getName());

        }

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, unitNameList);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerUnit.setAdapter(adapter);

        try {
            utility_id = unitArrayList.get(0).getName();
        } catch (Exception e) {

        }

        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  String valu = adapterView.getItemAtPosition(i).toString();
                utility_id = unitArrayList.get(i).getName();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

                TextView toastText = layout.findViewById(R.id.toasttext);
                toastText.setText("Stock Item insert Successfully");

                final Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();


                ModelCreateProduct modelCreateProduct = new ModelCreateProduct();
                modelCreateProduct.setName("" + name.getText());
                modelCreateProduct.setCategory("" + category_id);
                modelCreateProduct.setBrandName("" + brand.getText());
                modelCreateProduct.setPrice("" + mrp.getText());
                modelCreateProduct.setUnit("" + utility_id);
                modelCreateProduct.setOpeningQuantity("" + openingQty.getText());
                modelCreateProduct.setMinQuantity("" + miniQty.getText());
                //  stockItemList.add(new StockItem(100,"",""+utility_id,""+name.getText(),Integer.parseInt(""+miniQty.getText()),Double.parseDouble(""+mrp.getText())));
                createStockProduct(modelCreateProduct, "" + PreferenceManager.getSecretKey(getApplicationContext()));
            }
        });


        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                mrp.setText("");
                pp.setText("");
                brand.setText("");
                miniQty.setText("");
                openingQty.setText("");
                description.setText("");
            }
        });

        alertDialog.show();
    }


    private void createStockProduct(ModelCreateProduct modelCreateProduct, String uniqueCode) {


        compositeDisposable.add(APIClient.getInstance().createStockItem("" + uniqueCode, modelCreateProduct)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelCreateProduct>>() {
                    @Override
                    public void onNext(final Response<ModelCreateProduct> response) {


                       /* String userid = ""+response.body().getUser_id();
                        String name = ""+response.body().getName();
                        String address = ""+response.body().getAddress();
                        String anothernumber = ""+response.body().getPhone();
                        String email = ""+response.body().getEmail();

                        Intent intent = new Intent(SignUpActivity.this,OTPWebActivity.class);
                        intent.putExtra("otpcode",""+response.body().getPassword());
                        intent.putExtra("phone",""+response.body().getUsername());
                        intent.putExtra("userid",""+userid);
                        intent.putExtra("name",""+name);
                        intent.putExtra("address",""+address);
                        intent.putExtra("anothernumber",""+anothernumber);
                        intent.putExtra("email",""+email);
                        startActivity(intent);*/


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    public void goToNewPurchase(View view) {
        Intent intent = new Intent(StockActivity.this,NewPurchaseActivity.class);
        startActivity(intent);
    }

    public void btn_back(View view) {
        onBackPressed();
    }

    @Override
    public void onClick(Boolean response) {

    }

    @Override
    public void onClickNotify(Boolean response) {
        tv_cartno.setText(""+salesItemList.size());
        /*Toast.makeText(this, "Click: "+response, Toast.LENGTH_SHORT).show();*/
    }



    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        // MenuInflater inflater = popup.getMenuInflater();
        popup.inflate(R.menu.stockpagemenu);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.ic_addicons:
                        Intent intent = new Intent(StockActivity.this,AddNewProductActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.ic_syncicons:
                        Toast.makeText(StockActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
                        break;
                }
                //Toast.makeText(SearchSalesActivity.this, ""+menuItem, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        popup.show();

    }
}
