// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductGridAdapter$ProductGridViewHolder_ViewBinding implements Unbinder {
  private ProductGridAdapter.ProductGridViewHolder target;

  @UiThread
  public ProductGridAdapter$ProductGridViewHolder_ViewBinding(
      ProductGridAdapter.ProductGridViewHolder target, View source) {
    this.target = target;

    target.product_image = Utils.findRequiredViewAsType(source, R.id.product_image, "field 'product_image'", ImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.mrp = Utils.findRequiredViewAsType(source, R.id.mrp, "field 'mrp'", TextView.class);
    target.quantity_plus = Utils.findRequiredViewAsType(source, R.id.quantity_plus, "field 'quantity_plus'", Button.class);
    target.quantity_minus = Utils.findRequiredViewAsType(source, R.id.quantity_minus, "field 'quantity_minus'", Button.class);
    target.sale_item_quantity = Utils.findRequiredViewAsType(source, R.id.sale_item_quantity, "field 'sale_item_quantity'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductGridAdapter.ProductGridViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.product_image = null;
    target.name = null;
    target.mrp = null;
    target.quantity_plus = null;
    target.quantity_minus = null;
    target.sale_item_quantity = null;
  }
}
