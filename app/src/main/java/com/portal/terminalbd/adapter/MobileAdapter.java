package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.MobileAccount;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MobileViewHolder>{

    private Context context;
    private List<MobileAccount> mobileAccounts;

    public MobileAdapter(Context context, List<MobileAccount> mobileAccounts) {
        this.context = context;
        this.mobileAccounts = mobileAccounts;
    }

    @NonNull
    @Override
    public MobileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_vendors,viewGroup,false);

        return new MobileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileViewHolder holder, int i) {

        holder.vendorName.setText(mobileAccounts.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mobileAccounts != null ? mobileAccounts.size() : 0;
    }

    public class MobileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vendorName)
        TextView vendorName;



        public MobileViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
