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
import com.portal.terminalbd.activity.ExpenseDetailsActivity;
import com.portal.terminalbd.model.ExpenseItem;
import com.portal.terminalbd.model.SystemUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class ExpenseHistoryAdapter extends RecyclerView.Adapter<ExpenseHistoryAdapter.ExpenseHistoryViewHolder>{

    private Context context;
    private List<ExpenseItem> expenseHistories;

    Realm realm;

    public ExpenseHistoryAdapter(Context context, List<ExpenseItem> expenseHistories) {
        this.context = context;
        this.expenseHistories = expenseHistories;

        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public ExpenseHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_expense_history,viewGroup,false);

        return new ExpenseHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHistoryViewHolder holder, final int i) {

        holder.expense_date.setText(expenseHistories.get(i).getInvoiceId());
        holder.expense_amount.setText(String.valueOf(expenseHistories.get(i).getPayment()));

        SystemUser systemUser = realm.where(SystemUser.class).equalTo("userId",expenseHistories.get(i).getToUser()).findFirst();
        if (systemUser!=null)
        {
            holder.expense_to.setText(systemUser.getUsername());
        }

        holder.expense_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ExpenseDetailsActivity.class);
                intent.putExtra("INVOICE",expenseHistories.get(i).getInvoiceId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return expenseHistories != null ? expenseHistories.size() : 0;
    }

    public class ExpenseHistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.expense_date)
        TextView expense_date;

        @BindView(R.id.expense_to)
        TextView expense_to;

        @BindView(R.id.expense_amount)
        TextView expense_amount;

        @BindView(R.id.expense_details)
        ImageView expense_details;


        public ExpenseHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
