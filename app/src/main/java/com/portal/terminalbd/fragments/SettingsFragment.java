package com.portal.terminalbd.fragments;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.portal.terminalbd.activity.DashboardActivity;
import com.portal.terminalbd.R;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.DIMSItem;
import com.portal.terminalbd.model.ExpenseCategory;
import com.portal.terminalbd.model.ExpenseItem;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.PurchaseHistory;
import com.portal.terminalbd.model.PurchaseItemHistory;
import com.portal.terminalbd.model.SalesHistory;
import com.portal.terminalbd.model.SalesItemHistory;
import com.portal.terminalbd.model.StockItem;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.model.TransactionMethod;
import com.portal.terminalbd.model.Vendor;
import com.portal.terminalbd.network.APIClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    ValueAnimator mAnimator;
    ValueAnimator exportAnimator;

    @BindView(R.id.manual_sync_layout)
    LinearLayout manual_sync_layout;

    @BindView(R.id.medicine_layout)
    LinearLayout medicine_layout;

    @BindView(R.id.manual_sync)
    TextView manual_sync;

    @BindView(R.id.export_sync_layout)
    LinearLayout export_sync_layout;

    @BindView(R.id.export_sync)
    TextView export_sync;

    @BindView(R.id.sync_anonymous_customer)
    ImageView sync_anonymous_customer;

    @BindView(R.id.sync_bank_account)
    ImageView sync_bank_account;

    @BindView(R.id.sync_dims)
    ImageView sync_dims;

    @BindView(R.id.sync_expense_category)
    ImageView sync_expense_category;

    @BindView(R.id.sync_mobile_account)
    ImageView sync_mobile_account;

    @BindView(R.id.sync_system_users)
    ImageView sync_system_users;

    @BindView(R.id.sync_product_category)
    ImageView sync_product_category;

    @BindView(R.id.sync_transaction_method)
    ImageView sync_transaction_method;

    @BindView(R.id.sync_stock)
    ImageView sync_stock;

    @BindView(R.id.sync_vendor)
    ImageView sync_vendor;

    @BindView(R.id.sync_sales)
    ImageView sync_sales;

    @BindView(R.id.sync_purchase)
    ImageView sync_purchase;

    @BindView(R.id.sync_expense)
    ImageView sync_expense;

    @BindView(R.id.sales_mode_spinner)
    Spinner sales_mode_spinner;

    @BindView(R.id.printer_type_spinner)
    Spinner printer_type_spinner;

    @BindView(R.id.camera_mode_spinner)
    Spinner camera_mode_spinner;


    @BindView(R.id.online_offline_mode)
    Switch online_offline_mode;

    @BindView(R.id.barcode_mode)
    Switch barcode_mode;

    @BindView(R.id.discount_print_mode)
    Switch discount_print_mode;

    @BindView(R.id.auto_payment_mode)
    Switch auto_payment_mode;

    @BindView(R.id.previous_due_mode)
    Switch previous_due_mode;

    @BindView(R.id.show_token_mode)
    Switch show_token_mode;

    @BindView(R.id.salesMode_text)
    TextView salesModeText;

    @BindView(R.id.salesMode_layout)
    LinearLayout salesModeLayout;

    boolean isShowing = false;
    boolean isExportShowing = false;

    Animation rotation;

    Realm realm;

    CompositeDisposable compositeDisposable;

    int totalSize=0;
    int postSize = 0;

    int saleItemTotalSize = 0;
    int saleItemPostSize = 0;

    RealmResults<SalesHistory> salesHistories;
    RealmResults<SalesItemHistory> salesItemHistories;

    int purchaseTotalSize=0;
    int purchasePostSize = 0;

    int purchaseItemTotalSize = 0;
    int purchaseItemPostSize = 0;

    RealmResults<PurchaseHistory> purchaseHistories;
    RealmResults<PurchaseItemHistory> purchaseItemHistories;

    int expenseTotalSize = 0;
    int expensePostSize = 0;

    RealmResults<ExpenseItem> expenseItems;

    private String[] salesMode = {"Search","List","Grid"};
    ArrayAdapter<String> salesModeAdapter;

    private String[] printerType = {"80mm Bluetooth","50mm Bluetooth","USB Printer"};
    ArrayAdapter<String> printerTypeAdapter;

    private String[] cameraType = {"Back","Front"};
    ArrayAdapter<String> cameraTypeAdapter;

    ProgressDialog progressDialog;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        compositeDisposable = new CompositeDisposable();

        ((DashboardActivity)getActivity()).setTitle("Settings");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);

        rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_refresh);
        rotation.setRepeatCount(Animation.INFINITE);



         /*
        check medicine layout
         */
        if (PreferenceManager.getModule(getContext()).equalsIgnoreCase("miss"))
        {
            medicine_layout.setVisibility(View.VISIBLE);
            salesModeText.setVisibility(View.GONE);
            salesModeLayout.setVisibility(View.GONE);
        }else
        {
            medicine_layout.setVisibility(View.GONE);

            salesModeText.setVisibility(View.VISIBLE);
            salesModeLayout.setVisibility(View.VISIBLE);
        }

        /*
        sales mode array adapter
         */
        salesModeAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,salesMode);
        sales_mode_spinner.setAdapter(salesModeAdapter);

         /*
        printr type array adapter
         */
        printerTypeAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,printerType);
        printer_type_spinner.setAdapter(printerTypeAdapter);

         /*
        camera type array adapter
         */
        cameraTypeAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,cameraType);
        camera_mode_spinner.setAdapter(cameraTypeAdapter);


        /*
        check sales mode
         */
        String salesMode = PreferenceManager.getSalesMode(getContext());

        if (salesMode.equalsIgnoreCase("Search"))
        {
           sales_mode_spinner.setSelection(salesModeAdapter.getPosition("Search"));

        }else if (salesMode.equalsIgnoreCase("List"))
        {
            sales_mode_spinner.setSelection(salesModeAdapter.getPosition("List"));


        }else if (salesMode.equalsIgnoreCase("Grid"))
        {
            sales_mode_spinner.setSelection(salesModeAdapter.getPosition("Grid"));

        }


         /*
        check printer type
         */
        String printerTypeCheck = PreferenceManager.getPrinterType(getContext());

        if (printerTypeCheck.equalsIgnoreCase("80mm"))
        {
            printer_type_spinner.setSelection(printerTypeAdapter.getPosition("80mm Bluetooth"));

        }else if (printerTypeCheck.equalsIgnoreCase("50mm"))
        {
            printer_type_spinner.setSelection(printerTypeAdapter.getPosition("50mm Bluetooth"));

        }else if (printerTypeCheck.equalsIgnoreCase("usb"))
        {
            printer_type_spinner.setSelection(printerTypeAdapter.getPosition("USB Printer"));
        }

         /*
        check printer type
         */
        int cameraTypeCheck = PreferenceManager.getCameraType(getContext());

        if (cameraTypeCheck == 0)
        {
            camera_mode_spinner.setSelection(cameraTypeAdapter.getPosition("Back"));

        }else if (cameraTypeCheck == 1)
        {
            camera_mode_spinner.setSelection(cameraTypeAdapter.getPosition("Front"));

        }


        /* Online Offline Settings */

        if (PreferenceManager.getOnlineOfflineMode(getContext()).equalsIgnoreCase("Offline"))
        {
            online_offline_mode.setChecked(false);
            online_offline_mode.setText("Offline");

        }else if (PreferenceManager.getOnlineOfflineMode(getContext()).equalsIgnoreCase("Online"))
        {
            online_offline_mode.setChecked(true);
            online_offline_mode.setText("Online");
        }


        online_offline_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                {
                    PreferenceManager.setOnlineOfflineModes(getContext(),"Online");
                    online_offline_mode.setText("Online");

                }else if (!compoundButton.isChecked())
                {
                    PreferenceManager.setOnlineOfflineModes(getContext(),"Offline");
                    online_offline_mode.setText("Offline");

                }
            }
        });

        /* Barcode Settings */

        if (PreferenceManager.getBarcodeMode(getContext()).equalsIgnoreCase("Off"))
        {
            barcode_mode.setChecked(false);
            barcode_mode.setText("Off");
        }else if (PreferenceManager.getBarcodeMode(getContext()).equalsIgnoreCase("On"))
        {
            barcode_mode.setChecked(true);
            barcode_mode.setText("On");
        }

        barcode_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                {
                    PreferenceManager.setBarcodeMode(getContext(),"On");
                    barcode_mode.setText("On");

                }else if (!compoundButton.isChecked())
                {
                    PreferenceManager.setBarcodeMode(getContext(),"Off");
                    barcode_mode.setText("Off");

                }
            }
        });


        /* Discount Settings */

        if (PreferenceManager.getDiscountPrint(getContext()).equalsIgnoreCase("Off"))
        {
            discount_print_mode.setChecked(false);
            discount_print_mode.setText("Off");
        }else if (PreferenceManager.getDiscountPrint(getContext()).equalsIgnoreCase("On"))
        {
            discount_print_mode.setChecked(true);
            discount_print_mode.setText("On");
        }

        discount_print_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                {
                    PreferenceManager.setDiscountPrint(getContext(),"On");
                    discount_print_mode.setText("On");

                }else if (!compoundButton.isChecked())
                {
                    PreferenceManager.setDiscountPrint(getContext(),"Off");
                    discount_print_mode.setText("Off");

                }
            }
        });


        /* Auto Payment Settings */

        if (PreferenceManager.getAutoPayment(getContext()).equalsIgnoreCase("Off"))
        {
            auto_payment_mode.setChecked(false);
            auto_payment_mode.setText("Off");
        }else if (PreferenceManager.getAutoPayment(getContext()).equalsIgnoreCase("On"))
        {
            auto_payment_mode.setChecked(true);
            auto_payment_mode.setText("On");
        }

        auto_payment_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                {
                    PreferenceManager.setAutoPayment(getContext(),"On");
                    auto_payment_mode.setText("On");

                }else if (!compoundButton.isChecked())
                {
                    PreferenceManager.setAutoPayment(getContext(),"Off");
                    auto_payment_mode.setText("Off");

                }
            }
        });


        /* Previous Due Settings */

        if (PreferenceManager.getPreviousDue(getContext()).equalsIgnoreCase("Off"))
        {
            previous_due_mode.setChecked(false);
            previous_due_mode.setText("Off");
        }else if (PreferenceManager.getPreviousDue(getContext()).equalsIgnoreCase("On"))
        {
            previous_due_mode.setChecked(true);
            previous_due_mode.setText("On");
        }

        previous_due_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                {
                    PreferenceManager.setPreviousDue(getContext(),"On");
                    previous_due_mode.setText("On");

                }else if (!compoundButton.isChecked())
                {
                    PreferenceManager.setPreviousDue(getContext(),"Off");
                    previous_due_mode.setText("Off");

                }
            }
        });


        /* Show Token Settings */

        if (PreferenceManager.getShowToken(getContext()).equalsIgnoreCase("Off"))
        {
            show_token_mode.setChecked(false);
            show_token_mode.setText("Off");
        }else if (PreferenceManager.getShowToken(getContext()).equalsIgnoreCase("On"))
        {
            show_token_mode.setChecked(true);
            show_token_mode.setText("On");
        }

        show_token_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                {
                    PreferenceManager.setShowToken(getContext(),"On");
                    show_token_mode.setText("On");

                }else if (!compoundButton.isChecked())
                {
                    PreferenceManager.setShowToken(getContext(),"Off");
                    show_token_mode.setText("Off");

                }
            }
        });



        /*
        sales mode change listerner
        * */
        sales_mode_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedItem = adapterView.getItemAtPosition(i).toString();

                if (selectedItem.equalsIgnoreCase("Search"))
                {
                    PreferenceManager.setSalesMode(getContext(),"Search");

                }else if (selectedItem.equalsIgnoreCase("List"))
                {
                    PreferenceManager.setSalesMode(getContext(),"List");

                }else if (selectedItem.equalsIgnoreCase("Grid"))
                {
                    PreferenceManager.setSalesMode(getContext(),"Grid");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*
        * printer type change listerner
        * */
        printer_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedItem = adapterView.getItemAtPosition(i).toString();

                if (selectedItem.equalsIgnoreCase("80mm Bluetooth"))
                {
                    PreferenceManager.setPrinterType(getContext(),"80mm");

                }else if (selectedItem.equalsIgnoreCase("50mm Bluetooth"))
                {
                    PreferenceManager.setPrinterType(getContext(),"50mm");

                }else if (selectedItem.equalsIgnoreCase("USB Printer"))
                {
                    PreferenceManager.setPrinterType(getContext(),"usb");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /*
         * camrea type change listerner
         * */
        camera_mode_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedItem = adapterView.getItemAtPosition(i).toString();

                if (selectedItem.equalsIgnoreCase("Back"))
                {
                    PreferenceManager.setBarcodeCamera(getContext(),0);

                }else if (selectedItem.equalsIgnoreCase("Front"))
                {
                    PreferenceManager.setBarcodeCamera(getContext(),1);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //         Add onPreDrawListener
        manual_sync_layout.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        manual_sync_layout.getViewTreeObserver()
                                .removeOnPreDrawListener(this);
                        manual_sync_layout.setVisibility(View.GONE);

                        final int widthSpec =     View.MeasureSpec.makeMeasureSpec(
                                0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec
                                .makeMeasureSpec(0,
                                        View.MeasureSpec.UNSPECIFIED);
                        manual_sync_layout.measure(widthSpec, heightSpec);

                        mAnimator = slideAnimator(0,
                                manual_sync_layout.getMeasuredHeight());
                        return true;
                    }
                });

        /*
        export sync layout
         */

        //         Add onPreDrawListener
        export_sync_layout.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        export_sync_layout.getViewTreeObserver()
                                .removeOnPreDrawListener(this);
                        export_sync_layout.setVisibility(View.GONE);

                        final int widthSpec =     View.MeasureSpec.makeMeasureSpec(
                                0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec
                                .makeMeasureSpec(0,
                                        View.MeasureSpec.UNSPECIFIED);
                        export_sync_layout.measure(widthSpec, heightSpec);

                        exportAnimator = exportSlideAnimator(0,
                                export_sync_layout.getMeasuredHeight());
                        return true;
                    }
                });

        manual_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isShowing)
                {

                    isShowing = false;

                    int finalHeight = manual_sync_layout.getHeight();

                    ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

                    mAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationEnd(Animator animator) {
                            // Height=0, but it set visibility to GONE
                            manual_sync_layout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationStart(Animator animator) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                    mAnimator.start();


                }else
                {
                    isShowing = true;
                    manual_sync_layout.setVisibility(View.VISIBLE);
                    mAnimator.start();
                }
            }
        });


        /*
        export sync layout
         */
        export_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isExportShowing)
                {

                    isExportShowing = false;

                    int finalHeight = export_sync_layout.getHeight();

                    ValueAnimator exportAnimator = exportSlideAnimator(finalHeight, 0);

                    exportAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationEnd(Animator animator) {
                            // Height=0, but it set visibility to GONE
                            export_sync_layout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationStart(Animator animator) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                    exportAnimator.start();


                }else
                {
                    isExportShowing = true;
                    export_sync_layout.setVisibility(View.VISIBLE);
                    exportAnimator.start();
                }
            }
        });

        return view;
    }

    private ValueAnimator slideAnimator(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new     ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = manual_sync_layout
                        .getLayoutParams();
                layoutParams.height = value;
                manual_sync_layout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    private ValueAnimator exportSlideAnimator(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new     ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = export_sync_layout
                        .getLayoutParams();
                layoutParams.height = value;
                export_sync_layout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    @OnClick(R.id.sync_anonymous_customer)
    void onAnonymousCustomer()
    {
        progressDialog.show();
        sync_anonymous_customer.startAnimation(rotation);
        syncAnonymousCustomer();
    }

    @OnClick(R.id.sync_vendor)
    void onsync_vendor()
    {
        progressDialog.show();
        sync_vendor.startAnimation(rotation);
        syncVendor();
    }

    @OnClick(R.id.sync_stock)
    void onsync_stock()
    {
        progressDialog.show();
        sync_stock.startAnimation(rotation);
        syncStockItem();
    }

    @OnClick(R.id.sync_transaction_method)
    void onsync_transaction_method()
    {
        progressDialog.show();
        sync_transaction_method.startAnimation(rotation);
        syncTransactionMethod();
    }

    @OnClick(R.id.sync_bank_account)
    void onsync_bank_account()
    {
        progressDialog.show();
        sync_bank_account.startAnimation(rotation);
        syncBankAccount();
    }

    @OnClick(R.id.sync_system_users)
    void onsync_system_users()
    {
        progressDialog.show();
        sync_system_users.startAnimation(rotation);
        syncSystemUser();
    }

    @OnClick(R.id.sync_product_category)
    void sync_product_category()
    {
        progressDialog.show();
        sync_product_category.startAnimation(rotation);
        syncProductCategory();
    }

    @OnClick(R.id.sync_mobile_account)
    void onsync_mobile_account()
    {
        progressDialog.show();

        sync_mobile_account.startAnimation(rotation);

        syncMobileAccount();
    }

    @OnClick(R.id.sync_expense_category)
    void onsync_expense_category()
    {
        progressDialog.show();

        sync_expense_category.startAnimation(rotation);

        syncExpenseCategory();
    }

    @OnClick(R.id.sync_dims)
    void onsync_dims()
    {
        progressDialog.show();

        sync_dims.startAnimation(rotation);

        syncDIMS();
    }

    @OnClick(R.id.sync_sales)
    void onsync_sales()
    {

        new AlertDialog.Builder(getContext()).setMessage("Are you sure want to sync?")
                .setTitle("Alert!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        progressDialog.show();

                        sync_sales.startAnimation(rotation);

                        syncSales();

                        dialogInterface.dismiss();

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

    @OnClick(R.id.sync_purchase)
    void onsync_purchase()
    {
        new AlertDialog.Builder(getContext()).setMessage("Are you sure want to sync?")
                .setTitle("Alert!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        progressDialog.show();

                        sync_purchase.startAnimation(rotation);

                        syncPurchase();

                        dialogInterface.dismiss();

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

    @OnClick(R.id.sync_expense)
    void onsync_expense()
    {
        new AlertDialog.Builder(getContext()).setMessage("Are you sure want to sync?")
                .setTitle("Alert!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        progressDialog.show();

                        sync_expense.startAnimation(rotation);

                        syncExpense();

                        dialogInterface.dismiss();
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


    public void syncSystemUser()
    {
        realm.beginTransaction();
        realm.delete(SystemUser.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getSystemUser(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<SystemUser>>>() {
                    @Override
                    public void onNext(final Response<List<SystemUser>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_system_users.clearAnimation();
                        }

                        if (response.isSuccessful())
                        {


                            if (response.body()!=null && response.body().size()>0)
                            {
                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    SystemUser systemUser = realm.createObject(SystemUser.class);
                                    systemUser.setUserId(response.body().get(i).getUserId());
                                    systemUser.setEmail(response.body().get(i).getEmail());
                                    systemUser.setFullName(response.body().get(i).getFullName());
                                    systemUser.setPassword(response.body().get(i).getPassword());
                                    systemUser.setRoles(response.body().get(i).getRoles());
                                    systemUser.setUsername(response.body().get(i).getUsername());


                                }

                                realm.commitTransaction();


                            }

                            sync_system_users.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_system_users.clearAnimation();

                        }else
                        {
                            sync_system_users.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_system_users.clearAnimation();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        progressDialog.dismiss();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void syncProductCategory() {

        realm.beginTransaction();
        realm.delete(Category.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getCategory(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_product_category.clearAnimation();
                        }

                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();

                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    Category category = realm.createObject(Category.class);;
                                    category.setCategoryId(response.body().get(i).getCategoryId());
                                    category.setName(response.body().get(i).getName());
                                    category.setSlug(response.body().get(i).getSlug());


                                }

                                realm.commitTransaction();

                            }

                            sync_product_category.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_product_category.clearAnimation();

                        }else
                        {

                            sync_product_category.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_product_category.clearAnimation();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncAnonymousCustomer()
    {
        realm.beginTransaction();
        realm.delete(AnonymousCustomer.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getAnonymousCustomer(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<AnonymousCustomer>>>() {
                    @Override
                    public void onNext(final Response<List<AnonymousCustomer>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_anonymous_customer.clearAnimation();
                        }
                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    AnonymousCustomer anonymousCustomer = realm.createObject(AnonymousCustomer.class);;
                                    anonymousCustomer.setCustomerId(response.body().get(i).getCustomerId());
                                    anonymousCustomer.setGlobalId(response.body().get(i).getGlobalId());
                                    anonymousCustomer.setMobile(response.body().get(i).getMobile());
                                    anonymousCustomer.setName(response.body().get(i).getName());


                                }

                                realm.commitTransaction();
                            }

                            sync_anonymous_customer.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_anonymous_customer.clearAnimation();


                        }else
                        {
                            sync_anonymous_customer.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_anonymous_customer.clearAnimation();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();

                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncStockItem()
    {
        realm.beginTransaction();
        realm.delete(StockItem.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getStockItem(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<StockItem>>>() {
                    @Override
                    public void onNext(final Response<List<StockItem>> listResponse) {

                        progressDialog.dismiss();
                        if (listResponse.body() == null || listResponse.body().size() == 0){
                            sync_stock.clearAnimation();
                        }
                        if (listResponse.isSuccessful())
                        {

                            if (listResponse.body()!=null && listResponse.body().size()>0)
                            {

                                realm.beginTransaction();

                                for (int i = 0 ; i<listResponse.body().size(); i++) {

                                    StockItem stockItem = realm.createObject(StockItem.class);
                                    stockItem.setCategoryId(listResponse.body().get(i).getCategoryId());
                                    stockItem.setCategoryName(listResponse.body().get(i).getCategoryName());
                                    stockItem.setGlobalId(listResponse.body().get(i).getGlobalId());
                                    stockItem.setItemId(listResponse.body().get(i).getItemId());
                                    stockItem.setName(listResponse.body().get(i).getName());
                                    stockItem.setPrintName(listResponse.body().get(i).getPrintName());
                                    stockItem.setPurchasePrice(listResponse.body().get(i).getPurchasePrice());
                                    stockItem.setSalesPrice(listResponse.body().get(i).getSalesPrice());
                                    stockItem.setQuantity(listResponse.body().get(i).getQuantity());
                                    stockItem.setUnit(listResponse.body().get(i).getUnit());
                                    stockItem.setUnitId(listResponse.body().get(i).getUnitId());
                                    stockItem.setPrintHidden(listResponse.body().get(i).getPrintHidden());

                                    if (TextUtils.isEmpty(listResponse.body().get(i).getImagePath())) {

                                        stockItem.setImage(null);
                                    }else
                                    {

                                        DownloadImage task = new DownloadImage();
                                        byte[] result = null;
                                        try {
                                            result = task.execute("http://"+listResponse.body().get(i).getImagePath()).get();
                                            stockItem.setImage(result);
                                        } catch (InterruptedException | ExecutionException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }

                                realm.commitTransaction();

                            }

                            sync_stock.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_stock.clearAnimation();

                        }else
                        {
                            sync_stock.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_stock.clearAnimation();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncTransactionMethod()
    {
        realm.beginTransaction();
        realm.delete(TransactionMethod.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getTransactionMethod(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<TransactionMethod>>>() {
                    @Override
                    public void onNext(final Response<List<TransactionMethod>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_transaction_method.clearAnimation();
                        }
                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {

                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {



                                    TransactionMethod transactionMethod = realm.createObject(TransactionMethod.class);;
                                    transactionMethod.setItemId(response.body().get(i).getItemId());
                                    transactionMethod.setName(response.body().get(i).getName());

                                }

                                realm.commitTransaction();

                            }

                            sync_transaction_method.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_transaction_method.clearAnimation();

                        }else
                        {
                            sync_transaction_method.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_transaction_method.clearAnimation();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncBankAccount()
    {
        realm.beginTransaction();
        realm.delete(BankAccount.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getOnlineBankAccount(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<BankAccount>>>() {
                    @Override
                    public void onNext(final Response<List<BankAccount>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_bank_account.clearAnimation();
                        }
                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                 /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {

                                    BankAccount bankAccount = realm.createObject(BankAccount.class);;
                                    bankAccount.setGlobalId(response.body().get(i).getGlobalId());
                                    bankAccount.setName(response.body().get(i).getName());
                                    bankAccount.setItemId(response.body().get(i).getItemId());
                                    bankAccount.setService_charge(response.body().get(i).getService_charge());

                                }

                                realm.commitTransaction();

                                sync_bank_account.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                                sync_bank_account.clearAnimation();

                            }
                        }else
                        {
                            sync_bank_account.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_bank_account.clearAnimation();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();

                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncMobileAccount()
    {
        realm.beginTransaction();
        realm.delete(MobileAccount.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getMobileAccount(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<MobileAccount>>>() {
                    @Override
                    public void onNext(final Response<List<MobileAccount>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_mobile_account.clearAnimation();
                        }
                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {

                                /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {


                                    MobileAccount mobileAccount = realm.createObject(MobileAccount.class);;
                                    mobileAccount.setGlobalId(response.body().get(i).getGlobalId());
                                    mobileAccount.setName(response.body().get(i).getName());
                                    mobileAccount.setItemId(response.body().get(i).getItemId());
                                    mobileAccount.setService_charge(response.body().get(i).getService_charge());


                                }

                                realm.commitTransaction();



                            }

                            sync_mobile_account.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_mobile_account.clearAnimation();
                        }else
                        {
                            sync_bank_account.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_bank_account.clearAnimation();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();

                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncVendor()
    {
        realm.beginTransaction();
        realm.delete(Vendor.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getVendor(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Vendor>>>() {
                    @Override
                    public void onNext(final Response<List<Vendor>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_vendor.clearAnimation();
                        }
                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                 /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();

                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {



                                    Vendor vendor = realm.createObject(Vendor.class);;
                                    vendor.setName(response.body().get(i).getName());
                                    vendor.setVendorId(response.body().get(i).getVendorId());

                                }

                                realm.commitTransaction();


                            }

                            sync_vendor.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_vendor.clearAnimation();


                        }else
                        {
                            sync_vendor.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_vendor.clearAnimation();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();

                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncExpenseCategory()
    {
        realm.beginTransaction();
        realm.delete(ExpenseCategory.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getExpenseCategory(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ExpenseCategory>>>() {
                    @Override
                    public void onNext(final Response<List<ExpenseCategory>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_expense_category.clearAnimation();
                        }
                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                 /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {

                                    ExpenseCategory expenseCategory = realm.createObject(ExpenseCategory.class);;
                                    expenseCategory.setName(response.body().get(i).getName());
                                    expenseCategory.setCategoryId(response.body().get(i).getCategoryId());
                                    expenseCategory.setGlobalId(response.body().get(i).getGlobalId());
                                    expenseCategory.setSlug(response.body().get(i).getSlug());

                                }

                                realm.commitTransaction();

                            }

                            sync_expense_category.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_expense_category.clearAnimation();

                        }else
                        {
                            sync_vendor.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_vendor.clearAnimation();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();

                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void syncDIMS()
    {
        realm.beginTransaction();
        realm.delete(DIMSItem.class);
        realm.commitTransaction();

        compositeDisposable.add(APIClient.getInstance().getDIMS(PreferenceManager.getSecretKey(getContext()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<DIMSItem>>>() {
                    @Override
                    public void onNext(final Response<List<DIMSItem>> response) {

                        progressDialog.dismiss();
                        if (response.body() == null || response.body().size() == 0){
                            sync_dims.clearAnimation();
                        }
                        if (response.isSuccessful())
                        {

                            if (response.body()!=null && response.body().size()>0)
                            {
                                 /*
                                    start realm for save data
                                     */
                                realm.beginTransaction();
                                /*
                                get each data
                                 */
                                for (int i = 0 ; i<response.body().size(); i++) {

                                    DIMSItem dims = realm.createObject(DIMSItem.class);;
                                    dims.setBrand(response.body().get(i).getBrand());
                                    dims.setGenericName(response.body().get(i).getGenericName());
                                    dims.setGlobalId(response.body().get(i).getGlobalId());
                                    dims.setMedicineForm(response.body().get(i).getMedicineForm());
                                    dims.setMedicineId(response.body().get(i).getMedicineId());
                                    dims.setMedicineName(response.body().get(i).getMedicineName());
                                    dims.setSalesPrice(response.body().get(i).getSalesPrice());
                                    dims.setStrength(response.body().get(i).getStrength());
                                    dims.setUseFor(response.body().get(i).getUseFor());

                                }

                                realm.commitTransaction();

                            }

                            sync_dims.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                            sync_dims.clearAnimation();

                        }else
                        {
                            sync_dims.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                            sync_dims.clearAnimation();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();

                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void onDestroy() {
        realm.close();
        compositeDisposable.clear();
        super.onDestroy();
    }

    public void syncSales()
    {
        realm.beginTransaction();
        salesHistories = realm.where(SalesHistory.class).findAll();
        salesItemHistories = realm.where(SalesItemHistory.class).findAll();
        realm.commitTransaction();

        List<SalesHistory> salesHistoryList = new ArrayList<>();
        salesHistoryList.addAll(realm.copyFromRealm(salesHistories));

        totalSize = salesHistoryList.size();

        List<SalesItemHistory> salesItemHistoryList = new ArrayList<>();
        salesItemHistoryList.addAll(realm.copyFromRealm(salesItemHistories));

        saleItemTotalSize = salesItemHistoryList.size();


        if (totalSize>0) {

//            for (SalesHistory salesHistory : salesHistoryList) {
//                SalesRequestBody salesRequestBody = new SalesRequestBody();
//                salesRequestBody.setInvoiceId(salesHistory.getInvoiceId());
//                salesRequestBody.setBankAccount(salesHistory.getBankAccount());
//                salesRequestBody.setCreated(salesHistory.getCreated());
//                salesRequestBody.setCreatedBy(salesHistory.getCreatedBy());
//                salesRequestBody.setCustomerId(salesHistory.getCustomerId());
//                salesRequestBody.setCustomerMobile(salesHistory.getCustomerMobile());
//                salesRequestBody.setCustomerName(salesHistory.getCustomerName());
//                salesRequestBody.setDiscount(salesHistory.getDiscount());
//                salesRequestBody.setDiscountCalculation(salesHistory.getDiscountCalculation());
//                salesRequestBody.setDiscountType(salesHistory.getDiscountType());
//                salesRequestBody.setDue(salesHistory.getDue());
//                salesRequestBody.setMobileBankAccount(salesHistory.getMobileBankAccount());
//                salesRequestBody.setPaymentCard(salesHistory.getPaymentCard());
//                salesRequestBody.setPaymentCardNo(salesHistory.getPaymentCardNo());
//                salesRequestBody.setPaymentMobile(salesHistory.getPaymentMobile());
//                salesRequestBody.setReceive(salesHistory.getReceive());
//                salesRequestBody.setSalesBy(salesHistory.getSalesBy());
//                salesRequestBody.setSubTotal(salesHistory.getSubTotal());
//                salesRequestBody.setTotal(salesHistory.getTotal());
//                salesRequestBody.setTransactionId(salesHistory.getTransactionId());
//                salesRequestBody.setTransactionMethod(salesHistory.getTransactionMethod());
//                salesRequestBody.setVat(salesHistory.getVat());
//
//                Log.d("Dashboard", "DATA:" + salesRequestBody);
//                Log.d("Dashboard", "KEY:" + PreferenceManager.getSecretKey(getContext()));
//                Log.d("Dashboard", "ID:" + PreferenceManager.getDeviceId(getContext()));
                compositeDisposable.addAll(
                        APIClient.getInstance().syncSales(
                                PreferenceManager.getDeviceId(getContext())
                                , PreferenceManager.getSecretKey(getContext())
                                , new Gson().toJson(salesHistoryList)
                        ,totalSize
                        ,new Gson().toJson(salesItemHistoryList)
                        ,saleItemTotalSize)
                                .delay(2, TimeUnit.SECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
                                    @Override
                                    public void onNext(final Response<ResponseBody> response) {

                                        progressDialog.dismiss();

                                        if (response.isSuccessful()) {
//                                            postSize++;
//
//                                            if (totalSize == postSize) {

                                                realm.beginTransaction();
                                                realm.delete(SalesHistory.class);
                                                realm.delete(SalesItemHistory.class);
                                                realm.commitTransaction();

                                            sync_sales.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                                            sync_sales.clearAnimation();
//                                            }

                                        } else {
                                            sync_sales.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                                            sync_sales.clearAnimation();
                                            Toast.makeText(getContext(), "There is a problem "
                                                            + response.code()
                                                            + response.message()
                                                    , Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        progressDialog.dismiss();

                                        sync_sales.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                                        sync_sales.clearAnimation();
                                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                }));
//            }

        }else
        {
            progressDialog.dismiss();

            sync_sales.clearAnimation();
            Toast.makeText(getContext(), "Nothing to sync.Please add some sales first", Toast.LENGTH_LONG).show();
        }

    }

//    public void syncSalesItem()
//    {
//        realm.beginTransaction();
//        realm.commitTransaction();
//
//
//        for (SalesItemHistory salesItemHistory : salesItemHistoryList)
//
//        {
//            SalesItemRequestBody salesItemRequestBody = new SalesItemRequestBody();
//            salesItemRequestBody.setQuantity(salesItemHistory.getQuantity());
//            salesItemRequestBody.setSalesId(salesItemHistory.getSalesId());
//            salesItemRequestBody.setStockId(salesItemHistory.getStockId());
//            salesItemRequestBody.setSubTotal(salesItemHistory.getSubTotal());
//            salesItemRequestBody.setUnitPrice(salesItemHistory.getUnitPrice());
//
//            compositeDisposable.add(
//                    APIClient.getInstance().syncSalesItems(
//                            PreferenceManager.getDeviceId(getContext())
//                            ,PreferenceManager.getSecretKey(getContext())
//                            ,salesItemRequestBody)
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribeOn(Schedulers.io())
//                            .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
//                                @Override
//                                public void onNext(final Response<ResponseBody> response) {
//
//                                    if (response.isSuccessful())
//                                    {
//                                        saleItemPostSize++;
//
//                                        if (saleItemTotalSize==saleItemPostSize) {
//                                            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
//                                            realm.beginTransaction();
//                                            realm.delete(SalesItemHistory.class);
//                                            realm.commitTransaction();
//                                            sync_sales.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
//                                            sync_sales.clearAnimation();
//                                        }
//
//                                    }else
//                                    {
//                                        sync_sales.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
//                                        sync_sales.clearAnimation();
//                                        Toast.makeText(getContext(), "There is a problem "
//                                                        +response.code()
//                                                        +response.message()
//                                                , Toast.LENGTH_LONG).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    sync_sales.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
//                                    sync_sales.clearAnimation();
//                                    Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onComplete() {
//
//                                }
//                            }));
//        }
//    }

    public void syncPurchase()
    {
        realm.beginTransaction();
        purchaseHistories = realm.where(PurchaseHistory.class).findAll();
        purchaseItemHistories = realm.where(PurchaseItemHistory.class).findAll();

        realm.commitTransaction();

        List<PurchaseHistory> puchaseHistoryList = new ArrayList<>();
        puchaseHistoryList.addAll(realm.copyFromRealm(purchaseHistories));

        List<PurchaseItemHistory> purchaseItemHistoryList = new ArrayList<>();
        purchaseItemHistoryList.addAll(realm.copyFromRealm(purchaseItemHistories));

        purchaseItemTotalSize = purchaseItemHistoryList.size();

        purchaseTotalSize = puchaseHistoryList.size();

        if (purchaseTotalSize>0) {

//            for (PurchaseHistory purchaseHistory : puchaseHistoryList) {

                compositeDisposable.addAll(
                        APIClient.getInstance().syncPurchase(PreferenceManager.getDeviceId(getContext()), PreferenceManager.getSecretKey(getContext()), new Gson().toJson(puchaseHistoryList),purchaseTotalSize,new Gson().toJson(purchaseItemHistoryList),purchaseItemTotalSize)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
                                    @Override
                                    public void onNext(final Response<ResponseBody> response) {

                                        progressDialog.dismiss();

                                        if (response.isSuccessful()) {
//                                            purchasePostSize++;
//
//                                            if (purchaseTotalSize == purchasePostSize) {

                                                realm.beginTransaction();
                                                realm.delete(PurchaseHistory.class);
                                                realm.commitTransaction();

//                                                syncPurchaseItem();
//                                            }

                                            sync_purchase.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                                            sync_purchase.clearAnimation();

                                        } else {
                                            sync_purchase.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                                            sync_purchase.clearAnimation();
                                            Toast.makeText(getContext(), "There is a problem "
                                                            + response.code()
                                                            + response.message()
                                                    , Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        progressDialog.dismiss();

                                        sync_purchase.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                                        sync_purchase.clearAnimation();
                                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                }));
//            }

        }else
        {
            progressDialog.dismiss();

            sync_purchase.clearAnimation();
            Toast.makeText(getContext(), "Nothing to sync.Please add some purchase first", Toast.LENGTH_LONG).show();
        }

    }

//    public void syncPurchaseItem()
//    {
//        realm.beginTransaction();
//        purchaseItemHistories = realm.where(PurchaseItemHistory.class).findAll();
//        realm.commitTransaction();
//
//        List<PurchaseItemHistory> purchaseItemHistoryList = new ArrayList<>();
//        purchaseItemHistoryList.addAll(realm.copyFromRealm(purchaseItemHistories));
//
//        purchaseItemTotalSize = purchaseItemHistoryList.size();
//
//        for (PurchaseItemHistory purchaseItemHistory : purchaseItemHistoryList)
//
//        {
//
//            compositeDisposable.add(
//                    APIClient.getInstance().syncPurchaseItem(
//                            PreferenceManager.getDeviceId(getContext())
//                            ,PreferenceManager.getSecretKey(getContext())
//                            ,purchaseItemHistory)
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribeOn(Schedulers.io())
//                            .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
//                                @Override
//                                public void onNext(final Response<ResponseBody> response) {
//
//                                    if (response.isSuccessful())
//                                    {
//                                        purchaseItemPostSize++;
//
//                                        if (purchaseItemTotalSize==purchaseItemPostSize) {
//                                            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
//                                            realm.beginTransaction();
//                                            realm.delete(PurchaseItemHistory.class);
//                                            realm.commitTransaction();
//                                            sync_purchase.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
//                                            sync_purchase.clearAnimation();
//                                        }
//
//                                    }else
//                                    {
//                                        sync_purchase.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
//                                        sync_purchase.clearAnimation();
//                                        Toast.makeText(getContext(), "There is a problem "
//                                                        +response.code()
//                                                        +response.message()
//                                                , Toast.LENGTH_LONG).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    sync_purchase.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
//                                    sync_purchase.clearAnimation();
//                                    Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onComplete() {
//
//                                }
//                            }));
//        }
//    }

    public void syncExpense()
    {
        realm.beginTransaction();
        expenseItems = realm.where(ExpenseItem.class).findAll();
        realm.commitTransaction();

        List<ExpenseItem> expenseItemList = new ArrayList<>();
        expenseItemList.addAll(realm.copyFromRealm(expenseItems));

        expenseTotalSize = expenseItemList.size();

        if (expenseTotalSize>0) {

//        for (ExpenseItem expenseItem : expenseItemList)
//
//        {

            compositeDisposable.add(
                    APIClient.getInstance().syncExpense(
                            PreferenceManager.getDeviceId(getContext())
                            ,PreferenceManager.getSecretKey(getContext())
                            ,new Gson().toJson(expenseItemList)
                            ,expenseTotalSize)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
                                @Override
                                public void onNext(final Response<ResponseBody> response) {

                                    progressDialog.dismiss();

                                    if (response.isSuccessful())
                                    {
//                                        expensePostSize++;
//
//                                        if (expenseTotalSize==expensePostSize) {
                                            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                                            realm.beginTransaction();
                                            realm.delete(ExpenseItem.class);
                                            realm.commitTransaction();
                                            sync_expense.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green));
                                            sync_expense.clearAnimation();
//                                        }

                                    }else
                                    {
                                        sync_expense.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                                        sync_expense.clearAnimation();
                                        Toast.makeText(getContext(), "There is a problem "
                                                        +response.code()
                                                        +response.message()
                                                , Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    progressDialog.dismiss();

                                    sync_expense.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync_problem));
                                    sync_expense.clearAnimation();
                                    Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onComplete() {

                                }
                            }));
//        }

        }else
        {
            progressDialog.dismiss();

            sync_expense.clearAnimation();
            Toast.makeText(getContext(), "Nothing to sync.Please add some expense first", Toast.LENGTH_LONG).show();
        }
    }

    public class DownloadImage extends AsyncTask<String, Void, byte[]>
    {


        @Override
        protected byte[] doInBackground(String... imageurls) {

            try {

                URL imageUrl = new URL(imageurls[0]);
                URLConnection ucon = imageUrl.openConnection();

                InputStream is = ucon.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int read = 0;

                while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, read);
                }

                baos.flush();

                return  baos.toByteArray();

            } catch (Exception e) {
                Log.d("ImageManager", "Error: " + e.toString());
                return null;
            }
        }
    }


}
