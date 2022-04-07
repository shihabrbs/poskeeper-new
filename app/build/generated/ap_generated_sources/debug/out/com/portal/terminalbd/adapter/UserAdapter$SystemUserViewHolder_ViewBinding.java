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

public class UserAdapter$SystemUserViewHolder_ViewBinding implements Unbinder {
  private UserAdapter.SystemUserViewHolder target;

  @UiThread
  public UserAdapter$SystemUserViewHolder_ViewBinding(UserAdapter.SystemUserViewHolder target,
      View source) {
    this.target = target;

    target.customerName = Utils.findRequiredViewAsType(source, R.id.customerName, "field 'customerName'", TextView.class);
    target.customerMobile = Utils.findRequiredViewAsType(source, R.id.customerMobile, "field 'customerMobile'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserAdapter.SystemUserViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.customerName = null;
    target.customerMobile = null;
  }
}
