// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetupActivity_ViewBinding implements Unbinder {
  private SetupActivity target;

  private View view7f0a00c5;

  private View view7f0a0102;

  @UiThread
  public SetupActivity_ViewBinding(SetupActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetupActivity_ViewBinding(final SetupActivity target, View source) {
    this.target = target;

    View view;
    target.mobile = Utils.findRequiredViewAsType(source, R.id.edit_setup_mobile, "field 'mobile'", TextInputEditText.class);
    target.uniqueKey = Utils.findRequiredViewAsType(source, R.id.edit_setup_unique_key, "field 'uniqueKey'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.button_setup, "field 'mybtn' and method 'setup'");
    target.mybtn = Utils.castView(view, R.id.button_setup, "field 'mybtn'", Button.class);
    view7f0a00c5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setup();
      }
    });
    target.tvLanguage = Utils.findRequiredViewAsType(source, R.id.tv_languagee, "field 'tvLanguage'", TextView.class);
    view = Utils.findRequiredView(source, R.id.createnewbusiness, "method 'creatBusiness'");
    view7f0a0102 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.creatBusiness();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SetupActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mobile = null;
    target.uniqueKey = null;
    target.mybtn = null;
    target.tvLanguage = null;

    view7f0a00c5.setOnClickListener(null);
    view7f0a00c5 = null;
    view7f0a0102.setOnClickListener(null);
    view7f0a0102 = null;
  }
}
