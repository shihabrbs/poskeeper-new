package com.portal.terminalbd.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.AddCategoryAdapter;
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

public class CategoryActivity extends AppCompatActivity {

    boolean isOnlyImageAllowed = true;
    ImageView sampleimg;
    private String filePath;
    private static final int PICK_PHOTO = 1958;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    ArrayList<ModelAddProduct> category;
    AddCategoryAdapter categoryAdapter;
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    ConstraintLayout goproductpage;
    String status = "";
    String shopid;
    SwipeRefreshLayout swipeRefreshLayout;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventModel event) throws ClassNotFoundException {
        if (event.isTagMatchWith("response")) {
            String responseMessage = "Data insert " + event.getMessage();
            status = event.getMessage();
            // responseTextView.setText(responseMessage);
            Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        verifyStoragePermissions(this);
        swipeRefreshLayout = findViewById(R.id.refreshid);
        category = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        categoryAdapter = new AddCategoryAdapter(getApplicationContext(), category);


        shopid = getIntent().getStringExtra("shopid");
        Toast.makeText(this, ""+shopid, Toast.LENGTH_SHORT).show();
        getcategory();

        goproductpage = findViewById(R.id.constraintLayout4);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getcategory();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getcategory() {
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        ModelAddProduct modelAddProduct = new ModelAddProduct();
        modelAddProduct.setShopId(""+shopid);

        apiInterface.getCategory(modelAddProduct).enqueue(new Callback<List<ModelAddProduct>>() {
            @Override
            public void onResponse(Call<List<ModelAddProduct>> call, Response<List<ModelAddProduct>> response) {

                category.clear();
                category.addAll(response.body());
                recyclerView.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();

                if (category.size() == 0) {
                    //Toast.makeText(getApplication(), "kisu nai", Toast.LENGTH_SHORT).show();
                    goproductpage.setVisibility(View.GONE);
                } else {
                 //   Toast.makeText(getApplication(), "kisu ase", Toast.LENGTH_SHORT).show();
                    goproductpage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<ModelAddProduct>> call, Throwable t) {

            }
        });
    }

    public void btn_productpage(View view) {
        Intent intent = new Intent(CategoryActivity.this, ProductActivity.class);
        intent.putExtra("shopid",""+shopid);
        startActivity(intent);
    }

    /* Add Category dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(CategoryActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_category, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView picimg = view.findViewById(R.id.imageView4);
        TextInputEditText categorys = view.findViewById(R.id.categoryid);
        TextView btnaddcategory = view.findViewById(R.id.textView4);
        sampleimg = view.findViewById(R.id.imageView8);


        ImageView close = view.findViewById(R.id.dialogclose);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        btnaddcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                String cat = categorys.getText().toString();
              //  String id = "80";



                NetworkCallMain.categoryfileUpload(filePath, new ModelAddProduct(cat,shopid));

               /* if (status == "Succesful"){

                    if (category.size()==0){

                        goproductpage.setVisibility(View.GONE);
                    }else {

                        goproductpage.setVisibility(View.VISIBLE);
                    }
                }*/

                getcategory();
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

        alertDialog.show();

    }

    public void btn_add_cagegory(View view) {
        showTermsDialog();
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