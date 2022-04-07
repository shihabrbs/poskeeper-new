package com.portal.terminalbd.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.portal.terminalbd.R;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.textusererror)
   // EditText username;
    TextInputLayout usereditTexterror;

    @BindView(R.id.edit_sign_in_username)
   // EditText username
    TextInputEditText usereditText;


    @BindView(R.id.passerror)
   // EditText password;
    TextInputLayout passworderror;

    @BindView(R.id.edit_sign_in_password)
   // EditText password;
    TextInputEditText password;

    @BindView(R.id.logo)
    TextView logo;

    CompositeDisposable compositeDisposable;
    Realm realm;
    List<StockItem> stockItemList;
    ProgressDialog progressDialog;

    Setup setup;
    private String pharmacyName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();


        /*
        Pharmacy Information
         */
        setup = realm.where(Setup.class).findFirst();

        if (setup!=null) {
            pharmacyName = setup.getName();
            PreferenceManager.setModule(LoginActivity.this,setup.getMainAppName());
        }

        logo.setText(pharmacyName);

        compositeDisposable = new CompositeDisposable();

        stockItemList = new ArrayList<>();

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Syncing");
        progressDialog.setMessage("Please Wait...");




    }
    
    @OnClick(R.id.gotosignuppage)
    void goToSigninPage(){
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }

    @OnClick(R.id.button_login)
    void login()
    {
        /*final TextInputEditText username = new TextInputEditText(usereditTexterror.getContext());
        final TextInputEditText passworde = new TextInputEditText(password.getContext());*/
        final String usernameTxt = usereditText.getText().toString();
        final String passwordTxt = password.getText().toString();

       // Toast.makeText(this, ""+usernameTxt+" "+passwordTxt, Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(usernameTxt)){

            usereditText.setError("please enter username");
        }
        else if(TextUtils.isEmpty(passwordTxt)){
            usereditText.setError("please enter Password");
        }
        else{
         //   Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();

            progressDialog.show();

            SystemUser user = realm
                    .where(SystemUser.class)
                    .beginGroup()
                    .equalTo("username",usernameTxt)
                    .and()
                    .equalTo("password",passwordTxt)
                    .endGroup()
                    .findFirst();

            if (user!=null)
            {
                if (TextUtils.isEmpty(user.getRoles()))
                {
                    progressDialog.hide();
                    Toast.makeText(this, "You don't have any permission", Toast.LENGTH_SHORT).show();

                }else {
                    //save user information
                    PreferenceManager.setUserId(LoginActivity.this, String.valueOf(user.getUserId()));
                    PreferenceManager.setUserName(LoginActivity.this, String.valueOf(user.getFullName()));
                    PreferenceManager.setRoles(LoginActivity.this, user.getRoles());
                    PreferenceManager.setLogin(LoginActivity.this, true);


                    progressDialog.hide();
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finish();

                }


            }else
            {
                progressDialog.hide();
                usereditText.setError("Username or password is incorrect");
                usereditText.requestFocus();
            }
        }

        /*if (TextUtils.isEmpty(usernameTxt))
        {

           // usereditTexterror.setError("please enter username");
            username.setError("please enter username");
            username.requestFocus();
        }

        if (TextUtils.isEmpty(passwordTxt))
        {
            password.setError("Password Required");
            password.requestFocus();
            return;
        }

       */


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (PreferenceManager.isHasLogin(LoginActivity.this))
        {
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }
    }
}
