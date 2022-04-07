// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.fragments;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExpenseHistoryFragment_ViewBinding implements Unbinder {
  private ExpenseHistoryFragment target;

  @UiThread
  public ExpenseHistoryFragment_ViewBinding(ExpenseHistoryFragment target, View source) {
    this.target = target;

    target.expense_history_rv = Utils.findRequiredViewAsType(source, R.id.expense_history_rv, "field 'expense_history_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpenseHistoryFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.expense_history_rv = null;
  }
}
