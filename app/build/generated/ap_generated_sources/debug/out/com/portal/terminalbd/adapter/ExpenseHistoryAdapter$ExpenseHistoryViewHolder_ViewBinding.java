// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExpenseHistoryAdapter$ExpenseHistoryViewHolder_ViewBinding implements Unbinder {
  private ExpenseHistoryAdapter.ExpenseHistoryViewHolder target;

  @UiThread
  public ExpenseHistoryAdapter$ExpenseHistoryViewHolder_ViewBinding(
      ExpenseHistoryAdapter.ExpenseHistoryViewHolder target, View source) {
    this.target = target;

    target.expense_date = Utils.findRequiredViewAsType(source, R.id.expense_date, "field 'expense_date'", TextView.class);
    target.expense_to = Utils.findRequiredViewAsType(source, R.id.expense_to, "field 'expense_to'", TextView.class);
    target.expense_amount = Utils.findRequiredViewAsType(source, R.id.expense_amount, "field 'expense_amount'", TextView.class);
    target.expense_details = Utils.findRequiredViewAsType(source, R.id.expense_details, "field 'expense_details'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpenseHistoryAdapter.ExpenseHistoryViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.expense_date = null;
    target.expense_to = null;
    target.expense_amount = null;
    target.expense_details = null;
  }
}
