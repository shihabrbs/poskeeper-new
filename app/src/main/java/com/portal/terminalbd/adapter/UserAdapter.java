package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.SystemUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.SystemUserViewHolder>{

    private Context context;
    private List<SystemUser> systemUsers;

    public UserAdapter(Context context, List<SystemUser> systemUsers) {
        this.context = context;
        this.systemUsers = systemUsers;
    }

    @NonNull
    @Override
    public SystemUserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_customer,viewGroup,false);

        return new SystemUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemUserViewHolder holder, int i) {

        holder.customerName.setText(systemUsers.get(i).getFullName());
        holder.customerMobile.setText(systemUsers.get(i).getUsername());
    }

    @Override
    public int getItemCount() {
        return systemUsers != null ? systemUsers.size() : 0;
    }

    public class SystemUserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.customerName)
        TextView customerName;

        @BindView(R.id.customerMobile)
        TextView customerMobile;


        public SystemUserViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

}
