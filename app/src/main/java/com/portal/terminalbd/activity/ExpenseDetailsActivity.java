package com.portal.terminalbd.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.ExpenseCategory;
import com.portal.terminalbd.model.MobileAccount;
import com.portal.terminalbd.model.ExpenseItem;
import com.portal.terminalbd.model.SystemUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class ExpenseDetailsActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.remark)
    TextView remark;

    @BindView(R.id.category_name)
    TextView category_name;

    @BindView(R.id.account_no)
    TextView account_no;

    @BindView(R.id.invoice_id)
    TextView invoice_id;

    @BindView(R.id.created)
    TextView created;

    @BindView(R.id.totalBdt)
    TextView totalBdt;

    @BindView(R.id.payment_method)
    TextView payment_method;

    @BindView(R.id.sale_total_tk)
    TextView sale_total_tk;

    @BindView(R.id.edit_btn)
    Button edit_btn;

    @BindView(R.id.delete_btn)
    Button delete_btn;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    String invoiceID;

    Realm realm;

    ExpenseItem expenseHistory;

    SystemUser systemUserRealmQuery;

    String createdDate;

    String currentDateandTime = "";


    String formatDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, null));

        toolbar_title.setText("Expense Details");

        realm = Realm.getDefaultInstance();


        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy h:mm a");


        if (getIntent().getExtras() != null) {
            invoiceID = getIntent().getExtras().getString("INVOICE");
        }


        expenseHistory = realm.where(ExpenseItem.class).equalTo("invoiceId", invoiceID).findFirst();



        if (expenseHistory != null) {

            ExpenseCategory expenseCategory = realm.where(ExpenseCategory.class).equalTo("categoryId",expenseHistory.getExpenseCategory()).findFirst();
           try {
               category_name.setText(expenseCategory.getName());
           }catch (Exception e){

           }


            if (expenseHistory.getTransactionMethod().equalsIgnoreCase("cash"))
            {
                account_no.setVisibility(View.GONE);
            }else if (expenseHistory.getTransactionMethod().equalsIgnoreCase("bank"))
            {
                account_no.setVisibility(View.VISIBLE);

                BankAccount bankAccount = realm.where(BankAccount.class).equalTo("itemId",expenseHistory.getBankAccount()).findFirst();
                account_no.setText(bankAccount.getName());

            }else if (expenseHistory.getTransactionMethod().equalsIgnoreCase("mobile"))
            {
                account_no.setVisibility(View.VISIBLE);

                MobileAccount mobileAccount = realm.where(MobileAccount.class).equalTo("itemId",expenseHistory.getMobileBankAccount()).findFirst();
                account_no.setText(mobileAccount.getName());

            }
            invoice_id.setText(expenseHistory.getInvoiceId());
            totalBdt.setText("" + expenseHistory.getPayment());
            payment_method.setText(expenseHistory.getTransactionMethod());
            sale_total_tk.setText("" + expenseHistory.getPayment());
        }


        if (expenseHistory.getToUser() != 0) {

            systemUserRealmQuery = realm.where(SystemUser.class).equalTo("userId", expenseHistory.getToUser()).findFirst();
        }



        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy h:mm a");
        currentDateandTime = sdf.format(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createdDate = format.format(new Date());


        Date newDate = null;
        try {
            newDate = parseFormat.parse(expenseHistory.getCreated());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatDate = dateFormat.format(newDate);
        created.setText(formatDate);

        username.setText("" + systemUserRealmQuery.getUsername());

        remark.setText(expenseHistory.getRemark());
    }

    @OnClick(R.id.edit_btn)
    void onEditBtn() {
        Intent intent = new Intent(ExpenseDetailsActivity.this, ExpenseEditActivity.class);
        intent.putExtra("INVOICE", invoiceID);
        startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.delete_btn)
    void onDeleteBtn() {

        new AlertDialog.Builder(ExpenseDetailsActivity.this).setMessage("Are you sure to delete?")
                .setTitle("Alert!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        realm.beginTransaction();
                        expenseHistory.deleteFromRealm();
                        realm.commitTransaction();

                        onBackPressed();

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
