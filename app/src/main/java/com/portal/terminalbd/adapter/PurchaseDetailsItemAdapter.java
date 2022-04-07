package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.PurchaseItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseDetailsItemAdapter extends RecyclerView.Adapter<PurchaseDetailsItemAdapter.PurchaseDetailsItemViewHolder>{

    private Context context;
    private List<PurchaseItem> purchaseItems;

    public PurchaseDetailsItemAdapter(Context context, List<PurchaseItem> purchaseItems) {
        this.context = context;
        this.purchaseItems = purchaseItems;
    }

    @NonNull
    @Override
    public PurchaseDetailsItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_purchase_details_item,viewGroup,false);

        return new PurchaseDetailsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PurchaseDetailsItemViewHolder holder, final int i) {

        holder.sale_item_med_name.setText(purchaseItems.get(i).getPurchaseItemName());
        holder.sale_item_mrp_price.setText(purchaseItems.get(i).getPurchasePpPrice());
        holder.sale_item_quantity.setText(purchaseItems.get(i).getPurchaseQuantity());


        holder.sale_item_sub_total.setText("৳"+purchaseItems.get(i).getPurchaseSubTotal());


    }

    @Override
    public int getItemCount() {
        return purchaseItems != null ? purchaseItems.size() : 0;
    }

    public class PurchaseDetailsItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sale_item_med_name)
        TextView sale_item_med_name;


        @BindView(R.id.sale_item_mrp_price)
        TextView sale_item_mrp_price;

        @BindView(R.id.sale_item_quantity)
        TextView sale_item_quantity;


        @BindView(R.id.sale_item_sub_total)
        TextView sale_item_sub_total;



        public PurchaseDetailsItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
