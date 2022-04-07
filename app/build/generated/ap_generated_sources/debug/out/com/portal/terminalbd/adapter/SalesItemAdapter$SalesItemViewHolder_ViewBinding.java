// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SalesItemAdapter$SalesItemViewHolder_ViewBinding implements Unbinder {
  private SalesItemAdapter.SalesItemViewHolder target;

  @UiThread
  public SalesItemAdapter$SalesItemViewHolder_ViewBinding(
      SalesItemAdapter.SalesItemViewHolder target, View source) {
    this.target = target;

    target.sale_item_med_name = Utils.findRequiredViewAsType(source, R.id.name, "field 'sale_item_med_name'", TextView.class);
    target.sale_item_mrp_price = Utils.findRequiredViewAsType(source, R.id.edittextmrp, "field 'sale_item_mrp_price'", EditText.class);
    target.sale_item_quantity = Utils.findRequiredViewAsType(source, R.id.edittextqty, "field 'sale_item_quantity'", EditText.class);
    target.quantity_plus = Utils.findRequiredViewAsType(source, R.id.plusebtn, "field 'quantity_plus'", ImageView.class);
    target.quantity_minus = Utils.findRequiredViewAsType(source, R.id.minusbtn, "field 'quantity_minus'", ImageView.class);
    target.sale_item_sub_total = Utils.findRequiredViewAsType(source, R.id.textView17, "field 'sale_item_sub_total'", TextView.class);
    target.delete = Utils.findRequiredViewAsType(source, R.id.imageView16, "field 'delete'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SalesItemAdapter.SalesItemViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sale_item_med_name = null;
    target.sale_item_mrp_price = null;
    target.sale_item_quantity = null;
    target.quantity_plus = null;
    target.quantity_minus = null;
    target.sale_item_sub_total = null;
    target.delete = null;
  }
}
