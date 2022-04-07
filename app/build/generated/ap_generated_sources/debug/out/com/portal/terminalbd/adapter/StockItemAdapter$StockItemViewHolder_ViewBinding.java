// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StockItemAdapter$StockItemViewHolder_ViewBinding implements Unbinder {
  private StockItemAdapter.StockItemViewHolder target;

  @UiThread
  public StockItemAdapter$StockItemViewHolder_ViewBinding(
      StockItemAdapter.StockItemViewHolder target, View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.stockname = Utils.findRequiredViewAsType(source, R.id.textView18, "field 'stockname'", TextView.class);
    target.mrpname = Utils.findRequiredViewAsType(source, R.id.textView22, "field 'mrpname'", TextView.class);
    target.unitname = Utils.findRequiredViewAsType(source, R.id.textView24, "field 'unitname'", TextView.class);
    target.ppname = Utils.findRequiredViewAsType(source, R.id.textView26, "field 'ppname'", TextView.class);
    target.pp = Utils.findRequiredViewAsType(source, R.id.pp, "field 'pp'", TextView.class);
    target.mrp = Utils.findRequiredViewAsType(source, R.id.mrp, "field 'mrp'", TextView.class);
    target.qnt = Utils.findRequiredViewAsType(source, R.id.qnt, "field 'qnt'", TextView.class);
    target.unit = Utils.findRequiredViewAsType(source, R.id.unit, "field 'unit'", TextView.class);
    target.editbtn = Utils.findRequiredViewAsType(source, R.id.imageView11, "field 'editbtn'", ImageView.class);
    target.cartbtn = Utils.findRequiredViewAsType(source, R.id.iv_carticon, "field 'cartbtn'", ImageView.class);
    target.dotsbtn = Utils.findRequiredViewAsType(source, R.id.imageView13, "field 'dotsbtn'", ImageView.class);
    target.btndelete = Utils.findRequiredViewAsType(source, R.id.imageView15, "field 'btndelete'", ImageView.class);
    target.cartlayout = Utils.findRequiredViewAsType(source, R.id.constraintLayout6, "field 'cartlayout'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StockItemAdapter.StockItemViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.stockname = null;
    target.mrpname = null;
    target.unitname = null;
    target.ppname = null;
    target.pp = null;
    target.mrp = null;
    target.qnt = null;
    target.unit = null;
    target.editbtn = null;
    target.cartbtn = null;
    target.dotsbtn = null;
    target.btndelete = null;
    target.cartlayout = null;
  }
}
