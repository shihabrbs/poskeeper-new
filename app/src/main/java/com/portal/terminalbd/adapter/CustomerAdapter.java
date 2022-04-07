package com.portal.terminalbd.adapter;

import android.app.AlertDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.activity.CustomerPaymentActivity;
import com.portal.terminalbd.model.AnonymousCustomer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>{

    private Context context;
    private List<AnonymousCustomer> customers;

    public CustomerAdapter(Context context, List<AnonymousCustomer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_customer,viewGroup,false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customer_list,viewGroup,false);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder customerViewHolder, int i) {

        customerViewHolder.customerName.setText(customers.get(i).getName());
        customerViewHolder.customerMobile.setText(customers.get(i).getMobile());

        customerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check for Internet Connection
                if (isConnected()) {
                   // Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, CustomerPaymentActivity.class);
                    intent.putExtra("name",""+customers.get(i).getName());
                    intent.putExtra("phone",""+customers.get(i).getMobile());
                    intent.putExtra("cusId",""+customers.get(i).getCustomerId());
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    private void showTermsDialog(String namee,String number) {


        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_create_customer, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView close = view.findViewById(R.id.close);
        ImageView resetBtn = view.findViewById(R.id.imageView12);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        ConstraintLayout back = view.findViewById(R.id.btn_back);
        EditText name = view.findViewById(R.id.nameid);
        EditText mobile = view.findViewById(R.id.mobileid);
        EditText obalance = view.findViewById(R.id.openingid);
        EditText address = view.findViewById(R.id.descriptionid);

        name.setText(""+namee);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               /* realm.beginTransaction();

                // SaleItem saleItem = realm.where(SaleItem.class).equalTo("name", String.valueOf(saleItems.get(i).getSaleStockId())).findFirst();

                AnonymousCustomer anonymousCustomer = new AnonymousCustomer();
                anonymousCustomer.setName("" + name.getText().toString());
                anonymousCustomer.setMobile("" + mobile.getText().toString());


                realm.insertOrUpdate(anonymousCustomer);
                realm.commitTransaction();*/


                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return customers != null ? customers.size() : 0;
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView customerName;

        @BindView(R.id.phone)
        TextView customerMobile;


        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
