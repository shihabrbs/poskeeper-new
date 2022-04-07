package com.portal.terminalbd.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.fragments.CategoryFragment;
import com.portal.terminalbd.fragments.CustomerFragment;
import com.portal.terminalbd.fragments.ExpenseCatFragment;
import com.portal.terminalbd.fragments.ExpenseHistoryFragment;
import com.portal.terminalbd.fragments.HomeFragment;
import com.portal.terminalbd.fragments.PaymentListFragment;
import com.portal.terminalbd.fragments.PurchaseHistoryFragment;
import com.portal.terminalbd.fragments.SalesHistoryFragment;
import com.portal.terminalbd.fragments.SettingsFragment;
import com.portal.terminalbd.fragments.UserFragment;
import com.portal.terminalbd.fragments.VendorFragment;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.Setup;
import com.portal.terminalbd.network.APIClient;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    String title;

    Realm realm;

    ImageView iv;
    ImageView iv2;
    CompositeDisposable compositeDisposable;
    ProgressDialog progressDialog;

    TextView nav_header_name;
    ImageView app_help;
    String TAB_FRAGMENT_TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String language = PreferenceManager.getLanguage(DashboardActivity.this);
        setLocale(language);
        loadLocale();
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        progressDialog = new ProgressDialog(DashboardActivity.this);
        progressDialog.setTitle("Syncing");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        compositeDisposable = new CompositeDisposable();


        title = "" + getResources().getString(R.string.string_dashboard);
        toolbar_title.setText(title);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //request permission
        requestPermission();

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        nav_header_name = view.findViewById(R.id.nav_header_name);
        app_help = view.findViewById(R.id.app_help);
        nav_header_name.setText(PreferenceManager.getUserName(DashboardActivity.this));

        app_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                /*
               Pharmacy Information
                */
                Setup setup = realm.where(Setup.class).findFirst();

                if (setup != null) {

                    showHelpDialog(setup.getAppsManual());
                }
            }
        });



        /*
        load home framgemt
         */
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, homeFragment)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .commitAllowingStateLoss();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }


    public void setTitle(String title) {
        toolbar_title.setText(title);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

             /*
        load home framgemt
         */
            TAB_FRAGMENT_TAG = "homeFragment";
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, homeFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commit();
            DashboardActivity.this.invalidateOptionsMenu();

        } else if (id == R.id.nav_sale) {

             /*
        load sales history framgemt
         */
            SalesHistoryFragment salesHistoryFragment = new SalesHistoryFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, salesHistoryFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commitAllowingStateLoss();

        } else if (id == R.id.nav_purchase) {

              /*
        load purchase history framgemt
         */
            PurchaseHistoryFragment purchaseHistoryFragment = new PurchaseHistoryFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, purchaseHistoryFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commitAllowingStateLoss();

        } else if (id == R.id.nav_expense) {

             /*
        load purchase history framgemt
         */
        /*    ExpenseHistoryFragment expenseHistoryFragment = new ExpenseHistoryFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, expenseHistoryFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commitAllowingStateLoss();*/


            Intent intent = new Intent(DashboardActivity.this, NewExpenseActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_expense_cat) {

             /*
        load purchase history framgemt
         */
            ExpenseCatFragment expenseCatFragment = new ExpenseCatFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, expenseCatFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commitAllowingStateLoss();


        } else if (id == R.id.nav_customer) {
            TAB_FRAGMENT_TAG = "customerFragment";
            /*
            load customer framgemt
             */
            CustomerFragment customerFragment = new CustomerFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, customerFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commit();
            DashboardActivity.this.invalidateOptionsMenu();

        } else if (id == R.id.nav_vendor) {

            TAB_FRAGMENT_TAG = "vendorFragment";

             /*
            load vendor framgemt
             */
            VendorFragment vendorFragment = new VendorFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, vendorFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commit();
            DashboardActivity.this.invalidateOptionsMenu();
        } else if (id == R.id.nav_stock) {

            startActivity(new Intent(DashboardActivity.this, StockActivity.class));

        } else if (id == R.id.nav_category) {
              /*
            load user framgemt
             */
            TAB_FRAGMENT_TAG = "categoryFragment";
            CategoryFragment categoryFragment = new CategoryFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, categoryFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commit();
        } else if (id == R.id.nav_user) {

              /*
            load user framgemt
             */
            TAB_FRAGMENT_TAG = "userFragment";

            UserFragment userFragment = new UserFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, userFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commit();
            DashboardActivity.this.invalidateOptionsMenu();
        } else if (id == R.id.nav_mobile_bank_list) {

              /*
            load home framgemt
             */
            TAB_FRAGMENT_TAG = "paymentListFragment";
            PaymentListFragment paymentListFragment = new PaymentListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, paymentListFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commit();
            DashboardActivity.this.invalidateOptionsMenu();
        } else if (id == R.id.nav_setting) {

             /*
            load settings framgemt
             */
            TAB_FRAGMENT_TAG = "settingsFragment";
            SettingsFragment settingsFragment = new SettingsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, settingsFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("null")
                    .commit();
            DashboardActivity.this.invalidateOptionsMenu();
        } else if (id == R.id.nav_logout) {

            new AlertDialog.Builder(this).setMessage("Are you sure want to logout?")
                    .setTitle("Alert!!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            PreferenceManager.setLogin(DashboardActivity.this, false);
                           /* startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                            finishAffinity();*/

                            startActivity(new Intent(DashboardActivity.this, MultiLayout_SplashScreen.class));
                            finishAffinity();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        /*
        check user roles
         */
        List<String> stringList = Arrays.asList(PreferenceManager.getRoles(DashboardActivity.this).split(","));

        for (String s :
                stringList) {

            if (s.equalsIgnoreCase("ROLE_MANAGER") || s.equalsIgnoreCase("ROLE_SALES")) {

                menu.clear();
             /*   getMenuInflater().inflate(R.menu.sync_menu,menu);
                View view = menu.findItem(R.id.sync_status).getActionView();
                iv = view.findViewById(R.id.sync_progress);

                iv.setColorFilter(R.color.menue_icon);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashboardActivity.this,SalesActivity.class));
                    }
                });*/

                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.sync_menu, menu);


            }
        }


        MenuItem cart = menu.findItem(R.id.sync_status);
        MenuItem addcus = menu.findItem(R.id.add_customer);
        MenuItem synccuc = menu.findItem(R.id.new_sync);
        MenuItem vendor = menu.findItem(R.id.add_vendor);
        cart.setVisible(false);
        if (TAB_FRAGMENT_TAG.equals("customerFragment")) {

            addcus.setVisible(true);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        } else if (TAB_FRAGMENT_TAG.equals("vendorFragment")) {
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(true);
        } else if (TAB_FRAGMENT_TAG.equals("settingsFragment")) {
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        } else if (TAB_FRAGMENT_TAG.equals("paymentListFragment")) {
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        } else if (TAB_FRAGMENT_TAG.equals("userFragment")) {
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        } else if (TAB_FRAGMENT_TAG.equals("categoryFragment")) {
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        } else if (TAB_FRAGMENT_TAG.equals("homeFragment")) {
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sync_status:
                startActivity(new Intent(DashboardActivity.this, SalesActivity.class));
                return true;
            case R.id.add_customer:
                showTermsDialog();
                return true;
            case R.id.new_sync:
                Toast.makeText(this, "new Sync", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.add_vendor:
                showAddVendorDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

/*    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem cart = menu.findItem(R.id.sync_status);
        MenuItem addcus = menu.findItem(R.id.add_customer);
        MenuItem synccuc = menu.findItem(R.id.new_sync);
        MenuItem vendor = menu.findItem(R.id.add_vendor);
        cart.setVisible(false);
        if (TAB_FRAGMENT_TAG.equals("customerFragment")){

            addcus.setVisible(true);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        }
      else if (TAB_FRAGMENT_TAG.equals("vendorFragment")){
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(true);
        }
        else if(TAB_FRAGMENT_TAG.equals("settingsFragment")){
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        }
        else  if(TAB_FRAGMENT_TAG.equals("paymentListFragment")){
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        }
        else  if(TAB_FRAGMENT_TAG.equals("userFragment")){
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        }
        else if(TAB_FRAGMENT_TAG.equals("categoryFragment")){
           cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        }

        else if(TAB_FRAGMENT_TAG.equals("homeFragment")){
            cart.setVisible(true);
            addcus.setVisible(false);
            synccuc.setVisible(false);
            vendor.setVisible(false);
        }

        return true;
    }*/

    private void showTermsDialog() {


        LayoutInflater layoutInflater = LayoutInflater.from(DashboardActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_create_customer, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView close = view.findViewById(R.id.close);
        ImageView resetBtn = view.findViewById(R.id.imageView12);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        ConstraintLayout back = view.findViewById(R.id.btn_back);
        EditText name = view.findViewById(R.id.nameid);
        EditText mobile = view.findViewById(R.id.mobileid);
        EditText email = view.findViewById(R.id.emailid);
        EditText obalance = view.findViewById(R.id.openingid);
        EditText address = view.findViewById(R.id.descriptionid);
        String userid = "" + PreferenceManager.getUserId(DashboardActivity.this);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                createCustomer(name.getText().toString(), mobile.getText().toString(), address.getText().toString(), email.getText().toString(), obalance.getText().toString(), userid);


                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }




    private void createCustomer(String name, String mobile, String address, String email, String openingbalance, String userid) {

        progressDialog.setMessage("Syncing System Customer....");

        compositeDisposable.addAll(APIClient.getInstance().createCustomer(PreferenceManager.getSecretKey(DashboardActivity.this),
                name, mobile, address, email, openingbalance, userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<AnonymousCustomer>>() {
                    @Override
                    public void onNext(final Response<AnonymousCustomer> response) {

                        if (response.body().getStatus().equals("success")) {
                            syncAnonymousCustomer();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }


    public void syncAnonymousCustomer() {
        realm.beginTransaction();
        realm.delete(AnonymousCustomer.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getAnonymousCustomer(PreferenceManager.getSecretKey(getApplicationContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<AnonymousCustomer>>>() {
                    @Override
                    public void onNext(final Response<List<AnonymousCustomer>> response) {


                        if (response.isSuccessful()) {


                            if (response.body() != null && response.body().size() > 0) {
                                realm.beginTransaction();
                                realm.delete(AnonymousCustomer.class);
                                realm.commitTransaction();

                                compositeDisposable.add(APIClient.getInstance().getAnonymousCustomer(PreferenceManager.getSecretKey(getApplicationContext()))
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.io())
                                        .subscribeWith(new DisposableObserver<Response<List<AnonymousCustomer>>>() {
                                            @Override
                                            public void onNext(final Response<List<AnonymousCustomer>> response) {


                                                if (response.isSuccessful()) {


                                                    if (response.body() != null && response.body().size() > 0) {

                                                        /* start realm for save data*/

                                                        realm.beginTransaction();

                                                        /*get each data*/

                                                        for (int i = 0; i < response.body().size(); i++) {


                                                            AnonymousCustomer anonymousCustomer = realm.createObject(AnonymousCustomer.class);
                                                            ;
                                                            anonymousCustomer.setCustomerId(response.body().get(i).getCustomerId());
                                                            anonymousCustomer.setGlobalId(response.body().get(i).getGlobalId());
                                                            anonymousCustomer.setMobile(response.body().get(i).getMobile());
                                                            anonymousCustomer.setName(response.body().get(i).getName());
                                                            anonymousCustomer.setAddress(response.body().get(i).getAddress());
                                                            anonymousCustomer.setOpeningBalance(response.body().get(i).getOpeningBalance());
                                                            anonymousCustomer.setEmail(response.body().get(i).getEmail());


                                                        }

                                                        realm.commitTransaction();
                                                    }


                                                    progressDialog.hide();

                                                    CustomerFragment tabFragment = (CustomerFragment) getSupportFragmentManager().findFragmentById(R.id.my_fragment);

                                                    // Check if the tab fragment is available
                                                    if (tabFragment != null) {

                                                        // Call your method in the TabFragment
                                                        tabFragment.refreshData();
                                                    }

                                                } else {

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


                            progressDialog.hide();

                        } else {

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


    private void showAddVendorDialog() {


        LayoutInflater layoutInflater = LayoutInflater.from(DashboardActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_create_vendor, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView close = view.findViewById(R.id.close);
        ImageView resetBtn = view.findViewById(R.id.imageView12);
        ConstraintLayout save = view.findViewById(R.id.btn_save);
        ConstraintLayout back = view.findViewById(R.id.btn_back);
        EditText name = view.findViewById(R.id.nameid);
        EditText mobile = view.findViewById(R.id.mobileid);
        EditText email = view.findViewById(R.id.emailid);
        EditText obalance = view.findViewById(R.id.openingid);
        EditText address = view.findViewById(R.id.descriptionid);
        String userid = "" + PreferenceManager.getUserId(DashboardActivity.this);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                createVendor(name.getText().toString(), mobile.getText().toString(), address.getText().toString(), email.getText().toString(), obalance.getText().toString(), userid);


                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }

    private void createVendor(String name, String mobile, String address, String email, String openingbalance, String userid) {

        progressDialog.setMessage("Syncing System Vendor....");

       /* compositeDisposable.addAll(APIClient.getInstance().createCustomer(PreferenceManager.getSecretKey(DashboardActivity.this),
                name, mobile, address, email, openingbalance, userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<AnonymousCustomer>>() {
                    @Override
                    public void onNext(final Response<AnonymousCustomer> response) {

                        if (response.body().getStatus().equals("success")) {
                            syncAnonymousCustomer();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.hide();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));*/

    }



    @Override
    protected void onDestroy() {
        realm.close();
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void showHelpDialog(String text) {
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.dialog_help);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView rules_text = dialog.findViewById(R.id.help_text);
        if (!TextUtils.isEmpty(text)) {
            rules_text.setText(Html.fromHtml(text));
        }

        dialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    protected void onStart() {
        super.onStart();


        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_home).setVisible(false);
        nav_Menu.findItem(R.id.nav_sale).setVisible(false);
        nav_Menu.findItem(R.id.nav_purchase).setVisible(false);
        nav_Menu.findItem(R.id.nav_expense).setVisible(false);
        nav_Menu.findItem(R.id.nav_customer).setVisible(false);
        nav_Menu.findItem(R.id.nav_vendor).setVisible(false);
        nav_Menu.findItem(R.id.nav_stock).setVisible(false);
        nav_Menu.findItem(R.id.nav_user).setVisible(false);
        nav_Menu.findItem(R.id.nav_mobile_bank_list).setVisible(false);
        nav_Menu.findItem(R.id.nav_setting).setVisible(false);
        nav_Menu.findItem(R.id.nav_logout).setVisible(false);

        /*
        check user roles
         */
        List<String> stringList = Arrays.asList(PreferenceManager.getRoles(DashboardActivity.this).split(","));

        for (String s :
                stringList) {

            if (s.equalsIgnoreCase("ROLE_MANAGER")) {

                nav_Menu.findItem(R.id.nav_home).setVisible(true);
                nav_Menu.findItem(R.id.nav_sale).setVisible(true);
                nav_Menu.findItem(R.id.nav_purchase).setVisible(true);
                nav_Menu.findItem(R.id.nav_expense).setVisible(true);
                nav_Menu.findItem(R.id.nav_customer).setVisible(true);
                nav_Menu.findItem(R.id.nav_vendor).setVisible(true);
                nav_Menu.findItem(R.id.nav_stock).setVisible(true);
                nav_Menu.findItem(R.id.nav_user).setVisible(true);
                nav_Menu.findItem(R.id.nav_mobile_bank_list).setVisible(true);
                nav_Menu.findItem(R.id.nav_setting).setVisible(true);
                nav_Menu.findItem(R.id.nav_logout).setVisible(true);
            }
            if (s.equalsIgnoreCase("ROLE_SALES")) {
                nav_Menu.findItem(R.id.nav_home).setVisible(true);
                nav_Menu.findItem(R.id.nav_sale).setVisible(true);
                nav_Menu.findItem(R.id.nav_customer).setVisible(true);
                nav_Menu.findItem(R.id.nav_mobile_bank_list).setVisible(true);
                nav_Menu.findItem(R.id.nav_logout).setVisible(true);

            }
            if (s.equalsIgnoreCase("ROLE_PURCHASE")) {
                nav_Menu.findItem(R.id.nav_home).setVisible(true);
                nav_Menu.findItem(R.id.nav_purchase).setVisible(true);
                nav_Menu.findItem(R.id.nav_vendor).setVisible(true);
                nav_Menu.findItem(R.id.nav_logout).setVisible(true);


            }
            if (s.equalsIgnoreCase("ROLE_EXPENSE")) {
                nav_Menu.findItem(R.id.nav_home).setVisible(true);
                nav_Menu.findItem(R.id.nav_expense).setVisible(true);
                nav_Menu.findItem(R.id.nav_logout).setVisible(true);


            }
            if (s.equalsIgnoreCase("ROLE_STOCK")) {
                nav_Menu.findItem(R.id.nav_home).setVisible(true);
                nav_Menu.findItem(R.id.nav_stock).setVisible(true);
                nav_Menu.findItem(R.id.nav_logout).setVisible(true);

            }
        }
    }

//    AccessibilityNodeInfo source = event.getSource();
//if (source != null) {
//        //capture the EditText simply by using FOCUS_INPUT (since the EditText has the focus), you can probably find it with the viewId input_field
//        AccessibilityNodeInfo inputNode = source.findFocus(AccessibilityNodeInfo.FOCUS_INPUT);
//        if (inputNode != null) {//prepare you text then fill it using ACTION_SET_TEXT
//            Bundle arguments = new Bundle();
//            arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,"text to enter");
//            inputNode.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//        }
//        //"Click" the Send button
//        List<AccessibilityNodeInfo> list = source.findAccessibilityNodeInfosByText("Send");
//        for (AccessibilityNodeInfo node : list) {
//            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//        }
//    }

    void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0 && grantResults.length < 1) {
            requestPermission();
        }
    }
}
