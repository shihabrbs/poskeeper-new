// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewExpenseHistoryAdapter$ExpenseHistoryViewHolder_ViewBinding implements Unbinder {
  private NewExpenseHistoryAdapter.ExpenseHistoryViewHolder target;

  @UiThread
  public NewExpenseHistoryAdapter$ExpenseHistoryViewHolder_ViewBinding(
      NewExpenseHistoryAdapter.ExpenseHistoryViewHolder target, View source) {
    this.target = target;

    target.expense_date = Utils.findRequiredViewAsType(source, R.id.textView27, "field 'expense_date'", TextView.class);
    target.expense_method = Utils.findRequiredViewAsType(source, R.id.tv_method, "field 'expense_method'", TextView.class);
    target.expense_invoice = Utils.findRequiredViewAsType(source, R.id.tv_invoice, "field 'expense_invoice'", TextView.class);
    target.use = Utils.findRequiredViewAsType(source, R.id.tv_sales, "field 'use'", TextView.class);
    target.expense_amount = Utils.findRequiredViewAsType(source, R.id.tv_balance, "field 'expense_amount'", TextView.class);
    target.category = Utils.findRequiredViewAsType(source, R.id.tv_received, "field 'category'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewExpenseHistoryAdapter.ExpenseHistoryViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.expense_date = null;
    target.expense_method = null;
    target.expense_invoice = null;
    target.use = null;
    target.expense_amount = null;
    target.category = null;
  }
}
