package com.portal.terminalbd.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.activity.CustomerPaymentActivity;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.CustomerInvoice;
import com.portal.terminalbd.model.CustomerLedeger;
import com.portal.terminalbd.model.ModelCustomerInvoice;
import com.portal.terminalbd.model.OrderItem;
import com.portal.terminalbd.network.APIClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CustomerSalesListAdapter extends RecyclerView.Adapter<CustomerSalesListAdapter.CustomerViewHolder> {


    private CustomerPaymentActivity context;
    private ArrayList<CustomerLedeger> customers;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ArrayList<ModelCustomerInvoice> modelCustomerInvoices = new ArrayList<>();
    String numberid = "";
    String cusname = "";
    String currency;
    public CustomerSalesListAdapter(CustomerPaymentActivity context, ArrayList<CustomerLedeger> customers) {
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customer_sale_list, viewGroup, false);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder customerViewHolder, int i) {

        currency = PreferenceManager.getCurrency(context);

        customerViewHolder.date.setText(customers.get(i).getUpdated());
        customerViewHolder.sales.setText(currency+" " + customers.get(i).getSales());
        customerViewHolder.received.setText(currency+" " + customers.get(i).getReceive());
        customerViewHolder.balance.setText(currency+" " + customers.get(i).getBalance());
        customerViewHolder.invoice.setText("" + customers.get(i).getSourceInvoice());
        customerViewHolder.method.setText("" + customers.get(i).getMethod());


        if (customers.get(i).getSourceInvoice().equals("") && customers.get(i).getMethod().equals(""))
        {
            customerViewHolder.devider.setVisibility(View.GONE);
        }
        else {
            customerViewHolder.devider.setVisibility(View.VISIBLE);
        }



        customerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getcustomerInvoice();
                getorders();
                String customer_id  = "" + customers.get(i).getSourceInvoice();
                if (customer_id.equals("")){

                }else {
                    showTermsDialog("" + customers.get(i).getSourceInvoice());
                }


            }
        });
    }


    private void showTermsDialog(String invoice) {


        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_customer_invoice, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView close = view.findViewById(R.id.close);
        RecyclerView recyclerView = view.findViewById(R.id.recycerviewitem);
        TextView billno = view.findViewById(R.id.textView29);
        TextView date = view.findViewById(R.id.tv_date);
        TextView method = view.findViewById(R.id.tv_method);
        TextView mobile = view.findViewById(R.id.tv_mobile);
        TextView customername = view.findViewById(R.id.tv_customername);
        TextView salesby = view.findViewById(R.id.tv_salesby);
        TextView subtotal = view.findViewById(R.id.textView40);
        TextView vat = view.findViewById(R.id.textView41);
        TextView discount = view.findViewById(R.id.textView42);
        TextView total = view.findViewById(R.id.textView43);
        CustomerInvoiceAdapter customerInvoiceAdapter;
        RecyclerView recyclerViewitems = view.findViewById(R.id.recycerviewitem);

        ArrayList<OrderItem> orderItemArrayList = new ArrayList<>();

        customerInvoiceAdapter = new CustomerInvoiceAdapter(context, orderItemArrayList);
        recyclerViewitems.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));


        compositeDisposable.add(APIClient.getInstance().getCustomerIncoice(""+ PreferenceManager.getSecretKey(context), ""+invoice)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<CustomerInvoice>>() {
                    @Override
                    public void onNext(final Response<CustomerInvoice> response) {

                        billno.setText("Bill No: "+ response.body().getInvoice());
                        date.setText("Date: "+ response.body().getCreated());
                        method.setText("Method: "+ response.body().getMethod());
                        customername.setText("Customer: "+ response.body().getCustomer());
                        mobile.setText("Mobile: "+ response.body().getCustomerMobile());
                        salesby.setText("Sales By: "+ response.body().getSalesBy());
                        subtotal.setText("৳ "+ response.body().getSubTotal());
                        if (response.body().getVat() == null){
                            vat.setText("৳ 0");
                        }else {
                            vat.setText("৳ "+ response.body().getVat());
                        }

                        if (response.body().getDiscount() == null){
                            discount.setText("৳ 0");
                        }else {
                            discount.setText("৳ "+ response.body().getDiscount());
                        }

                        total.setText("৳ "+ response.body().getTotal());

                        orderItemArrayList.addAll(response.body().getOrderItem());
                        recyclerViewitems.setAdapter(customerInvoiceAdapter);
                        customerInvoiceAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

      /*  try {
            billno.setText("" + numberid);
            customername.setText("Customer: " + cusname);
        } catch (Exception e) {

        }*/

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


       /* cusadapter = new CustomerSalesListAdapter(CustomerPaymentActivity.this, customerLedegers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

*/


        alertDialog.show();
    }


    private void getorders() {


    }



    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView27)
        TextView date;

        @BindView(R.id.textView25)
        TextView devider;

        @BindView(R.id.tv_sales)
        TextView sales;
        @BindView(R.id.tv_received)
        TextView received;
        @BindView(R.id.tv_balance)
        TextView balance;
        @BindView(R.id.tv_invoice)
        TextView invoice;
        @BindView(R.id.tv_method)
        TextView method;


        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
