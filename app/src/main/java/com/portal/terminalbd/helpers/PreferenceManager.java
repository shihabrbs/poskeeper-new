package com.portal.terminalbd.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private static final String HAS_SETUP = "has_setup";
    private static final String SECRET_KEY = "secret_key";
    private static final String DEVICE_ID = "device_id";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String CURRENCY = "currency";
    private static final String HAS_LOGIN = "has_login";
    private static final String SALES_MODE = "sales_mode";
    private static final String MODULE = "module";
    private static final String ROLES = "roles";
    private static final String language = "bn";
    private static final String ONLINE_OFFLINE = "online_offline";
    private static final String BARCODE = "barcode";
    private static final String PRINTER_TYPE = "printer_type";
    private static final String CAMERA_TYPE = "camera_type";
    private static SharedPreferences mSharedPreferences;
    private static final String KEY_USER_PREF = "KEY_USER_PREFERENCES";
    private static final String DISCOUNT = "discount";
    private static final String AUTOPAYMENT = "autopayment";
    private static final String PREVIOUSDUE = "previousdue";
    private static final String SHOWTOKEN = "showtoken";
    private static final String PAIRED_DEVICE = "last_paired_device";
    private static final String PRINTER_CONNECTION = "printer_connection";

    public static boolean setHasBackup(Context mContext, boolean hasBackup) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(HAS_SETUP, hasBackup);
        return editor.commit();
    }


    public static boolean isHasBackup(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(HAS_SETUP, false);
    }

    public static boolean setSecretKey(Context mContext, String secretKey) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SECRET_KEY, secretKey);
        return editor.commit();
    }

    public static String getSecretKey(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(SECRET_KEY, null);
    }

    public static boolean setDeviceId(Context mContext, String deviceId) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(DEVICE_ID, deviceId);
        return editor.commit();
    }

    public static String getDeviceId(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(DEVICE_ID, null);
    }

    public static boolean setUserId(Context mContext, String userId) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_ID, userId);
        return editor.commit();
    }

    public static String getUserId(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(USER_ID, null);
    }

    public static boolean setUserName(Context mContext, String userName) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_NAME, userName);
        return editor.commit();
    }

    public static String getCurrency(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(CURRENCY, "$");
    }

    public static boolean setCurrency(Context mContext, String currency) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(CURRENCY, currency);
        return editor.commit();
    }

    public static String getUserName(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(USER_NAME, null);
    }


    public static boolean setLogin(Context mContext, boolean hasLogin) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(HAS_LOGIN, hasLogin);
        return editor.commit();
    }


    public static boolean isHasLogin(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(HAS_LOGIN, false);
    }

    public static boolean setSalesMode(Context mContext, String salesMode) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SALES_MODE, salesMode);
        return editor.commit();
    }

    public static String getSalesMode(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(SALES_MODE, "search");
    }

    public static boolean setModule(Context mContext, String module) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(MODULE, module);
        return editor.commit();
    }

    public static String getModule(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MODULE, "medicine");
    }

    public static boolean setRoles(Context mContext, String roles) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ROLES, roles);
        return editor.commit();
    }

    public static String getRoles(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(ROLES, "");
    }

    public static boolean setLanguage(Context mContext, String lan) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(language, lan);
        return editor.commit();
    }

    public static String getLanguage(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(language, "en");
    }


    public static boolean setOnlineOfflineModes(Context mContext, String mode) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ONLINE_OFFLINE, mode);
        return editor.commit();
    }

    public static String getOnlineOfflineMode(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(ONLINE_OFFLINE, "Offline");
    }

    public static boolean setBarcodeMode(Context mContext, String mode) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(BARCODE, mode);
        return editor.commit();
    }

    public static String getBarcodeMode(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(BARCODE, "Off");
    }

    public static boolean setPrinterType(Context mContext, String printerType) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PRINTER_TYPE, printerType);
        return editor.commit();
    }

    public static String getPrinterType(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(PRINTER_TYPE, "80mm");
    }

    public static boolean setBarcodeCamera(Context mContext, int cameraType) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_TYPE, cameraType);
        return editor.commit();
    }

    public static int getCameraType(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(CAMERA_TYPE, 0);
    }


    public static boolean setDiscountPrint(Context mContext, String print) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(DISCOUNT, print);
        return editor.commit();
    }

    public static String getDiscountPrint(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(DISCOUNT, "Off");
    }


    public static boolean setAutoPayment(Context mContext, String payment) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(AUTOPAYMENT, payment);
        return editor.commit();
    }

    public static String getAutoPayment(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(AUTOPAYMENT, "Off");
    }

    public static boolean setPreviousDue(Context mContext, String previousdue) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREVIOUSDUE, previousdue);
        return editor.commit();
    }

    public static String getPreviousDue(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(PREVIOUSDUE, "Off");
    }

    public static boolean setShowToken(Context mContext, String token) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SHOWTOKEN, token);
        return editor.commit();
    }

    public static String getShowToken(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(SHOWTOKEN, "Off");
    }

    public static boolean setLastPairedDeviceSocketAddre(Context mContext, String pairedDevice) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PAIRED_DEVICE, pairedDevice);
        return editor.commit();
    }

    public static String getLastPairedDeviceSocketAddrId(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(PAIRED_DEVICE, null);
    }

    public static boolean setPrinterConnection(Context mContext, String connection) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PRINTER_CONNECTION, connection);
        return editor.commit();
    }

    public static String getPrinterConnection(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(KEY_USER_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(PRINTER_CONNECTION, "Off");
    }
}
