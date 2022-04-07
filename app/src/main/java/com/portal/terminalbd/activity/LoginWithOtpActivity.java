package com.portal.terminalbd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.network.APIClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Response;

public class LoginWithOtpActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_otp);
        editText = findViewById(R.id.et_phone);
        compositeDisposable = new CompositeDisposable();

    }

    public void goToOTP(View view) {
        String mobileTxt = editText.getText().toString();
        compositeDisposable.add(APIClient.getInstance().otpLogin(mobileTxt)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Response<Setup>>() {
                    @Override
                    public void onSuccess(final Response<Setup> response) {

                        try {


                            if (response.body().getStatus().equals("valid")) {

                                Intent intent = new Intent(LoginWithOtpActivity.this, OTPActivity.class);
                                intent.putExtra("otpcode",""+response.body().getOtp());
                                intent.putExtra("phone",""+editText.getText());
                                intent.putExtra("uniqueKey",""+response.body().getApiSecret());
                                startActivity(intent);

                            } else {
                                Toast.makeText(LoginWithOtpActivity.this, "Mobile Number is not Registered yet !", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(LoginWithOtpActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));


    }


}