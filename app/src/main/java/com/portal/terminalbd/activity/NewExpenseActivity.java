package com.portal.terminalbd.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.CustomerSalesListAdapter;
import com.portal.terminalbd.adapter.ExpenseHistoryAdapter;
import com.portal.terminalbd.adapter.NewExpenseHistoryAdapter;
import com.portal.terminalbd.helpers.PreferenceManager;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.Category;
import com.portal.terminalbd.model.CustomerLedeger;
import com.portal.terminalbd.model.ExpenseCategory;
import com.portal.terminalbd.model.ExpenseItem;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.ModelPaymentReceive;
import com.portal.terminalbd.model.SystemUser;
import com.portal.terminalbd.network.APIClient;
import com.portal.terminalbd.network.PaymentCards;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;

public class NewExpenseActivity extends AppCompatActivity {
    ArrayList<String> spinnertextlist = new ArrayList<>();
    TextView cusName;
    String phone;
    String customerid;
    CustomerSalesListAdapter cusadapter;
    List<String> cusitem = new ArrayList<>();
    ArrayList<CustomerLedeger> customerLedegers = new ArrayList<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView recyclerView;
    List<ExpenseItem> expenseHistoriesList;
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
    int paymentMobileID;
    private int paymentReceiveMobileNo;
    private int paymentBankAccount;
    List<ExpenseCategory> expenCategories;
    RealmResults<ExpenseItem> expenseHistories;
    NewExpenseHistoryAdapter adapter;
    @BindView(R.id.mobile_bank_layout)
    LinearLayout mobileBankLayout;

    @BindView(R.id.mobile_payment_form)
    LinearLayout mobilePaymentForm;

    @BindView(R.id.payment_card_bank_spinner)
    Spinner payment_card_bank_spinner;

    @BindView(R.id.payment_receive_spinner)
    Spinner payment_receive_spinner;

    boolean mobilebanklayoutshow = false;

    @BindView(R.id.bank_paynement_layout)
    LinearLayout bankPaynementLayout;


    @BindView(R.id.spinnermode)
    Spinner expense_cat_spinner;

    @BindView(R.id.et_invoice)
    EditText et_invoice;

    @BindView(R.id.bank_payment_form)
    LinearLayout bankPaymentForm;
    boolean banklayoutshow = false;

    @BindView(R.id.cash_payment_layout)
    LinearLayout cashPaymentLayout;
    boolean cashlayoutshow = false;


    @BindView(R.id.customerSpinner)
    Spinner customerSpinner;




    @BindView(R.id.textView28)
    TextView balance;


    @BindView(R.id.startDateLayout)
    ConstraintLayout startDateLayout;
    @BindView(R.id.endDateLayout)
    ConstraintLayout endDateLayout;
    @BindView(R.id.filter_layout)
    LinearLayout filter_layout;
    @BindView(R.id.filterbtn)
    ImageView filterbtn;

    @BindView(R.id.recycerviewid)
    RecyclerView recycerviewid;

    boolean filterlayoutshow = true;

    List<MobileAccount> mobileAccounts;
    List<BankAccount> bankAccounts;
    List<PaymentCards> paymentCardsList;

    RealmResults<PaymentCards> paymentCards;
    RealmResults<BankAccount> banks;
    RealmResults<MobileAccount> mobile;
    List<SystemUser> systemUserList;
    String expensecategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();


/*
        Category category = realm.where(Category.class).equalTo("categoryId", 624).findFirst();
        if(category != null) {
            // dog
            Toast.makeText(this, "has data", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }*/

        getExpenseHistory();

        currency = PreferenceManager.getCurrency(NewExpenseActivity.this);
        balance.setText(currency + " 0");
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        cusName = findViewById(R.id.et_customerName);
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


        progressDialog = new ProgressDialog(NewExpenseActivity.this);
        progressDialog.setTitle("Syncing");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);


        amount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                return false;
            }
        });

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

        amount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
              //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                return false;
            }
        });


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        customerid = intent.getStringExtra("cusId");



      /*  spinnertextlist.add("Category");
        spinnertextlist.add("Advance Salary");
        spinnertextlist.add("Donation");
        spinnertextlist.add("Entertainment");
        spinnertextlist.add("Stationary");
        spinnertextlist.add("Travel & Others");

        final Spinner spinner = findViewById(R.id.spinnermode);
        TextView textViewspinner = findViewById(R.id.spinnertextid);


        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, spinnertextlist);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                expensecategory = spinner.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/



           /*
        mobile accounts adapter
         */
        RealmResults<MobileAccount> mobile = realm.where(MobileAccount.class).findAll();

        mobileAccounts = realm.copyFromRealm(mobile);

        String[] accounts = new String[mobileAccounts.size()];
        for (int i=0;i<mobileAccounts.size();i++)
        {
            accounts[i] = mobileAccounts.get(i).getName();

        }

        ArrayAdapter<String> mobileAccountAdapter =
                new ArrayAdapter<>(NewExpenseActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,accounts);

        payment_receive_spinner.setAdapter(mobileAccountAdapter);


        /*
        payment bank adapter
         */

        RealmResults<BankAccount> banks = realm.where(BankAccount.class).findAll();

        bankAccounts = realm.copyFromRealm(banks);

        String[] bank = new String[bankAccounts.size()];
        for (int i=0;i<bankAccounts.size();i++)
        {
            bank[i] = bankAccounts.get(i).getName();

        }

        ArrayAdapter<String> bankAccountAdapter =
                new ArrayAdapter<>(NewExpenseActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);




          /*
        Expense category adapter
         */

        RealmResults<ExpenseCategory> results = realm.where(ExpenseCategory.class).findAll();

        expenCategories = realm.copyFromRealm(results);

        String[] expenseType = new String[expenCategories.size()];
        for (int i=0;i<expenCategories.size();i++)
        {
            expenseType[i] = expenCategories.get(i).getName();

        }

        ArrayAdapter<String> expenseTypeAccountAdapter =
                new ArrayAdapter<>(NewExpenseActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,expenseType);

        expense_cat_spinner.setAdapter(expenseTypeAccountAdapter);




         /*
        sale by adapter
         */

        RealmResults<SystemUser> users = realm.where(SystemUser.class).findAll();

        systemUserList = realm.copyFromRealm(users);

        String[] systemUsers = new String[systemUserList.size()];
        for (int i=0;i<systemUserList.size();i++)
        {
            systemUsers[i] = systemUserList.get(i).getUsername();

        }

        ArrayAdapter<String> systemUsersAdapter =
                new ArrayAdapter<>(NewExpenseActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,systemUsers);

        customerSpinner.setAdapter(systemUsersAdapter);


       /* cusadapter = new CustomerSalesListAdapter(NewExpenseActivity.this, customerLedegers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
*/
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

    private void getExpenseHistory() {
        expenseHistoriesList = new ArrayList<>();

        expenseHistories = realm.where(ExpenseItem.class).findAll();

        expenseHistoriesList.addAll(realm.copyFromRealm(expenseHistories));

        Log.d("ExpenseList:",""+expenseHistoriesList.toString());

        adapter = new NewExpenseHistoryAdapter(getApplicationContext(),expenseHistoriesList);
        recycerviewid.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
      //  recycerviewid.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        recycerviewid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
                new ArrayAdapter<>(NewExpenseActivity.this
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
                new ArrayAdapter<>(NewExpenseActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);





    }


    private void getcustomerLedegers() {
        // + PreferenceManager.getSecretKey(getApplicationContext())
        compositeDisposable.add(APIClient.getInstance().getCustomerLedeger("" + PreferenceManager.getSecretKey(getApplicationContext()), "" + customerid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<CustomerLedeger>>>() {
                    @Override
                    public void onNext(final Response<List<CustomerLedeger>> response) {
                        /*customerLedegers.clear();
                        customerLedegers.addAll(response.body());
                        recyclerView.setAdapter(cusadapter);
                        cusadapter.notifyDataSetChanged();*/

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
        DatePickerDialog datePickerDialog = new DatePickerDialog(NewExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            DatePickerDialog datePickerDialog = new DatePickerDialog(NewExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
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



            saveExpense();

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    public void saveExpense()
    {

        int memoNo;
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
        String createdDate = format.format(new Date());

        memoNo = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        String userid = "" + PreferenceManager.getUserId(NewExpenseActivity.this);
        //save saled details

        realm.beginTransaction();


        ExpenseItem expenseHistory = realm.createObject(ExpenseItem.class, UUID.randomUUID().toString());



        if (banklayoutshow == false && mobilebanklayoutshow == false) {
            paymentType = "cash";
            expenseHistory.setMobileBankAccount(0);
            expenseHistory.setBankAccount(0);

        } else if (mobilebanklayoutshow) {
            paymentType = "mobile";
            int selectPos = payment_receive_spinner.getSelectedItemPosition();
            paymentReceiveMobileNo = mobileAccounts.get(selectPos).getItemId();
            expenseHistory.setMobileBankAccount(paymentReceiveMobileNo);

        } else if (banklayoutshow) {
            paymentType = "bank";
            int pos = payment_card_bank_spinner.getSelectedItemPosition();
            paymentBankAccount = bankAccounts.get(pos).getItemId();
            expenseHistory.setBankAccount(paymentBankAccount);

        }





        expenseHistory.setTransactionMethod(paymentType);
        expenseHistory.setCreated(createdDate);
        expenseHistory.setPayment(Integer.parseInt(""+amount.getText().toString()));
        expenseHistory.setInvoiceId(String.valueOf(memoNo));
        int userSelectPos = customerSpinner.getSelectedItemPosition();
        expenseHistory.setToUser(systemUserList.get(userSelectPos).getUserId());
        expenseHistory.setRemark(addremark.getText().toString());
        int expenseSelectPos = expense_cat_spinner.getSelectedItemPosition();
        expenseHistory.setExpenseCategory(expenCategories.get(expenseSelectPos).getCategoryId());



        realm.commitTransaction();


        Toast.makeText(this, "Payment Successfull", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        startActivity(intent);
        finish();
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
       // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
      /*  String invoice = et_invoice.getText().toString();
        getcustomerLedegersFilter(startDateText, endDateText, invoice);
        hideSoftKeyboard(view);*/
    }

    public void btn_reset(View view) {
        amount.setText(null);
        addremark.setText(null);
    }
}