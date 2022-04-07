// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.fragments;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view7f0a011b;

  private View view7f0a0118;

  private View view7f0a0116;

  private View view7f0a011c;

  private View view7f0a0115;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    target.dash_balance = Utils.findRequiredViewAsType(source, R.id.dash_balance, "field 'dash_balance'", TextView.class);
    target.dash_sale_amount = Utils.findRequiredViewAsType(source, R.id.dash_sale_amount, "field 'dash_sale_amount'", TextView.class);
    target.dash_purchase_amount = Utils.findRequiredViewAsType(source, R.id.dash_purchase_amount, "field 'dash_purchase_amount'", TextView.class);
    target.dash_expense_amount = Utils.findRequiredViewAsType(source, R.id.dash_expense_amount, "field 'dash_expense_amount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.dash_sales, "field 'dash_sales' and method 'dash_sales'");
    target.dash_sales = Utils.castView(view, R.id.dash_sales, "field 'dash_sales'", RelativeLayout.class);
    view7f0a011b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dash_sales();
      }
    });
    view = Utils.findRequiredView(source, R.id.dash_purchase, "field 'dash_purchase' and method 'dash_purchase'");
    target.dash_purchase = Utils.castView(view, R.id.dash_purchase, "field 'dash_purchase'", RelativeLayout.class);
    view7f0a0118 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dash_purchase();
      }
    });
    view = Utils.findRequiredView(source, R.id.dash_expense, "field 'dash_expense' and method 'dash_expense'");
    target.dash_expense = Utils.castView(view, R.id.dash_expense, "field 'dash_expense'", RelativeLayout.class);
    view7f0a0116 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dash_expense();
      }
    });
    view = Utils.findRequiredView(source, R.id.dash_stock, "field 'dash_stock' and method 'dash_stock'");
    target.dash_stock = Utils.castView(view, R.id.dash_stock, "field 'dash_stock'", RelativeLayout.class);
    view7f0a011c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dash_stock();
      }
    });
    view = Utils.findRequiredView(source, R.id.dash_dims, "field 'dash_dims' and method 'dash_dims'");
    target.dash_dims = Utils.castView(view, R.id.dash_dims, "field 'dash_dims'", RelativeLayout.class);
    view7f0a0115 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dash_dims();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dash_balance = null;
    target.dash_sale_amount = null;
    target.dash_purchase_amount = null;
    target.dash_expense_amount = null;
    target.dash_sales = null;
    target.dash_purchase = null;
    target.dash_expense = null;
    target.dash_stock = null;
    target.dash_dims = null;

    view7f0a011b.setOnClickListener(null);
    view7f0a011b = null;
    view7f0a0118.setOnClickListener(null);
    view7f0a0118 = null;
    view7f0a0116.setOnClickListener(null);
    view7f0a0116 = null;
    view7f0a011c.setOnClickListener(null);
    view7f0a011c = null;
    view7f0a0115.setOnClickListener(null);
    view7f0a0115 = null;
  }
}
