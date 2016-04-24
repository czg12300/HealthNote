package com.jake.health.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jake.health.R;
import com.jake.health.config.ActionConfig;
import com.jake.health.ui.base.BaseSwipeBackFragmentActivity;
import com.jake.health.ui.helper.ViewHelper;
import com.jake.health.ui.widgt.ThemeUtils;
import com.jake.health.utils.TranslateUtil;

/**
 * 描述：登录页面
 * 作者：jake on 2016/4/21 23:25
 */
public class LoginActivity extends BaseSwipeBackFragmentActivity {
    private boolean isLogin = false;
    private ImageView mIvBack;
    private ImageView mIvBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ThemeUtils.adjustStatusBar(findViewById(R.id.v_state_bar), this);
        mIvBack = (ImageView) findViewById(R.id.iv_title_back);
        mIvBg = (ImageView) findViewById(R.id.iv_bg);
        mIvBack.setImageDrawable(ViewHelper.createBackDrawable());
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadImage("http://life.chinaunix.net/bbsfile/forum/month_0807/20080725_f20e048532825ae192dashNZJXNgofdT.jpg", mIvBg);
    }

    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.btn_login) {
            isLogin = true;
            sendBroadcast(ActionConfig.ACTION_LOGIN_SUCCESS);
            finish();
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
}
