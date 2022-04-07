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

public class SalesDetailsItemAdapter$SalesDetailsItemViewHolder_ViewBinding implements Unbinder {
  private SalesDetailsItemAdapter.SalesDetailsItemViewHolder target;

  @UiThread
  public SalesDetailsItemAdapter$SalesDetailsItemViewHolder_ViewBinding(
      SalesDetailsItemAdapter.SalesDetailsItemViewHolder target, View source) {
    this.target = target;

    target.sale_item_med_name = Utils.findRequiredViewAsType(source, R.id.sale_item_med_name, "field 'sale_item_med_name'", TextView.class);
    target.sale_item_mrp_price = Utils.findRequiredViewAsType(source, R.id.sale_item_mrp_price, "field 'sale_item_mrp_price'", TextView.class);
    target.sale_item_quantity = Utils.findRequiredViewAsType(source, R.id.sale_item_quantity, "field 'sale_item_quantity'", TextView.class);
    target.sale_item_sub_total = Utils.findRequiredViewAsType(source, R.id.sale_item_sub_total, "field 'sale_item_sub_total'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SalesDetailsItemAdapter.SalesDetailsItemViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sale_item_med_name = null;
    target.sale_item_mrp_price = null;
    target.sale_item_quantity = null;
    target.sale_item_sub_total = null;
  }
}
