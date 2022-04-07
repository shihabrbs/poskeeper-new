package com.portal.terminalbd.activity;

import android.content.Intent;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.ExpenseCategory;
import com.portal.terminalbd.model.ExpenseItem;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.SystemUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class ExpenseActivity extends AppCompatActivity {

    @BindView(R.id.cashPayment)
    TextView cashPayment;

    @BindView(R.id.bkashPayment)
    TextView bkashPayment;

//    @BindView(R.id.rocketPayment)
//    ImageView rocketPayment;

    @BindView(R.id.visaPayment)
    TextView visaPayment;

    @BindView(R.id.mobile_payment_form)
    LinearLayout mobile_payment_form;

    @BindView(R.id.bank_payment_form)
    LinearLayout bank_payment_form;

    @BindView(R.id.payment_receive_spinner)
    Spinner payment_receive_spinner;

    @BindView(R.id.payment_card_bank_spinner)
    Spinner payment_card_bank_spinner;

    @BindView(R.id.account_user_spinner)
    Spinner account_user_spinner;

    @BindView(R.id.expense_cat_spinner)
    Spinner expense_cat_spinner;

    @BindView(R.id.payment_amount_et)
    EditText payment_amount_et;

    @BindView(R.id.remark_et)
    EditText remark_et;

    @BindView(R.id.payment_btn)
    Button payment_btn;

    int index = 0;

    Realm realm;
    private String paymentType;
    private int paymentReceiveMobileNo;
    private int paymentBankAccount;
    private int memoNo;

    /*
   toolbar
    */
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    List<MobileAccount> mobileAccounts;

    List<BankAccount> bankAccounts;

    List<ExpenseCategory> expenCategories;

    List<SystemUser> systemUserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));

        toolbar_title.setText("Add Expense");


        memoNo = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        cashPayment.setBackgroundColor(getResources().getColor(R.color.cash_background));
        bkashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        visaPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));

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
                new ArrayAdapter<>(ExpenseActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,systemUsers);

        account_user_spinner.setAdapter(systemUsersAdapter);

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
                new ArrayAdapter<>(ExpenseActivity.this
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
                new ArrayAdapter<>(ExpenseActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,bank);

        payment_card_bank_spinner.setAdapter(bankAccountAdapter);

         /*
        payment bank adapter
         */

        RealmResults<ExpenseCategory> results = realm.where(ExpenseCategory.class).findAll();

        expenCategories = realm.copyFromRealm(results);

        String[] expenseType = new String[expenCategories.size()];
        for (int i=0;i<expenCategories.size();i++)
        {
            expenseType[i] = expenCategories.get(i).getName();

        }

        ArrayAdapter<String> expenseTypeAccountAdapter =
                new ArrayAdapter<>(ExpenseActivity.this
                        ,android.R.layout.simple_spinner_dropdown_item
                        ,expenseType);

        expense_cat_spinner.setAdapter(expenseTypeAccountAdapter);


    }

    @OnClick(R.id.bkashPayment)
    void bkashPayment()
    {
        cashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        bkashPayment.setBackgroundColor(getResources().getColor(R.color.bkash_background));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        visaPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));

        mobile_payment_form.setVisibility(View.VISIBLE);
        bank_payment_form.setVisibility(View.GONE);

        index = 1;

    }


//    @OnClick(R.id.rocketPayment)
//    void rocketPayment()
//    {
//        cashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        bkashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.rocket_background));
//        visaPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//
//        mobile_payment_form.setVisibility(View.VISIBLE);
//        bank_payment_form.setVisibility(View.GONE);
//        index = 2;
//
//    }

    @OnClick(R.id.visaPayment)
    void visaPayment()
    {
        cashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        bkashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        visaPayment.setBackgroundColor(getResources().getColor(R.color.rocket_background));

        mobile_payment_form.setVisibility(View.GONE);
        bank_payment_form.setVisibility(View.VISIBLE);
        index = 2;

    }

    @OnClick(R.id.cashPayment)
    void cashPayment()
    {
        cashPayment.setBackgroundColor(getResources().getColor(R.color.cash_background));
        bkashPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
//        rocketPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        visaPayment.setBackgroundColor(getResources().getColor(R.color.dark_grey));

        mobile_payment_form.setVisibility(View.GONE);
        bank_payment_form.setVisibility(View.GONE);
        index = 0;

    }

    @OnClick(R.id.payment_btn)
    void onPaymentBtn()
    {
        saveExpense();
    }

    public void saveExpense()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
        String createdDate = format.format(new Date());

        /*
        save saled details
         */
        realm.beginTransaction();


        ExpenseItem expenseHistory = realm.createObject(ExpenseItem.class,UUID.randomUUID().toString());
        if (index==0)
        {
            paymentType = "cash";
            expenseHistory.setTransactionMethod(paymentType);
        }else if (index==1)
        {
            paymentType = "mobile";
            expenseHistory.setTransactionMethod(paymentType);

            int selectPos = payment_receive_spinner.getSelectedItemPosition();
            paymentReceiveMobileNo = mobileAccounts.get(selectPos).getItemId();
            expenseHistory.setMobileBankAccount(paymentReceiveMobileNo);

        }
//        else if (index==2)
//        {
//            paymentType = "mobile";
//            expenseHistory.setTransactionMethod(paymentType);
//            int selectPos = payment_receive_spinner.getSelectedItemPosition();
//            paymentReceiveMobileNo = mobileAccounts.get(selectPos).getItemId();
//            expenseHistory.setMobileBankAccount(paymentReceiveMobileNo);
//
//        }
        else if (index==2)
        {
            paymentType = "bank";
            int pos = payment_card_bank_spinner.getSelectedItemPosition();
            paymentBankAccount = bankAccounts.get(pos).getItemId();
            expenseHistory.setBankAccount(paymentBankAccount);
        }

        expenseHistory.setCreated(createdDate);


        expenseHistory.setPayment(Integer.parseInt(payment_amount_et.getText().toString()));
        expenseHistory.setInvoiceId(String.valueOf(memoNo));

        int userSelectPos = account_user_spinner.getSelectedItemPosition();
        expenseHistory.setToUser(systemUserList.get(userSelectPos).getUserId());
        expenseHistory.setRemark(remark_et.getText().toString());
        int expenseSelectPos = expense_cat_spinner.getSelectedItemPosition();
        expenseHistory.setExpenseCategory(expenCategories.get(expenseSelectPos).getCategoryId());


        realm.commitTransaction();


        Toast.makeText(this, "Payment Successfull", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
