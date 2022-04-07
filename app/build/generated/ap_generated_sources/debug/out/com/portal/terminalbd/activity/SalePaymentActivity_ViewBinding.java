// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.AutoCompleteTextView;
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
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SalePaymentActivity_ViewBinding implements Unbinder {
  private SalePaymentActivity target;

  private View view7f0a00d4;

  private View view7f0a00b3;

  private View view7f0a038a;

  private View view7f0a0299;

  private View view7f0a023d;

  @UiThread
  public SalePaymentActivity_ViewBinding(SalePaymentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SalePaymentActivity_ViewBinding(final SalePaymentActivity target, View source) {
    this.target = target;

    View view;
    target.saleItemsLayout = Utils.findRequiredViewAsType(source, R.id.saleItemsLayout, "field 'saleItemsLayout'", LinearLayout.class);
    target.showSaleItem = Utils.findRequiredViewAsType(source, R.id.showSaleItem, "field 'showSaleItem'", RelativeLayout.class);
    target.saleItemRv = Utils.findRequiredViewAsType(source, R.id.sales_history_rv, "field 'saleItemRv'", RecyclerView.class);
    target.sale_total_tk = Utils.findRequiredViewAsType(source, R.id.sale_total_tk, "field 'sale_total_tk'", TextView.class);
    target.sale_discount_tk = Utils.findRequiredViewAsType(source, R.id.sale_discount_tk, "field 'sale_discount_tk'", TextView.class);
    target.sale_vat_tk = Utils.findRequiredViewAsType(source, R.id.sale_vat_tk, "field 'sale_vat_tk'", TextView.class);
    target.sale_grand_total_tk = Utils.findRequiredViewAsType(source, R.id.sale_grand_total_tk, "field 'sale_grand_total_tk'", TextView.class);
    target.sale_total_profit = Utils.findRequiredViewAsType(source, R.id.sale_total_profit, "field 'sale_total_profit'", TextView.class);
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
    target.customCustomer_btn = Utils.findRequiredViewAsType(source, R.id.customCustomer_btn, "field 'customCustomer_btn'", ImageView.class);
    target.mobile_payment_form = Utils.findRequiredViewAsType(source, R.id.mobile_payment_form, "field 'mobile_payment_form'", LinearLayout.class);
    target.bank_payment_form = Utils.findRequiredViewAsType(source, R.id.bank_payment_form, "field 'bank_payment_form'", LinearLayout.class);
    target.customer_info_layout = Utils.findRequiredViewAsType(source, R.id.custom_customer_info_layout, "field 'customer_info_layout'", LinearLayout.class);
    target.discount_et = Utils.findRequiredViewAsType(source, R.id.discount_et, "field 'discount_et'", EditText.class);
    target.discount_switch = Utils.findRequiredViewAsType(source, R.id.discount_switch, "field 'discount_switch'", Switch.class);
    target.payment_mobile_no_et = Utils.findRequiredViewAsType(source, R.id.payment_mobile_no, "field 'payment_mobile_no_et'", EditText.class);
    target.payment_transaction_id = Utils.findRequiredViewAsType(source, R.id.payment_transaction_id, "field 'payment_transaction_id'", EditText.class);
    target.payment_receive_spinner = Utils.findRequiredViewAsType(source, R.id.payment_receive_spinner, "field 'payment_receive_spinner'", Spinner.class);
    target.payment_card_no_et = Utils.findRequiredViewAsType(source, R.id.payment_card_no, "field 'payment_card_no_et'", EditText.class);
    target.payment_card_spinner = Utils.findRequiredViewAsType(source, R.id.payment_card_spinner, "field 'payment_card_spinner'", Spinner.class);
    target.payment_card_bank_spinner = Utils.findRequiredViewAsType(source, R.id.payment_card_bank_spinner, "field 'payment_card_bank_spinner'", Spinner.class);
    target.sale_by_adapter = Utils.findRequiredViewAsType(source, R.id.sale_by_adapter, "field 'sale_by_adapter'", Spinner.class);
    target.customer_search = Utils.findRequiredViewAsType(source, R.id.customer_search, "field 'customer_search'", AutoCompleteTextView.class);
    target.returnText = Utils.findRequiredViewAsType(source, R.id.returnText, "field 'returnText'", TextView.class);
    target.sale_return_tk = Utils.findRequiredViewAsType(source, R.id.sale_return_tk, "field 'sale_return_tk'", TextView.class);
    target.receivedTk = Utils.findRequiredViewAsType(source, R.id.receivedTk, "field 'receivedTk'", EditText.class);
    target.customerName_et = Utils.findRequiredViewAsType(source, R.id.customerName_et, "field 'customerName_et'", EditText.class);
    target.customerMobile_et = Utils.findRequiredViewAsType(source, R.id.customerMobile_et, "field 'customerMobile_et'", EditText.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.print_status = Utils.findRequiredViewAsType(source, R.id.print_status, "field 'print_status'", TextView.class);
    target.scroll = Utils.findRequiredViewAsType(source, R.id.scroll, "field 'scroll'", NestedScrollView.class);
    target.floating_layout = Utils.findRequiredViewAsType(source, R.id.floating_layout, "field 'floating_layout'", CardView.class);
    target.floating_total_tk = Utils.findRequiredViewAsType(source, R.id.floating_total_tk, "field 'floating_total_tk'", TextView.class);
    view = Utils.findRequiredView(source, R.id.save_btn, "method 'save_btn'");
    view7f0a0299 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.save_btn();
      }
    });
    view = Utils.findRequiredView(source, R.id.pos_print_btn, "method 'onPosPrint'");
    view7f0a023d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPosPrint();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SalePaymentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.saleItemsLayout = null;
    target.showSaleItem = null;
    target.saleItemRv = null;
    target.sale_total_tk = null;
    target.sale_discount_tk = null;
    target.sale_vat_tk = null;
    target.sale_grand_total_tk = null;
    target.sale_total_profit = null;
    target.cashPayment = null;
    target.arrow_icon = null;
    target.bkashPayment = null;
    target.visaPayment = null;
    target.customCustomer_btn = null;
    target.mobile_payment_form = null;
    target.bank_payment_form = null;
    target.customer_info_layout = null;
    target.discount_et = null;
    target.discount_switch = null;
    target.payment_mobile_no_et = null;
    target.payment_transaction_id = null;
    target.payment_receive_spinner = null;
    target.payment_card_no_et = null;
    target.payment_card_spinner = null;
    target.payment_card_bank_spinner = null;
    target.sale_by_adapter = null;
    target.customer_search = null;
    target.returnText = null;
    target.sale_return_tk = null;
    target.receivedTk = null;
    target.customerName_et = null;
    target.customerMobile_et = null;
    target.toolbar = null;
    target.print_status = null;
    target.scroll = null;
    target.floating_layout = null;
    target.floating_total_tk = null;

    view7f0a00d4.setOnClickListener(null);
    view7f0a00d4 = null;
    view7f0a00b3.setOnClickListener(null);
    view7f0a00b3 = null;
    view7f0a038a.setOnClickListener(null);
    view7f0a038a = null;
    view7f0a0299.setOnClickListener(null);
    view7f0a0299 = null;
    view7f0a023d.setOnClickListener(null);
    view7f0a023d = null;
  }
}
