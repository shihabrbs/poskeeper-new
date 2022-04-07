package com.portal.terminalbd.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.MultiUserAdapter;
import com.portal.terminalbd.model.ModelUser;
import com.portal.terminalbd.network.ApiInterface;
import com.portal.terminalbd.network.RetrofitApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultipleUserActivity extends AppCompatActivity {
    String setupid="";
    String limit;
    String cc = "";

    ArrayList<ModelUser> users;
    MultiUserAdapter multiUserAdapter;
    ApiInterface apiInterface;
    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_user);

        swipeRefreshLayout = findViewById(R.id.refreshid);



        setupid = getIntent().getStringExtra("setupId");
        limit = getIntent().getStringExtra("limit");

        //  Toast.makeText(this, "" + setupid + "  " + limit, Toast.LENGTH_LONG).show();

        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        multiUserAdapter = new MultiUserAdapter(getApplicationContext(), users);


        getUsers();

     /*   swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
            }
        });
        onRefresh();*/


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }



    public void btn_add_multiuser(View view) {
        showMultiUserDialog();
    }


    /* Add Multi User dialog */
    private void showMultiUserDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(MultipleUserActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_multiple_user, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MultipleUserActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        CountryCodePicker cccp = view.findViewById(R.id.cccp);

        TextView createbtn = view.findViewById(R.id.textView3);
        TextInputEditText username = view.findViewById(R.id.shopnameid);
        TextInputEditText phone = view.findViewById(R.id.phonenumberid);
        TextInputEditText email = view.findViewById(R.id.emailnoid);
        TextInputEditText password = view.findViewById(R.id.passwordid);
        ImageView close = view.findViewById(R.id.dialogclose);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(MultipleUserActivity.this, "multi user create", Toast.LENGTH_SHORT).show();

                String usernamee = username.getText().toString();
                String phone_no = phone.getText().toString();
                String cc = cccp.getSelectedCountryCodeWithPlus();
                String emailll = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(usernamee)) {
                    username.setError("Please enter username");
                    username.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(phone_no)) {
                    phone.setError("Please enter phone number");
                    phone.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(emailll)) {
                    email.setError("Please enter email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    password.setError("Please enter Password");
                    password.requestFocus();
                    return;
                } else {

                    ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
                    ModelUser modelUser = new ModelUser();
                    modelUser.setUsername(usernamee);
                    modelUser.setPassword(pass);
                    modelUser.setSetupid(setupid);
                    modelUser.setUserlimit(limit);
                    modelUser.setEmail(emailll);
                    modelUser.setPhone(cc + phone_no);

                    apiInterface.createuser(modelUser).enqueue(new Callback<ModelUser>() {
                        @Override
                        public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                            // Toast.makeText(SignupActivity.this, ""+response.body().getRes(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MultipleUserActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onFailure(Call<ModelUser> call, Throwable t) {
                            //  Toast.makeText(SignupActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                multiUserAdapter.notifyDataSetChanged();
                getUsers();

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

    private void getUsers() {
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        ModelUser modelUser = new ModelUser();
        modelUser.setSetupid(setupid);
        apiInterface.getUsers(modelUser).enqueue(new Callback<List<ModelUser>>() {
            @Override
            public void onResponse(Call<List<ModelUser>> call, Response<List<ModelUser>> response) {

                users.clear();
                users.addAll(response.body());
                recyclerView.setAdapter(multiUserAdapter);
                multiUserAdapter.notifyDataSetChanged();

                /*if (users.size()==0){
                    Toast.makeText(getApplication(), "kisu nai", Toast.LENGTH_SHORT).show();
                    goproductpage.setVisibility(View.GONE);
                }else {
                    Toast.makeText(getApplication(), "kisu ase", Toast.LENGTH_SHORT).show();
                    goproductpage.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void onFailure(Call<List<ModelUser>> call, Throwable t) {

            }
        });
    }

    public void btn_back(View view) {
        onBackPressed();
    }

    public void btn_add_cagegory(View view) {
        Intent intent = new Intent(MultipleUserActivity.this, CategoryActivity.class);
        intent.putExtra("shopid",""+setupid);
        startActivity(intent);
    }
}