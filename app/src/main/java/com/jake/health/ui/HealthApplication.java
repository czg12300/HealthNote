package com.jake.health.ui;


import com.jake.health.ui.base.BaseApplication;

/**
 * 描述：程序入口
 *
 * @author jakechen
 * @since 2016/4/19 15:09
 */
public class HealthApplication extends BaseApplication {
    @Override
    protected void onConfig() {

    }

    @Override
    protected void onRelease() {

    }

    @Override
    protected BaseApplication getChildInstance() {
        return this;
    }
}
