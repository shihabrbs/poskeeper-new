package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.SaleItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesDetailsItemAdapter extends RecyclerView.Adapter<SalesDetailsItemAdapter.SalesDetailsItemViewHolder>{

    private Context context;
    private List<SaleItem> saleItems;

    public SalesDetailsItemAdapter(Context context, List<SaleItem> saleItems) {
        this.context = context;
        this.saleItems = saleItems;
    }

    @NonNull
    @Override
    public SalesDetailsItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_sale_details_item,viewGroup,false);

        return new SalesDetailsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SalesDetailsItemViewHolder salesItemViewHolder, final int i) {

        salesItemViewHolder.sale_item_med_name.setText(saleItems.get(i).getSaleItemName());
        salesItemViewHolder.sale_item_mrp_price.setText(saleItems.get(i).getSaleMrpPrice());
        salesItemViewHolder.sale_item_quantity.setText(saleItems.get(i).getSaleQuantity());


        salesItemViewHolder.sale_item_sub_total.setText("৳"+saleItems.get(i).getSaleSubTotal());


    }

    @Override
    public int getItemCount() {
        return saleItems != null ? saleItems.size() : 0;
    }

    public class SalesDetailsItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sale_item_med_name)
        TextView sale_item_med_name;


        @BindView(R.id.sale_item_mrp_price)
        TextView sale_item_mrp_price;

        @BindView(R.id.sale_item_quantity)
        TextView sale_item_quantity;


        @BindView(R.id.sale_item_sub_total)
        TextView sale_item_sub_total;



        public SalesDetailsItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
