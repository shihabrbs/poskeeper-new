// Generated code from Butter Knife. Do not modify!
package com.portal.terminalbd.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;
import com.portal.terminalbd.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignupActivity_ViewBinding implements Unbinder {
  private SignupActivity target;

  @UiThread
  public SignupActivity_ViewBinding(SignupActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignupActivity_ViewBinding(SignupActivity target, View source) {
    this.target = target;

    target.conditioncheckBox = Utils.findRequiredViewAsType(source, R.id.checkBox, "field 'conditioncheckBox'", CheckBox.class);
    target.mybtn = Utils.findRequiredViewAsType(source, R.id.textView3, "field 'mybtn'", TextView.class);
    target.shopname = Utils.findRequiredViewAsType(source, R.id.shopnameid, "field 'shopname'", TextInputEditText.class);
    target.phonenumber = Utils.findRequiredViewAsType(source, R.id.phonenumberid, "field 'phonenumber'", TextInputEditText.class);
    target.emaill = Utils.findRequiredViewAsType(source, R.id.emailnoid, "field 'emaill'", TextInputEditText.class);
    target.username = Utils.findRequiredViewAsType(source, R.id.usernameid, "field 'username'", TextInputEditText.class);
    target.rg = Utils.findRequiredViewAsType(source, R.id.selectusertype, "field 'rg'", RadioGroup.class);
    target.ccp = Utils.findRequiredViewAsType(source, R.id.ccp, "field 'ccp'", CountryCodePicker.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignupActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.conditioncheckBox = null;
    target.mybtn = null;
    target.shopname = null;
    target.phonenumber = null;
    target.emaill = null;
    target.username = null;
    target.rg = null;
    target.ccp = null;
  }
}
