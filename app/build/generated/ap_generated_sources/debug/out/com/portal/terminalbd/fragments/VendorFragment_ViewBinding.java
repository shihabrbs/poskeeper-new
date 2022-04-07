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

public class VendorFragment_ViewBinding implements Unbinder {
  private VendorFragment target;

  @UiThread
  public VendorFragment_ViewBinding(VendorFragment target, View source) {
    this.target = target;

    target.vendor_rv = Utils.findRequiredViewAsType(source, R.id.vendor_rv, "field 'vendor_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VendorFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vendor_rv = null;
  }
}
