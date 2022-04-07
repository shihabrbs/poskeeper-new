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

public class SalesHistoryFragment_ViewBinding implements Unbinder {
  private SalesHistoryFragment target;

  @UiThread
  public SalesHistoryFragment_ViewBinding(SalesHistoryFragment target, View source) {
    this.target = target;

    target.sales_history_rv = Utils.findRequiredViewAsType(source, R.id.sales_history_rv, "field 'sales_history_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SalesHistoryFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sales_history_rv = null;
  }
}
