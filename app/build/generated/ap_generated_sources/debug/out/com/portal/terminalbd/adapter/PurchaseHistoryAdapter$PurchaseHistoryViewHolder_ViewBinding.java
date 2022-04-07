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

public class PurchaseHistoryAdapter$PurchaseHistoryViewHolder_ViewBinding implements Unbinder {
  private PurchaseHistoryAdapter.PurchaseHistoryViewHolder target;

  @UiThread
  public PurchaseHistoryAdapter$PurchaseHistoryViewHolder_ViewBinding(
      PurchaseHistoryAdapter.PurchaseHistoryViewHolder target, View source) {
    this.target = target;

    target.purchase_date = Utils.findRequiredViewAsType(source, R.id.purchase_date, "field 'purchase_date'", TextView.class);
    target.purchase_total = Utils.findRequiredViewAsType(source, R.id.purchase_total, "field 'purchase_total'", TextView.class);
    target.purchase_payment = Utils.findRequiredViewAsType(source, R.id.purchase_payment, "field 'purchase_payment'", TextView.class);
    target.purchase_vendor_name = Utils.findRequiredViewAsType(source, R.id.purchase_vendor_name, "field 'purchase_vendor_name'", TextView.class);
    target.purchase_details = Utils.findRequiredViewAsType(source, R.id.purchase_details, "field 'purchase_details'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PurchaseHistoryAdapter.PurchaseHistoryViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.purchase_date = null;
    target.purchase_total = null;
    target.purchase_payment = null;
    target.purchase_vendor_name = null;
    target.purchase_details = null;
  }
}
