package com.portal.terminalbd.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.PurchaseDetailsItemAdapter;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.PurchaseHistory;
import com.portal.terminalbd.model.PurchaseItem;
import com.portal.terminalbd.model.PurchaseItemHistory;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.model.Vendor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class PurchaseDetailsActivity extends AppCompatActivity {

    @BindView(R.id.vendor_name)
    TextView vendor_name;

    @BindView(R.id.account_no)
    TextView account_no;

//    @BindView(R.id.address)
//    TextView address;

    @BindView(R.id.invoice_id)
    TextView invoice_id;

    @BindView(R.id.created)
    TextView created;

    @BindView(R.id.totalBdt)
    TextView totalBdt;

    @BindView(R.id.paymentBdt)
    TextView paymentBdt;

    @BindView(R.id.payment_method)
    TextView payment_method;

    @BindView(R.id.salesBy)
    TextView salesBy;

    @BindView(R.id.sale_total_tk)
    TextView sale_total_tk;

    @BindView(R.id.sale_discount_tk)
    TextView sale_discount_tk;

//    @BindView(R.id.sale_vat_tk)
//    TextView sale_vat_tk;

    @BindView(R.id.sale_grand_total_tk)
    TextView sale_grand_total_tk;

    @BindView(R.id.sales_details_item_rv)
    RecyclerView sales_details_item_rv;

    @BindView(R.id.edit_btn)
    Button edit_btn;

    @BindView(R.id.delete_btn)
    Button delete_btn;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    String invoiceID;

    Realm realm;

    PurchaseHistory purchaseHistory;

    PurchaseDetailsItemAdapter adapter;

    RealmResults<PurchaseItemHistory> purchaseItems;

    List<PurchaseItem> purchaseItemList;

    StockItem stockItem;

    SystemUser systemUserRealmQuery;

    Vendor vendor;

    String createdDate;

    String currentDateandTime = "";


    String formatDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, null));

        toolbar_title.setText("Purchase Details");

        realm = Realm.getDefaultInstance();


        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy h:mm a");


        purchaseItemList = new ArrayList<>();

        if (getIntent().getExtras() != null) {
            invoiceID = getIntent().getExtras().getString("INVOICE");
        }


        purchaseHistory = realm.where(PurchaseHistory.class).equalTo("invoiceId", invoiceID).findFirst();
        purchaseItems = realm.where(PurchaseItemHistory.class).equalTo("purchaseId", Integer.parseInt(invoiceID)).findAll();


        for (int i = 0; i < purchaseItems.size(); i++) {

            PurchaseItem saleItem = new PurchaseItem();
            stockItem = realm.where(StockItem.class).equalTo("itemId", purchaseItems.get(i).getStockId()).findFirst();
            saleItem.setPurchaseItemName(stockItem.getName());
            saleItem.setPurchasePpPrice(String.valueOf(stockItem.getPurchasePrice()));
            saleItem.setPurchaseStockId(String.valueOf(purchaseItems.get(i).getStockId()));
            saleItem.setPurchaseMrpPrice(String.valueOf(purchaseItems.get(i).getSalesPrice()));
            saleItem.setPurchaseSubTotal(String.valueOf(purchaseItems.get(i).getSubTotal()));
            saleItem.setPurchaseQuantity(String.valueOf(purchaseItems.get(i).getQuantity()));

            purchaseItemList.add(saleItem);

        }

        if (purchaseHistory.getVendorId() != 0) {

            vendor = realm.where(Vendor.class).equalTo("vendorId", purchaseHistory.getVendorId()).findFirst();
        }

        if (purchaseHistory != null) {
            vendor_name.setText(vendor.getName());

            if (purchaseHistory.getTransactionMethod().equalsIgnoreCase("cash"))
            {
                account_no.setVisibility(View.GONE);
            }else if (purchaseHistory.getTransactionMethod().equalsIgnoreCase("bank"))
            {
                account_no.setVisibility(View.VISIBLE);

                BankAccount bankAccount = realm.where(BankAccount.class).equalTo("itemId",purchaseHistory.getBankAccount()).findFirst();
                account_no.setText(bankAccount.getName());

            }else if (purchaseHistory.getTransactionMethod().equalsIgnoreCase("mobile"))
            {
                account_no.setVisibility(View.VISIBLE);

                MobileAccount mobileAccount = realm.where(MobileAccount.class).equalTo("itemId",purchaseHistory.getMobileBankAccount()).findFirst();
                account_no.setText(mobileAccount.getName());

            }
            invoice_id.setText(purchaseHistory.getInvoiceId());
            totalBdt.setText("" + purchaseHistory.getTotal());
            paymentBdt.setText("" + purchaseHistory.getTotal());
            payment_method.setText(purchaseHistory.getTransactionMethod());
            sale_total_tk.setText("" + purchaseHistory.getSubTotal());
            sale_discount_tk.setText("" + purchaseHistory.getDiscount());
//            sale_vat_tk.setText("" + purchaseHistory.getVat());
            sale_grand_total_tk.setText("" + purchaseHistory.getTotal());
        }


        //set adapter
        adapter = new PurchaseDetailsItemAdapter(PurchaseDetailsActivity.this, purchaseItemList);
        sales_details_item_rv.setLayoutManager(new LinearLayoutManager(PurchaseDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        sales_details_item_rv.addItemDecoration(new DividerItemDecoration(PurchaseDetailsActivity.this, DividerItemDecoration.VERTICAL));
        sales_details_item_rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        if (purchaseHistory.getReceivedBy() != 0) {

            systemUserRealmQuery = realm.where(SystemUser.class).equalTo("userId", purchaseHistory.getReceivedBy()).findFirst();
        }



        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy h:mm a");
        currentDateandTime = sdf.format(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createdDate = format.format(new Date());


        Date newDate = null;
        try {
            newDate = parseFormat.parse(purchaseHistory.getCreated());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatDate = dateFormat.format(newDate);
        created.setText(formatDate);

        salesBy.setText("" + systemUserRealmQuery.getUsername());
    }

    @OnClick(R.id.edit_btn)
    void onEditBtn() {
        Intent intent = new Intent(PurchaseDetailsActivity.this, PurchaseEditActivity.class);
        intent.putExtra("INVOICE", invoiceID);
        startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.delete_btn)
    void onDeleteBtn() {

        new AlertDialog.Builder(PurchaseDetailsActivity.this).setMessage("Are you sure to delete?")
                .setTitle("Alert!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        realm.beginTransaction();
                        purchaseHistory.deleteFromRealm();
                        purchaseItems.deleteAllFromRealm();
                        realm.commitTransaction();

                        onBackPressed();

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}


