package com.portal.terminalbd.activity;

import android.content.Intent;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.PurchaseItemAdapter;
import com.portal.terminalbd.adapter.SuggestionAdapter;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.portal.terminalbd.model.PurchaseItem;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.utils.ClearableAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class PurchaseActivity extends AppCompatActivity implements SalesItemclickEvent {

    @BindView(R.id.med_search)
    ClearableAutoCompleteTextView search;

    @BindView(R.id.purchase_pp)
    EditText purchase_pp;

    @BindView(R.id.purchase_mrp)
    EditText purchase_mrp;

    @BindView(R.id.purchase_quantity)
    EditText purchase_quantity;

    @BindView(R.id.purchase_history_rv)
    RecyclerView purchase_history_rv;

    @BindView(R.id.total_item_count)
    TextView total_item_count;

    @BindView(R.id.grand_total_text)
    TextView grand_total_text;

    @BindView(R.id.sale_layout)
    RelativeLayout sale_layout;

//    @BindView(R.id.purchase_date_picker)
//    LinearLayout purchase_date_picker;

//    @BindView(R.id.purchase_date)
//            TextView purchase_date;


    List<StockItem> stockItemList;
    Realm realm;

    RealmResults<PurchaseItem> purchaseItemList;

    PurchaseItemAdapter adapter;


    StockItem stockItem;


    int total;
    int total_item =  0;


    RealmChangeListener<RealmResults<PurchaseItem>> realmListener = new RealmChangeListener<RealmResults<PurchaseItem>>() {
        @Override
        public void onChange(RealmResults<PurchaseItem> purchaseItems) {

            checkSaleLayoutVisibility();
            adapter.notifyDataSetChanged();
        }
    };

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
        setContentView(R.layout.activity_purchase);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));

        toolbar_title.setText("Add Purchase");

        realm = Realm.getDefaultInstance();



        stockItemList = new ArrayList<>();
        purchaseItemList = realm.where(PurchaseItem.class).findAll();

        purchaseItemList.addChangeListener(realmListener);


        stockItem = new StockItem();



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
                    stockItem=(StockItem) item;
                    purchase_mrp.setText(String.valueOf(stockItem.getSalesPrice()));
                    purchase_pp.setText(String.valueOf(stockItem.getPurchasePrice()));
                    purchase_quantity.requestFocus();
                }
            }
        });

        search.setOnClearListener(new ClearableAutoCompleteTextView.OnClearListener() {
            @Override
            public void onClear() {

                search.setText("");
                purchase_mrp.setText("");
                purchase_quantity.setText("");
                purchase_pp.setText("");


            }
        });


        adapter = new PurchaseItemAdapter(PurchaseActivity.this,purchaseItemList,this);
        purchase_history_rv.setLayoutManager(new LinearLayoutManager(PurchaseActivity.this,LinearLayoutManager.VERTICAL,false));
        purchase_history_rv.addItemDecoration(new DividerItemDecoration(PurchaseActivity.this,DividerItemDecoration.VERTICAL));
        purchase_history_rv.setAdapter(adapter);

        checkSaleLayoutVisibility();

    }

    public void checkSaleLayoutVisibility()
    {
        if (purchaseItemList.size()>0) {
            sale_layout.setVisibility(View.VISIBLE);
            calculatePrice();
        }else
        {
            sale_layout.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.sale_layout)
    void sale_layout()
    {
        startActivity(new Intent(this,PurchasePaymentActivity.class));
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

        if (TextUtils.isEmpty(purchase_pp.getText().toString()))
        {
            purchase_pp.setError("PP cannot empty");
            purchase_pp.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(purchase_mrp.getText().toString()))
        {
            purchase_mrp.setError("Mrp cannot empty");
            purchase_mrp.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(purchase_quantity.getText().toString()))
        {
            purchase_quantity.setError("Please enter quantity");
            purchase_quantity.requestFocus();
            return;
        }


        realm.beginTransaction();
        PurchaseItem purchaseItem = realm.createObject(PurchaseItem.class, UUID.randomUUID().toString());
        purchaseItem.setPurchaseStockId(String.valueOf(stockItem.getItemId()));
        purchaseItem.setPurchaseItemName(search.getText().toString().trim());
        purchaseItem.setPurchasePpPrice(purchase_pp.getText().toString());
        purchaseItem.setPurchaseMrpPrice(purchase_mrp.getText().toString());
        purchaseItem.setPurchaseQuantity(purchase_quantity.getText().toString());
        int sub_total = (int) Math.round(Double.valueOf(purchase_pp.getText().toString())*Double.valueOf(purchase_quantity.getText().toString()));
        purchaseItem.setPurchaseSubTotal(String.valueOf(sub_total));
        realm.commitTransaction();


        calculatePrice();


        search.setText("");
        purchase_mrp.setText("0");
        purchase_quantity.setText("");

        search.requestFocus();

        adapter.notifyDataSetChanged();
    }

//    @OnClick(R.id.purchase_date_picker)
//    void onDatePicker()
//    {
//        Calendar now = Calendar.getInstance();
//        DatePickerDialog dpd = DatePickerDialog.newInstance(
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//                        purchase_date.setText(String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth));
//                    }
//                },
//                now.get(Calendar.YEAR),
//                now.get(Calendar.MONTH),
//                now.get(Calendar.DAY_OF_MONTH)
//        );
//        dpd.setTitle("Choose Date");
//        dpd.show(getFragmentManager(), "Datepickerdialog");
//    }

    public void calculatePrice()
    {
        total = 0;

        for (PurchaseItem item : purchaseItemList) {
            total += Math.round(Double.valueOf(item.getPurchasePpPrice()) * Double.valueOf(item.getPurchaseQuantity()));

        }

        total_item = purchaseItemList.size();

        grand_total_text.setText("৳"+total);
        total_item_count.setText(String.valueOf(total_item));
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
