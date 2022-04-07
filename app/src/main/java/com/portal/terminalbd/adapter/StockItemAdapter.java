package com.portal.terminalbd.adapter;

import android.app.AlertDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.portal.terminalbd.R;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.interfaces.ItemDeleteClickListener;
import com.portal.terminalbd.model.ModelCategory;
import com.portal.terminalbd.model.ModelCreateProduct;
import com.portal.terminalbd.model.ModelUnit;
import com.portal.terminalbd.model.PurchaseItem;
import com.portal.terminalbd.model.SaleItem;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.network.APIClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmQuery;
import retrofit2.Response;

public class StockItemAdapter extends RecyclerView.Adapter<StockItemAdapter.StockItemViewHolder> {

    private Context context;
    private List<StockItem> stockItems;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    ArrayList<ModelUnit> unitArrayList = new ArrayList<>();
    ArrayList unitNameList = new ArrayList<>();
    String utility_id;
    boolean isFirst = false;
    private int quantity;
    private double subTotal;
    String discountpercent;
    double discountprice;

    Realm realm;
    ArrayList<ModelCategory> categoryArrayList = new ArrayList<>();
    ArrayList categoryNameList = new ArrayList<>();
    String category_id;

    String name, mrp, pp, brand;
    String brandd;
    Boolean btnshow = false;
    Boolean cartlayoutshow = false;
    private ItemDeleteClickListener itemDeleteClickListener;
    public StockItemAdapter(Context context, List<StockItem> stockItems,ItemDeleteClickListener itemDeleteClickListener) {
        this.context = context;
        this.stockItems = stockItems;
        this.itemDeleteClickListener = itemDeleteClickListener;
        realm = Realm.getDefaultInstance();

    }

    @NonNull
    @Override
    public StockItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart_stock, viewGroup, false);
        // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_stock_items,viewGroup,false);

        return new StockItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockItemViewHolder salesItemViewHolder, final int i) {

        categoryArrayList.clear();
        unitArrayList.clear();

        ModelCategory modelCategory = new ModelCategory();
        modelCategory.setCategoryId(100);
        modelCategory.setName(""+context.getResources().getString(R.string.string_selectacat));
        categoryArrayList.add(modelCategory);

        ModelUnit modelUnit = new ModelUnit();
        modelUnit.setUnitId(100);
        modelUnit.setName(""+context.getResources().getString(R.string.string_selectaunit));
        unitArrayList.add(modelUnit);

        getUnits();
        getCategory();

        salesItemViewHolder.name.setText(stockItems.get(i).getName());
        salesItemViewHolder.pp.setText(String.valueOf("৳ " + stockItems.get(i).getPurchasePrice()));
        salesItemViewHolder.mrp.setText(String.valueOf("৳ " + stockItems.get(i).getSalesPrice()));
        salesItemViewHolder.qnt.setText(String.valueOf("" + stockItems.get(i).getQuantity()));
        salesItemViewHolder.unit.setText("" + stockItems.get(i).getUnit());

        String title = context.getResources().getString(R.string.stocktitle);
        String mrpname = context.getResources().getString(R.string.string_mrp);
        String unitname = context.getResources().getString(R.string.string_unit);
        String ppname = context.getResources().getString(R.string.string_pp);

        salesItemViewHolder.stockname.setText(""+title);
        salesItemViewHolder.mrpname.setText(""+mrpname);
        salesItemViewHolder.unitname.setText(""+unitname);
        salesItemViewHolder.ppname.setText(""+ppname);

        salesItemViewHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                /*salesItemViewHolder.cartlayout.setVisibility(View.VISIBLE);

                if (cartlayoutshow == false){
                    salesItemViewHolder.cartlayout.setVisibility(View.VISIBLE);
                    cartlayoutshow = true;
                }else {
                    salesItemViewHolder.cartlayout.setVisibility(View.GONE);
                    cartlayoutshow = false;
                }*/
            }
        });

 /*       salesItemViewHolder.dotsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (btnshow == false){
                    salesItemViewHolder.editbtn.setVisibility(View.VISIBLE);
                 //   salesItemViewHolder.cartlayout.setVisibility(View.VISIBLE);
                    salesItemViewHolder.dotsbtn.setVisibility(View.GONE);
                    btnshow = true;
                }else {
                    salesItemViewHolder.editbtn.setVisibility(View.GONE);
                    salesItemViewHolder.cartlayout.setVisibility(View.GONE);
                    btnshow = false;
                }

            }
        });*/
        
        salesItemViewHolder.cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean find = salesItemexists("" + stockItems.get(i).getItemId());

                if (find == false) {


                        if (!realm.isInTransaction()) {
                            realm.beginTransaction();
                        }

                        PurchaseItem purchaseItem = new PurchaseItem();

                        quantity = Integer.parseInt("1");
                        double edit_mrp_value = Double.parseDouble(""+stockItems.get(i).getSalesPrice());

                        try {
                            if (discountpercent.equals("%")) {
                                int dispercent = Integer.parseInt("0");
                                discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                                subTotal = discountprice * quantity;
                            } else {
                                int dispercent = Integer.parseInt("" + discountpercent);
                                discountprice = edit_mrp_value - ((edit_mrp_value / 100) * dispercent);
                                subTotal = discountprice * quantity;
                            }
                        } catch (Exception e) {

                        }

                        purchaseItem.setPurchaseStockId(String.valueOf(stockItems.get(i).getItemId()));
                        purchaseItem.setPurchaseItemName(stockItems.get(i).getName());
                        purchaseItem.setPurchasePpPrice(String.valueOf(stockItems.get(i).getPurchasePrice()));
                        purchaseItem.setPurchaseMrpPrice(String.valueOf(stockItems.get(i).getSalesPrice()));
                        purchaseItem.setPurchaseQuantity(String.valueOf(quantity));
                        purchaseItem.setPurchaseSubTotal(String.valueOf(subTotal));


                        realm.copyToRealmOrUpdate(purchaseItem);

                        realm.commitTransaction();


                }

                itemDeleteClickListener.onClickNotify(true);
            }
        });

        salesItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProductDetails("" + stockItems.get(i).getItemId());
            }
        });


        salesItemViewHolder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = stockItems.get(i).getName();
                mrp = "" + stockItems.get(i).getSalesPrice();
                //  getEditProducts(""+stockItems.get(i).getItemId());
                showTermsDialog("" + stockItems.get(i).getItemId());
            }
        });


    }

    private void getCategory() {
        compositeDisposable.add(APIClient.getInstance().getCategories("" + PreferenceManager.getSecretKey(context))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelCategory>>>() {
                    @Override
                    public void onNext(final Response<List<ModelCategory>> response) {


                        try {

                            categoryArrayList.addAll(response.body());

                        } catch (Exception e) {

                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private boolean salesItemexists(String id) {

        RealmQuery<PurchaseItem> query = realm.where(PurchaseItem.class)
                .equalTo("purchaseStockId", id);
        return query.count() != 0;
    }

    private void getEditProducts(String id) {

        compositeDisposable.add(APIClient.getInstance().getEditProduct("" + PreferenceManager.getSecretKey(context), "" + id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelCategory>>() {
                    @Override
                    public void onNext(final Response<ModelCategory> response) {

                        Toast.makeText(context, "" + response.body().getBrandName(), Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getUnits() {

        compositeDisposable.add(APIClient.getInstance().getUnit("" + PreferenceManager.getSecretKey(context))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelUnit>>>() {
                    @Override
                    public void onNext(final Response<List<ModelUnit>> response) {

                        try {
                            unitArrayList.addAll(response.body());
                        }catch (Exception e){

                        }



                        // Toast.makeText(StockActivity.this, ""+unitArrayList.get(10).getName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    private void showTermsDialog(String id) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_stock_product_update, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView close = view.findViewById(R.id.close);
        ImageView resetBtn = view.findViewById(R.id.imageView12);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        TextView tvsave = view.findViewById(R.id.savetv);
        ConstraintLayout back = view.findViewById(R.id.btn_back);
        Spinner spinner = view.findViewById(R.id.spinnercatid);
        Spinner spinnerUnit = view.findViewById(R.id.spinnerunitid);
        EditText namee = view.findViewById(R.id.nameid);
        EditText mrpp = view.findViewById(R.id.mrpid);

        EditText pname = view.findViewById(R.id.nameid);
        EditText pmrp = view.findViewById(R.id.mrpid);
        EditText pp = view.findViewById(R.id.ppid);
        EditText brand = view.findViewById(R.id.Brandid);
        EditText miniQty = view.findViewById(R.id.descriptionid);
        EditText openingQty = view.findViewById(R.id.openingqtyid);
        EditText description = view.findViewById(R.id.minqtyid);


        compositeDisposable.add(APIClient.getInstance().getEditProduct("" + PreferenceManager.getSecretKey(context), "" + id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelCategory>>() {
                    @Override
                    public void onNext(final Response<ModelCategory> response) {

                        try {

                            pname.setText("" + response.body().getName());
                            pmrp.setText("" + response.body().getPrice());
                            pp.setText("");
                            brand.setText("" + response.body().getBrandName());
                            miniQty.setText("" + response.body().getMinQuantity());
                            description.setText("");

                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));


       /* namee.setText(""+name);
        mrpp.setText(""+mrp);
*/
        //spinner category dropdown

        for (ModelCategory helpers : categoryArrayList) {

            categoryNameList.add(helpers.getName());

        }

        //android.R.layout.simple_spinner_item
        ArrayAdapter adapter2 = new ArrayAdapter(context, R.layout.spinner_item, categoryNameList);

        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter2);

        try {
            category_id = categoryArrayList.get(0).getName();
        } catch (Exception e) {

        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  String valu = adapterView.getItemAtPosition(i).toString();
                category_id = categoryArrayList.get(i).getName();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        for (ModelUnit helper : unitArrayList) {

            unitNameList.add(helper.getName());

        }

        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.spinner_item, unitNameList);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerUnit.setAdapter(adapter);

        try {
            utility_id = unitArrayList.get(0).getName();
        } catch (Exception e) {

        }

        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  String valu = adapterView.getItemAtPosition(i).toString();
                utility_id = unitArrayList.get(i).getName();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelCreateProduct modelCreateProduct = new ModelCreateProduct();

                modelCreateProduct.setId("" + id);
                modelCreateProduct.setName("" + pname.getText());
                modelCreateProduct.setCategory("" + category_id);
                modelCreateProduct.setBrandName("" + brand.getText());
                modelCreateProduct.setPrice("" + pmrp.getText());
                modelCreateProduct.setUnit("" + utility_id);
              //  modelCreateProduct.setMinQuantity("" + miniQty.getText());

                updateStockProduct(modelCreateProduct, "" + PreferenceManager.getSecretKey(context));

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate( R.layout.custom_toast, null );

             //   View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

                TextView toastText = layout.findViewById(R.id.toasttext);
                toastText.setText("Stock Item insert Successfully");

                final Toast toast = new Toast(context);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

                alertDialog.dismiss();


            }
        });


        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compositeDisposable.add(APIClient.getInstance().getEditProduct("" + PreferenceManager.getSecretKey(context), "" + id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableObserver<Response<ModelCategory>>() {
                            @Override
                            public void onNext(final Response<ModelCategory> response) {

                                try {
                                    pname.setText("" + response.body().getName());
                                    pmrp.setText("" + response.body().getPrice());
                                    pp.setText("");
                                    brand.setText("" + response.body().getBrandName());
                                    miniQty.setText("" + response.body().getMinQuantity());
                                    description.setText("");
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }));

            }
        });


        alertDialog.show();
    }

    private void showProductDetails(String id) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_stock_product_details, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView close = view.findViewById(R.id.close);
        ImageView resetBtn = view.findViewById(R.id.imageView12);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        ConstraintLayout back = view.findViewById(R.id.btn_back);

        TextView name = view.findViewById(R.id.productname);
        SpinKitView spinKitView = view.findViewById(R.id.spin_kit);


        compositeDisposable.add(APIClient.getInstance().getEditProduct("" + PreferenceManager.getSecretKey(context), "" + id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelCategory>>() {
                    @Override
                    public void onNext(final Response<ModelCategory> response) {

                        try {

                            name.setText("" + response.body().getName());
                            spinKitView.setVisibility(View.GONE);

                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });




        alertDialog.show();
    }

    private void syncData() {

        realm.beginTransaction();
        realm.delete(StockItem.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getStockItem(PreferenceManager.getSecretKey(context))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<StockItem>>>() {
                    @Override
                    public void onNext(final Response<List<StockItem>> listResponse) {

                        // progressDialog.dismiss();

                        if (listResponse.isSuccessful()) {

                            if (listResponse.body() != null && listResponse.body().size() > 0) {

                                realm.beginTransaction();

                                for (int i = 0; i < listResponse.body().size(); i++) {

                                    StockItem stockItem = realm.createObject(StockItem.class);
                                    stockItem.setCategoryId(listResponse.body().get(i).getCategoryId());
                                    stockItem.setCategoryName(listResponse.body().get(i).getCategoryName());
                                    stockItem.setGlobalId(listResponse.body().get(i).getGlobalId());
                                    stockItem.setItemId(listResponse.body().get(i).getItemId());
                                    stockItem.setName(listResponse.body().get(i).getName());
                                    stockItem.setPrintName(listResponse.body().get(i).getPrintName());
                                    stockItem.setPurchasePrice(listResponse.body().get(i).getPurchasePrice());
                                    stockItem.setSalesPrice(listResponse.body().get(i).getSalesPrice());
                                    stockItem.setQuantity(listResponse.body().get(i).getQuantity());
                                    stockItem.setUnit(listResponse.body().get(i).getUnit());
                                    stockItem.setUnitId(listResponse.body().get(i).getUnitId());
                                    stockItem.setPrintHidden(listResponse.body().get(i).getPrintHidden());

                                    if (TextUtils.isEmpty(listResponse.body().get(i).getImagePath())) {

                                        stockItem.setImage(null);
                                    } else {

                                    }

                                }

                                realm.commitTransaction();

                            }


                        } else {


                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }


    private void updateStockProduct(ModelCreateProduct modelCreateProduct, String uniqueCode) {
        //Toast.makeText(context, "Product Updated", Toast.LENGTH_SHORT).show();

        compositeDisposable.add(APIClient.getInstance().updateStockItem("" + uniqueCode, modelCreateProduct)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelCreateProduct>>() {
                    @Override
                    public void onNext(final Response<ModelCreateProduct> response) {


                      

                       /* String userid = ""+response.body().getUser_id();
                        String name = ""+response.body().getName();
                        String address = ""+response.body().getAddress();
                        String anothernumber = ""+response.body().getPhone();
                        String email = ""+response.body().getEmail();

                        Intent intent = new Intent(SignUpActivity.this,OTPWebActivity.class);
                        intent.putExtra("otpcode",""+response.body().getPassword());
                        intent.putExtra("phone",""+response.body().getUsername());
                        intent.putExtra("userid",""+userid);
                        intent.putExtra("name",""+name);
                        intent.putExtra("address",""+address);
                        intent.putExtra("anothernumber",""+anothernumber);
                        intent.putExtra("email",""+email);
                        startActivity(intent);*/


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public int getItemCount() {
        return stockItems != null ? stockItems.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setFilter(List<StockItem> stockItem) {
        stockItems = new ArrayList<>();
        stockItems.addAll(stockItem);
        notifyDataSetChanged();
    }

    public class StockItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.textView18)
        TextView stockname;

        @BindView(R.id.textView22)
        TextView mrpname;

        @BindView(R.id.textView24)
        TextView unitname;

        @BindView(R.id.textView26)
        TextView ppname;

        @BindView(R.id.pp)
        TextView pp;

        @BindView(R.id.mrp)
        TextView mrp;

        @BindView(R.id.qnt)
        TextView qnt;

        @BindView(R.id.unit)
        TextView unit;

        @BindView(R.id.imageView11)
        ImageView editbtn;
        
        @BindView(R.id.iv_carticon)
        ImageView cartbtn;

        @BindView(R.id.imageView13)
        ImageView dotsbtn;

        @BindView(R.id.imageView15)
        ImageView btndelete;

        @BindView(R.id.constraintLayout6)
        ConstraintLayout cartlayout;


        public StockItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
