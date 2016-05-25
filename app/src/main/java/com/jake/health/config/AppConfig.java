
package com.jake.health.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.jake.health.ui.HealthApplication;

/**
 * 描述：当前app的配置
 *
 * @author jakechen
 * @since 2016/5/25 9:52
 */
public final class AppConfig {
    public static int getVersion() {
        int version = 0;
        Context context = HealthApplication.getInstance().getApplicationContext();
        try {
            PackageInfo pi = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            version = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static String getVersionName() {
        String name = "1.0";
        Context context = HealthApplication.getInstance().getApplicationContext();
        try {
            PackageInfo pi = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            name = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 获取手机串号(IMEI), 为空时返回MAC地址
     *
     * @return
     */
    public static String getIMEI() {
        Context context = HealthApplication.getInstance().getApplicationContext();
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
        } catch (Exception e) {
        }
        // 平板imei为null
        if (TextUtils.isEmpty(imei)) {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            if (info != null) {
                imei = info.getMacAddress();
            }
            if (imei == null) {
                imei = "";
            }
        }
        return imei;
    }


}
