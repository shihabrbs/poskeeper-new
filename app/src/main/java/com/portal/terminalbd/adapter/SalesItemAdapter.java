package com.portal.terminalbd.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.interfaces.ItemDeleteClickListener;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.portal.terminalbd.model.SaleItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class SalesItemAdapter extends RecyclerView.Adapter<SalesItemAdapter.SalesItemViewHolder> {

    private Context context;
    private List<SaleItem> saleItems;
    private Realm realm;
    private int quantity;
    private double subTotal;
    private int subTotalint;
    private SalesItemclickEvent salesItemclickEvent;
    String discountpercent;
    double discountprice;
    boolean isFirst = false;

    public SalesItemAdapter(Context context, List<SaleItem> saleItems,SalesItemclickEvent salesItemclickEvent) {
        this.context = context;
        this.saleItems = saleItems;
        this.salesItemclickEvent = salesItemclickEvent;
        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public SalesItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sales_cart_show, viewGroup, false);

        return new SalesItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SalesItemViewHolder salesItemViewHolder, final int i) {
        salesItemViewHolder.setIsRecyclable(false);
        int position = i + 1;
        //salesItemViewHolder.cartno.setText(position+". ");
        salesItemViewHolder.sale_item_med_name.setText(position + ". " + saleItems.get(i).getSaleItemName());
        salesItemViewHolder.sale_item_mrp_price.setText(saleItems.get(i).getSaleMrpPrice());
        salesItemViewHolder.sale_item_quantity.setText(saleItems.get(i).getSaleQuantity());


        salesItemViewHolder.sale_item_sub_total.setText("৳" + saleItems.get(i).getSaleSubTotal());

        salesItemViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                // final RealmResults<SaleItem> saleItems = realm.where(SaleItem.class).findAll();
                final RealmResults<SaleItem> rows = realm.where(SaleItem.class).equalTo("saleStockId", saleItems.get(i).getSaleStockId()).findAll();
                rows.deleteFirstFromRealm();
                //deleteAllFromRealm();
              /*  realm.beginTransaction();
                SaleItem saleItem = saleItems.get(i);
                saleItem.deleteFromRealm();
                realm.commitTransaction();*/
                realm.commitTransaction();
                notifyDataSetChanged();
            }
        });



        salesItemViewHolder.sale_item_quantity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                salesItemclickEvent.onClick("click");
                return false;
            }
        });

        /*salesItemViewHolder.sale_item_mrp_price.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    handled = true;
                    realm.beginTransaction();

                    SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(saleItems.get(i).getSaleStockId())).findFirst();

                    quantity = Integer.parseInt(salesItemViewHolder.sale_item_quantity.getText().toString());

                    double edit_mrp_value = Double.parseDouble(salesItemViewHolder.sale_item_mrp_price.getText().toString());



                    subTotal = edit_mrp_value * quantity;



                    salesItemViewHolder.sale_item_quantity.setText(String.valueOf(quantity));
                    salesItemViewHolder.sale_item_sub_total.setText("৳" + subTotal);
                    saleItem.setSaleMrpPrice(String.valueOf(edit_mrp_value));
                    saleItem.setSaleQuantity(String.valueOf(quantity));
                    saleItem.setSaleSubTotal(String.valueOf(subTotal));

                    realm.copyToRealmOrUpdate(saleItem);
                    realm.commitTransaction();
                    Toast.makeText(context, "Its OK", Toast.LENGTH_SHORT).show();

                }
                return handled;
            }
        });
*/

       /* salesItemViewHolder.sale_item_mrp_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.length() != 0){



                    if (!isFirst) {

                        isFirst = true;
                        realm.beginTransaction();

                        SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(saleItems.get(i).getSaleStockId())).findFirst();

                        quantity = Integer.parseInt(salesItemViewHolder.sale_item_quantity.getText().toString());

                        double edit_mrp_value = Double.parseDouble(salesItemViewHolder.sale_item_mrp_price.getText().toString());



                        subTotal = edit_mrp_value * quantity;



                        salesItemViewHolder.sale_item_quantity.setText(String.valueOf(quantity));
                        salesItemViewHolder.sale_item_sub_total.setText("৳" + subTotal);
                        saleItem.setSaleMrpPrice(String.valueOf(edit_mrp_value));
                        saleItem.setSaleQuantity(String.valueOf(quantity));
                        saleItem.setSaleSubTotal(String.valueOf(subTotal));

                        realm.copyToRealmOrUpdate(saleItem);
                        realm.commitTransaction();
                        isFirst =false;
                    }



                }

            }
        });*/


        salesItemViewHolder.sale_item_quantity.addTextChangedListener(new TextWatcher() {
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

                //  boolean find = salesItemexists(""+saleItems.get(i).getSaleStockId());


                if (salesItemViewHolder.sale_item_quantity.getText().length() != 0) {

                  //  Toast.makeText(context, "" + salesItemViewHolder.sale_item_quantity.getText(), Toast.LENGTH_SHORT).show();

                    if (!isFirst) {
                        //salesItemViewHolder.sale_item_quantity.requestFocus();
                        isFirst = true;
                        if (!realm.isInTransaction()) {
                            realm.beginTransaction();
                        }

                        SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(saleItems.get(i).getSaleStockId())).findFirst();

                        quantity = Integer.parseInt(salesItemViewHolder.sale_item_quantity.getText().toString());

                        double edit_mrp_value = Double.parseDouble(salesItemViewHolder.sale_item_mrp_price.getText().toString());


                        subTotal = edit_mrp_value * quantity;

                        // quantity = Integer.parseInt(salesItemViewHolder.sale_item_quantity.getText().toString());

                        salesItemViewHolder.sale_item_quantity.setText(String.valueOf(quantity));
                        // salesItemViewHolder.sale_item_sub_total.setText("৳" + subTotal);
                        salesItemViewHolder.sale_item_sub_total.setText(String.format("৳" + "%.2f", subTotal));
                        saleItem.setSaleQuantity(String.valueOf(quantity));
                        saleItem.setSaleSubTotal(String.valueOf(subTotal));

                        realm.copyToRealmOrUpdate(saleItem);
                        realm.commitTransaction();
                        isFirst = false;

                    }


                }
                salesItemViewHolder.sale_item_quantity.setSelection(salesItemViewHolder.sale_item_quantity.getText().length());
                salesItemclickEvent.onClick("click");

            }
        });

        salesItemViewHolder.sale_item_mrp_price.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() != 0) {


                    if (!isFirst) {

                        isFirst = true;
                        if (!realm.isInTransaction()) {
                            realm.beginTransaction();
                        }

                        SaleItem saleItem = realm.where(SaleItem.class).equalTo("saleStockId", String.valueOf(saleItems.get(i).getSaleStockId())).findFirst();

                        quantity = Integer.parseInt(salesItemViewHolder.sale_item_quantity.getText().toString());

                        double edit_mrp_value = Double.parseDouble(salesItemViewHolder.sale_item_mrp_price.getText().toString());


                        subTotal = edit_mrp_value * quantity;


                        salesItemViewHolder.sale_item_quantity.setText(String.valueOf(quantity));
                        // salesItemViewHolder.sale_item_sub_total.setText("৳" + subTotal);
                        salesItemViewHolder.sale_item_sub_total.setText(String.format("৳" + "%.2f", subTotal));
                        saleItem.setSaleMrpPrice(String.valueOf(edit_mrp_value));
                        saleItem.setSaleQuantity(String.valueOf(quantity));
                        saleItem.setSaleSubTotal(String.valueOf(subTotal));

                        realm.copyToRealmOrUpdate(saleItem);
                        realm.commitTransaction();
                        isFirst = false;
                        salesItemclickEvent.onClick("click");
                    }


                }

            }
        });

        salesItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salesItemclickEvent.onClick("visible");
            }
        });


    }

   /* private boolean salesItemexists(String id) {

        RealmQuery<SaleItem> query = realm.where(SaleItem.class)
                .equalTo("saleStockId", id);
        return query.count() != 0;
    }*/

    public void deletemethod(){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return saleItems != null ? saleItems.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class SalesItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView sale_item_med_name;


        @BindView(R.id.edittextmrp)
        EditText sale_item_mrp_price;

        @BindView(R.id.edittextqty)
        EditText sale_item_quantity;

        @BindView(R.id.plusebtn)
        ImageView quantity_plus;

        @BindView(R.id.minusbtn)
        ImageView quantity_minus;

        @BindView(R.id.textView17)
        TextView sale_item_sub_total;


        @BindView(R.id.imageView16)
        ImageView delete;


        public SalesItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}