package com.portal.terminalbd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.portal.terminalbd.R;
import com.portal.terminalbd.firebaseFCM.FcmNotificationsSender;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.ModelCategory;
import com.portal.terminalbd.model.ModelCreateProduct;
import com.portal.terminalbd.model.ModelUnit;

import java.util.ArrayList;
import java.util.Objects;

public class AddNewProductActivity extends AppCompatActivity {
    TextInputEditText editText, editTexttoken;
    String UUID;

    ImageView imageView;
    boolean imageopen = true;

    String fcmToken;

    ArrayList<String> fcmlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        fcmlist = new ArrayList<>();
        editText = findViewById(R.id.nameid);
        editTexttoken = findViewById(R.id.descriptionid);
        imageView = findViewById(R.id.imageView7);

  /*      fcmlist.add("dxKY_OPyRf6G_N9Ez1bYlv:APA91bG8laANiJ04SjbmgjDuuCtUj5v-l8-PrkdniudCq0sPvnUVgaV5RFO_JHiu56JPwGvIP-QTRDPD8Q8MVYBYmE9czCieZW8ASUXHHKJ6HNAobfobZvAYfY7DNOc6iUFNWX-vPj7g");
        fcmlist.add("cpOI9YmMR2-gjkwTxOhFds:APA91bERUsx50KmjMCS6vPDBXLBpBTyMHmJipCQhsBKAhtLxfWCEuj1RIEf3fykHF3Rjim5r7t9Pclh8alB7TxBLxR9e9TZ2fJbRuvdKs1ZM3bHcjFi6mvL37SC06HEldBTrm_kh_G9w");

        // for sending notification to all
        FirebaseMessaging.getInstance().subscribeToTopic("all");

        // fcm settings for perticular user

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            fcmToken = Objects.requireNonNull(task.getResult()).getToken();
                            editTexttoken.setText(fcmToken);
                        }

                    }
                });*/


        UUID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);


        editText.setText(UUID);

    }

    public void openImage(View view) {

        if (imageopen) {
            imageView.setVisibility(View.VISIBLE);
            imageopen = false;
        } else {
            imageView.setVisibility(View.GONE);
            imageopen = true;
        }

    }

    private void showTermsDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(AddNewProductActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_add_category, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddNewProductActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCanceledOnTouchOutside(false);
        ImageView close = view.findViewById(R.id.close);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        ConstraintLayout back = view.findViewById(R.id.btn_back);
        EditText categoryName = view.findViewById(R.id.nameid);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddNewProductActivity.this, "" + categoryName.getText(), Toast.LENGTH_SHORT).show();

            }
        });


        alertDialog.show();
    }

    private void showBrandDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(AddNewProductActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_add_brand, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddNewProductActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCanceledOnTouchOutside(false);
        ImageView close = view.findViewById(R.id.close);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        ConstraintLayout back = view.findViewById(R.id.btn_back);
        EditText brandName = view.findViewById(R.id.nameid);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddNewProductActivity.this, "" + brandName.getText(), Toast.LENGTH_SHORT).show();

            }
        });


        alertDialog.show();
    }

    public void btn_category(View view) {
        showTermsDialog();
    }

    public void btn_send_notification(View view) {

   /*     FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",
                "First Notification", "This is the Notification body.",
                getApplicationContext(),
                AddNewProductActivity.this);

        notificationsSender.SendNotifications();*/
    }

    public void send_specific(View view) {
     /*   int i;
        for(i=0;i<fcmlist.size();i++){
            FcmNotificationsSender notificationsSender = new FcmNotificationsSender(fcmlist.get(i),
                    "Specific Notification", "This is the Specific Notification body.",
                    getApplicationContext(),
                    AddNewProductActivity.this);

            notificationsSender.SendNotifications();
        }
*/

    }

    public void btn_ad_brand(View view) {
        showBrandDialog();
    }
}