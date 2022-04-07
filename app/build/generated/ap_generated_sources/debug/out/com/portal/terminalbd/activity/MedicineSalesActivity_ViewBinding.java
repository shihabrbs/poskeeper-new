// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import com.portal.terminalbd.utils.ClearableAutoCompleteTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MedicineSalesActivity_ViewBinding implements Unbinder {
  private MedicineSalesActivity target;

  private View view7f0a024d;

  private View view7f0a00af;

  private View view7f0a00b0;

  private View view7f0a00a9;

  @UiThread
  public MedicineSalesActivity_ViewBinding(MedicineSalesActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MedicineSalesActivity_ViewBinding(final MedicineSalesActivity target, View source) {
    this.target = target;

    View view;
    target.search = Utils.findRequiredViewAsType(source, R.id.med_search, "field 'search'", ClearableAutoCompleteTextView.class);
    target.menu_btn = Utils.findRequiredViewAsType(source, R.id.menubtn, "field 'menu_btn'", ImageView.class);
    target.saleItemRv = Utils.findRequiredViewAsType(source, R.id.sales_history_rv, "field 'saleItemRv'", RecyclerView.class);
    target.cartItemRV = Utils.findRequiredViewAsType(source, R.id.cart_ItemRV, "field 'cartItemRV'", RecyclerView.class);
    target.cartempty = Utils.findRequiredViewAsType(source, R.id.cartempty, "field 'cartempty'", TextView.class);
    target.closesearchbr = Utils.findRequiredViewAsType(source, R.id.closesearchbr, "field 'closesearchbr'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.productqty, "field 'productqty' and method 'productqty'");
    target.productqty = Utils.castView(view, R.id.productqty, "field 'productqty'", TextView.class);
    view7f0a024d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.productqty();
      }
    });
    target.category_rv = Utils.findRequiredViewAsType(source, R.id.category_rv, "field 'category_rv'", RecyclerView.class);
    target.product_rv = Utils.findRequiredViewAsType(source, R.id.product_rv, "field 'product_rv'", RecyclerView.class);
    target.token_no_rv = Utils.findRequiredViewAsType(source, R.id.token_no_rv, "field 'token_no_rv'", RecyclerView.class);
    target.total_item_count = Utils.findRequiredViewAsType(source, R.id.total_item_count, "field 'total_item_count'", TextView.class);
    target.grand_total_text = Utils.findRequiredViewAsType(source, R.id.grand_total_text, "field 'grand_total_text'", TextView.class);
    target.sale_layout = Utils.findRequiredViewAsType(source, R.id.sale_layout, "field 'sale_layout'", RelativeLayout.class);
    target.search_layout = Utils.findRequiredViewAsType(source, R.id.search_layout, "field 'search_layout'", LinearLayout.class);
    target.list_grid_layout = Utils.findRequiredViewAsType(source, R.id.list_grid_layout, "field 'list_grid_layout'", LinearLayout.class);
    target.title_layout = Utils.findRequiredViewAsType(source, R.id.title_layout, "field 'title_layout'", LinearLayout.class);
    target.category_all = Utils.findRequiredViewAsType(source, R.id.category_all, "field 'category_all'", TextView.class);
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
    target.editTextSearch = Utils.findRequiredViewAsType(source, R.id.editTextSearch, "field 'editTextSearch'", EditText.class);
    target.cartlayout = Utils.findRequiredViewAsType(source, R.id.cartlayout, "field 'cartlayout'", ConstraintLayout.class);
    target.searchSuggestionrecyclerView = Utils.findRequiredViewAsType(source, R.id.searchSuggestionrecyclerView, "field 'searchSuggestionrecyclerView'", RecyclerView.class);
    target.list_grid_search = Utils.findRequiredViewAsType(source, R.id.list_grid_search, "field 'list_grid_search'", EditText.class);
    target.tokenLayout = Utils.findRequiredViewAsType(source, R.id.tokenLayout, "field 'tokenLayout'", LinearLayout.class);
    target.searchOpentv = Utils.findRequiredViewAsType(source, R.id.searchopentv, "field 'searchOpentv'", TextView.class);
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
    MedicineSalesActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.search = null;
    target.menu_btn = null;
    target.saleItemRv = null;
    target.cartItemRV = null;
    target.cartempty = null;
    target.closesearchbr = null;
    target.productqty = null;
    target.category_rv = null;
    target.product_rv = null;
    target.token_no_rv = null;
    target.total_item_count = null;
    target.grand_total_text = null;
    target.sale_layout = null;
    target.search_layout = null;
    target.list_grid_layout = null;
    target.title_layout = null;
    target.category_all = null;
    target.barcode_scan = null;
    target.barcode_scan_list_grid = null;
    target.editTextSearch = null;
    target.cartlayout = null;
    target.searchSuggestionrecyclerView = null;
    target.list_grid_search = null;
    target.tokenLayout = null;
    target.searchOpentv = null;

    view7f0a024d.setOnClickListener(null);
    view7f0a024d = null;
    view7f0a00af.setOnClickListener(null);
    view7f0a00af = null;
    view7f0a00b0.setOnClickListener(null);
    view7f0a00b0 = null;
    view7f0a00a9.setOnClickListener(null);
    view7f0a00a9 = null;
  }
}
