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

public class CategoryAdapter$CategoryViewHolder_ViewBinding implements Unbinder {
  private CategoryAdapter.CategoryViewHolder target;

  @UiThread
  public CategoryAdapter$CategoryViewHolder_ViewBinding(CategoryAdapter.CategoryViewHolder target,
      View source) {
    this.target = target;

    target.category_name = Utils.findRequiredViewAsType(source, R.id.category_name, "field 'category_name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoryAdapter.CategoryViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.category_name = null;
  }
}
