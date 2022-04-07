// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewExpenseActivity_ViewBinding implements Unbinder {
  private NewExpenseActivity target;

  @UiThread
  public NewExpenseActivity_ViewBinding(NewExpenseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewExpenseActivity_ViewBinding(NewExpenseActivity target, View source) {
    this.target = target;

    target.mobileBankLayout = Utils.findRequiredViewAsType(source, R.id.mobile_bank_layout, "field 'mobileBankLayout'", LinearLayout.class);
    target.mobilePaymentForm = Utils.findRequiredViewAsType(source, R.id.mobile_payment_form, "field 'mobilePaymentForm'", LinearLayout.class);
    target.payment_card_bank_spinner = Utils.findRequiredViewAsType(source, R.id.payment_card_bank_spinner, "field 'payment_card_bank_spinner'", Spinner.class);
    target.payment_receive_spinner = Utils.findRequiredViewAsType(source, R.id.payment_receive_spinner, "field 'payment_receive_spinner'", Spinner.class);
    target.bankPaynementLayout = Utils.findRequiredViewAsType(source, R.id.bank_paynement_layout, "field 'bankPaynementLayout'", LinearLayout.class);
    target.expense_cat_spinner = Utils.findRequiredViewAsType(source, R.id.spinnermode, "field 'expense_cat_spinner'", Spinner.class);
    target.et_invoice = Utils.findRequiredViewAsType(source, R.id.et_invoice, "field 'et_invoice'", EditText.class);
    target.bankPaymentForm = Utils.findRequiredViewAsType(source, R.id.bank_payment_form, "field 'bankPaymentForm'", LinearLayout.class);
    target.cashPaymentLayout = Utils.findRequiredViewAsType(source, R.id.cash_payment_layout, "field 'cashPaymentLayout'", LinearLayout.class);
    target.customerSpinner = Utils.findRequiredViewAsType(source, R.id.customerSpinner, "field 'customerSpinner'", Spinner.class);
    target.balance = Utils.findRequiredViewAsType(source, R.id.textView28, "field 'balance'", TextView.class);
    target.startDateLayout = Utils.findRequiredViewAsType(source, R.id.startDateLayout, "field 'startDateLayout'", ConstraintLayout.class);
    target.endDateLayout = Utils.findRequiredViewAsType(source, R.id.endDateLayout, "field 'endDateLayout'", ConstraintLayout.class);
    target.filter_layout = Utils.findRequiredViewAsType(source, R.id.filter_layout, "field 'filter_layout'", LinearLayout.class);
    target.filterbtn = Utils.findRequiredViewAsType(source, R.id.filterbtn, "field 'filterbtn'", ImageView.class);
    target.recycerviewid = Utils.findRequiredViewAsType(source, R.id.recycerviewid, "field 'recycerviewid'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewExpenseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mobileBankLayout = null;
    target.mobilePaymentForm = null;
    target.payment_card_bank_spinner = null;
    target.payment_receive_spinner = null;
    target.bankPaynementLayout = null;
    target.expense_cat_spinner = null;
    target.et_invoice = null;
    target.bankPaymentForm = null;
    target.cashPaymentLayout = null;
    target.customerSpinner = null;
    target.balance = null;
    target.startDateLayout = null;
    target.endDateLayout = null;
    target.filter_layout = null;
    target.filterbtn = null;
    target.recycerviewid = null;
  }
}
