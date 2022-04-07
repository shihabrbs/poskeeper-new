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

public class BankAdapter$BankViewHolder_ViewBinding implements Unbinder {
  private BankAdapter.BankViewHolder target;

  @UiThread
  public BankAdapter$BankViewHolder_ViewBinding(BankAdapter.BankViewHolder target, View source) {
    this.target = target;

    target.vendorName = Utils.findRequiredViewAsType(source, R.id.vendorName, "field 'vendorName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BankAdapter.BankViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vendorName = null;
  }
}
