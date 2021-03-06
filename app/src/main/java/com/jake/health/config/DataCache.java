package com.jake.health.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.jake.health.ui.HealthApplication;

/**
 * 描述：缓存数据
 *
 * @author jakechen
 * @since 2016/4/28 11:25
 */
public class DataCache {

    private DataCache() {
    }

    /**
     * 文件名
     */
    private static final String FILE_NAME = "cache";

    private static final String KEY_IS_NEW_VOICE = "key_is_new_voice";

    private static final String KEY_USER_ID = "keyUserId";

    private static final String KEY_TOKEN = "keyToken";

    private static final String KEY_MOBILE = "keyMobile";

    private static final String KEY_PASSWORD = "keyPassWord";

    private static final String KEY_DEVICE_DATA = "key_device_data";
    private static final String KEY_MESSAGE_TIME = "key_message_time";
    private static final String KEY_MESSAGE_NUM = "key_message_num";

    public static void setMessageNum(int time) {
        getSharedPreferences().edit().putInt(KEY_MESSAGE_NUM, time).commit();
    }

    public static int getMessageNum() {
        return getSharedPreferences().getInt(KEY_MESSAGE_NUM, 0);
    }
    public static void setMessageTime(int time) {
        getSharedPreferences().edit().putInt(KEY_MESSAGE_TIME, time).commit();
    }

    public static int getMessageTime() {
        return getSharedPreferences().getInt(KEY_MESSAGE_TIME, 0);
    }
    public static void setDeviceData(String code) {
        getSharedPreferences().edit().putString(KEY_DEVICE_DATA, code).commit();
    }

    public static String getDeviceData() {
        return getSharedPreferences().getString(KEY_DEVICE_DATA, null);
    }

    public static void setToken(String code) {
        getSharedPreferences().edit().putString(KEY_TOKEN, code).commit();
    }

    public static String getToken() {
        return getSharedPreferences().getString(KEY_TOKEN, null);
    }

    public static void setPassword(String pw) {
        getSharedPreferences().edit().putString(KEY_PASSWORD, pw).commit();
    }

    public static String getPassword() {
        return getSharedPreferences().getString(KEY_PASSWORD, null);
    }

    public static void setMobile(String mobile) {
        getSharedPreferences().edit().putString(KEY_MOBILE, mobile).commit();
    }

    public static String getMobile() {
        return getSharedPreferences().getString(KEY_MOBILE, null);
    }

    public static void setUserId(String id) {
        getSharedPreferences().edit().putString(KEY_USER_ID, id).commit();
    }

    public static String getUserId() {
        return getSharedPreferences().getString(KEY_USER_ID, null);
    }

    public static boolean hasLogin() {
        return !TextUtils.isEmpty(getMobile()) && !TextUtils.isEmpty(getPassword());
    }

    public static boolean isNewVoice(int id) {
        String save = getSharedPreferences().getString(KEY_IS_NEW_VOICE, "");
        if (save.contains("#" + id + "#")) {
            return false;
        }
        return true;
    }

    public static void setNewVoice(int id) {
        String save = getSharedPreferences().getString(KEY_IS_NEW_VOICE, "") + "#" + id + "#";
        getSharedPreferences().edit().putString(KEY_IS_NEW_VOICE, save).commit();
    }

    /**
     * 获取sharePreference的编辑器
     *
     * @return
     */
    private static SharedPreferences getSharedPreferences() {
        return HealthApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
    }
}
