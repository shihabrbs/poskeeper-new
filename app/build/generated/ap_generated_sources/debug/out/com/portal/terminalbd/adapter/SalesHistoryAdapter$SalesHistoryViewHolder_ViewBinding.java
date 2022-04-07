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

public class SalesHistoryAdapter$SalesHistoryViewHolder_ViewBinding implements Unbinder {
  private SalesHistoryAdapter.SalesHistoryViewHolder target;

  @UiThread
  public SalesHistoryAdapter$SalesHistoryViewHolder_ViewBinding(
      SalesHistoryAdapter.SalesHistoryViewHolder target, View source) {
    this.target = target;

    target.serial_no = Utils.findRequiredViewAsType(source, R.id.serial_no, "field 'serial_no'", TextView.class);
    target.sales_date = Utils.findRequiredViewAsType(source, R.id.sales_date, "field 'sales_date'", TextView.class);
    target.sales_total = Utils.findRequiredViewAsType(source, R.id.sales_total, "field 'sales_total'", TextView.class);
    target.sales_payment = Utils.findRequiredViewAsType(source, R.id.sales_payment, "field 'sales_payment'", TextView.class);
    target.sales_transaction = Utils.findRequiredViewAsType(source, R.id.sales_transaction, "field 'sales_transaction'", TextView.class);
    target.sales_details = Utils.findRequiredViewAsType(source, R.id.sales_details, "field 'sales_details'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SalesHistoryAdapter.SalesHistoryViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.serial_no = null;
    target.sales_date = null;
    target.sales_total = null;
    target.sales_payment = null;
    target.sales_transaction = null;
    target.sales_details = null;
  }
}
