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
import com.portal.terminalbd.activity.SalesDetailsActivity;
import com.portal.terminalbd.model.SalesHistory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesHistoryAdapter extends RecyclerView.Adapter<SalesHistoryAdapter.SalesHistoryViewHolder>{

    private Context context;
    private List<SalesHistory> salesHistories;

    public SalesHistoryAdapter(Context context, List<SalesHistory> salesHistories) {
        this.context = context;
        this.salesHistories = salesHistories;
    }

    @NonNull
    @Override
    public SalesHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_sales_history,viewGroup,false);

        return new SalesHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesHistoryViewHolder holder, final int i) {

        holder.serial_no.setText((i + 1) +".");
        holder.sales_date.setText(salesHistories.get(i).getInvoiceId());
        holder.sales_total.setText(String.valueOf(salesHistories.get(i).getTotal()));
        holder.sales_payment.setText(String.valueOf(salesHistories.get(i).getReceive()));
        holder.sales_transaction.setText(salesHistories.get(i).getTransactionMethod());

        holder.sales_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("INVOICE",salesHistories.get(i).getInvoiceId());
//
//                SalesDetailsFragment salesDetailsFragment = new SalesDetailsFragment();
//                salesDetailsFragment.setArguments(bundle);
//
//                AppCompatActivity activity = (AppCompatActivity) context;
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,salesDetailsFragment)
//                        .commitAllowingStateLoss();

                Intent intent = new Intent(context, SalesDetailsActivity.class);
                intent.putExtra("INVOICE",salesHistories.get(i).getInvoiceId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return salesHistories != null ? salesHistories.size() : 0;
    }

    public void setFilter(List<SalesHistory> salesHistoryList) {
        salesHistories = new ArrayList<>();
        salesHistories.addAll(salesHistoryList);
        notifyDataSetChanged();
    }

    public class SalesHistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.serial_no)
        TextView serial_no;

        @BindView(R.id.sales_date)
        TextView sales_date;

        @BindView(R.id.sales_total)
        TextView sales_total;

        @BindView(R.id.sales_payment)
        TextView sales_payment;

        @BindView(R.id.sales_transaction)
        TextView sales_transaction;

        @BindView(R.id.sales_details)
        ImageView sales_details;



        public SalesHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
