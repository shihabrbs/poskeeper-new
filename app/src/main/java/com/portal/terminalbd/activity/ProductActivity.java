package com.portal.terminalbd.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.ProductsAdapter;
import com.portal.terminalbd.model.EventModel;
import com.portal.terminalbd.model.ModelAddProduct;
import com.portal.terminalbd.network.ApiInterface;
import com.portal.terminalbd.network.NetworkCallMain;
import com.portal.terminalbd.network.RetrofitApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    boolean isOnlyImageAllowed = true;
    ImageView sampleimg;
    private String filePath;
    private static final int PICK_PHOTO = 1958;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    ArrayList<ModelAddProduct> products;
    ProductsAdapter productsAdapter;
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    String shopid;
    SwipeRefreshLayout swipeRefreshLayout;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventModel event) throws ClassNotFoundException {
        if (event.isTagMatchWith("response")) {
            String responseMessage = "Data insert " + event.getMessage();
            // responseTextView.setText(responseMessage);
            Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        verifyStoragePermissions(this);
        swipeRefreshLayout = findViewById(R.id.refreshid);
        shopid = getIntent().getStringExtra("shopid");

        products = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        productsAdapter = new ProductsAdapter(getApplicationContext(), products);
        getProducts();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProducts();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void btn_add_product(View view) {
       // showProductDialog();
        Intent intent = new Intent(ProductActivity.this,AddNewProductActivity.class);
        startActivity(intent);
    }

    /* Add Product dialog */
    private void showProductDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(ProductActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_product, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView picimg = view.findViewById(R.id.imageView6);
        sampleimg = view.findViewById(R.id.imageView7);
        TextView addProductBtn = view.findViewById(R.id.textView5);

        TextInputEditText name, price, dprice;

        name = view.findViewById(R.id.nameid);
        price = view.findViewById(R.id.priceid);
        dprice = view.findViewById(R.id.discountprice);

        ImageView close = view.findViewById(R.id.dialogclose);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        Spinner spinner;

        spinner = view.findViewById(R.id.spinnercatid);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
                    ((TextView) parent.getChildAt(0)).setTextSize(18);
                } else {
                    Toast.makeText(ProductActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        picimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sampleimg.setVisibility(View.VISIBLE);

             /*   Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

*/

                Intent intent;

                if (isOnlyImageAllowed) {
                    // only image can be selected
                    intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                } else {
                    // any type of files including image can be selected
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("file/*");
                }

                startActivityForResult(intent, PICK_PHOTO);


            }
        });


        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alertDialog.dismiss();


                String namee = name.getText().toString();
                String pricee = price.getText().toString();
                String disount = dprice.getText().toString();
                String categorytext = spinner.getSelectedItem().toString();
                if (namee.equals("")) {
                    name.setError("Name Required");
                    name.requestFocus();
                    return;
                }
                if (pricee.equals("")) {
                    price.setError("Price Required");
                    price.requestFocus();
                    return;
                }
                if (categorytext.equals("Select Category")) {

                    Toast.makeText(ProductActivity.this, "Select Category", Toast.LENGTH_SHORT).show();

                } else {


                    String pathname = "" + filePath;


                    if (pathname.equals("null")) {
                        Toast.makeText(ProductActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
                    } else {
                        NetworkCallMain.fileUpload(filePath, new ModelAddProduct(namee, pricee, disount, categorytext, shopid));
                    }


                }
                getProducts();

            }
        });

      /*  TextView close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });*/

        alertDialog.show();

    }


    private void getProducts() {
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        ModelAddProduct modelAddProduct = new ModelAddProduct();
        modelAddProduct.setShopId(shopid);




        apiInterface.getproducts(modelAddProduct).enqueue(new Callback<List<ModelAddProduct>>() {
            @Override
            public void onResponse(Call<List<ModelAddProduct>> call, Response<List<ModelAddProduct>> response) {

                try {

                    products.clear();
                    products.addAll(response.body());
                    recyclerView.setAdapter(productsAdapter);
                    productsAdapter.notifyDataSetChanged();

                }catch (Exception e){

                }



            }

            @Override
            public void onFailure(Call<List<ModelAddProduct>> call, Throwable t) {

            }
        });
    }

    public void btn_back(View view) {
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO) {
            Uri imageUri = data.getData();
            filePath = getPath(imageUri);
            sampleimg.setImageURI(imageUri);

        }
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}