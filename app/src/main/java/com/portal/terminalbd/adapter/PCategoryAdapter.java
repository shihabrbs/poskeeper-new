package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PCategoryAdapter extends RecyclerView.Adapter<PCategoryAdapter.PCategoryViewHolder> {


    private Context context;
    private List<Category> categories;

    public PCategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public PCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_categories, viewGroup, false);

        return new PCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PCategoryViewHolder holder, int i) {
        holder.catName.setText(categories.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public class PCategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.catName)
        TextView catName;

        public PCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
