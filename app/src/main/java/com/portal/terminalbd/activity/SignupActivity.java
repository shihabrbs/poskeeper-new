package com.portal.terminalbd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;
import com.portal.terminalbd.R;
import com.portal.terminalbd.model.ModelUser;
import com.portal.terminalbd.network.ApiInterface;
import com.portal.terminalbd.network.RetrofitApiClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    Spinner spinner;

    ArrayList<String> myList;
    @BindView(R.id.checkBox)
    CheckBox conditioncheckBox;

    @BindView(R.id.textView3)
    TextView mybtn;

    @BindView(R.id.shopnameid)
    TextInputEditText shopname;

    @BindView(R.id.phonenumberid)
    TextInputEditText phonenumber;

    @BindView(R.id.emailnoid)
    TextInputEditText emaill;

    @BindView(R.id.usernameid)
    TextInputEditText username;

    @BindView(R.id.selectusertype)
    RadioGroup rg;

    @BindView(R.id.ccp)
    CountryCodePicker ccp;

    String cc="";
    String setup_idd ="";
    String setup_iddd ="";
    String idd="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /*shoptypelist.add(0,"zero item");
        shoptypelist.add(1,"first item");
        shoptypelist.add(2,"second item");*/


        myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");


        ButterKnife.bind(this);

        rg.check(R.id.radio1);








        spinner = (Spinner) findViewById(R.id.spinnerid);
// Create an ArrayAdapter using the string array and a default spinner layout
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);*/

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, myList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        try {
            spinner.setAdapter(adapter);
        }catch (Exception e){

        }


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
                    ((TextView) parent.getChildAt(0)).setTextSize(18);
                } else {
                    Toast.makeText(SignupActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView textView = findViewById(R.id.dthana);
        String text = "Already have an account ? LOGIN";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {


                startActivity(new Intent(SignupActivity.this, SetupActivity.class));
                finish();

            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        ss.setSpan(clickableSpan1, 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new BackgroundColorSpan(Color.WHITE), 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        TextView textView2 = findViewById(R.id.termsandcondition);
        String text2 = "By using this app, you agree to the Terms and Condition";

        SpannableString sss = new SpannableString(text2);

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //Toast.makeText(SetupActivity.this, "one", Toast.LENGTH_SHORT).show();
                showTermsDialog();

            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        sss.setSpan(clickableSpan2, 35, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        sss.setSpan(new BackgroundColorSpan(Color.WHITE), 35, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 35, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2.setText(sss);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());


        conditioncheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conditioncheckBox.isChecked()) {
                    DrawableCompat.setTint(mybtn.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.orenge));
                    mybtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            final String value =
                                    ((RadioButton) findViewById(rg.getCheckedRadioButtonId()))
                                            .getText().toString();

                            /*Intent intent = new Intent(SignupActivity.this, VerifyOTPActivity.class);
                            intent.putExtra("PHONE", phonenumber.getText().toString());
                            startActivity(intent);*/
                            if (value.equals("Multiple User")) {

                                String shop_name = shopname.getText().toString();
                                String phone_no = removeLeadingZeroes(phonenumber.getText().toString());
                                cc = ccp.getSelectedCountryCodeWithPlus();


                                String emailll = emaill.getText().toString();
                                String password = "12345678";
                                String usernamee = username.getText().toString();
                                String text = spinner.getSelectedItem().toString();

                                if(TextUtils.isEmpty(shop_name)){
                                    shopname.setError("Please enter shop name");
                                    shopname.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(usernamee)){
                                    username.setError("Please enter username");
                                    username.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(phone_no)){
                                    phonenumber.setError("Please enter phone number");
                                    phonenumber.requestFocus();
                                    return;
                                }

                                if(TextUtils.isEmpty(emailll)){
                                    emaill.setError("Please enter email");
                                    emaill.requestFocus();
                                    return;
                                }if (text.equals("Shop Type")){

                                }
                                else{

                                    Intent intent = new Intent(SignupActivity.this,VerifyOTPActivity.class);
                                    intent.putExtra("phone",""+phone_no);
                                    intent.putExtra("cc",""+cc);
                                    intent.putExtra("usertype","multi");
                                    intent.putExtra("shopName",""+shop_name);
                                    intent.putExtra("shopType",""+text);
                                    intent.putExtra("email",""+emailll);
                                    intent.putExtra("userName",""+usernamee);
                                    intent.putExtra("multiuser","1");
                                    startActivity(intent);


                                   /* ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
                                    ModelUser modelUser = new ModelUser();
                                    modelUser.setShop_name(shop_name);
                                    modelUser.setShop_type(text);
                                    modelUser.setEmail(emailll);
                                    modelUser.setPhone(cc+phone_no);
                                    modelUser.setMulti_user("1");

                                    apiInterface.createsetup(modelUser).enqueue(new Callback<ModelUser>() {
                                        @Override
                                        public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                                            // Toast.makeText(SignupActivity.this, ""+response.body().getRes(), Toast.LENGTH_SHORT).show();
                                            Toast.makeText(SignupActivity.this, ""+response.body().getSetupid(), Toast.LENGTH_SHORT).show();
                                           idd = response.body().getSetupid();
                                            setup_idd= ""+response.body().getSetupid();
                                            String setup_id = response.body().getSetupid();
                                            String limitt = response.body().getUserlimit();
                                            Intent intent = new Intent(SignupActivity.this, MultipleUserActivity.class);
                                            intent.putExtra("setupId", setup_id);
                                            intent.putExtra("limit", limitt);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<ModelUser> call, Throwable t) {
                                            //  Toast.makeText(SignupActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    createMultiUser(usernamee,emailll,password,phone_no,setup_idd);*/

                                }






                            } else {

                                String shop_name = shopname.getText().toString();
                                String phone_no = phonenumber.getText().toString();
                                String emailll = emaill.getText().toString();
                                String password = "12345678";
                                String usernamee = username.getText().toString();
                                String text = spinner.getSelectedItem().toString();
                                cc = ccp.getSelectedCountryCodeWithPlus();

                                if(TextUtils.isEmpty(shop_name)){
                                    shopname.setError("Please enter shop name");
                                    shopname.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(phone_no)){
                                    phonenumber.setError("Please enter phone number");
                                    phonenumber.requestFocus();
                                    return;
                                }

                                if(TextUtils.isEmpty(emailll)){
                                    emaill.setError("Please enter email");
                                    emaill.requestFocus();
                                    return;
                                }if (text.equals("Shop Type")){

                                }
                                else{

                                    Intent intent = new Intent(SignupActivity.this,VerifyOTPActivity.class);
                                    intent.putExtra("phone",""+phone_no);
                                    intent.putExtra("cc",""+cc);
                                    intent.putExtra("usertype","single");
                                    intent.putExtra("shopName",""+shop_name);
                                    intent.putExtra("shopType",""+text);
                                    intent.putExtra("email",""+emailll);
                                    intent.putExtra("userName",""+usernamee);
                                    intent.putExtra("multiuser","0");
                                    startActivity(intent);





                                }








                            }

                        }
                    });
                } else {

                    DrawableCompat.setTint(mybtn.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.btn_disable));
                }
            }
        });


    }



    private void createMultiUser(String usernamee,String emailll,String password,String phone_no,String setupid) {

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        ModelUser modelUser = new ModelUser();
        modelUser.setUsername(usernamee);
        modelUser.setEmail(emailll);
        modelUser.setPhone(cc+phone_no);
        modelUser.setPassword(password);
        modelUser.setSetupid(setupid);
        modelUser.setUserlimit("3");

        apiInterface.createuser(modelUser).enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                // Toast.makeText(SignupActivity.this, ""+response.body().getRes(), Toast.LENGTH_SHORT).show();
                Toast.makeText(SignupActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                //  Toast.makeText(SignupActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static String removeLeadingZeroes(String str) {
        String strPattern = "^0+(?!$)";
        str = str.replaceAll(strPattern, "");
        return str;
    }

    /* Terms and condition dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(SignupActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_terms, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    /*@OnClick(R.id.gotologinpage)
    void gotologinpage(){
        startActivity(new Intent(SignupActivity.this, SetupActivity.class));
        finish();
    }*/
}