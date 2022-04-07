package com.portal.terminalbd.adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.interfaces.ItemClickListener;
import com.portal.terminalbd.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categories;

    private ItemClickListener clickListener;

    private int row_index = -1;


    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_category,viewGroup,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int i) {

        holder.category_name.setText(categories.get(i).getName());

        if(row_index==i){

            holder.category_name.setBackground(context.getResources().getDrawable(R.drawable.selected_category_background));
            holder.category_name.setTextColor(Color.WHITE);
        }
        else
        {
            holder.category_name.setBackground(context.getResources().getDrawable(R.drawable.edit_text_round_corner_border));
            holder.category_name.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public void SetItemClickListener(ItemClickListener itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    public void SetPosition(int position)
    {
        this.row_index = position;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.category_name)
        TextView category_name;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view,getAdapterPosition());
        }
    }
}
