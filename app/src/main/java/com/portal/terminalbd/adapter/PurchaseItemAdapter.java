package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.interfaces.SalesItemclickEvent;
import com.portal.terminalbd.model.PurchaseItem;
import com.portal.terminalbd.model.SaleItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class PurchaseItemAdapter extends RecyclerView.Adapter<PurchaseItemAdapter.PurchaseItemViewHolder>{

    private Context context;
    private List<PurchaseItem> purchaseItems;
    private Realm realm;

    private int quantity;
    private double subTotal;
    private SalesItemclickEvent salesItemclickEvent;
    boolean isFirst = false;

    public PurchaseItemAdapter(Context context, List<PurchaseItem> purchaseItems,SalesItemclickEvent salesItemclickEvent) {
        this.context = context;
        this.purchaseItems = purchaseItems;
        this.salesItemclickEvent = salesItemclickEvent;
        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public PurchaseItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sales_cart_show,viewGroup,false);

        return new PurchaseItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PurchaseItemViewHolder salesItemViewHolder, final int i) {
        salesItemViewHolder.setIsRecyclable(false);
        int position = i + 1;
        //salesItemViewHolder.cartno.setText(position+". ");
        salesItemViewHolder.sale_item_med_name.setText(position + ". " + purchaseItems.get(i).getPurchaseItemName());
        salesItemViewHolder.sale_item_mrp_price.setText(purchaseItems.get(i).getPurchaseMrpPrice());
        salesItemViewHolder.sale_item_quantity.setText(purchaseItems.get(i).getPurchaseQuantity());


        salesItemViewHolder.sale_item_sub_total.setText("৳" + purchaseItems.get(i).getPurchaseSubTotal());

        salesItemViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();

                final RealmResults<PurchaseItem> rows = realm.where(PurchaseItem.class).equalTo("purchaseStockId", purchaseItems.get(i).getPurchaseStockId()).findAll();
                rows.deleteFirstFromRealm();
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

                //  boolean find = salesItemexists(""+purchaseItems.get(i).getSaleStockId());


                if (salesItemViewHolder.sale_item_quantity.getText().length() != 0) {

                    Toast.makeText(context, "" + salesItemViewHolder.sale_item_quantity.getText(), Toast.LENGTH_SHORT).show();

                    if (!isFirst) {
                        //salesItemViewHolder.sale_item_quantity.requestFocus();
                        isFirst = true;
                        if (!realm.isInTransaction()) {
                            realm.beginTransaction();
                        }

                        PurchaseItem purchaseItem = realm.where(PurchaseItem.class).equalTo("purchaseStockId", String.valueOf(purchaseItems.get(i).getPurchaseStockId())).findFirst();

                        quantity = Integer.parseInt(salesItemViewHolder.sale_item_quantity.getText().toString());

                        double edit_mrp_value = Double.parseDouble(salesItemViewHolder.sale_item_mrp_price.getText().toString());


                        subTotal = edit_mrp_value * quantity;

                        // quantity = Integer.parseInt(salesItemViewHolder.sale_item_quantity.getText().toString());

                        salesItemViewHolder.sale_item_quantity.setText(String.valueOf(quantity));
                        // salesItemViewHolder.sale_item_sub_total.setText("৳" + subTotal);
                        salesItemViewHolder.sale_item_sub_total.setText(String.format("৳" + "%.2f", subTotal));
                        purchaseItem.setPurchaseQuantity(String.valueOf(quantity));
                        purchaseItem.setPurchaseSubTotal(String.valueOf(subTotal));

                        realm.copyToRealmOrUpdate(purchaseItem);
                        realm.commitTransaction();
                        isFirst = false;
                    }


                }
                salesItemViewHolder.sale_item_quantity.setSelection(salesItemViewHolder.sale_item_quantity.getText().length());


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

                        PurchaseItem purchaseItem = realm.where(PurchaseItem.class).equalTo("purchaseStockId", String.valueOf(purchaseItems.get(i).getPurchaseStockId())).findFirst();

                        quantity = Integer.parseInt(salesItemViewHolder.sale_item_quantity.getText().toString());

                        double edit_mrp_value = Double.parseDouble(salesItemViewHolder.sale_item_mrp_price.getText().toString());


                        subTotal = edit_mrp_value * quantity;


                        salesItemViewHolder.sale_item_quantity.setText(String.valueOf(quantity));
                        // salesItemViewHolder.sale_item_sub_total.setText("৳" + subTotal);
                        salesItemViewHolder.sale_item_sub_total.setText(String.format("৳" + "%.2f", subTotal));
                        purchaseItem.setPurchaseMrpPrice(String.valueOf(edit_mrp_value));
                        purchaseItem.setPurchaseQuantity(String.valueOf(quantity));
                        purchaseItem.setPurchaseSubTotal(String.valueOf(subTotal));

                        realm.copyToRealmOrUpdate(purchaseItem);
                        realm.commitTransaction();
                        isFirst = false;
                    }


                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return purchaseItems != null ? purchaseItems.size() : 0;
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class PurchaseItemViewHolder extends RecyclerView.ViewHolder {

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



        public PurchaseItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
