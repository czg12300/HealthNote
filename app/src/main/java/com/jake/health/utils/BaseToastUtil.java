
package com.jake.health.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.jake.health.ui.base.BaseApplication;


/**
 * 描述：用于显示toast
 *
 * @author Created by jakechen on 2015/8/11.
 */
public class BaseToastUtil {
    protected static Toast mToast;
    protected static String mMsg;

    public static void show(int stringId) {
        show(BaseApplication.getInstance().getString(stringId));
    }

    public static void show(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (!TextUtils.equals(msg, mMsg)) {
            mToast = Toast.makeText(BaseApplication.getInstance().getContext(), msg, Toast.LENGTH_SHORT);
            mMsg = msg;
        }
        mToast.show();
    }
}
