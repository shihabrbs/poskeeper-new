// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExpenseDetailsActivity_ViewBinding implements Unbinder {
  private ExpenseDetailsActivity target;

  private View view7f0a0146;

  private View view7f0a0126;

  @UiThread
  public ExpenseDetailsActivity_ViewBinding(ExpenseDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExpenseDetailsActivity_ViewBinding(final ExpenseDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.username = Utils.findRequiredViewAsType(source, R.id.username, "field 'username'", TextView.class);
    target.remark = Utils.findRequiredViewAsType(source, R.id.remark, "field 'remark'", TextView.class);
    target.category_name = Utils.findRequiredViewAsType(source, R.id.category_name, "field 'category_name'", TextView.class);
    target.account_no = Utils.findRequiredViewAsType(source, R.id.account_no, "field 'account_no'", TextView.class);
    target.invoice_id = Utils.findRequiredViewAsType(source, R.id.invoice_id, "field 'invoice_id'", TextView.class);
    target.created = Utils.findRequiredViewAsType(source, R.id.created, "field 'created'", TextView.class);
    target.totalBdt = Utils.findRequiredViewAsType(source, R.id.totalBdt, "field 'totalBdt'", TextView.class);
    target.payment_method = Utils.findRequiredViewAsType(source, R.id.payment_method, "field 'payment_method'", TextView.class);
    target.sale_total_tk = Utils.findRequiredViewAsType(source, R.id.sale_total_tk, "field 'sale_total_tk'", TextView.class);
    view = Utils.findRequiredView(source, R.id.edit_btn, "field 'edit_btn' and method 'onEditBtn'");
    target.edit_btn = Utils.castView(view, R.id.edit_btn, "field 'edit_btn'", Button.class);
    view7f0a0146 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEditBtn();
      }
    });
    view = Utils.findRequiredView(source, R.id.delete_btn, "field 'delete_btn' and method 'onDeleteBtn'");
    target.delete_btn = Utils.castView(view, R.id.delete_btn, "field 'delete_btn'", Button.class);
    view7f0a0126 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDeleteBtn();
      }
    });
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.toolbar_title = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpenseDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.username = null;
    target.remark = null;
    target.category_name = null;
    target.account_no = null;
    target.invoice_id = null;
    target.created = null;
    target.totalBdt = null;
    target.payment_method = null;
    target.sale_total_tk = null;
    target.edit_btn = null;
    target.delete_btn = null;
    target.toolbar = null;
    target.toolbar_title = null;

    view7f0a0146.setOnClickListener(null);
    view7f0a0146 = null;
    view7f0a0126.setOnClickListener(null);
    view7f0a0126 = null;
  }
}
