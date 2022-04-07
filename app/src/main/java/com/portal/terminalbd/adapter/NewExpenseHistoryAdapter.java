package com.portal.terminalbd.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.activity.ExpenseDetailsActivity;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.CustomerInvoice;
import com.portal.terminalbd.model.ExpenseCategory;
import com.portal.terminalbd.model.ExpenseItem;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.OrderItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.network.APIClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Response;

public class NewExpenseHistoryAdapter extends RecyclerView.Adapter<NewExpenseHistoryAdapter.ExpenseHistoryViewHolder>{

    private Context context;
    private List<ExpenseItem> expenseHistories;
    private ExpenseItem expenseHistory;

    Realm realm;

    public NewExpenseHistoryAdapter(Context context, List<ExpenseItem> expenseHistories) {
        this.context = context;
        this.expenseHistories = expenseHistories;

        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public ExpenseHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customer_sale_list,viewGroup,false);

        return new ExpenseHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHistoryViewHolder holder, final int i) {

        holder.expense_invoice.setText(expenseHistories.get(i).getInvoiceId());
        holder.expense_amount.setText(String.valueOf(expenseHistories.get(i).getPayment()));


        expenseHistory = realm.where(ExpenseItem.class).equalTo("invoiceId", expenseHistories.get(i).getInvoiceId()).findFirst();





            ExpenseCategory expenseCategory = realm.where(ExpenseCategory.class).equalTo("categoryId",expenseHistory.getExpenseCategory()).findFirst();
            try {
                holder.category.setText(expenseCategory.getName());
            }catch (Exception e){

            }

       // holder.category.setText(String.valueOf(expenseHistories.get(i).getExpenseCategory()));

        holder.expense_method.setText(String.valueOf(expenseHistories.get(i).getTransactionMethod()));
        holder.expense_date.setText(String.valueOf(expenseHistories.get(i).getCreated()));

        SystemUser systemUser = realm.where(SystemUser.class).equalTo("userId",expenseHistories.get(i).getToUser()).findFirst();
        if (systemUser!=null)
        {
            holder.use.setText(systemUser.getUsername());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ExpenseDetailsActivity.class);
                intent.putExtra("INVOICE",expenseHistories.get(i).getInvoiceId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

    }



    @Override
    public int getItemCount() {
        return expenseHistories != null ? expenseHistories.size() : 0;
    }

    public class ExpenseHistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView27)
        TextView expense_date;

        @BindView(R.id.tv_method)
        TextView expense_method;

        @BindView(R.id.tv_invoice)
        TextView expense_invoice;

        @BindView(R.id.tv_sales)
        TextView use;

        @BindView(R.id.tv_balance)
        TextView expense_amount;

        @BindView(R.id.tv_received)
        TextView category;


        public ExpenseHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

}
