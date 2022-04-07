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
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomerPaymentActivity_ViewBinding implements Unbinder {
  private CustomerPaymentActivity target;

  @UiThread
  public CustomerPaymentActivity_ViewBinding(CustomerPaymentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomerPaymentActivity_ViewBinding(CustomerPaymentActivity target, View source) {
    this.target = target;

    target.mobileBankLayout = Utils.findRequiredViewAsType(source, R.id.mobile_bank_layout, "field 'mobileBankLayout'", LinearLayout.class);
    target.mobilePaymentForm = Utils.findRequiredViewAsType(source, R.id.mobile_payment_form, "field 'mobilePaymentForm'", LinearLayout.class);
    target.bankPaynementLayout = Utils.findRequiredViewAsType(source, R.id.bank_paynement_layout, "field 'bankPaynementLayout'", LinearLayout.class);
    target.et_invoice = Utils.findRequiredViewAsType(source, R.id.et_invoice, "field 'et_invoice'", EditText.class);
    target.bankPaymentForm = Utils.findRequiredViewAsType(source, R.id.bank_payment_form, "field 'bankPaymentForm'", LinearLayout.class);
    target.cashPaymentLayout = Utils.findRequiredViewAsType(source, R.id.cash_payment_layout, "field 'cashPaymentLayout'", LinearLayout.class);
    target.payment_receive_spinner = Utils.findRequiredViewAsType(source, R.id.payment_receive_spinner, "field 'payment_receive_spinner'", Spinner.class);
    target.payment_card_bank_spinner = Utils.findRequiredViewAsType(source, R.id.payment_card_bank_spinner, "field 'payment_card_bank_spinner'", Spinner.class);
    target.payment_card_spinner = Utils.findRequiredViewAsType(source, R.id.payment_card_spinner, "field 'payment_card_spinner'", Spinner.class);
    target.paymentTransactionTd = Utils.findRequiredViewAsType(source, R.id.payment_transaction_id, "field 'paymentTransactionTd'", EditText.class);
    target.balance = Utils.findRequiredViewAsType(source, R.id.textView28, "field 'balance'", TextView.class);
    target.paymentMobileNo = Utils.findRequiredViewAsType(source, R.id.payment_mobile_no, "field 'paymentMobileNo'", EditText.class);
    target.startDateLayout = Utils.findRequiredViewAsType(source, R.id.startDateLayout, "field 'startDateLayout'", ConstraintLayout.class);
    target.endDateLayout = Utils.findRequiredViewAsType(source, R.id.endDateLayout, "field 'endDateLayout'", ConstraintLayout.class);
    target.filter_layout = Utils.findRequiredViewAsType(source, R.id.filter_layout, "field 'filter_layout'", LinearLayout.class);
    target.filterbtn = Utils.findRequiredViewAsType(source, R.id.filterbtn, "field 'filterbtn'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomerPaymentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mobileBankLayout = null;
    target.mobilePaymentForm = null;
    target.bankPaynementLayout = null;
    target.et_invoice = null;
    target.bankPaymentForm = null;
    target.cashPaymentLayout = null;
    target.payment_receive_spinner = null;
    target.payment_card_bank_spinner = null;
    target.payment_card_spinner = null;
    target.paymentTransactionTd = null;
    target.balance = null;
    target.paymentMobileNo = null;
    target.startDateLayout = null;
    target.endDateLayout = null;
    target.filter_layout = null;
    target.filterbtn = null;
  }
}
