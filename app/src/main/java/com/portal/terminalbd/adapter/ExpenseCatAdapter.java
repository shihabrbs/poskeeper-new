package com.portal.terminalbd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.ExpenseCategory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpenseCatAdapter extends RecyclerView.Adapter<ExpenseCatAdapter.ExpenseViewHolder> {

    private Context context;
    private List<ExpenseCategory> expenseCategories;

    public ExpenseCatAdapter(Context context, List<ExpenseCategory> expenseCategories) {
        this.context = context;
        this.expenseCategories = expenseCategories;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_expense_category, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        holder.catName.setText(expenseCategories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return expenseCategories != null ? expenseCategories.size() : 0;
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.catName)
        TextView catName;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
