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

public class AddCategoryAdapter extends RecyclerView.Adapter<AddCategoryAdapter.MyViewHolder> {

    Context context;
    List<ModelAddProduct> categorys;

    public AddCategoryAdapter(Context context, List<ModelAddProduct> categorys) {
        this.context = context;
        this.categorys = categorys;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.catname.setText(categorys.get(position).getCategorys());
        holder.id.setText(""+getItemId(position));

        Glide.with(context)
                .load("http://shihab.techdevbd.com/sokol_bazar/file_upload_api/"+categorys.get(position).getImgurl())
                .override(300, 200)
                .into(holder.catimg);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
              ModelAddProduct modelAddProduct = new ModelAddProduct();
              modelAddProduct.setId(categorys.get(position).getId());
              modelAddProduct.setImgurl(categorys.get(position).getImgurl());

              apiInterface.deleteCategory(modelAddProduct).enqueue(new Callback<ModelAddProduct>() {
                  @Override
                  public void onResponse(Call<ModelAddProduct> call, Response<ModelAddProduct> response) {
                      Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
        return categorys.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView catimg,delete;
        TextView catname,id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catname = itemView.findViewById(R.id.categoryname);
            id = itemView.findViewById(R.id.textView15);
            catimg = itemView.findViewById(R.id.categoryimg);
            delete = itemView.findViewById(R.id.deletecategory);
        }
    }
}
