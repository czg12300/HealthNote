
package com.jake.health.ui.activity;

import com.jake.health.R;
import com.jake.health.config.ActionConfig;
import com.jake.health.ui.base.BaseSwipeBackFragmentActivity;
import com.jake.health.ui.dialog.CommonDialog;
import com.jake.health.ui.helper.ViewHelper;
import com.jake.health.ui.widgt.DrawableEditText;
import com.jake.health.ui.widgt.ThemeUtils;
import com.jake.health.ui.widgt.materialdesign.ProgressWheel;
import com.jake.health.utils.ToastUtil;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 描述：登录页面 作者：jake on 2016/4/21 23:25
 */
public class LoginActivity extends BaseSwipeBackFragmentActivity {
    private boolean isLogin = false;

    private ImageView mIvBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ThemeUtils.adjustStatusBar(findViewById(R.id.v_state_bar), this);
        mIvBg = (ImageView) findViewById(R.id.iv_bg);
        ((ImageView) findViewById(R.id.iv_title_back)).setImageDrawable(ViewHelper
                .createBackDrawable());
        loadImage(
                "http://life.chinaunix.net/bbsfile/forum/month_0807/20080725_f20e048532825ae192dashNZJXNgofdT.jpg",
                mIvBg);
    }

    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.btn_login) {
            showLoginDialog();
        } else if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.btn_wechat) {
            ToastUtil.show("微信");
        } else if (id == R.id.btn_wb) {
            ToastUtil.show("微播");
        } else if (id == R.id.btn_qq) {
            ToastUtil.show("QQ");
        } else if (id == R.id.btn_register) {
            showRegisterDialog();
        }
    }

    @Override
    public void handleBackgroundMessage(Message msg) {
        super.handleBackgroundMessage(msg);
    }

    @Override
    public void finish() {
        super.finish();
        if (!isLogin) {
            sendBroadcast(ActionConfig.ACTION_LOGIN_CANCEL);
        }
    }

    private void showLoginDialog() {
        if (!isFinishing()) {
            final CommonDialog dialog = new CommonDialog(this) {
                @Override
                protected void onNegativeBtnClick() {
                    stopPositionProgress();
                }

                @Override
                protected void onPositiveBtnClick() {
                    startPositionProgress();
                    isLogin = true;
                    sendBroadcast(ActionConfig.ACTION_LOGIN_SUCCESS);
                    dismissDelayed(2000);
                }
            };
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (isLogin) {
                        finish();
                    }
                }
            });
            dialog.setOptDismiss(false);
            dialog.setTitle(R.string.login);
            dialog.setNegativeButton(R.string.cancel, R.string.ok);
            dialog.setContentView(R.layout.dialog_login);
            dialog.setPositiveBtnTextColor(Color.parseColor("#FF4081"));
            ViewHelper.changeTextInputLayoutLabelColor((TextInputLayout) dialog.findViewById(R.id.til_account));
            ViewHelper.changeTextInputLayoutLabelColor((TextInputLayout) dialog.findViewById(R.id.til_pwd));
            DrawableEditText evPwd = (DrawableEditText) dialog.findViewById(R.id.ev_pwd);
            evPwd.setDrawableRightListener(new DrawableEditText.DrawableRightListener() {
                @Override
                public void onDrawableRightClick(View view) {
                    dialog.dismiss();
                    showLoginByMessageCodeDialog();
                }
            });
            dialog.show();
        }
    }

    private void showRegisterDialog() {
        if (!isFinishing()) {
            CommonDialog dialog = new CommonDialog(this) {
                @Override
                protected void onNegativeBtnClick() {
                    super.onNegativeBtnClick();
                    stopPositionProgress();
                }

                @Override
                protected void onPositiveBtnClick() {
                    startPositionProgress();
                }
            };
            dialog.setTitle(R.string.register);
            dialog.setOptDismiss(false);
            dialog.setNegativeButton(R.string.cancel, R.string.ok);
            dialog.setContentView(R.layout.dialog_register);
            dialog.setPositiveBtnTextColor(Color.parseColor("#FF4081"));
            ViewHelper.changeTextInputLayoutLabelColor((TextInputLayout) dialog.findViewById(R.id.til_account));
            ViewHelper.changeTextInputLayoutLabelColor((TextInputLayout) dialog.findViewById(R.id.til_code));
            ViewHelper.changeTextInputLayoutLabelColor((TextInputLayout) dialog.findViewById(R.id.til_pwd));
            final Button btnCode = (Button) dialog.findViewById(R.id.btn_code);
            btnCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnCode.setEnabled(false);
                    new CountDownTimer(60 * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int temp = (int) millisUntilFinished / 1000;
                            String show = getString(R.string.count_down_left);
                            btnCode.setText(show.replace("#", "" + temp));
                        }

                        @Override
                        public void onFinish() {
                            btnCode.setEnabled(true);
                            btnCode.setText(R.string.get_phone_code);
                            btnCode.setVisibility(View.VISIBLE);
                        }
                    }.start();

                }
            });
            dialog.show();
        }
    }
    private void showLoginByMessageCodeDialog() {
        if (!isFinishing()) {
            CommonDialog dialog = new CommonDialog(this) {
                @Override
                protected void onNegativeBtnClick() {
                    super.onNegativeBtnClick();
                    stopPositionProgress();
                }

                @Override
                protected void onPositiveBtnClick() {
                    startPositionProgress();
                    //登录后弹出重置密码的弹出
                    sendBroadcast(ActionConfig.ACTION_AUTO_RESET_PWD);
                }
            };
            dialog.setTitle(R.string.login_by_message_code);
            dialog.setOptDismiss(false);
            dialog.setNegativeButton(R.string.cancel, R.string.ok);
            dialog.setContentView(R.layout.dialog_login_by_message_code);
            dialog.setPositiveBtnTextColor(Color.parseColor("#FF4081"));
            ViewHelper.changeTextInputLayoutLabelColor((TextInputLayout) dialog.findViewById(R.id.til_account));
            ViewHelper.changeTextInputLayoutLabelColor((TextInputLayout) dialog.findViewById(R.id.til_code));
            ViewHelper.changeTextInputLayoutLabelColor((TextInputLayout) dialog.findViewById(R.id.til_pwd));
            final Button btnCode = (Button) dialog.findViewById(R.id.btn_code);
            btnCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnCode.setEnabled(false);
                    new CountDownTimer(60 * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int temp = (int) millisUntilFinished / 1000;
                            String show = getString(R.string.count_down_left);
                            btnCode.setText(show.replace("#", "" + temp));
                        }

                        @Override
                        public void onFinish() {
                            btnCode.setEnabled(true);
                            btnCode.setText(R.string.get_phone_code);
                            btnCode.setVisibility(View.VISIBLE);
                        }
                    }.start();

                }
            });
            dialog.show();
        }
    }
}
