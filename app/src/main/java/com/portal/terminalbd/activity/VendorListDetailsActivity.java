package com.portal.terminalbd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.portal.terminalbd.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VendorListDetailsActivity extends AppCompatActivity {
    @BindView(R.id.filterbtn)
    ImageView filterbtn;

    @BindView(R.id.filter_layout)
    LinearLayout filter_layout;

    boolean filterlayoutshow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_list_details);
        ButterKnife.bind(this);

        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterlayoutshow) {
                    filterbtn.setImageResource(R.drawable.ic_close_black);
                    filter_layout.setVisibility(View.VISIBLE);
                    filterlayoutshow = false;
                } else {
                    filterbtn.setImageResource(R.drawable.ic_filter);
                    filter_layout.setVisibility(View.GONE);
                    filterlayoutshow = true;
                }
            }
        });

    }

    public void btn_filter_search(View view) {

    }

    public void btn_back(View view) {
        onBackPressed();
    }

    public void internetcheck(View view) {

    }
}