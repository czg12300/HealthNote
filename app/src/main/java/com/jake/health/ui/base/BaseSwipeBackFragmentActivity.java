
package com.jake.health.ui.base;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.jake.health.ui.widgt.ThemeUtils;
import com.jake.health.ui.widgt.swipeback.SwipeBackActivityHelper;
import com.jake.health.ui.widgt.swipeback.SwipeBackLayout;
import com.jake.health.ui.widgt.swipebacknew.SwipeBackHelper;

public class BaseSwipeBackFragmentActivity extends BaseWorkerFragmentActivity {
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(true).setSwipeSensitivity(0.5f)
                .setSwipeRelateEnable(false);
        // mHelper = new SwipeBackActivityHelper(this);
        // mHelper.onActivityCreate();
        // getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        // DisplayMetrics metric = new DisplayMetrics();
        // getWindowManager().getDefaultDisplay().getMetrics(metric);
        // int width = metric.widthPixels;
        // getSwipeBackLayout().setEdgeSize(width/8);
    }

    public void setSwipeBackEnable(boolean enable) {
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(enable);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    // @Override
    // protected void onPostCreate(Bundle savedInstanceState) {
    // super.onPostCreate(savedInstanceState);
    // mHelper.onPostCreate();
    // }
    //
    // @Override
    // public View findViewById(int id) {
    // View v = super.findViewById(id);
    // if (v == null && mHelper != null) {
    // return mHelper.findViewById(id);
    // }
    // return v;
    // }
    //
    // public SwipeBackLayout getSwipeBackLayout() {
    // return mHelper.getSwipeBackLayout();
    // }
    //
    // public void setSwipeBackEnable(boolean enable) {
    // getSwipeBackLayout().setEnableGesture(enable);
    // }
    //
    // public void scrollToFinishActivity() {
    // ThemeUtils.convertActivityToTranslucent(this);
    // getSwipeBackLayout().scrollToFinishActivity();
    // }
}
