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

public class UserFragment_ViewBinding implements Unbinder {
  private UserFragment target;

  @UiThread
  public UserFragment_ViewBinding(UserFragment target, View source) {
    this.target = target;

    target.user_rv = Utils.findRequiredViewAsType(source, R.id.user_rv, "field 'user_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.user_rv = null;
  }
}
