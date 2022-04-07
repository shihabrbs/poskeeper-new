// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VerifyOTPActivity_ViewBinding implements Unbinder {
  private VerifyOTPActivity target;

  private View view7f0a0271;

  private View view7f0a0380;

  @UiThread
  public VerifyOTPActivity_ViewBinding(VerifyOTPActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VerifyOTPActivity_ViewBinding(final VerifyOTPActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.resendBtn, "field 'resendBtn' and method 'onResendBtnClicked'");
    target.resendBtn = Utils.castView(view, R.id.resendBtn, "field 'resendBtn'", Button.class);
    view7f0a0271 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onResendBtnClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.verifyBtn, "field 'verifyBtn' and method 'onVerifyBtnClicked'");
    target.verifyBtn = Utils.castView(view, R.id.verifyBtn, "field 'verifyBtn'", Button.class);
    view7f0a0380 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onVerifyBtnClicked();
      }
    });
    target.edtOtp = Utils.findRequiredViewAsType(source, R.id.edtOtp, "field 'edtOtp'", EditText.class);
    target.registerCard = Utils.findRequiredViewAsType(source, R.id.registerCard, "field 'registerCard'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VerifyOTPActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.resendBtn = null;
    target.verifyBtn = null;
    target.edtOtp = null;
    target.registerCard = null;

    view7f0a0271.setOnClickListener(null);
    view7f0a0271 = null;
    view7f0a0380.setOnClickListener(null);
    view7f0a0380 = null;
  }
}
