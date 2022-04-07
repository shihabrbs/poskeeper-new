// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
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
import com.portal.terminalbd.utils.ClearableAutoCompleteTextView;
import com.portal.terminalbd.utils.PrefixEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SalesActivity_ViewBinding implements Unbinder {
  private SalesActivity target;

  private View view7f0a0284;

  private View view7f0a00d7;

  private View view7f0a00af;

  private View view7f0a00b0;

  private View view7f0a01cf;

  private View view7f0a00a9;

  private View view7f0a0094;

  @UiThread
  public SalesActivity_ViewBinding(SalesActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SalesActivity_ViewBinding(final SalesActivity target, View source) {
    this.target = target;

    View view;
    target.search = Utils.findRequiredViewAsType(source, R.id.med_search, "field 'search'", ClearableAutoCompleteTextView.class);
    target.sales_mrp = Utils.findRequiredViewAsType(source, R.id.sales_mrp, "field 'sales_mrp'", EditText.class);
    target.menu_btn = Utils.findRequiredViewAsType(source, R.id.menubtn, "field 'menu_btn'", ImageView.class);
    target.sales_quantity = Utils.findRequiredViewAsType(source, R.id.sales_quantity, "field 'sales_quantity'", EditText.class);
    target.saleItemRv = Utils.findRequiredViewAsType(source, R.id.sales_history_rv, "field 'saleItemRv'", RecyclerView.class);
    target.cartItemRV = Utils.findRequiredViewAsType(source, R.id.cart_ItemRV, "field 'cartItemRV'", RecyclerView.class);
    target.cartempty = Utils.findRequiredViewAsType(source, R.id.cartempty, "field 'cartempty'", TextView.class);
    target.closesearchbr = Utils.findRequiredViewAsType(source, R.id.closesearchbr, "field 'closesearchbr'", ImageView.class);
    target.category_rv = Utils.findRequiredViewAsType(source, R.id.category_rv, "field 'category_rv'", RecyclerView.class);
    target.product_rv = Utils.findRequiredViewAsType(source, R.id.product_rv, "field 'product_rv'", RecyclerView.class);
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
    target.list_grid_layout = Utils.findRequiredViewAsType(source, R.id.list_grid_layout, "field 'list_grid_layout'", LinearLayout.class);
    target.title_layout = Utils.findRequiredViewAsType(source, R.id.title_layout, "field 'title_layout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.category_all, "field 'category_all' and method 'onAllCategory'");
    target.category_all = Utils.castView(view, R.id.category_all, "field 'category_all'", TextView.class);
    view7f0a00d7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAllCategory();
      }
    });
    view = Utils.findRequiredView(source, R.id.barcode_scan, "field 'barcode_scan' and method 'onBarcodeScan'");
    target.barcode_scan = Utils.castView(view, R.id.barcode_scan, "field 'barcode_scan'", Button.class);
    view7f0a00af = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBarcodeScan();
      }
    });
    view = Utils.findRequiredView(source, R.id.barcode_scan_list_grid, "field 'barcode_scan_list_grid' and method 'onBarcodeScanList'");
    target.barcode_scan_list_grid = Utils.castView(view, R.id.barcode_scan_list_grid, "field 'barcode_scan_list_grid'", Button.class);
    view7f0a00b0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBarcodeScanList();
      }
    });
    target.search_bar_Layout = Utils.findRequiredViewAsType(source, R.id.searchbarLayout, "field 'search_bar_Layout'", ConstraintLayout.class);
    target.itemtitlelayout = Utils.findRequiredViewAsType(source, R.id.itemtitlelayout, "field 'itemtitlelayout'", LinearLayout.class);
    target.searchEdittext = Utils.findRequiredViewAsType(source, R.id.searchedittext, "field 'searchEdittext'", EditText.class);
    target.editTextSearch = Utils.findRequiredViewAsType(source, R.id.editTextSearch, "field 'editTextSearch'", EditText.class);
    target.cartlayout = Utils.findRequiredViewAsType(source, R.id.cartlayout, "field 'cartlayout'", ConstraintLayout.class);
    target.clearbtn = Utils.findRequiredViewAsType(source, R.id.clearbtn, "field 'clearbtn'", ImageView.class);
    target.productqty = Utils.findRequiredViewAsType(source, R.id.productqty, "field 'productqty'", TextView.class);
    target.recyclerViewSearchSuggestion = Utils.findRequiredViewAsType(source, R.id.recyclerViewSearchSuggestion, "field 'recyclerViewSearchSuggestion'", RecyclerView.class);
    target.searchSuggestionrecyclerView = Utils.findRequiredViewAsType(source, R.id.searchSuggestionrecyclerView, "field 'searchSuggestionrecyclerView'", RecyclerView.class);
    target.sale_return_tk = Utils.findRequiredViewAsType(source, R.id.sale_return_tk, "field 'sale_return_tk'", TextView.class);
    target.discount_et = Utils.findRequiredViewAsType(source, R.id.discount_et, "field 'discount_et'", PrefixEditText.class);
    target.discount_switch = Utils.findRequiredViewAsType(source, R.id.discount_switch, "field 'discount_switch'", Switch.class);
    target.sale_vat_tk = Utils.findRequiredViewAsType(source, R.id.sale_vat_tk, "field 'sale_vat_tk'", TextView.class);
    target.sale_discount_tk = Utils.findRequiredViewAsType(source, R.id.sale_discount_tk, "field 'sale_discount_tk'", TextView.class);
    target.sale_grand_total_tk = Utils.findRequiredViewAsType(source, R.id.sale_total_tk, "field 'sale_grand_total_tk'", TextView.class);
    target.receivedTk = Utils.findRequiredViewAsType(source, R.id.receivedTk, "field 'receivedTk'", EditText.class);
    target.return_due_text = Utils.findRequiredViewAsType(source, R.id.return_due_text, "field 'return_due_text'", TextView.class);
    view = Utils.findRequiredView(source, R.id.list_show_btn, "field 'list_show_btn' and method 'onShowBtn'");
    target.list_show_btn = Utils.castView(view, R.id.list_show_btn, "field 'list_show_btn'", FloatingActionButton.class);
    view7f0a01cf = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onShowBtn();
      }
    });
    target.bottom_sheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'bottom_sheet'", LinearLayout.class);
    target.list_grid_search = Utils.findRequiredViewAsType(source, R.id.list_grid_search, "field 'list_grid_search'", EditText.class);
    target.search_Back = Utils.findRequiredViewAsType(source, R.id.searchback, "field 'search_Back'", ImageView.class);
    target.searchOpentv = Utils.findRequiredViewAsType(source, R.id.searchopentv, "field 'searchOpentv'", TextView.class);
    target.cartlayoutbg = Utils.findRequiredViewAsType(source, R.id.cart_layout, "field 'cartlayoutbg'", ConstraintLayout.class);
    target.printMessageSpinner = Utils.findRequiredViewAsType(source, R.id.printMessage, "field 'printMessageSpinner'", Spinner.class);
    target.mobileBankLayout = Utils.findRequiredViewAsType(source, R.id.mobile_bank_layout, "field 'mobileBankLayout'", LinearLayout.class);
    target.mobilePaymentForm = Utils.findRequiredViewAsType(source, R.id.mobile_payment_form, "field 'mobilePaymentForm'", LinearLayout.class);
    target.bankPaynementLayout = Utils.findRequiredViewAsType(source, R.id.bank_paynement_layout, "field 'bankPaynementLayout'", LinearLayout.class);
    target.bankPaymentForm = Utils.findRequiredViewAsType(source, R.id.bank_payment_form, "field 'bankPaymentForm'", LinearLayout.class);
    target.cashPaymentLayout = Utils.findRequiredViewAsType(source, R.id.cash_payment_layout, "field 'cashPaymentLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.backbtn, "method 'backbtn'");
    view7f0a00a9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.backbtn();
      }
    });
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
    SalesActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.search = null;
    target.sales_mrp = null;
    target.menu_btn = null;
    target.sales_quantity = null;
    target.saleItemRv = null;
    target.cartItemRV = null;
    target.cartempty = null;
    target.closesearchbr = null;
    target.category_rv = null;
    target.product_rv = null;
    target.sale_layout = null;
    target.search_layout = null;
    target.list_grid_layout = null;
    target.title_layout = null;
    target.category_all = null;
    target.barcode_scan = null;
    target.barcode_scan_list_grid = null;
    target.search_bar_Layout = null;
    target.itemtitlelayout = null;
    target.searchEdittext = null;
    target.editTextSearch = null;
    target.cartlayout = null;
    target.clearbtn = null;
    target.productqty = null;
    target.recyclerViewSearchSuggestion = null;
    target.searchSuggestionrecyclerView = null;
    target.sale_return_tk = null;
    target.discount_et = null;
    target.discount_switch = null;
    target.sale_vat_tk = null;
    target.sale_discount_tk = null;
    target.sale_grand_total_tk = null;
    target.receivedTk = null;
    target.return_due_text = null;
    target.list_show_btn = null;
    target.bottom_sheet = null;
    target.list_grid_search = null;
    target.search_Back = null;
    target.searchOpentv = null;
    target.cartlayoutbg = null;
    target.printMessageSpinner = null;
    target.mobileBankLayout = null;
    target.mobilePaymentForm = null;
    target.bankPaynementLayout = null;
    target.bankPaymentForm = null;
    target.cashPaymentLayout = null;

    view7f0a0284.setOnClickListener(null);
    view7f0a0284 = null;
    view7f0a00d7.setOnClickListener(null);
    view7f0a00d7 = null;
    view7f0a00af.setOnClickListener(null);
    view7f0a00af = null;
    view7f0a00b0.setOnClickListener(null);
    view7f0a00b0 = null;
    view7f0a01cf.setOnClickListener(null);
    view7f0a01cf = null;
    view7f0a00a9.setOnClickListener(null);
    view7f0a00a9 = null;
    view7f0a0094.setOnClickListener(null);
    view7f0a0094 = null;
  }
}
