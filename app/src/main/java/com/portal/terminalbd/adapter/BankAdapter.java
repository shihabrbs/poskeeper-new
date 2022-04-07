package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.BankAccount;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder>{

    private Context context;
    private List<BankAccount> BankAccounts;

    public BankAdapter(Context context, List<BankAccount> BankAccounts) {
        this.context = context;
        this.BankAccounts = BankAccounts;
    }

    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_vendors,viewGroup,false);

        return new BankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder customerViewHolder, int i) {

        customerViewHolder.vendorName.setText(BankAccounts.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return BankAccounts != null ? BankAccounts.size() : 0;
    }

    public class BankViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vendorName)
        TextView vendorName;



        public BankViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
