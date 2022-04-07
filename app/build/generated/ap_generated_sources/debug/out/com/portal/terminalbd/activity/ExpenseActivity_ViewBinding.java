// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExpenseActivity_ViewBinding implements Unbinder {
  private ExpenseActivity target;

  private View view7f0a00d4;

  private View view7f0a00b3;

  private View view7f0a038a;

  private View view7f0a022e;

  @UiThread
  public ExpenseActivity_ViewBinding(ExpenseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExpenseActivity_ViewBinding(final ExpenseActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.cashPayment, "field 'cashPayment' and method 'cashPayment'");
    target.cashPayment = Utils.castView(view, R.id.cashPayment, "field 'cashPayment'", TextView.class);
    view7f0a00d4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cashPayment();
      }
    });
    view = Utils.findRequiredView(source, R.id.bkashPayment, "field 'bkashPayment' and method 'bkashPayment'");
    target.bkashPayment = Utils.castView(view, R.id.bkashPayment, "field 'bkashPayment'", TextView.class);
    view7f0a00b3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.bkashPayment();
      }
    });
    view = Utils.findRequiredView(source, R.id.visaPayment, "field 'visaPayment' and method 'visaPayment'");
    target.visaPayment = Utils.castView(view, R.id.visaPayment, "field 'visaPayment'", TextView.class);
    view7f0a038a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.visaPayment();
      }
    });
    target.mobile_payment_form = Utils.findRequiredViewAsType(source, R.id.mobile_payment_form, "field 'mobile_payment_form'", LinearLayout.class);
    target.bank_payment_form = Utils.findRequiredViewAsType(source, R.id.bank_payment_form, "field 'bank_payment_form'", LinearLayout.class);
    target.payment_receive_spinner = Utils.findRequiredViewAsType(source, R.id.payment_receive_spinner, "field 'payment_receive_spinner'", Spinner.class);
    target.payment_card_bank_spinner = Utils.findRequiredViewAsType(source, R.id.payment_card_bank_spinner, "field 'payment_card_bank_spinner'", Spinner.class);
    target.account_user_spinner = Utils.findRequiredViewAsType(source, R.id.account_user_spinner, "field 'account_user_spinner'", Spinner.class);
    target.expense_cat_spinner = Utils.findRequiredViewAsType(source, R.id.expense_cat_spinner, "field 'expense_cat_spinner'", Spinner.class);
    target.payment_amount_et = Utils.findRequiredViewAsType(source, R.id.payment_amount_et, "field 'payment_amount_et'", EditText.class);
    target.remark_et = Utils.findRequiredViewAsType(source, R.id.remark_et, "field 'remark_et'", EditText.class);
    view = Utils.findRequiredView(source, R.id.payment_btn, "field 'payment_btn' and method 'onPaymentBtn'");
    target.payment_btn = Utils.castView(view, R.id.payment_btn, "field 'payment_btn'", Button.class);
    view7f0a022e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPaymentBtn();
      }
    });
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.toolbar_title = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpenseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cashPayment = null;
    target.bkashPayment = null;
    target.visaPayment = null;
    target.mobile_payment_form = null;
    target.bank_payment_form = null;
    target.payment_receive_spinner = null;
    target.payment_card_bank_spinner = null;
    target.account_user_spinner = null;
    target.expense_cat_spinner = null;
    target.payment_amount_et = null;
    target.remark_et = null;
    target.payment_btn = null;
    target.toolbar = null;
    target.toolbar_title = null;

    view7f0a00d4.setOnClickListener(null);
    view7f0a00d4 = null;
    view7f0a00b3.setOnClickListener(null);
    view7f0a00b3 = null;
    view7f0a038a.setOnClickListener(null);
    view7f0a038a = null;
    view7f0a022e.setOnClickListener(null);
    view7f0a022e = null;
  }
}
