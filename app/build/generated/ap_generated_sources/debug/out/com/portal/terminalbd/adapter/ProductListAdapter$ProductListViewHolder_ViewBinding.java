// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductListAdapter$ProductListViewHolder_ViewBinding implements Unbinder {
  private ProductListAdapter.ProductListViewHolder target;

  @UiThread
  public ProductListAdapter$ProductListViewHolder_ViewBinding(
      ProductListAdapter.ProductListViewHolder target, View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.stockmrp = Utils.findRequiredViewAsType(source, R.id.mrp, "field 'stockmrp'", TextView.class);
    target.mrp = Utils.findRequiredViewAsType(source, R.id.edittextmrp, "field 'mrp'", EditText.class);
    target.quantity_plus = Utils.findRequiredViewAsType(source, R.id.plusebtn, "field 'quantity_plus'", ImageView.class);
    target.quantity_minus = Utils.findRequiredViewAsType(source, R.id.minusbtn, "field 'quantity_minus'", ImageView.class);
    target.sale_item_quantity = Utils.findRequiredViewAsType(source, R.id.edittextqty, "field 'sale_item_quantity'", EditText.class);
    target.total = Utils.findRequiredViewAsType(source, R.id.textView17, "field 'total'", TextView.class);
    target.stock = Utils.findRequiredViewAsType(source, R.id.qnt, "field 'stock'", TextView.class);
    target.unit = Utils.findRequiredViewAsType(source, R.id.unit, "field 'unit'", TextView.class);
    target.spinner = Utils.findRequiredViewAsType(source, R.id.spinnerid, "field 'spinner'", Spinner.class);
    target.itemcartlayout = Utils.findRequiredViewAsType(source, R.id.constraintLayout6, "field 'itemcartlayout'", ConstraintLayout.class);
    target.bgconst = Utils.findRequiredViewAsType(source, R.id.sales_cart_bg_const, "field 'bgconst'", ConstraintLayout.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.re_fresh, "field 'refresh'", ImageView.class);
    target.addbtn = Utils.findRequiredViewAsType(source, R.id.textView31, "field 'addbtn'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductListAdapter.ProductListViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.stockmrp = null;
    target.mrp = null;
    target.quantity_plus = null;
    target.quantity_minus = null;
    target.sale_item_quantity = null;
    target.total = null;
    target.stock = null;
    target.unit = null;
    target.spinner = null;
    target.itemcartlayout = null;
    target.bgconst = null;
    target.refresh = null;
    target.addbtn = null;
  }
}
