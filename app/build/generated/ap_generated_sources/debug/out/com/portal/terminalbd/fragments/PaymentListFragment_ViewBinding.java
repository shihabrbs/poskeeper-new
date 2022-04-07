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

public class PaymentListFragment_ViewBinding implements Unbinder {
  private PaymentListFragment target;

  @UiThread
  public PaymentListFragment_ViewBinding(PaymentListFragment target, View source) {
    this.target = target;

    target.mobile_rv = Utils.findRequiredViewAsType(source, R.id.mobile_rv, "field 'mobile_rv'", RecyclerView.class);
    target.bank_rv = Utils.findRequiredViewAsType(source, R.id.bank_rv, "field 'bank_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PaymentListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mobile_rv = null;
    target.bank_rv = null;
  }
}
