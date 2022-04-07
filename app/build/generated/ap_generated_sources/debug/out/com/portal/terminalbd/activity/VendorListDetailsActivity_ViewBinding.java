// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VendorListDetailsActivity_ViewBinding implements Unbinder {
  private VendorListDetailsActivity target;

  @UiThread
  public VendorListDetailsActivity_ViewBinding(VendorListDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VendorListDetailsActivity_ViewBinding(VendorListDetailsActivity target, View source) {
    this.target = target;

    target.filterbtn = Utils.findRequiredViewAsType(source, R.id.filterbtn, "field 'filterbtn'", ImageView.class);
    target.filter_layout = Utils.findRequiredViewAsType(source, R.id.filter_layout, "field 'filter_layout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VendorListDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.filterbtn = null;
    target.filter_layout = null;
  }
}
