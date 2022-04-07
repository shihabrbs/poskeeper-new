package com.portal.terminalbd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.ModelUser;

import java.util.ArrayList;

public class MultiUserAdapter extends RecyclerView.Adapter<MultiUserAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelUser> users;

    public MultiUserAdapter(Context context, ArrayList<ModelUser> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multiuser,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(users.get(position).getUsername());
        holder.phone.setText(users.get(position).getPhone());

        holder.id.setText(""+getItemId(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView,phone,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.usernameid);
            phone = itemView.findViewById(R.id.phonenumber);
            id = itemView.findViewById(R.id.textView14);
        }
    }
}
