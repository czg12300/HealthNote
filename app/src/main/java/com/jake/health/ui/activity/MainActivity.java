
package com.jake.health.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.config.ActionConfig;
import com.jake.health.entity.HomeNavInfo;
import com.jake.health.entity.QAInfo;
import com.jake.health.ui.adapter.HomeAdapter;
import com.jake.health.ui.adapter.HomeNavAdapter;
import com.jake.health.ui.base.BaseWorkerFragmentActivity;
import com.jake.health.ui.fragment.HomeFragment;
import com.jake.health.ui.helper.MainFabHelper;
import com.jake.health.ui.widgt.LoadMoreListView;
import com.jake.health.ui.widgt.ThemeUtils;
import com.jake.health.ui.widgt.ZoomOutPageTransformer;
import com.jake.health.ui.widgt.banner.BannerView;
import com.jake.health.ui.widgt.materialdesign.FabButton;
import com.jake.health.utils.DisplayUtil;
import com.jake.health.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseWorkerFragmentActivity {
    private static final int MSG_UI_INIT_DATA = 0x001;

    private static final int MSG_UI_SHOW_MINE = 0x002;

    private static final int MSG_UI_LOAD_MORE = 0x003;

    private ImageView mIvTitleMenu;

    private DrawerLayout mDrawerLayout;

    private FabButton mFabOpt;

    private DrawerArrowDrawable mDrawerArrowDrawable;

    private TextView mTvTitle;

    private View mVTitleMenuRedDot;

    private MainFabHelper mMainFabHelper;

    private ImageView mTvTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitleBar();
        initView();
        initEvent();
        initData();
    }

    private void initTitleBar() {
        ThemeUtils.adjustStatusBar(findViewById(R.id.layout_title), this);
        mTvTitle = ((TextView) findViewById(R.id.tv_title_text));
        mTvTool = (ImageView) findViewById(R.id.iv_title_opt);
        mTvTool.setImageResource(R.drawable.img_tool);
        mTvTool.setVisibility(View.VISIBLE);
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mVTitleMenuRedDot = findViewById(R.id.v_red_dot);
        mFabOpt = (FabButton) findViewById(R.id.fab_opt);
        mMainFabHelper = new MainFabHelper(this);
    }

    private void initData() {
        mTvTitle.setText(R.string.title_home);
        sendEmptyUiMessage(MSG_UI_INIT_DATA);
        if (!isLogin) {
            mVTitleMenuRedDot.setVisibility(View.VISIBLE);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
        }

    }

    private boolean isLogin = true;

    public void closeMine() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    private void initEvent() {
        mDrawerArrowDrawable = new DrawerArrowDrawable(this);
        mDrawerArrowDrawable.setColor(Color.WHITE);
        mIvTitleMenu = (ImageView) findViewById(R.id.iv_title_back);
        mIvTitleMenu.setVisibility(View.VISIBLE);
        mIvTitleMenu.setImageDrawable(mDrawerArrowDrawable);
        mIvTitleMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin) {
                    goActivity(LoginActivity.class);
                    return;
                }
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float position = Math.min(1f, Math.max(0, slideOffset));
                if (position == 1f) {
                    mDrawerArrowDrawable.setVerticalMirror(true);
                } else if (position == 0f) {
                    mDrawerArrowDrawable.setVerticalMirror(false);
                }
                mDrawerArrowDrawable.setProgress(position);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mDrawerArrowDrawable.setVerticalMirror(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerArrowDrawable.setVerticalMirror(false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case MSG_UI_INIT_DATA:
                break;
            case MSG_UI_SHOW_MINE:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case MSG_UI_LOAD_MORE:
                break;
        }
    }

    public void loginOut() {
        // isLogin = false;
        // mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        goActivity(LoginActivity.class);
    }

    @Override
    public void setupBroadcastActions(List<String> actions) {
        super.setupBroadcastActions(actions);
        actions.add(ActionConfig.ACTION_LOGIN_CANCEL);
        actions.add(ActionConfig.ACTION_LOGIN_SUCCESS);
    }

    @Override
    public void handleBroadcast(Context context, Intent intent) {
        super.handleBroadcast(context, intent);
        final String action = intent.getAction();
        if (TextUtils.equals(action, ActionConfig.ACTION_LOGIN_SUCCESS)) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
            sendEmptyUiMessageDelayed(MSG_UI_SHOW_MINE, 500);
            ToastUtil.show("登录成功");
            isLogin = true;
        } else {
            if (TextUtils.equals(action, ActionConfig.ACTION_LOGIN_CANCEL)) {
                ToastUtil.show("取消登录");

            }
        }
    }

}
