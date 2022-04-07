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

public class ExpenseCatAdapter$ExpenseViewHolder_ViewBinding implements Unbinder {
  private ExpenseCatAdapter.ExpenseViewHolder target;

  @UiThread
  public ExpenseCatAdapter$ExpenseViewHolder_ViewBinding(ExpenseCatAdapter.ExpenseViewHolder target,
      View source) {
    this.target = target;

    target.catName = Utils.findRequiredViewAsType(source, R.id.catName, "field 'catName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpenseCatAdapter.ExpenseViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.catName = null;
  }
}
