// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StockActivity_ViewBinding implements Unbinder {
  private StockActivity target;

  @UiThread
  public StockActivity_ViewBinding(StockActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StockActivity_ViewBinding(StockActivity target, View source) {
    this.target = target;

    target.stock_item_rv = Utils.findRequiredViewAsType(source, R.id.stock_item_rv, "field 'stock_item_rv'", RecyclerView.class);
    target.filter = Utils.findRequiredViewAsType(source, R.id.filterbtn, "field 'filter'", ImageView.class);
    target.filtersearchbutton = Utils.findRequiredViewAsType(source, R.id.filtersearchbtn, "field 'filtersearchbutton'", Button.class);
    target.edittextstart = Utils.findRequiredViewAsType(source, R.id.outlinedTextField4, "field 'edittextstart'", TextInputLayout.class);
    target.edittextend = Utils.findRequiredViewAsType(source, R.id.outlinedTextField5, "field 'edittextend'", TextInputLayout.class);
    target.menubtn = Utils.findRequiredViewAsType(source, R.id.menubtn, "field 'menubtn'", ImageView.class);
    target.categoryspinner = Utils.findRequiredViewAsType(source, R.id.spinnercat, "field 'categoryspinner'", Spinner.class);
    target.brandspinner = Utils.findRequiredViewAsType(source, R.id.spinnerbrand, "field 'brandspinner'", Spinner.class);
    target.modespinner = Utils.findRequiredViewAsType(source, R.id.spinnermode, "field 'modespinner'", Spinner.class);
    target.fabb = Utils.findRequiredViewAsType(source, R.id.fab, "field 'fabb'", FloatingActionButton.class);
    target.filter_layoutt = Utils.findRequiredViewAsType(source, R.id.filter_layoutt, "field 'filter_layoutt'", ConstraintLayout.class);
    target.tv_cartno = Utils.findRequiredViewAsType(source, R.id.tv_cartno, "field 'tv_cartno'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StockActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.stock_item_rv = null;
    target.filter = null;
    target.filtersearchbutton = null;
    target.edittextstart = null;
    target.edittextend = null;
    target.menubtn = null;
    target.categoryspinner = null;
    target.brandspinner = null;
    target.modespinner = null;
    target.fabb = null;
    target.filter_layoutt = null;
    target.tv_cartno = null;
  }
}
