package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.DIMSItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DIMSAdapter extends RecyclerView.Adapter<DIMSAdapter.DIMSViewHolder>{

    private Context context;
    private List<DIMSItem> dimsItems;

    public DIMSAdapter(Context context, List<DIMSItem> dimsItems) {
        this.context = context;
        this.dimsItems = dimsItems;
    }

    @NonNull
    @Override
    public DIMSViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_dims_item,viewGroup,false);

        return new DIMSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DIMSViewHolder salesItemViewHolder, final int i) {

        salesItemViewHolder.dims_medicine_name.setText(dimsItems.get(i).getMedicineName());
        salesItemViewHolder.dims_medicine_form.setText(dimsItems.get(i).getMedicineForm());
        salesItemViewHolder.dims_medicine_generic_name.setText(dimsItems.get(i).getGenericName());
        salesItemViewHolder.dims_medicine_brand_name.setText(dimsItems.get(i).getBrand());

    }

    @Override
    public int getItemCount() {
        return dimsItems != null ? dimsItems.size() : 0;
    }

    public void setFilter(List<DIMSItem> dims) {
        dimsItems = new ArrayList<>();
        dimsItems.addAll(dims);
        notifyDataSetChanged();
    }

    public class DIMSViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dims_medicine_name)
        TextView dims_medicine_name;

        @BindView(R.id.dims_medicine_form)
        TextView dims_medicine_form;

        @BindView(R.id.dims_medicine_generic_name)
        TextView dims_medicine_generic_name;

        @BindView(R.id.dims_medicine_brand_name)
        TextView dims_medicine_brand_name;


        public DIMSViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
