// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.adapter;

import android.view.View;
import android.widget.CheckBox;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TokenAdapter$TokenViewHolder_ViewBinding implements Unbinder {
  private TokenAdapter.TokenViewHolder target;

  @UiThread
  public TokenAdapter$TokenViewHolder_ViewBinding(TokenAdapter.TokenViewHolder target,
      View source) {
    this.target = target;

    target.tokenBox = Utils.findRequiredViewAsType(source, R.id.token_box, "field 'tokenBox'", CheckBox.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TokenAdapter.TokenViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tokenBox = null;
  }
}
