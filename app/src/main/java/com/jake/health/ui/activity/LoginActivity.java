package com.jake.health.ui.activity;

import android.view.View;

import com.jake.health.R;
import com.jake.health.config.ActionConfig;

/**
 * 描述：登录页面
 * 作者：jake on 2016/4/21 23:25
 */
public class LoginActivity extends TitleActivity {
    private boolean isLogin=false;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        setTitle("登录");
    }

    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.btn_login) {
            isLogin=true;
            sendBroadcast(ActionConfig.ACTION_LOGIN_SUCCESS);
            finish();
        }
    }


    @Override
    public void finish() {
        super.finish();
        if (!isLogin){
            sendBroadcast(ActionConfig.ACTION_LOGIN_CANCEL);
        }
    }
}
