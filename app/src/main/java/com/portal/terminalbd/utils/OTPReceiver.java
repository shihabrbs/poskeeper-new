package com.portal.terminalbd.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

public class OTPReceiver extends BroadcastReceiver {
    private static EditText editText_otp;

    public void  setEditText_otp(EditText editText){
        OTPReceiver.editText_otp = editText;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsManagers = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for(SmsMessage smsManager : smsManagers){
            String message_body = smsManager.getMessageBody();
            String getOTP = message_body.split("- ")[1];
            editText_otp.setText(getOTP);
        }
    }
}
