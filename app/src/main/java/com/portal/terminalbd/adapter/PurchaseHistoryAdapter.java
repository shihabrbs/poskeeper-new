package com.portal.terminalbd.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.activity.PurchaseDetailsActivity;
import com.portal.terminalbd.model.PurchaseHistory;
import com.portal.terminalbd.model.Vendor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryAdapter.PurchaseHistoryViewHolder>{

    private Context context;
    private List<PurchaseHistory> purchaseHistories;

    private Realm realm;

    public PurchaseHistoryAdapter(Context context, List<PurchaseHistory> purchaseHistories) {
        this.context = context;
        this.purchaseHistories = purchaseHistories;

        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public PurchaseHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_purchase_history,viewGroup,false);

        return new PurchaseHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseHistoryViewHolder holder, final int i) {

        holder.purchase_date.setText(purchaseHistories.get(i).getInvoiceId());
        holder.purchase_total.setText(String.valueOf(purchaseHistories.get(i).getTotal()));
        holder.purchase_payment.setText(String.valueOf(purchaseHistories.get(i).getPayment()));

        Vendor vendor = realm.where(Vendor.class).equalTo("vendorId",purchaseHistories.get(i).getVendorId()).findFirst();
        holder.purchase_vendor_name.setText(vendor.getName());

        holder.purchase_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PurchaseDetailsActivity.class);
                intent.putExtra("INVOICE",purchaseHistories.get(i).getInvoiceId());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return purchaseHistories != null ? purchaseHistories.size() : 0;
    }

    public class PurchaseHistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.purchase_date)
        TextView purchase_date;

        @BindView(R.id.purchase_total)
        TextView purchase_total;

        @BindView(R.id.purchase_payment)
        TextView purchase_payment;

        @BindView(R.id.purchase_vendor_name)
        TextView purchase_vendor_name;

        @BindView(R.id.purchase_details)
        ImageView purchase_details;



        public PurchaseHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
