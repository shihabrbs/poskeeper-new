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

public class CustomerFragment_ViewBinding implements Unbinder {
  private CustomerFragment target;

  @UiThread
  public CustomerFragment_ViewBinding(CustomerFragment target, View source) {
    this.target = target;

    target.customer_rv = Utils.findRequiredViewAsType(source, R.id.customer_rv, "field 'customer_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomerFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.customer_rv = null;
  }
}
