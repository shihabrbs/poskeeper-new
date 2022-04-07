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

public class PurchaseHistoryFragment_ViewBinding implements Unbinder {
  private PurchaseHistoryFragment target;

  @UiThread
  public PurchaseHistoryFragment_ViewBinding(PurchaseHistoryFragment target, View source) {
    this.target = target;

    target.purchase_history_rv = Utils.findRequiredViewAsType(source, R.id.purchase_rv, "field 'purchase_history_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PurchaseHistoryFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.purchase_history_rv = null;
  }
}
