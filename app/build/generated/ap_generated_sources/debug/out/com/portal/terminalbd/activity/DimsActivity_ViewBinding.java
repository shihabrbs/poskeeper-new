// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
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

public class DimsActivity_ViewBinding implements Unbinder {
  private DimsActivity target;

  private View view7f0a01db;

  private View view7f0a0381;

  private View view7f0a0184;

  @UiThread
  public DimsActivity_ViewBinding(DimsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DimsActivity_ViewBinding(final DimsActivity target, View source) {
    this.target = target;

    View view;
    target.dims_rv = Utils.findRequiredViewAsType(source, R.id.dims_rv, "field 'dims_rv'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.medicine, "field 'medicine' and method 'medicine'");
    target.medicine = Utils.castView(view, R.id.medicine, "field 'medicine'", TextView.class);
    view7f0a01db = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.medicine();
      }
    });
    view = Utils.findRequiredView(source, R.id.veternary, "field 'veternary' and method 'veternary'");
    target.veternary = Utils.castView(view, R.id.veternary, "field 'veternary'", TextView.class);
    view7f0a0381 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.veternary();
      }
    });
    view = Utils.findRequiredView(source, R.id.herbal, "field 'herbal' and method 'herbal'");
    target.herbal = Utils.castView(view, R.id.herbal, "field 'herbal'", TextView.class);
    view7f0a0184 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.herbal();
      }
    });
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.toolbar_title = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DimsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dims_rv = null;
    target.medicine = null;
    target.veternary = null;
    target.herbal = null;
    target.toolbar = null;
    target.toolbar_title = null;

    view7f0a01db.setOnClickListener(null);
    view7f0a01db = null;
    view7f0a0381.setOnClickListener(null);
    view7f0a0381 = null;
    view7f0a0184.setOnClickListener(null);
    view7f0a0184 = null;
  }
}
