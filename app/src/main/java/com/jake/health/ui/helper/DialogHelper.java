package com.jake.health.ui.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.jake.health.R;
import com.jake.health.config.ActionConfig;
import com.jake.health.ui.dialog.CommonDialog;
import com.jake.health.ui.widgt.DrawableEditText;

/**
 * 描述：弹窗相关逻辑
 * 作者：jake on 2016/4/26 23:19
 */
public class DialogHelper {
    public static void showResetPwdDialog(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            final CommonDialog dialog = new CommonDialog(activity) {
                @Override
                protected void onNegativeBtnClick() {
                    stopPositionProgress();
                }

                @Override
                protected void onPositiveBtnClick() {
                    startPositionProgress();
                    dismissDelayed(2000);
                }
            };
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                }
            });
            dialog.setOptDismiss(false);
            dialog.setTitle(R.string.login);
            dialog.setNegativeButton(R.string.cancel, R.string.ok);
            dialog.setContentView(R.layout.dialog_login);
            dialog.setPositiveBtnTextColor(Color.parseColor("#FF4081"));
            TextInputLayout tilAccount = (TextInputLayout) dialog.findViewById(R.id.til_account);
            TextInputLayout tilPwd = (TextInputLayout) dialog.findViewById(R.id.til_pwd);
            ViewHelper.changeTextInputLayoutLabelColor(tilAccount);
            ViewHelper.changeTextInputLayoutLabelColor(tilPwd);
            DrawableEditText evPwd = (DrawableEditText) dialog.findViewById(R.id.ev_pwd);
            evPwd.setDrawableRightListener(new DrawableEditText.DrawableRightListener() {
                @Override
                public void onDrawableRightClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}
