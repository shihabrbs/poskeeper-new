package com.portal.terminalbd.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.SalesDetailsItemAdapter;
import com.portal.terminalbd.model.SaleItem;
import com.portal.terminalbd.model.SalesHistory;
import com.portal.terminalbd.model.SalesItemHistory;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.utils.Common;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class SalesDetailsActivity extends AppCompatActivity implements Runnable{

    @BindView(R.id.customer_name)
    TextView customer_name;

    @BindView(R.id.customer_mobile)
    TextView customer_mobile;

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

    @BindView(R.id.sale_vat_tk)
    TextView sale_vat_tk;

    @BindView(R.id.sale_grand_total_tk)
    TextView sale_grand_total_tk;

    @BindView(R.id.sales_details_item_rv)
    RecyclerView sales_details_item_rv;

    @BindView(R.id.edit_btn)
    Button edit_btn;

    @BindView(R.id.delete_btn)
    Button delete_btn;

    @BindView(R.id.reprint_btn)
    Button reprint_btn;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    String invoiceID;

    Realm realm;

    SalesHistory salesHistory;

    SalesDetailsItemAdapter adapter;

    RealmResults<SalesItemHistory> saleItems;

    List<SaleItem> salesItemList;

    StockItem stockItem;

    SystemUser systemUserRealmQuery;

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

    StockItem stock;

    String itemPrintName="";

    boolean isBluetoothOff = false;

    Menu menu;

    boolean isprinterConnect = false;
    int serial=0;
    String items="";

    int total;
    int subTotal;
    int totalVat;

    Setup setup;
    String pharmacyName="";
    String locationName="";
    String pharmacyMobileName="";

    String anoCustomerName="";
    String anoCustomerMobile="";


    int discountAmount;
    int returnAmount;
    int dueAmount;

    String paymentType="";
    String vatRegNo = "";
    String vatPercentage = "";

    String siteName = "";

    String discountType="";

    String createdDate;

    String currentDateandTime="";

    int receiveAmount;
    String salesByName;

    String formatDate="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));

        toolbar_title.setText("Sales Details");

        realm = Realm.getDefaultInstance();



        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy h:mm a");
        /*
         ********************************Printer connect code here***********************************
         */

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        ListPairedDevices();

        salesItemList = new ArrayList<>();

        if (getIntent().getExtras()!=null)
        {
            invoiceID = getIntent().getExtras().getString("INVOICE");
        }


        salesHistory = realm.where(SalesHistory.class).equalTo("invoiceId",invoiceID).findFirst();
        saleItems = realm.where(SalesItemHistory.class).equalTo("salesId",Integer.parseInt(invoiceID)).findAll();


        for (int i=0;i<saleItems.size();i++) {

            SaleItem saleItem = new SaleItem();
            stockItem = realm.where(StockItem.class).equalTo("itemId",saleItems.get(i).getStockId()).findFirst();
            saleItem.setSaleItemName(stockItem.getName());
            saleItem.setSalePpPrice(String.valueOf(stockItem.getPurchasePrice()));
            saleItem.setSaleStockId(String.valueOf(saleItems.get(i).getStockId()));
            saleItem.setSaleMrpPrice(String.valueOf(saleItems.get(i).getUnitPrice()));
            saleItem.setSaleSubTotal(String.valueOf(saleItems.get(i).getSubTotal()));
            saleItem.setSaleQuantity(String.valueOf(saleItems.get(i).getQuantity()));

            salesItemList.add(saleItem);

        }

        if (salesHistory!=null)
        {
            customer_name.setText(salesHistory.getCustomerName());
            customer_mobile.setText(salesHistory.getCustomerMobile());
            invoice_id.setText(salesHistory.getInvoiceId());
            totalBdt.setText(""+salesHistory.getTotal());
            paymentBdt.setText(""+salesHistory.getReceive());
            payment_method.setText(salesHistory.getTransactionMethod());
            sale_total_tk.setText(""+salesHistory.getSubTotal());
            sale_discount_tk.setText(""+salesHistory.getDiscount());
            sale_vat_tk.setText(""+salesHistory.getVat());
            sale_grand_total_tk.setText(""+salesHistory.getTotal());
        }


        //set adapter
        adapter = new SalesDetailsItemAdapter(SalesDetailsActivity.this,salesItemList);
        sales_details_item_rv.setLayoutManager(new LinearLayoutManager(SalesDetailsActivity.this,LinearLayoutManager.VERTICAL,false));
        sales_details_item_rv.addItemDecoration(new DividerItemDecoration(SalesDetailsActivity.this,DividerItemDecoration.VERTICAL));
        sales_details_item_rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        if (salesHistory.getSalesBy()!=0) {

            systemUserRealmQuery = realm.where(SystemUser.class).equalTo("userId", salesHistory.getSalesBy()).findFirst();
        }

        /*
        ========================================Printer code=========================================
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy h:mm a");
        currentDateandTime = sdf.format(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createdDate = format.format(new Date());

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

        anoCustomerName = salesHistory.getCustomerName();
        anoCustomerMobile = salesHistory.getCustomerMobile();
        paymentType = salesHistory.getTransactionMethod();
        total = salesHistory.getTotal();
        subTotal = salesHistory.getSubTotal();
        discountAmount = salesHistory.getDiscountCalculation();
        receiveAmount = salesHistory.getReceive();
        discountType = salesHistory.getDiscountType();
        dueAmount = salesHistory.getDue();
        salesByName = systemUserRealmQuery.getUsername();

        if (!TextUtils.isEmpty(vatRegNo))
        {
            totalVat = salesHistory.getVat();
        }

        if (salesHistory.getReceive()>salesHistory.getTotal())
        {
            returnAmount = salesHistory.getReceive()-salesHistory.getTotal();
        }else
        {
            returnAmount = 0;
        }

        Date newDate = null;
        try {
            newDate = parseFormat.parse(salesHistory.getCreated());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatDate = dateFormat.format(newDate);
        created.setText(formatDate);

        salesBy.setText(""+systemUserRealmQuery.getUsername());

    }

    @OnClick(R.id.edit_btn)
    void onEditBtn()
    {
        Intent intent = new Intent(SalesDetailsActivity.this, SalesEditActivity.class);
        intent.putExtra("INVOICE",invoiceID);
        startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.delete_btn)
    void onDeleteBtn()
    {

        new AlertDialog.Builder(SalesDetailsActivity.this).setMessage("Are you sure to delete?")
                .setTitle("Alert!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        realm.beginTransaction();
                        salesHistory.deleteFromRealm();
                        saleItems.deleteAllFromRealm();
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
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.print_status,menu);
        this.menu = menu;

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

    @OnClick(R.id.reprint_btn)
    void onReprintBtn()
    {


        if(!isprinterConnect)
        {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(SalesDetailsActivity.this, "Message1", Toast.LENGTH_SHORT).show();
            } else {
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(
                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent,
                            REQUEST_ENABLE_BT);
                } else {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(SalesDetailsActivity.this,
                            DeviceListActivity.class);
                    startActivityForResult(connectIntent,
                            REQUEST_CONNECT_DEVICE);

                }
            }

        }
        else if(isprinterConnect)
        {
            new AlertDialog.Builder(SalesDetailsActivity.this).setMessage("Are you sure want to print?")
                    .setTitle("Alert!!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            printNow();
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
    }

    @Override
    public void onDestroy() {

//        realm.beginTransaction();
//        realm.delete(SaleItem.class);
//        realm.commitTransaction();
//
//        realm.close();

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
                    mBluetoothConnectProgressDialog = ProgressDialog.show(SalesDetailsActivity.this,
                            "Connecting...", mBluetoothDevice.getName() + " : "
                                    + mBluetoothDevice.getAddress(), true, false);


                    Thread mBlutoothConnectThread = new Thread(this);
                    mBlutoothConnectThread.start();
                }
                break;

            case REQUEST_ENABLE_BT:
                if (mResultCode == Activity.RESULT_OK) {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(SalesDetailsActivity.this,
                            DeviceListActivity.class);
                    startActivityForResult(connectIntent, REQUEST_CONNECT_DEVICE);
                } else {
                    Toast.makeText(SalesDetailsActivity.this, "Message", Toast.LENGTH_SHORT).show();
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

            Thread mBlutoothConnectThread = new Thread(this);
            mBlutoothConnectThread.start();
        }
    }

    public void run() {
        try {
            mBluetoothSocket = mBluetoothDevice
                    .createRfcommSocketToServiceRecord(applicationUUID);
            mBluetoothAdapter.cancelDiscovery();
            mBluetoothSocket.connect();
            mHandler.sendEmptyMessage(0);
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

            isprinterConnect = true;

            menu.getItem(0).setIcon(ContextCompat.getDrawable(SalesDetailsActivity.this, R.drawable.ic_print_connected));

        }
    };

    public void printNow(){

        items = "";

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
//                        BILL = "Customer:" + anoCustomerName + ",Mobile:" + anoCustomerMobile + "\n\n";
                        header = header+String.format("%1$-30s %2$-1s", "Bill No:"+invoiceID, "Sales By:"+salesByName)+"\n";
                        header = header+String.format("%1$-30s %2$-1s", "Date:"+currentDateandTime, "Pay Mode:"+paymentType)+"\n";
                        header = header+String.format("%1$-30s %2$-1s", "Customer:"+anoCustomerName, "Mobile:"+anoCustomerMobile)+"\n";
                        header = header+"\n";

                    }else
                    {
                        header = header+String.format("%1$-30s %2$-1s", "Bill No:"+invoiceID, "Sales By:"+salesByName)+"\n";
                        header = header+String.format("%1$-30s %2$-1s", "Date:"+currentDateandTime, "Pay Mode:"+paymentType)+"\n";

                        header = header+"\n";
                    }

                    header2=String.format("%1$-47s %2$-8s %3$-1s", "Item Name", "Qnt", "Amount")+"\n";
                    header2 = header2 + "----------------------------------------------------------------\n";
                    vio = items;
                    vio = vio + "----------------------------------------------------------------\n";

                    header3 =              "Sub Total:                                      Tk.      "+subTotal+"\n";
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

                    if (!TextUtils.isEmpty(discountType)) {
                        mvdtail = mvdtail + "Net Payable:                                    Tk.      "+total + "\n";
                        mvdtail = mvdtail + "Paid:                                           Tk.      "+receiveAmount + "\n";
                        mvdtail = mvdtail + "Due:                                            Tk.      "+dueAmount + "\n";
                        mvdtail = mvdtail + "Return:                                         Tk.      "+returnAmount + "\n\n";
                    }else
                    {
                        mvdtail = mvdtail + "Net Payable:                                    Tk.      "+total + "\n";
                        mvdtail = mvdtail + "Paid:                                           Tk.      "+receiveAmount + "\n";
                        mvdtail = mvdtail + "Due:                                            Tk.      "+dueAmount + "\n";
                        mvdtail = mvdtail + "Return:                                         Tk.      "+returnAmount + "\n\n";
                    }


                    site ="** Visit "+siteName+" **\n";
                    copy ="Development By www.terminalbd.com\n";
                    copy =copy+"Reprint\n\n\n\n\n\n\n\n\n\n\n";


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
                    os.write(site.getBytes());
                    os.write(Common.center);
                    os.write(copy.getBytes());


                } catch (Exception e) {
                    Log.e("PrintActivity", "Exe ", e);
                }
            }
        };
        t.start();
    }


    @Override
    public void onStart() {
        super.onStart();
    }
}
