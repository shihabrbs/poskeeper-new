package com.portal.terminalbd.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.SliderAdapter;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.ModelSliderLayout;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MultiLayout_SplashScreen extends AppCompatActivity {
    private ViewPager mSliderViewPager;
    private LinearLayout linearLayout;
    Button button, btnskip,buttonback;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    TextView btnstart,btnstartFirst;
    int count = 1;
    int mcurrentpage;
    boolean loginflag;
    String pageno="0";
   // ArrayList<ModelSetup> setupArrayList;

    ArrayList<ModelSliderLayout> slideimage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginflag = PreferenceManager.isHasLogin(this);
        if (loginflag){
            Intent intent = new Intent(MultiLayout_SplashScreen.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_layout__splash_screen);


        btnstartFirst = findViewById(R.id.tv_getstarted_start);
        button = findViewById(R.id.nextbtn);

        slideimage.add(new ModelSliderLayout("Existing Shop","This is your Existing Shop",R.drawable.sliderimage4));
        slideimage.add(new ModelSliderLayout("Login New Shop","Login your New Shop",R.drawable.sliderimage2));
        slideimage.add(new ModelSliderLayout("Create New Shop","Don't have any Shop.Create New Shop",R.drawable.sliderimage3));


        mSliderViewPager = (ViewPager) findViewById(R.id.viewPager);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout6);


        sliderAdapter = new SliderAdapter(this, slideimage);
        mSliderViewPager.setAdapter(sliderAdapter);
        addDotsInticator(0);


        mSliderViewPager.addOnPageChangeListener(listener);


        btnstartFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (mcurrentpage == 0){
                   Intent intent = new Intent(MultiLayout_SplashScreen.this,SetupActivity.class);
                   startActivity(intent);

               }
               else if(mcurrentpage==1){
                   Intent intent = new Intent(MultiLayout_SplashScreen.this,LoginWithOtpActivity.class);
                   startActivity(intent);

               }else {
                   Intent intent = new Intent(MultiLayout_SplashScreen.this,SignupActivity.class);
                   startActivity(intent);

               }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = slideimage.size();
                mSliderViewPager.setCurrentItem(mcurrentpage + 1);
                if (mcurrentpage == number) {
                    count++;

                } else {

                }
            }
        });

    }


    public void addDotsInticator(int position) {
        int number = slideimage.size();
        mDots = new TextView[number];
        linearLayout.removeAllViews();


        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.grey));


            linearLayout.addView(mDots[i]);
            pageno = ""+i;
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.blue));
        }
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onPageSelected(int i) {
            addDotsInticator(i);
            mcurrentpage = i;

            if (mcurrentpage == 0) {
              //  button.setEnabled(true);
               // button.setText("Next");
        /*        linearLayout.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                btnskip.setVisibility(View.GONE);
                btnstartFirst.setVisibility(View.GONE);
                btnstart.setVisibility(View.GONE);
                buttonback.setVisibility(View.GONE);
                btnskip.setText("SKIP");*/
            } else if (i == mDots.length - 1) {
             //   button.setEnabled(true);
             //   button.setText("Get Started");
             /*   linearLayout.setVisibility(View.VISIBLE);
                buttonback.setVisibility(View.VISIBLE);
                btnstart.setVisibility(View.VISIBLE);
                btnstartFirst.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                btnskip.setVisibility(View.GONE);
                btnskip.setText("");*/
            } else {
            //    button.setEnabled(true);
               // button.setText("Next");
            /*    buttonback.setVisibility(View.GONE);
                btnstartFirst.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                btnskip.setVisibility(View.GONE);
                btnstart.setVisibility(View.GONE);
                btnskip.setText("");*/
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}