// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import com.portal.terminalbd.utils.ClearableAutoCompleteTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PurchaseActivity_ViewBinding implements Unbinder {
  private PurchaseActivity target;

  private View view7f0a0284;

  private View view7f0a0094;

  @UiThread
  public PurchaseActivity_ViewBinding(PurchaseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PurchaseActivity_ViewBinding(final PurchaseActivity target, View source) {
    this.target = target;

    View view;
    target.search = Utils.findRequiredViewAsType(source, R.id.med_search, "field 'search'", ClearableAutoCompleteTextView.class);
    target.purchase_pp = Utils.findRequiredViewAsType(source, R.id.purchase_pp, "field 'purchase_pp'", EditText.class);
    target.purchase_mrp = Utils.findRequiredViewAsType(source, R.id.purchase_mrp, "field 'purchase_mrp'", EditText.class);
    target.purchase_quantity = Utils.findRequiredViewAsType(source, R.id.purchase_quantity, "field 'purchase_quantity'", EditText.class);
    target.purchase_history_rv = Utils.findRequiredViewAsType(source, R.id.purchase_history_rv, "field 'purchase_history_rv'", RecyclerView.class);
    target.total_item_count = Utils.findRequiredViewAsType(source, R.id.total_item_count, "field 'total_item_count'", TextView.class);
    target.grand_total_text = Utils.findRequiredViewAsType(source, R.id.grand_total_text, "field 'grand_total_text'", TextView.class);
    view = Utils.findRequiredView(source, R.id.sale_layout, "field 'sale_layout' and method 'sale_layout'");
    target.sale_layout = Utils.castView(view, R.id.sale_layout, "field 'sale_layout'", RelativeLayout.class);
    view7f0a0284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sale_layout();
      }
    });
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.toolbar_title = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbar_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.add_btn, "method 'add_btn'");
    view7f0a0094 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.add_btn();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PurchaseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.search = null;
    target.purchase_pp = null;
    target.purchase_mrp = null;
    target.purchase_quantity = null;
    target.purchase_history_rv = null;
    target.total_item_count = null;
    target.grand_total_text = null;
    target.sale_layout = null;
    target.toolbar = null;
    target.toolbar_title = null;

    view7f0a0284.setOnClickListener(null);
    view7f0a0284 = null;
    view7f0a0094.setOnClickListener(null);
    view7f0a0094 = null;
  }
}
