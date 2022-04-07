// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PurchasePaymentActivity_ViewBinding implements Unbinder {
  private PurchasePaymentActivity target;

  private View view7f0a00e8;

  private View view7f0a00d4;

  private View view7f0a00b3;

  private View view7f0a038a;

  private View view7f0a0299;

  @UiThread
  public PurchasePaymentActivity_ViewBinding(PurchasePaymentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PurchasePaymentActivity_ViewBinding(final PurchasePaymentActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.choose_date, "field 'choose_date' and method 'choose_date'");
    target.choose_date = Utils.castView(view, R.id.choose_date, "field 'choose_date'", LinearLayout.class);
    view7f0a00e8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.choose_date();
      }
    });
    target.date = Utils.findRequiredViewAsType(source, R.id.date, "field 'date'", TextView.class);
    target.saleItemsLayout = Utils.findRequiredViewAsType(source, R.id.saleItemsLayout, "field 'saleItemsLayout'", LinearLayout.class);
    target.showSaleItem = Utils.findRequiredViewAsType(source, R.id.showSaleItem, "field 'showSaleItem'", RelativeLayout.class);
    target.purchase_history_rv = Utils.findRequiredViewAsType(source, R.id.purchase_history_rv, "field 'purchase_history_rv'", RecyclerView.class);
    target.sale_total_tk = Utils.findRequiredViewAsType(source, R.id.sale_total_tk, "field 'sale_total_tk'", TextView.class);
    target.sale_discount_tk = Utils.findRequiredViewAsType(source, R.id.sale_discount_tk, "field 'sale_discount_tk'", TextView.class);
    target.sale_grand_total_tk = Utils.findRequiredViewAsType(source, R.id.sale_grand_total_tk, "field 'sale_grand_total_tk'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cashPayment, "field 'cashPayment' and method 'cashPayment'");
    target.cashPayment = Utils.castView(view, R.id.cashPayment, "field 'cashPayment'", TextView.class);
    view7f0a00d4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cashPayment();
      }
    });
    target.arrow_icon = Utils.findRequiredViewAsType(source, R.id.arrow_icon, "field 'arrow_icon'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.bkashPayment, "field 'bkashPayment' and method 'bkashPayment'");
    target.bkashPayment = Utils.castView(view, R.id.bkashPayment, "field 'bkashPayment'", TextView.class);
    view7f0a00b3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.bkashPayment();
      }
    });
    view = Utils.findRequiredView(source, R.id.visaPayment, "field 'visaPayment' and method 'visaPayment'");
    target.visaPayment = Utils.castView(view, R.id.visaPayment, "field 'visaPayment'", TextView.class);
    view7f0a038a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.visaPayment();
      }
    });
    target.mobile_payment_form = Utils.findRequiredViewAsType(source, R.id.mobile_payment_form, "field 'mobile_payment_form'", LinearLayout.class);
    target.bank_payment_form = Utils.findRequiredViewAsType(source, R.id.bank_payment_form, "field 'bank_payment_form'", LinearLayout.class);
    target.commision_mode_spinner = Utils.findRequiredViewAsType(source, R.id.commision_mode_spinner, "field 'commision_mode_spinner'", Spinner.class);
    target.vendor_spinner = Utils.findRequiredViewAsType(source, R.id.vendor_spinner, "field 'vendor_spinner'", Spinner.class);
    target.discount_et = Utils.findRequiredViewAsType(source, R.id.discount_et, "field 'discount_et'", EditText.class);
    target.discount_switch = Utils.findRequiredViewAsType(source, R.id.discount_switch, "field 'discount_switch'", Switch.class);
    target.payment_receive_spinner = Utils.findRequiredViewAsType(source, R.id.payment_receive_spinner, "field 'payment_receive_spinner'", Spinner.class);
    target.payment_card_bank_spinner = Utils.findRequiredViewAsType(source, R.id.payment_card_bank_spinner, "field 'payment_card_bank_spinner'", Spinner.class);
    target.returnText = Utils.findRequiredViewAsType(source, R.id.returnText, "field 'returnText'", TextView.class);
    target.sale_return_tk = Utils.findRequiredViewAsType(source, R.id.sale_return_tk, "field 'sale_return_tk'", TextView.class);
    target.receivedTk = Utils.findRequiredViewAsType(source, R.id.receivedTk, "field 'receivedTk'", EditText.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.toolbar_title = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbar_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.save_btn, "method 'onSaveBtn'");
    view7f0a0299 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSaveBtn();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PurchasePaymentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.choose_date = null;
    target.date = null;
    target.saleItemsLayout = null;
    target.showSaleItem = null;
    target.purchase_history_rv = null;
    target.sale_total_tk = null;
    target.sale_discount_tk = null;
    target.sale_grand_total_tk = null;
    target.cashPayment = null;
    target.arrow_icon = null;
    target.bkashPayment = null;
    target.visaPayment = null;
    target.mobile_payment_form = null;
    target.bank_payment_form = null;
    target.commision_mode_spinner = null;
    target.vendor_spinner = null;
    target.discount_et = null;
    target.discount_switch = null;
    target.payment_receive_spinner = null;
    target.payment_card_bank_spinner = null;
    target.returnText = null;
    target.sale_return_tk = null;
    target.receivedTk = null;
    target.toolbar = null;
    target.toolbar_title = null;

    view7f0a00e8.setOnClickListener(null);
    view7f0a00e8 = null;
    view7f0a00d4.setOnClickListener(null);
    view7f0a00d4 = null;
    view7f0a00b3.setOnClickListener(null);
    view7f0a00b3 = null;
    view7f0a038a.setOnClickListener(null);
    view7f0a038a = null;
    view7f0a0299.setOnClickListener(null);
    view7f0a0299 = null;
  }
}
