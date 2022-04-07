package com.portal.terminalbd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.portal.terminalbd.R;
import com.portal.terminalbd.model.ModelAddProduct;
import com.portal.terminalbd.network.ApiInterface;
import com.portal.terminalbd.network.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder> {
    Context context;
    List<ModelAddProduct> tables;

    public TableAdapter(Context context, List<ModelAddProduct> tables) {
        this.context = context;
        this.tables = tables;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int index = holder.getAdapterPosition()+1;
        int positionss = Integer.parseInt("" + holder.getAdapterPosition());
        holder.tableno.setText(tables.get(position).getTabel_no());
        holder.seatno.setText(tables.get(position).getSeat_no());
        holder.id.setText(""+index);

        Glide.with(context)
                .load("http://shihab.techdevbd.com/sokol_bazar/file_upload_api/"+tables.get(position).getUrl())
                .into(holder.catimg);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
                ModelAddProduct modelAddProduct = new ModelAddProduct();
                modelAddProduct.setId(tables.get(position).getId());
                modelAddProduct.setImgurl(tables.get(position).getImgurl());

                apiInterface.deleteTable(modelAddProduct).enqueue(new Callback<ModelAddProduct>() {
                    @Override
                    public void onResponse(Call<ModelAddProduct> call, Response<ModelAddProduct> response) {
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        tables.remove(positionss);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ModelAddProduct> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView catimg,delete;
        TextView tableno,seatno,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tableno = itemView.findViewById(R.id.tableNo);
            seatno = itemView.findViewById(R.id.seatNo);
            id = itemView.findViewById(R.id.textView15);
            catimg = itemView.findViewById(R.id.categoryimg);
            delete = itemView.findViewById(R.id.deletecategory);
        }
    }
}
