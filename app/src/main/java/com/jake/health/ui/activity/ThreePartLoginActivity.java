
package com.jake.health.ui.activity;

import com.jake.health.R;
import com.jake.health.config.ActionConfig;
import com.jake.health.core.ImageLoadManager;
import com.jake.health.ui.base.BaseSwipeBackFragmentActivity;
import com.jake.health.ui.helper.LoginHelper;
import com.jake.health.ui.helper.ViewHelper;
import com.jake.health.ui.widgt.ThemeUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

/**
 * 描述：第三方登录页面 作者：jake on 2016/4/21 23:25
 */
public class ThreePartLoginActivity extends BaseSwipeBackFragmentActivity {
    private boolean isLogin = false;

    private ImageView mIvBg;

    private LoginHelper mLoginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_part_login);
        ThemeUtils.adjustStatusBar(findViewById(R.id.v_state_bar), this);
        mIvBg = (ImageView) findViewById(R.id.iv_bg);
        ((ImageView) findViewById(R.id.iv_title_back)).setImageDrawable(ViewHelper
                .createBackDrawable());
        ImageLoadManager.load(this,
                "http://life.chinaunix.net/bbsfile/forum/month_0807/20080725_f20e048532825ae192dashNZJXNgofdT.jpg",
                mIvBg);
        mLoginHelper = new LoginHelper();
    }

    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.btn_wechat) {
            mLoginHelper.wechatLogin();
        } else if (id == R.id.btn_wb) {
            mLoginHelper.weiboLogin();
        } else if (id == R.id.btn_qq) {
            mLoginHelper.qqLogin();
        }
        goActivity(MainActivity.class, null, Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        finish();
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

}
