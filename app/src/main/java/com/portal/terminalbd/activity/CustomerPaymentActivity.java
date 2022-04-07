package com.portal.terminalbd.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.CustomerAdapter;
import com.portal.terminalbd.adapter.CustomerSalesListAdapter;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.AnonymousCustomer;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.CustomerLedeger;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.ModelCategory;
import com.portal.terminalbd.model.ModelPaymentReceive;
import com.portal.terminalbd.network.APIClient;
import com.portal.terminalbd.network.PaymentCards;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;

public class CustomerPaymentActivity extends AppCompatActivity {
    ArrayList<String> spinnertextlist = new ArrayList<>();
    TextView cusName;
    String phone;
    String customerid;
    CustomerSalesListAdapter cusadapter;
    List<String> cusitem = new ArrayList<>();
    ArrayList<CustomerLedeger> customerLedegers = new ArrayList<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView recyclerView;
    int paymentBankAccount;
    DatePickerDialog.OnDateSetListener setListener;

    TextView startDate, endDate;
    String startDateText, endDateText;
    ImageView startDateimage, endDateimage;
    EditText amount, addremark;
    ProgressDialog progressDialog;
    Calendar calendar;
    int year;
    int month;
    int day;
    ImageView imageViewTaka;
    LinearLayout linearLayout;
    TextView textViewReceive;
    Boolean showPaymentLayout = true;
    String paymentType = "cash";
    Realm realm;
    String currency;

    @BindView(R.id.mobile_bank_layout)
    LinearLayout mobileBankLayout;

    @BindView(R.id.mobile_payment_form)
    LinearLayout mobilePaymentForm;

    boolean mobilebanklayoutshow = false;

    @BindView(R.id.bank_paynement_layout)
    LinearLayout bankPaynementLayout;
    int paymentMobileID;

    @BindView(R.id.et_invoice)
    EditText et_invoice;

    @BindView(R.id.bank_payment_form)
    LinearLayout bankPaymentForm;
    boolean banklayoutshow = false;

    @BindView(R.id.cash_payment_layout)
    LinearLayout cashPaymentLayout;
    boolean cashlayoutshow = false;

    @BindView(R.id.payment_receive_spinner)
    Spinner payment_receive_spinner;
    @BindView(R.id.payment_card_bank_spinner)
    Spinner payment_card_bank_spinner;
    @BindView(R.id.payment_card_spinner)
    Spinner payment_card_spinner;

    @BindView(R.id.payment_transaction_id)
    EditText paymentTransactionTd;

    @BindView(R.id.textView28)
    TextView balance;

    @BindView(R.id.payment_mobile_no)
    EditText paymentMobileNo;
    @BindView(R.id.startDateLayout)
    ConstraintLayout startDateLayout;
    @BindView(R.id.endDateLayout)
    ConstraintLayout endDateLayout;
    @BindView(R.id.filter_layout)
    LinearLayout filter_layout;
    @BindView(R.id.filterbtn)
    ImageView filterbtn;

    boolean filterlayoutshow = true;

    List<MobileAccount> mobileAccounts;
    List<BankAccount> bankAccounts;
    List<PaymentCards> paymentCardsList;

    RealmResults<PaymentCards> paymentCards;
    RealmResults<BankAccount> banks;
    RealmResults<MobileAccount> mobile;

    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        currency = PreferenceManager.getCurrency(CustomerPaymentActivity.this);
        balance.setText(currency + " 0");
        cusName = findViewById(R.id.et_customerName);
        recyclerView = findViewById(R.id.cart_ItemRV);
        startDate = findViewById(R.id.tv_startDate);
        endDate = findViewById(R.id.tv_endDate);
        startDateimage = findViewById(R.id.imageView20);
        endDateimage = findViewById(R.id.imageView21);
        linearLayout = findViewById(R.id.linearLayout4);
        imageViewTaka = findViewById(R.id.img_taka);
        textViewReceive = findViewById(R.id.textView25);
        amount = findViewById(R.id.receivedTk);
        addremark = findViewById(R.id.et_remark);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        progressDialog = new ProgressDialog(CustomerPaymentActivity.this);
        progressDialog.setTitle("Syncing");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);


        startDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                startDateFind();
            }
        });
        startDateimage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                startDateFind();
            }
        });
        startDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDateFind();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                endDateFind();
            }
        });
        endDateimage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                endDateFind();
            }
        });
        endDateLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                endDateFind();
            }
        });

        imageViewTaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showPaymentLayout) {
                    linearLayout.setVisibility(View.VISIBLE);
                    showPaymentLayout = false;

                    getspinnerData();
                } else {
                    linearLayout.setVisibility(View.GONE);
                    showPaymentLayout = true;

                }
            }
        });
        textViewReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showPaymentLayout) {
                    getspinnerData();
                    linearLayout.setVisibility(View.VISIBLE);
                    showPaymentLayout = false;
                } else {
                    linearLayout.setVisibility(View.GONE);
                    showPaymentLayout = true;
                }
            }
        });


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        customerid = intent.getStringExtra("cusId");


        cusName.setText("" + name);

        spinnertextlist.add("Due");
        spinnertextlist.add("Opening");

        final Spinner spinner = findViewById(R.id.spinnermode);
        TextView textViewspinner = findViewById(R.id.spinnertextid);


        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, spinnertextlist);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mode = spinner.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


            /*
        mobile accounts adapter
         */
        RealmResults<MobileAccount> mobile = realm.where(MobileAccount.class).findAll();

        mobileAccounts = realm.copyFromRealm(mobile);

        String[] accounts = new String[mobileAccounts.size()];
        for (int i = 0; i < mobileAccounts.size(); i++) {
            accounts[i] = mobileAccounts.get(i).getName();

        }

        ArrayAdapter<String> mobileAccountAdapter =
                new ArrayAdapter<>(CustomerPaymentActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , accounts);

        payment_receive_spinner.setAdapter(mobileAccountAdapter);


           /*
        payment bank adapter
         */

        RealmResults<BankAccount> banks = realm.where(BankAccount.class).findAll();

        bankAccounts = realm.copyFromRealm(banks);

        String[] bank = new String[bankAccounts.size()];
        for (int i = 0; i < bankAccounts.size(); i++) {
            bank[i] = bankAccounts.get(i).getName();

        }

        ArrayAdapter<String> bankAccountAdapter =
                new ArrayAdapter<>(CustomerPaymentActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);


              /*
        payment cards adapter
         */

        paymentCards = realm.where(PaymentCards.class).findAll();

        paymentCardsList = realm.copyFromRealm(paymentCards);

        String[] cards = new String[paymentCardsList.size()];
        for (int i = 0; i < paymentCardsList.size(); i++) {
            cards[i] = paymentCardsList.get(i).getName();

        }

        ArrayAdapter<String> paymentCardsAdapter =
                new ArrayAdapter<>(CustomerPaymentActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , cards);

        payment_card_spinner.setAdapter(paymentCardsAdapter);


        cusadapter = new CustomerSalesListAdapter(CustomerPaymentActivity.this, customerLedegers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        getcustomerLedegers();

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

        cashPaymentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankPaymentForm.setVisibility(View.GONE);
                mobilePaymentForm.setVisibility(View.GONE);
                mobilebanklayoutshow = false;
                banklayoutshow = false;
                cashPaymentLayout.setBackgroundResource(R.drawable.red_border_style);
                bankPaynementLayout.setBackgroundResource(R.drawable.white_border);
                mobileBankLayout.setBackgroundResource(R.drawable.white_border);
                paymentType = "Cash";
            }
        });
        mobileBankLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentType = "Mobile";

                if (mobilebanklayoutshow == false) {
                    mobileBankLayout.setBackgroundResource(R.drawable.red_border_style);
                    bankPaynementLayout.setBackgroundResource(R.drawable.white_border);
                    cashPaymentLayout.setBackgroundResource(R.drawable.white_border);
                    mobilePaymentForm.setVisibility(View.VISIBLE);
                    bankPaymentForm.setVisibility(View.GONE);
                    mobilebanklayoutshow = true;
                    banklayoutshow = false;
                } else {
                    mobilePaymentForm.setVisibility(View.GONE);

                    mobilebanklayoutshow = false;
                }
            }
        });

        bankPaynementLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                paymentType = "Bank";
                if (banklayoutshow == false) {
                    bankPaymentForm.setVisibility(View.VISIBLE);
                    mobilePaymentForm.setVisibility(View.GONE);
                    bankPaynementLayout.setBackgroundResource(R.drawable.red_border_style);
                    mobileBankLayout.setBackgroundResource(R.drawable.white_border);
                    cashPaymentLayout.setBackgroundResource(R.drawable.white_border);
                    banklayoutshow = true;
                    mobilebanklayoutshow = false;
                } else {
                    bankPaymentForm.setVisibility(View.GONE);
                    banklayoutshow = false;
                }
            }
        });


    }

    private void getspinnerData() {


          /*
        mobile accounts adapter
         */
        mobile = realm.where(MobileAccount.class).findAll();

        mobileAccounts = realm.copyFromRealm(mobile);
        ArrayList<String> accounts = new ArrayList<>();

        for (int i = 0; i < mobileAccounts.size(); i++) {

            accounts.add("" + mobileAccounts.get(i).getName());
        }


        ArrayAdapter<String> mobileAccountAdapter =
                new ArrayAdapter<>(CustomerPaymentActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , accounts);

        payment_receive_spinner.setAdapter(mobileAccountAdapter);


        /*
        payment bank adapter
         */

        banks = realm.where(BankAccount.class).findAll();

        bankAccounts = realm.copyFromRealm(banks);

        String[] bank = new String[bankAccounts.size()];
        for (int i = 0; i < bankAccounts.size(); i++) {
            bank[i] = bankAccounts.get(i).getName();

        }

        ArrayAdapter<String> bankAccountAdapter =
                new ArrayAdapter<>(CustomerPaymentActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);


            /*
        payment cards adapter
         */

        paymentCards = realm.where(PaymentCards.class).findAll();

        paymentCardsList = realm.copyFromRealm(paymentCards);

        String[] cards = new String[paymentCardsList.size()];
        for (int i = 0; i < paymentCardsList.size(); i++) {
            cards[i] = paymentCardsList.get(i).getName();

        }

        ArrayAdapter<String> paymentCardsAdapter =
                new ArrayAdapter<>(CustomerPaymentActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , cards);

        payment_card_spinner.setAdapter(paymentCardsAdapter);


    }


    private void getcustomerLedegers() {
        // + PreferenceManager.getSecretKey(getApplicationContext())
        compositeDisposable.add(APIClient.getInstance().getCustomerLedeger("" + PreferenceManager.getSecretKey(getApplicationContext()), "" + customerid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<CustomerLedeger>>>() {
                    @Override
                    public void onNext(final Response<List<CustomerLedeger>> response) {
                        customerLedegers.clear();
                        try {
                            customerLedegers.addAll(response.body());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        recyclerView.setAdapter(cusadapter);
                        cusadapter.notifyDataSetChanged();

                        try {
                            int balancee = customerLedegers.get(0).getBalance();
                            if (balancee < 0) {
                                String remsign = "" + balancee;
                                int size = remsign.length();
                                balance.setText(currency + " (" + remsign.substring(1, size) + ")");
                            } else {

                                balance.setText(currency + " " + balancee);
                            }
                        } catch (Exception e) {

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

    private void getcustomerLedegersFilter(String startdate, String enddate, String invoice) {
        compositeDisposable.add(APIClient.getInstance().getCustomerLedegerFilter("" + PreferenceManager.getSecretKey(getApplicationContext()), "" + customerid, startdate, enddate, invoice)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<CustomerLedeger>>>() {
                    @Override
                    public void onNext(final Response<List<CustomerLedeger>> response) {
                        customerLedegers.clear();
                        customerLedegers.addAll(response.body());
                        recyclerView.setAdapter(cusadapter);
                        cusadapter.notifyDataSetChanged();

                        try {
                            int balancee = customerLedegers.get(0).getBalance();
                            if (balancee < 0) {
                                String remsign = "" + balancee;
                                int size = remsign.length();
                                balance.setText(currency + " (" + remsign.substring(1, size) + ")");
                            } else {

                                balance.setText(currency + " " + balancee);
                            }
                        } catch (Exception e) {

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

    private void startDateFind() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(CustomerPaymentActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String data = day + "-" + month + "-" + year;
                startDate.setText(data);
                startDateText = data;
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void endDateFind() {
        if (!startDate.getText().toString().equals("Start Date")) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(CustomerPaymentActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String data = day + "-" + month + "-" + year;
                    endDate.setText(data);
                    endDateText = data;
                }
            }, year, month, day);
            datePickerDialog.show();
        }
    }

    public void btn_call(View view) {

        checkAndRequestPermissions();

        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + phone));
        startActivity(i);
    }

    public void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 5);
            }
        }

    }

    public void btn_back(View view) {
        onBackPressed();
    }

    public void internetcheck(View view) {
        // Check for Internet Connection
        if (isConnected()) {
            //  Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();

            String receiveamount = amount.getText().toString();
            String user_id = "" + PreferenceManager.getUserId(CustomerPaymentActivity.this);
            int paymentPos = payment_receive_spinner.getSelectedItemPosition();
            paymentMobileID = mobileAccounts.get(paymentPos).getItemId();

            int position = payment_card_bank_spinner.getSelectedItemPosition();
            paymentBankAccount = bankAccounts.get(position).getItemId();

            if (!receiveamount.equals("")) {
                String userid = "" + user_id;
                String cusid = "" + customerid;
                String method = "" + paymentType;
                String amount = "" + receiveamount;
                String modee = "" + mode.toLowerCase();
                String remark = "" + addremark.getText().toString();
                String bankaccount = "" + paymentBankAccount;
                String mobileaccount = "" + paymentMobileID;
                String transactionid = "" + paymentTransactionTd.getText().toString();
                String mobileno = "" + paymentMobileNo.getText().toString();

                insertPaymentReceive(userid, cusid, method, amount, modee, remark, bankaccount, mobileaccount, transactionid, mobileno);

            } else {
                amount.setError("Required");
            }


        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    private void insertPaymentReceive(String userid, String customerid, String method,
                                      String amountt, String mode, String remark,
                                      String bankaccount, String mobileaccount,
                                      String transactionid, String mobileno) {


        progressDialog.setMessage("Syncing Customer Ledger....");

        compositeDisposable.addAll(APIClient.getInstance().createPaymentReceive(PreferenceManager.getSecretKey(CustomerPaymentActivity.this),
                userid, customerid, method, amountt, mode, remark, bankaccount, mobileaccount, transactionid, mobileno)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelPaymentReceive>>() {
                    @Override
                    public void onNext(final Response<ModelPaymentReceive> response) {

                        if (response.body().getStatus().equals("valid")) {
                            //syncAnonymousCustomer();
                            getcustomerLedegers();
                            Toast.makeText(CustomerPaymentActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            addremark.setText("");
                            amount.setText("");
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

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplication().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void btn_filter_search(View view) {
        String invoice = et_invoice.getText().toString();
        getcustomerLedegersFilter(startDateText, endDateText, invoice);
        hideSoftKeyboard(view);
    }
}