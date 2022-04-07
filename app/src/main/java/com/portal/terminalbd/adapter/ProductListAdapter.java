package com.portal.terminalbd.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.interfaces.ItemDeleteClickListener;
import com.portal.terminalbd.model.SaleItem;
import com.portal.terminalbd.model.StockItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

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

    public ProductListAdapter(Context context, List<StockItem> stockItemList, ItemDeleteClickListener itemDeleteClickListener) {
        this.context = context;
        this.stockItemList = stockItemList;
        this.itemDeleteClickListener = itemDeleteClickListener;

        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sales_cart_wtihout_pic, viewGroup, false);

        return new ProductListViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ProductListViewHolder holder, final int i) {

        /*if (i % 2 == 1){
            holder.bgconst.setBackgroundColor(R.color.grey);
        }else {
            holder.bgconst.setBackgroundColor(Color.parseColor("#ffffff"));
        }*/

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


         /*   boolean find = salesItemexists("" + stockItemList.get(i).getItemId());

            if (find == false) {

                if (!isFirst) {

                    isFirst = true;
                    if (!realm.isInTransaction()) {
                        realm.beginTransaction();
                    }

                    SaleItem saleItem = new SaleItem();

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
                    saleItem.setSaleStockId(String.valueOf(stockItemList.get(i).getItemId()));
                    saleItem.setSaleItemName(stockItemList.get(i).getName());
                    saleItem.setSalePpPrice(String.valueOf(stockItemList.get(i).getPurchasePrice()));
                    saleItem.setSaleMrpPrice(String.valueOf(stockItemList.get(i).getSalesPrice()));
                    saleItem.setSaleQuantity(String.valueOf(quantity));
                    saleItem.setSaleSubTotal(String.valueOf(subTotal));


                    realm.copyToRealmOrUpdate(saleItem);

                    realm.commitTransaction();

                    isFirst = false;
                }

            } else {


                if (!isFirst) {

                    isFirst = true;
                    if (!realm.isInTransaction()) {
                        realm.beginTransaction();
                    }

                    SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(stockItemList.get(i).getItemId())).findFirst();

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
                    saleItem.setSaleQuantity(String.valueOf(quantity));
                    saleItem.setSaleSubTotal(String.valueOf(subTotal));

                    realm.copyToRealmOrUpdate(saleItem);
                    realm.commitTransaction();
                    isFirst = false;
                }

            }*/


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

                            SaleItem saleItem = new SaleItem();

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
                            saleItem.setSaleStockId(String.valueOf(stockItemList.get(i).getItemId()));
                            saleItem.setSaleItemName(stockItemList.get(i).getName());
                            saleItem.setSalePpPrice(String.valueOf(stockItemList.get(i).getPurchasePrice()));
                            saleItem.setSaleMrpPrice(String.valueOf(stockItemList.get(i).getSalesPrice()));
                            saleItem.setSaleQuantity(String.valueOf(quantity));
                            saleItem.setSaleSubTotal(String.valueOf(subTotal));


                            realm.copyToRealmOrUpdate(saleItem);

                            realm.commitTransaction();

                            isFirst = false;
                        }

                    } else {


                        if (!isFirst) {

                            isFirst = true;
                            if (!realm.isInTransaction()) {
                                realm.beginTransaction();
                            }

                            SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(stockItemList.get(i).getItemId())).findFirst();

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
                            saleItem.setSaleQuantity(String.valueOf(quantity));
                            saleItem.setSaleSubTotal(String.valueOf(subTotal));

                            realm.copyToRealmOrUpdate(saleItem);
                            realm.commitTransaction();
                            isFirst = false;
                        }

                    }


                }
                holder.sale_item_quantity.setSelection(holder.sale_item_quantity.getText().length());


            }
        });


        List<SaleItem> saleItem = realm.where(SaleItem.class).findAll();
        if (saleItem.size() > 0) {
            for (SaleItem item :
                    saleItem) {
                if (Integer.parseInt(item.getSaleStockId()) == stockItemList.get(i).getItemId()) {
                    holder.sale_item_quantity.setText(item.getSaleQuantity());
                    holder.total.setText(item.getSaleSubTotal());
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


       /* holder.dotsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemcartlayout.setVisibility(View.VISIBLE);
            }
        });*/

       /* holder.quantity_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quantity = Integer.parseInt(holder.sale_item_quantity.getText().toString());
                holder.quantity_minus.setEnabled(true);

                if (quantity > 0) {
                    realm.beginTransaction();

                    SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(stockItemList.get(i).getItemId())).findFirst();

                    quantity = Integer.parseInt(holder.sale_item_quantity.getText().toString());

                    double edit_mrp_value = Double.parseDouble(holder.mrp.getText().toString());
                    quantity++;
                    // subTotal = (int) Math.round(Double.valueOf(stockItemList.get(i).getSalesPrice()) * quantity);

                    if (discountpercent.equals("%")) {
                        int dispercent = Integer.parseInt("0");
                        discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                        subTotal = discountprice * quantity;
                    } else {
                        int dispercent = Integer.parseInt("" + discountpercent);
                        discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                        subTotal = discountprice * quantity;
                    }

                    holder.sale_item_quantity.setText(String.valueOf(quantity));
                    holder.total.setText("৳" + subTotal);
//                    saleItem.setSaleStockId(String.valueOf(stockItemList.get(i).getItemId()));
//                    saleItem.setSaleItemName(stockItemList.get(i).getName());
//                    saleItem.setSalePpPrice(String.valueOf(stockItemList.get(i).getPurchasePrice()));
//                    saleItem.setSaleMrpPrice(String.valueOf(stockItemList.get(i).getSalesPrice()));
                    saleItem.setSaleQuantity(String.valueOf(quantity));
                    saleItem.setSaleSubTotal(String.valueOf(subTotal));

                    realm.copyToRealmOrUpdate(saleItem);
                    realm.commitTransaction();

                } else {
                    realm.beginTransaction();

                    SaleItem saleItem = new SaleItem();

                    quantity = Integer.parseInt(holder.sale_item_quantity.getText().toString());
                    quantity++;
                    double edit_mrp_value = Double.parseDouble(holder.mrp.getText().toString());

                    if (discountpercent.equals("%")) {
                        int dispercent = Integer.parseInt("0");
                        discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                        subTotal = discountprice * quantity;
                    } else {
                        int dispercent = Integer.parseInt("" + discountpercent);
                        discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                        subTotal = discountprice * quantity;
                    }
                    holder.sale_item_quantity.setText(String.valueOf(quantity));
                    holder.total.setText("৳" + subTotal);
                    saleItem.setSaleStockId(String.valueOf(stockItemList.get(i).getItemId()));
                    saleItem.setSaleItemName(stockItemList.get(i).getName());
                    saleItem.setSalePpPrice(String.valueOf(stockItemList.get(i).getPurchasePrice()));
                    saleItem.setSaleMrpPrice(String.valueOf(stockItemList.get(i).getSalesPrice()));
                    saleItem.setSaleQuantity(String.valueOf(quantity));
                    saleItem.setSaleSubTotal(String.valueOf(subTotal));


                    realm.copyToRealmOrUpdate(saleItem);
                    realm.commitTransaction();
                }
            }
        });

        holder.quantity_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(stockItemList.get(i).getItemId())).findFirst();
                realm.beginTransaction();
                quantity = Integer.parseInt(saleItem.getSaleQuantity());
                quantity--;
                double edit_mrp_value = Double.parseDouble(holder.mrp.getText().toString());

                if (quantity > 0) {

                    if (discountpercent.equals("%")) {
                        int dispercent = Integer.parseInt("0");
                        discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                        subTotal = discountprice * quantity;
                    } else {
                        int dispercent = Integer.parseInt("" + discountpercent);
                        discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                        subTotal = discountprice * quantity;
                    }

                    // subTotal = (int) Math.round(edit_mrp_value * quantity);
                    holder.sale_item_quantity.setText(String.valueOf(quantity));
                    holder.total.setText("৳" + subTotal);
//                    saleItem.setSaleStockId(String.valueOf(stockItemList.get(i).getItemId()));
//                    saleItem.setSaleItemName(stockItemList.get(i).getName());
//                    saleItem.setSalePpPrice(String.valueOf(stockItemList.get(i).getPurchasePrice()));
//                    saleItem.setSaleMrpPrice(String.valueOf(stockItemList.get(i).getSalesPrice()));
                    saleItem.setSaleQuantity(String.valueOf(quantity));
                    saleItem.setSaleSubTotal(String.valueOf(subTotal));


                    realm.copyToRealmOrUpdate(saleItem);

                } else {
                    holder.sale_item_quantity.setText("0");
                    holder.total.setText("৳" + "0");
                    saleItem.deleteFromRealm();
                    holder.quantity_minus.setEnabled(false);
                }

                realm.commitTransaction();

            }
        });

        */

        holder.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(stockItemList.get(i).getItemId())).findFirst();


                try {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<SaleItem> rows = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(stockItemList.get(i).getItemId())).findAll();
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

               /* holder.sale_item_quantity.setText("0");
                holder.total.setText("৳" + "0");*/

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

        RealmQuery<SaleItem> query = realm.where(SaleItem.class)
                .equalTo("saleStockId", id);
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

        @BindView(R.id.qnt)
        TextView stock;
        @BindView(R.id.unit)
        TextView unit;

        @BindView(R.id.spinnerid)
        Spinner spinner;


        @BindView(R.id.constraintLayout6)
        ConstraintLayout itemcartlayout;

        @BindView(R.id.sales_cart_bg_const)
        ConstraintLayout bgconst;

        @BindView(R.id.re_fresh)
        ImageView refresh;

        @BindView(R.id.textView31)
        TextView addbtn;


   /*     @BindView(R.id.imageView13)
        ImageView dotsbtn;*/


        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}
