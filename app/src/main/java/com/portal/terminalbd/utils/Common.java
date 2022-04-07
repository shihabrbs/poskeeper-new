package com.portal.terminalbd.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

public class Common {

    public static final byte[] left = new byte[]{0x1b, 0x61, 0x00};// 靠左
    public static final byte[] center = new byte[]{0x1b, 0x61, 0x01};// 居中
    public static final byte[] right = new byte[]{0x1b, 0x61, 0x02};// 靠右
    public static final byte[] bold = new byte[]{0x1b, 0x45, 0x01};// 选择加粗模式
    public static final byte[] bold_cancel = new byte[]{0x1b, 0x45, 0x00};// 取消加粗模式
    public static final byte[] text_normal_size = new byte[]{0x1d, 0x21, 0x00};// 字体不放大
    public static final byte[] text_small_size = new byte[]{0x1b,0x21,0x01};// 字体不放大
    public static final byte[] text_big_height = new byte[]{0x1b, 0x21, 0x10};// 高加倍
    public static final byte[] text_big_size = new byte[]{0x1d, 0x21, 0x11};// 宽高加倍
    public static final byte[] reset = new byte[]{0x1b, 0x40};//复位打印机
    public static final byte[] print = new byte[]{0x0a};//打印并换行
    public static final byte[] under_line = new byte[]{0x1b, 0x2d, 2};//下划线
    public static final byte[] under_line_cancel = new byte[]{0x1b, 0x2d, 0};//下划线

    public static final byte[] ESC_ALIGN_LEFT = new byte[] { 0x1b, 'a', 0x00 };
    public static final byte[] ESC_ALIGN_RIGHT = new byte[] { 0x1b, 'a', 0x02 };
    public static final byte[] ESC_ALIGN_CENTER = new byte[] { 0x1b, 'a', 0x01 };
    public static final byte[] ESC_CANCEL_BOLD = new byte[] { 0x1B, 0x45, 0 };

    /**
     * check internet connection
     * @param activity
     * @return
     */
    public static boolean isNetworkAvailable(Activity activity) {

        ConnectivityManager connectivity = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Network[] networks = connectivity.getAllNetworks();
            NetworkInfo networkInfo;

            for (Network mNetwork : networks) {

                networkInfo = connectivity.getNetworkInfo(mNetwork);

                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }

        }
        else {
            if (connectivity != null) {

                NetworkInfo[] info = connectivity.getAllNetworkInfo();

                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
