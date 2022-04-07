package com.portal.terminalbd.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.interfaces.ItemDeleteClickListener;
import com.portal.terminalbd.model.PurchaseItem;
import com.portal.terminalbd.model.SaleItem;
import com.portal.terminalbd.model.StockItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class NewPurchaseProductListAdapter extends RecyclerView.Adapter<NewPurchaseProductListAdapter.ProductListViewHolder>{

    private Context context;
    private List<StockItem> stockItemList;

    private Realm realm;
    private int quantity;
    private double subTotal;
    String discountpercent;
    double discountprice;


    Handler handler;
    boolean isFirst = false;
    Timer timer = new Timer();
    final long DELAY = 1000; // milliseconds

    private ItemDeleteClickListener itemDeleteClickListener;

    public NewPurchaseProductListAdapter(Context context, List<StockItem> stockItemList, ItemDeleteClickListener itemDeleteClickListener) {
        this.context = context;
        this.stockItemList = stockItemList;
        this.itemDeleteClickListener = itemDeleteClickListener;

        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales_cart_wtihout_pic, parent, false);

        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int i) {


        getDiscountSpinner(holder);

        holder.name.setText(stockItemList.get(i).getName());
        holder.stock.setText(""+stockItemList.get(i).getQuantity());
        holder.unit.setText(""+stockItemList.get(i).getUnit());
        holder.mrp.setText(String.valueOf(stockItemList.get(i).getSalesPrice()));
        // holder.stockmrp.setText(String.valueOf(stockItemList.get(i).getSalesPrice()));
        holder.sale_item_quantity.setText("");
        holder.total.setText("৳ 0");
        holder.quantity_minus.setEnabled(false);


        boolean find = salesItemexists("" + stockItemList.get(i).getItemId());

        if (find) {
            holder.refresh.setVisibility(View.VISIBLE);
            holder.itemcartlayout.setVisibility(View.VISIBLE);
        } else {
            holder.refresh.setVisibility(View.GONE);
            holder.itemcartlayout.setVisibility(View.GONE);
        }


        holder.addbtn.setOnClickListener(view -> {




            holder.sale_item_quantity.setSelection(holder.sale_item_quantity.getText().length());


            // holder.itemcartlayout.setVisibility(View.GONE);

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    holder.itemcartlayout.setVisibility(View.GONE);

                }
            },2000);

            holder.refresh.setVisibility(View.VISIBLE);
            itemDeleteClickListener.onClick(true);

        });


        holder.sale_item_quantity.addTextChangedListener(new TextWatcher() {
            boolean isTyping = false;
            CountDownTimer timer = null;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable charSequence) {

                boolean find = salesItemexists("" + stockItemList.get(i).getItemId());


                if (charSequence.length() != 0) {
                    holder.refresh.setVisibility(View.VISIBLE);
                    if (find == false) {

                        if (!isFirst) {

                            isFirst = true;
                            if (!realm.isInTransaction()) {
                                realm.beginTransaction();
                            }

                            PurchaseItem purchaseItem = new PurchaseItem();

                            quantity = Integer.parseInt(holder.sale_item_quantity.getText().toString());
                            double edit_mrp_value = Double.parseDouble(holder.mrp.getText().toString());

                            try {
                                if (discountpercent.equals("%")) {
                                    int dispercent = Integer.parseInt("0");
                                    discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                                    subTotal = discountprice * quantity;
                                } else {
                                    int dispercent = Integer.parseInt("" + discountpercent);
                                    discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                                    subTotal = discountprice * quantity;
                                }
                            } catch (Exception e) {

                            }
                            holder.sale_item_quantity.setText(String.valueOf(quantity));
                            // holder.total.setText("৳" + subTotal);
                            holder.total.setText(String.format("৳"+"%.2f", subTotal));
                            purchaseItem.setPurchaseStockId(String.valueOf(stockItemList.get(i).getItemId()));
                            purchaseItem.setPurchaseItemName(stockItemList.get(i).getName());
                            purchaseItem.setPurchasePpPrice(String.valueOf(stockItemList.get(i).getPurchasePrice()));
                            purchaseItem.setPurchaseMrpPrice(String.valueOf(stockItemList.get(i).getSalesPrice()));
                            purchaseItem.setPurchaseQuantity(String.valueOf(quantity));
                            purchaseItem.setPurchaseSubTotal(String.valueOf(subTotal));


                            realm.copyToRealmOrUpdate(purchaseItem);

                            realm.commitTransaction();

                            isFirst = false;
                        }

                    } else {


                        if (!isFirst) {

                            isFirst = true;
                            if (!realm.isInTransaction()) {
                                realm.beginTransaction();
                            }

                            PurchaseItem purchaseItem = realm.where(PurchaseItem.class).equalTo("purchaseStockId", String.valueOf(stockItemList.get(i).getItemId())).findFirst();

                            quantity = Integer.parseInt(holder.sale_item_quantity.getText().toString());

                            double edit_mrp_value = Double.parseDouble(holder.mrp.getText().toString());

                            try {
                                if (discountpercent.equals("%")) {
                                    int dispercent = Integer.parseInt("0");
                                    discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                                    subTotal = discountprice * quantity;
                                } else {
                                    int dispercent = Integer.parseInt("" + discountpercent);
                                    discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                                    subTotal = discountprice * quantity;
                                }
                            } catch (Exception e) {

                            }

                            quantity = Integer.parseInt(holder.sale_item_quantity.getText().toString());

                            holder.sale_item_quantity.setText(String.valueOf(quantity));
                            //  holder.total.setText("৳" + subTotal);
                            holder.total.setText(String.format("৳"+"%.2f", subTotal));
                            purchaseItem.setPurchaseQuantity(String.valueOf(quantity));
                            purchaseItem.setPurchaseSubTotal(String.valueOf(subTotal));

                            realm.copyToRealmOrUpdate(purchaseItem);
                            realm.commitTransaction();
                            isFirst = false;
                        }

                    }


                }
                holder.sale_item_quantity.setSelection(holder.sale_item_quantity.getText().length());


            }
        });


        List<PurchaseItem> purchaseItems = realm.where(PurchaseItem.class).findAll();
        if (purchaseItems.size() > 0) {
            for (PurchaseItem item :
                    purchaseItems) {
                if (Integer.parseInt(item.getPurchaseStockId()) == stockItemList.get(i).getItemId()) {
                    holder.sale_item_quantity.setText(item.getPurchaseQuantity());
                    holder.total.setText(item.getPurchaseSubTotal());
                    holder.quantity_minus.setEnabled(true);
                }
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean showcart = true;

                if (showcart) {
                    holder.itemcartlayout.setVisibility(View.VISIBLE);
                    holder.sale_item_quantity.requestFocus();
                    showcart = false;
                } else {
                    holder.itemcartlayout.setVisibility(View.GONE);
                    showcart = true;
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }

            }
        });



        holder.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PurchaseItem saleItem = realm.where(PurchaseItem.class).equalTo("purchaseStockId", String.valueOf(stockItemList.get(i).getItemId())).findFirst();


                try {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<PurchaseItem> rows = realm.where(PurchaseItem.class).equalTo("purchaseStockId", String.valueOf(stockItemList.get(i).getItemId())).findAll();
                            rows.deleteFirstFromRealm();
                            itemDeleteClickListener.onClickNotify(true);
                            notifyDataSetChanged();
                            holder.sale_item_quantity.setText("");
                            holder.total.setText("৳" + "0");
                            holder.refresh.setVisibility(View.GONE);
                        }
                    });
                }catch (Exception e){

                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return stockItemList != null ? stockItemList.size() : 0;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private boolean salesItemexists(String id) {

        RealmQuery<PurchaseItem> query = realm.where(PurchaseItem.class)
                .equalTo("purchaseStockId", id);
        return query.count() != 0;
    }

    private void getDiscountSpinner(@NonNull final ProductListViewHolder holder) {

        ArrayList<String> number = new ArrayList<>();
        number.add("%");
        number.add("1");
        number.add("2");
        number.add("3");
        number.add("4");
        number.add("5");
        number.add("6");
        number.add("7");
        number.add("8");
        number.add("9");
        number.add("10");
        number.add("11");
        number.add("12");
        number.add("13");
        number.add("14");
        number.add("15");
        number.add("16");
        number.add("17");
        number.add("18");
        number.add("19");
        number.add("20");
        number.add("21");
        number.add("22");
        number.add("23");
        number.add("24");
        number.add("25");
        number.add("26");
        number.add("27");
        number.add("28");
        number.add("29");
        number.add("30");


        ArrayAdapter adapter2 = new ArrayAdapter(context, R.layout.spinner_item, number);


        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        holder.spinner.setAdapter(adapter2);

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                discountpercent = "" + holder.spinner.getItemAtPosition(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    public void setFilter(List<StockItem> stockItem) {
        stockItemList = new ArrayList<>();
        stockItemList.addAll(stockItem);
        notifyDataSetChanged();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.mrp)
        TextView stockmrp;

        @BindView(R.id.edittextmrp)
        EditText mrp;

        @BindView(R.id.plusebtn)
        ImageView quantity_plus;

        @BindView(R.id.minusbtn)
        ImageView quantity_minus;

        @BindView(R.id.edittextqty)
        EditText sale_item_quantity;

        @BindView(R.id.textView17)
        TextView total;

        @BindView(R.id.spinnerid)
        Spinner spinner;

        @BindView(R.id.qnt)
        TextView stock;
        @BindView(R.id.unit)
        TextView unit;

        @BindView(R.id.constraintLayout6)
        ConstraintLayout itemcartlayout;

        @BindView(R.id.sales_cart_bg_const)
        ConstraintLayout bgconst;

        @BindView(R.id.re_fresh)
        ImageView refresh;

        @BindView(R.id.textView31)
        TextView addbtn;
        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
