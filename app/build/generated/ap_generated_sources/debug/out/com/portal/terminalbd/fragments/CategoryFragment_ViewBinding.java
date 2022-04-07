// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.fragments;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoryFragment_ViewBinding implements Unbinder {
  private CategoryFragment target;

  @UiThread
  public CategoryFragment_ViewBinding(CategoryFragment target, View source) {
    this.target = target;

    target.p_category_rv = Utils.findRequiredViewAsType(source, R.id.p_category_rv, "field 'p_category_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoryFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.p_category_rv = null;
  }
}
