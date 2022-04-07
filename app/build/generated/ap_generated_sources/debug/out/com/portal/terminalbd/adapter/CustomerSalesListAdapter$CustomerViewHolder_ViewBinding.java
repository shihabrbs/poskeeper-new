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

public class CustomerSalesListAdapter$CustomerViewHolder_ViewBinding implements Unbinder {
  private CustomerSalesListAdapter.CustomerViewHolder target;

  @UiThread
  public CustomerSalesListAdapter$CustomerViewHolder_ViewBinding(
      CustomerSalesListAdapter.CustomerViewHolder target, View source) {
    this.target = target;

    target.date = Utils.findRequiredViewAsType(source, R.id.textView27, "field 'date'", TextView.class);
    target.devider = Utils.findRequiredViewAsType(source, R.id.textView25, "field 'devider'", TextView.class);
    target.sales = Utils.findRequiredViewAsType(source, R.id.tv_sales, "field 'sales'", TextView.class);
    target.received = Utils.findRequiredViewAsType(source, R.id.tv_received, "field 'received'", TextView.class);
    target.balance = Utils.findRequiredViewAsType(source, R.id.tv_balance, "field 'balance'", TextView.class);
    target.invoice = Utils.findRequiredViewAsType(source, R.id.tv_invoice, "field 'invoice'", TextView.class);
    target.method = Utils.findRequiredViewAsType(source, R.id.tv_method, "field 'method'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomerSalesListAdapter.CustomerViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.date = null;
    target.devider = null;
    target.sales = null;
    target.received = null;
    target.balance = null;
    target.invoice = null;
    target.method = null;
  }
}
