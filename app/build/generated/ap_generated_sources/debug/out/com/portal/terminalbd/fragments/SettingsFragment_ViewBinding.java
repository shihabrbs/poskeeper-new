// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingsFragment_ViewBinding implements Unbinder {
  private SettingsFragment target;

  private View view7f0a02e5;

  private View view7f0a02e6;

  private View view7f0a02e7;

  private View view7f0a02e9;

  private View view7f0a02ea;

  private View view7f0a02f1;

  private View view7f0a02eb;

  private View view7f0a02f2;

  private View view7f0a02f0;

  private View view7f0a02f3;

  private View view7f0a02ee;

  private View view7f0a02ed;

  private View view7f0a02e8;

  @UiThread
  public SettingsFragment_ViewBinding(final SettingsFragment target, View source) {
    this.target = target;

    View view;
    target.manual_sync_layout = Utils.findRequiredViewAsType(source, R.id.manual_sync_layout, "field 'manual_sync_layout'", LinearLayout.class);
    target.medicine_layout = Utils.findRequiredViewAsType(source, R.id.medicine_layout, "field 'medicine_layout'", LinearLayout.class);
    target.manual_sync = Utils.findRequiredViewAsType(source, R.id.manual_sync, "field 'manual_sync'", TextView.class);
    target.export_sync_layout = Utils.findRequiredViewAsType(source, R.id.export_sync_layout, "field 'export_sync_layout'", LinearLayout.class);
    target.export_sync = Utils.findRequiredViewAsType(source, R.id.export_sync, "field 'export_sync'", TextView.class);
    view = Utils.findRequiredView(source, R.id.sync_anonymous_customer, "field 'sync_anonymous_customer' and method 'onAnonymousCustomer'");
    target.sync_anonymous_customer = Utils.castView(view, R.id.sync_anonymous_customer, "field 'sync_anonymous_customer'", ImageView.class);
    view7f0a02e5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnonymousCustomer();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_bank_account, "field 'sync_bank_account' and method 'onsync_bank_account'");
    target.sync_bank_account = Utils.castView(view, R.id.sync_bank_account, "field 'sync_bank_account'", ImageView.class);
    view7f0a02e6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_bank_account();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_dims, "field 'sync_dims' and method 'onsync_dims'");
    target.sync_dims = Utils.castView(view, R.id.sync_dims, "field 'sync_dims'", ImageView.class);
    view7f0a02e7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_dims();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_expense_category, "field 'sync_expense_category' and method 'onsync_expense_category'");
    target.sync_expense_category = Utils.castView(view, R.id.sync_expense_category, "field 'sync_expense_category'", ImageView.class);
    view7f0a02e9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_expense_category();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_mobile_account, "field 'sync_mobile_account' and method 'onsync_mobile_account'");
    target.sync_mobile_account = Utils.castView(view, R.id.sync_mobile_account, "field 'sync_mobile_account'", ImageView.class);
    view7f0a02ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_mobile_account();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_system_users, "field 'sync_system_users' and method 'onsync_system_users'");
    target.sync_system_users = Utils.castView(view, R.id.sync_system_users, "field 'sync_system_users'", ImageView.class);
    view7f0a02f1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_system_users();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_product_category, "field 'sync_product_category' and method 'sync_product_category'");
    target.sync_product_category = Utils.castView(view, R.id.sync_product_category, "field 'sync_product_category'", ImageView.class);
    view7f0a02eb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sync_product_category();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_transaction_method, "field 'sync_transaction_method' and method 'onsync_transaction_method'");
    target.sync_transaction_method = Utils.castView(view, R.id.sync_transaction_method, "field 'sync_transaction_method'", ImageView.class);
    view7f0a02f2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_transaction_method();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_stock, "field 'sync_stock' and method 'onsync_stock'");
    target.sync_stock = Utils.castView(view, R.id.sync_stock, "field 'sync_stock'", ImageView.class);
    view7f0a02f0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_stock();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_vendor, "field 'sync_vendor' and method 'onsync_vendor'");
    target.sync_vendor = Utils.castView(view, R.id.sync_vendor, "field 'sync_vendor'", ImageView.class);
    view7f0a02f3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_vendor();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_sales, "field 'sync_sales' and method 'onsync_sales'");
    target.sync_sales = Utils.castView(view, R.id.sync_sales, "field 'sync_sales'", ImageView.class);
    view7f0a02ee = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_sales();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_purchase, "field 'sync_purchase' and method 'onsync_purchase'");
    target.sync_purchase = Utils.castView(view, R.id.sync_purchase, "field 'sync_purchase'", ImageView.class);
    view7f0a02ed = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_purchase();
      }
    });
    view = Utils.findRequiredView(source, R.id.sync_expense, "field 'sync_expense' and method 'onsync_expense'");
    target.sync_expense = Utils.castView(view, R.id.sync_expense, "field 'sync_expense'", ImageView.class);
    view7f0a02e8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onsync_expense();
      }
    });
    target.sales_mode_spinner = Utils.findRequiredViewAsType(source, R.id.sales_mode_spinner, "field 'sales_mode_spinner'", Spinner.class);
    target.printer_type_spinner = Utils.findRequiredViewAsType(source, R.id.printer_type_spinner, "field 'printer_type_spinner'", Spinner.class);
    target.camera_mode_spinner = Utils.findRequiredViewAsType(source, R.id.camera_mode_spinner, "field 'camera_mode_spinner'", Spinner.class);
    target.online_offline_mode = Utils.findRequiredViewAsType(source, R.id.online_offline_mode, "field 'online_offline_mode'", Switch.class);
    target.barcode_mode = Utils.findRequiredViewAsType(source, R.id.barcode_mode, "field 'barcode_mode'", Switch.class);
    target.discount_print_mode = Utils.findRequiredViewAsType(source, R.id.discount_print_mode, "field 'discount_print_mode'", Switch.class);
    target.auto_payment_mode = Utils.findRequiredViewAsType(source, R.id.auto_payment_mode, "field 'auto_payment_mode'", Switch.class);
    target.previous_due_mode = Utils.findRequiredViewAsType(source, R.id.previous_due_mode, "field 'previous_due_mode'", Switch.class);
    target.show_token_mode = Utils.findRequiredViewAsType(source, R.id.show_token_mode, "field 'show_token_mode'", Switch.class);
    target.salesModeText = Utils.findRequiredViewAsType(source, R.id.salesMode_text, "field 'salesModeText'", TextView.class);
    target.salesModeLayout = Utils.findRequiredViewAsType(source, R.id.salesMode_layout, "field 'salesModeLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.manual_sync_layout = null;
    target.medicine_layout = null;
    target.manual_sync = null;
    target.export_sync_layout = null;
    target.export_sync = null;
    target.sync_anonymous_customer = null;
    target.sync_bank_account = null;
    target.sync_dims = null;
    target.sync_expense_category = null;
    target.sync_mobile_account = null;
    target.sync_system_users = null;
    target.sync_product_category = null;
    target.sync_transaction_method = null;
    target.sync_stock = null;
    target.sync_vendor = null;
    target.sync_sales = null;
    target.sync_purchase = null;
    target.sync_expense = null;
    target.sales_mode_spinner = null;
    target.printer_type_spinner = null;
    target.camera_mode_spinner = null;
    target.online_offline_mode = null;
    target.barcode_mode = null;
    target.discount_print_mode = null;
    target.auto_payment_mode = null;
    target.previous_due_mode = null;
    target.show_token_mode = null;
    target.salesModeText = null;
    target.salesModeLayout = null;

    view7f0a02e5.setOnClickListener(null);
    view7f0a02e5 = null;
    view7f0a02e6.setOnClickListener(null);
    view7f0a02e6 = null;
    view7f0a02e7.setOnClickListener(null);
    view7f0a02e7 = null;
    view7f0a02e9.setOnClickListener(null);
    view7f0a02e9 = null;
    view7f0a02ea.setOnClickListener(null);
    view7f0a02ea = null;
    view7f0a02f1.setOnClickListener(null);
    view7f0a02f1 = null;
    view7f0a02eb.setOnClickListener(null);
    view7f0a02eb = null;
    view7f0a02f2.setOnClickListener(null);
    view7f0a02f2 = null;
    view7f0a02f0.setOnClickListener(null);
    view7f0a02f0 = null;
    view7f0a02f3.setOnClickListener(null);
    view7f0a02f3 = null;
    view7f0a02ee.setOnClickListener(null);
    view7f0a02ee = null;
    view7f0a02ed.setOnClickListener(null);
    view7f0a02ed = null;
    view7f0a02e8.setOnClickListener(null);
    view7f0a02e8 = null;
  }
}
