package com.portal.terminalbd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.ModelCustomerInvoice;
import com.portal.terminalbd.model.OrderItem;

import java.util.ArrayList;

public class CustomerInvoiceAdapter extends RecyclerView.Adapter<CustomerInvoiceAdapter.MyViewHolder> {
   Context context;
    ArrayList<OrderItem> customerInvoiceArrayList;

    public CustomerInvoiceAdapter(Context context, ArrayList<OrderItem> customerInvoiceArrayList) {
        this.context = context;
        this.customerInvoiceArrayList = customerInvoiceArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_invoice, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(""+customerInvoiceArrayList.get(position).getName());
        holder.mrp.setText(""+customerInvoiceArrayList.get(position).getPrice());
        holder.qty.setText(""+customerInvoiceArrayList.get(position).getQuantity());
        holder.subtotal.setText("৳ "+customerInvoiceArrayList.get(position).getSubTotal());

    }

    @Override
    public int getItemCount() {
        return customerInvoiceArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,mrp,qty,subtotal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            mrp = itemView.findViewById(R.id.tv_mrp);
            qty = itemView.findViewById(R.id.tv_qty);
            subtotal = itemView.findViewById(R.id.tv_subtotal);
        }
    }
}
