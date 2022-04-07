package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.portal.terminalbd.R;
import com.portal.terminalbd.interfaces.ItemClickListener;
import com.portal.terminalbd.model.TokenNo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.TokenViewHolder> {

    private Context context;
    private List<TokenNo> tokenNos;

    private List<TokenNo> tokenNoList = new ArrayList<>();

    public TokenAdapter(Context context, List<TokenNo> tokenNos) {
        this.context = context;
        this.tokenNos = tokenNos;
    }

    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_token_no,viewGroup,false);

        return new TokenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder holder, int i) {

        holder.tokenBox.setText(tokenNos.get(i).getTokenName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                CheckBox chk = (CheckBox) view;

                if (chk.isChecked())
                {
                    tokenNoList.add(tokenNos.get(position));

                }else if (!chk.isChecked())
                {
                    tokenNoList.remove(tokenNos.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tokenNos != null ? tokenNos.size() : 0;
    }

    public List<TokenNo> getSelectedToken()
    {
        return tokenNoList;
    }


    public class TokenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.token_box)
        CheckBox tokenBox;

        private ItemClickListener itemClickListener;

        public TokenViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

            tokenBox.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition());
        }
    }
}
