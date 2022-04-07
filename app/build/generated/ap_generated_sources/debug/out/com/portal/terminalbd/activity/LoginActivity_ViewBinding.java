// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f0a017f;

  private View view7f0a00c3;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.usereditTexterror = Utils.findRequiredViewAsType(source, R.id.textusererror, "field 'usereditTexterror'", TextInputLayout.class);
    target.usereditText = Utils.findRequiredViewAsType(source, R.id.edit_sign_in_username, "field 'usereditText'", TextInputEditText.class);
    target.passworderror = Utils.findRequiredViewAsType(source, R.id.passerror, "field 'passworderror'", TextInputLayout.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.edit_sign_in_password, "field 'password'", TextInputEditText.class);
    target.logo = Utils.findRequiredViewAsType(source, R.id.logo, "field 'logo'", TextView.class);
    view = Utils.findRequiredView(source, R.id.gotosignuppage, "method 'goToSigninPage'");
    view7f0a017f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToSigninPage();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_login, "method 'login'");
    view7f0a00c3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.usereditTexterror = null;
    target.usereditText = null;
    target.passworderror = null;
    target.password = null;
    target.logo = null;

    view7f0a017f.setOnClickListener(null);
    view7f0a017f = null;
    view7f0a00c3.setOnClickListener(null);
    view7f0a00c3 = null;
  }
}
