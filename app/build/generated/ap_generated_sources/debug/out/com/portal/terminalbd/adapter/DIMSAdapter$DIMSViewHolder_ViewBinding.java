// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DIMSAdapter$DIMSViewHolder_ViewBinding implements Unbinder {
  private DIMSAdapter.DIMSViewHolder target;

  @UiThread
  public DIMSAdapter$DIMSViewHolder_ViewBinding(DIMSAdapter.DIMSViewHolder target, View source) {
    this.target = target;

    target.dims_medicine_name = Utils.findRequiredViewAsType(source, R.id.dims_medicine_name, "field 'dims_medicine_name'", TextView.class);
    target.dims_medicine_form = Utils.findRequiredViewAsType(source, R.id.dims_medicine_form, "field 'dims_medicine_form'", TextView.class);
    target.dims_medicine_generic_name = Utils.findRequiredViewAsType(source, R.id.dims_medicine_generic_name, "field 'dims_medicine_generic_name'", TextView.class);
    target.dims_medicine_brand_name = Utils.findRequiredViewAsType(source, R.id.dims_medicine_brand_name, "field 'dims_medicine_brand_name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DIMSAdapter.DIMSViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dims_medicine_name = null;
    target.dims_medicine_form = null;
    target.dims_medicine_generic_name = null;
    target.dims_medicine_brand_name = null;
  }
}
