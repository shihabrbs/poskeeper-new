// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewPurchaseActivity_ViewBinding implements Unbinder {
  private NewPurchaseActivity target;

  private View view7f0a0284;

  private View view7f0a023d;

  private View view7f0a0299;

  private View view7f0a00a9;

  @UiThread
  public NewPurchaseActivity_ViewBinding(NewPurchaseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewPurchaseActivity_ViewBinding(final NewPurchaseActivity target, View source) {
    this.target = target;

    View view;
    target.menu_btn = Utils.findRequiredViewAsType(source, R.id.menubtn, "field 'menu_btn'", ImageView.class);
    target.cartItemRV = Utils.findRequiredViewAsType(source, R.id.cart_ItemRV, "field 'cartItemRV'", RecyclerView.class);
    target.paymentitemRv = Utils.findRequiredViewAsType(source, R.id.paymentitemRv, "field 'paymentitemRv'", RecyclerView.class);
    target.cartempty = Utils.findRequiredViewAsType(source, R.id.cartempty, "field 'cartempty'", TextView.class);
    target.closesearchbr = Utils.findRequiredViewAsType(source, R.id.closesearchbr, "field 'closesearchbr'", ImageView.class);
    target.addNewCustomer = Utils.findRequiredViewAsType(source, R.id.addNewCustomer, "field 'addNewCustomer'", ImageView.class);
    target.lineview = Utils.findRequiredView(source, R.id.lineview, "field 'lineview'");
    target.customer_layout = Utils.findRequiredViewAsType(source, R.id.customer_layout, "field 'customer_layout'", LinearLayout.class);
    target.payment_receive_spinner = Utils.findRequiredViewAsType(source, R.id.payment_receive_spinner, "field 'payment_receive_spinner'", Spinner.class);
    target.payment_card_bank_spinner = Utils.findRequiredViewAsType(source, R.id.payment_card_bank_spinner, "field 'payment_card_bank_spinner'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.sale_layout, "field 'sale_layout' and method 'sale_layout'");
    target.sale_layout = Utils.castView(view, R.id.sale_layout, "field 'sale_layout'", RelativeLayout.class);
    view7f0a0284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sale_layout();
      }
    });
    target.search_layout = Utils.findRequiredViewAsType(source, R.id.search_layout, "field 'search_layout'", LinearLayout.class);
    target.title_layout = Utils.findRequiredViewAsType(source, R.id.title_layout, "field 'title_layout'", LinearLayout.class);
    target.search_bar_Layout = Utils.findRequiredViewAsType(source, R.id.searchbarLayout, "field 'search_bar_Layout'", ConstraintLayout.class);
    target.searchEdittext = Utils.findRequiredViewAsType(source, R.id.searchedittext, "field 'searchEdittext'", EditText.class);
    target.editTextSearch = Utils.findRequiredViewAsType(source, R.id.editTextSearch, "field 'editTextSearch'", EditText.class);
    target.cartlayout = Utils.findRequiredViewAsType(source, R.id.cartlayout, "field 'cartlayout'", ConstraintLayout.class);
    target.clearbtn = Utils.findRequiredViewAsType(source, R.id.clearbtn, "field 'clearbtn'", ImageView.class);
    target.productqty = Utils.findRequiredViewAsType(source, R.id.productqty, "field 'productqty'", TextView.class);
    target.emptytext = Utils.findRequiredViewAsType(source, R.id.emptytext, "field 'emptytext'", TextView.class);
    target.recyclerViewSearchSuggestion = Utils.findRequiredViewAsType(source, R.id.recyclerViewSearchSuggestion, "field 'recyclerViewSearchSuggestion'", RecyclerView.class);
    target.searchSuggestionrecyclerView = Utils.findRequiredViewAsType(source, R.id.searchSuggestionrecyclerView, "field 'searchSuggestionrecyclerView'", RecyclerView.class);
    target.sale_return_tk = Utils.findRequiredViewAsType(source, R.id.sale_return_tk, "field 'sale_return_tk'", EditText.class);
    target.percent = Utils.findRequiredViewAsType(source, R.id.due_tk, "field 'percent'", TextView.class);
    target.sale_grand_total_tk = Utils.findRequiredViewAsType(source, R.id.sale_total_tk, "field 'sale_grand_total_tk'", TextView.class);
    target.receivedTk = Utils.findRequiredViewAsType(source, R.id.receivedTk, "field 'receivedTk'", EditText.class);
    target.list_show_btn = Utils.findRequiredViewAsType(source, R.id.list_show_btn, "field 'list_show_btn'", FloatingActionButton.class);
    target.bottom_sheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'bottom_sheet'", LinearLayout.class);
    target.paymentlayout = Utils.findRequiredViewAsType(source, R.id.linearLayout4, "field 'paymentlayout'", LinearLayout.class);
    target.search_Back = Utils.findRequiredViewAsType(source, R.id.searchback, "field 'search_Back'", ImageView.class);
    target.cartlayoutbg = Utils.findRequiredViewAsType(source, R.id.cart_layout, "field 'cartlayoutbg'", RelativeLayout.class);
    target.customerSpinner = Utils.findRequiredViewAsType(source, R.id.customerSpinner, "field 'customerSpinner'", Spinner.class);
    target.mobileBankLayout = Utils.findRequiredViewAsType(source, R.id.mobile_bank_layout, "field 'mobileBankLayout'", LinearLayout.class);
    target.mobilePaymentForm = Utils.findRequiredViewAsType(source, R.id.mobile_payment_form, "field 'mobilePaymentForm'", LinearLayout.class);
    target.bankPaynementLayout = Utils.findRequiredViewAsType(source, R.id.bank_paynement_layout, "field 'bankPaynementLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.pos_print_btn, "field 'posPrintBtn' and method 'onPurchase'");
    target.posPrintBtn = Utils.castView(view, R.id.pos_print_btn, "field 'posPrintBtn'", Button.class);
    view7f0a023d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPurchase();
      }
    });
    target.bankPaymentForm = Utils.findRequiredViewAsType(source, R.id.bank_payment_form, "field 'bankPaymentForm'", LinearLayout.class);
    target.cashPaymentLayout = Utils.findRequiredViewAsType(source, R.id.cash_payment_layout, "field 'cashPaymentLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.save_btn, "method 'save_btn'");
    view7f0a0299 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.save_btn();
      }
    });
    view = Utils.findRequiredView(source, R.id.backbtn, "method 'backbtn'");
    view7f0a00a9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.backbtn();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    NewPurchaseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.menu_btn = null;
    target.cartItemRV = null;
    target.paymentitemRv = null;
    target.cartempty = null;
    target.closesearchbr = null;
    target.addNewCustomer = null;
    target.lineview = null;
    target.customer_layout = null;
    target.payment_receive_spinner = null;
    target.payment_card_bank_spinner = null;
    target.sale_layout = null;
    target.search_layout = null;
    target.title_layout = null;
    target.search_bar_Layout = null;
    target.searchEdittext = null;
    target.editTextSearch = null;
    target.cartlayout = null;
    target.clearbtn = null;
    target.productqty = null;
    target.emptytext = null;
    target.recyclerViewSearchSuggestion = null;
    target.searchSuggestionrecyclerView = null;
    target.sale_return_tk = null;
    target.percent = null;
    target.sale_grand_total_tk = null;
    target.receivedTk = null;
    target.list_show_btn = null;
    target.bottom_sheet = null;
    target.paymentlayout = null;
    target.search_Back = null;
    target.cartlayoutbg = null;
    target.customerSpinner = null;
    target.mobileBankLayout = null;
    target.mobilePaymentForm = null;
    target.bankPaynementLayout = null;
    target.posPrintBtn = null;
    target.bankPaymentForm = null;
    target.cashPaymentLayout = null;

    view7f0a0284.setOnClickListener(null);
    view7f0a0284 = null;
    view7f0a023d.setOnClickListener(null);
    view7f0a023d = null;
    view7f0a0299.setOnClickListener(null);
    view7f0a0299 = null;
    view7f0a00a9.setOnClickListener(null);
    view7f0a00a9 = null;
  }
}
