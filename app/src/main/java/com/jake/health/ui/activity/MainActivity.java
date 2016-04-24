
package com.jake.health.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.config.ActionConfig;
import com.jake.health.entity.HomeNavInfo;
import com.jake.health.ui.adapter.HomeNavAdapter;
import com.jake.health.ui.base.BaseWorkerFragmentActivity;
import com.jake.health.ui.helper.ViewHelper;
import com.jake.health.ui.widgt.ThemeUtils;
import com.jake.health.ui.widgt.ZoomOutPageTransformer;
import com.jake.health.ui.widgt.banner.BannerView;
import com.jake.health.utils.DisplayUtil;
import com.jake.health.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseWorkerFragmentActivity {
    private static final int MSG_UI_INIT_DATA = 0x001;
    private static final int MSG_UI_SHOW_MINE = 0x002;
    private ImageView mIvTitleMenu;

    private DrawerLayout mDrawerLayout;

    private FloatingActionButton mFabOpt;

    private DrawerArrowDrawable mDrawerArrowDrawable;

    private TextView mTvTitle;

    private GridView mGvNav;
    private HomeNavAdapter mHomeNavAdapter;
    private BannerView mBannerTop;
    private View mVTitleMenuRedDot;

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
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mVTitleMenuRedDot = findViewById(R.id.v_red_dot);
        mFabOpt = (FloatingActionButton) findViewById(R.id.fab_opt);
        mGvNav = (GridView) findViewById(R.id.gv_nav);
        mBannerTop = (BannerView) findViewById(R.id.banner_top);
        mBannerTop.setStyle(BannerView.STYLE_DOT_RIGHT);
        mBannerTop.getDotView().setRadius(DisplayUtil.dip(2));
        mBannerTop.getDotView().setNormalColor(Color.parseColor("#60000000"));
        mBannerTop.getDotView().setSelectColor(Color.parseColor("#afffffff"));
        mBannerTop.getViewPager().setPageTransformer(true, new ZoomOutPageTransformer());
        mBannerTop.setDuration(300);
    }

    private void initData() {
        mTvTitle.setText(R.string.title_home);
        mHomeNavAdapter = new HomeNavAdapter(this);
        mGvNav.setAdapter(mHomeNavAdapter);

        sendEmptyUiMessage(MSG_UI_INIT_DATA);
        if (!isLogin) {
            mVTitleMenuRedDot.setVisibility(View.VISIBLE);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
        }
    }

    private boolean isLogin = false;

    public void closeMine() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    private void initEvent() {
        mBannerTop.setBannerListener(new BannerView.IListener() {
            @Override
            public void clickBannerItem(Object banner) {
                String url = (String) banner;
                ToastUtil.show(url);
            }

            @Override
            public void loadImageToBanner(Object banner, ImageView ivBanner) {
                String url = (String) banner;
                loadImage(url, ivBanner);
            }
        });
        mGvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeNavInfo info = (HomeNavInfo) parent.getAdapter().getItem(position);
                if (info != null) {
                    ToastUtil.show(info.getTitle());
                }
            }
        });
        mFabOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("哈哈");
            }
        });
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
                dealInitData();
                break;
            case MSG_UI_SHOW_MINE:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    private void dealInitData() {
        mHomeNavAdapter.setDataAndNotifyDataSetChanged(getTestNav());
        mBannerTop.setBannerList(getTestBanner());
        mBannerTop.notifyDataSetChanged();
        mBannerTop.startScroll(5);
    }

    public void loginOut() {
        isLogin = false;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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

    private ArrayList<?> getTestBanner() {
        ArrayList<String> list = new ArrayList<>();
        list.add("http://img0.imgtn.bdimg.com/it/u=535245040,1392341624&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=513166044,2711533450&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=2877222865,4042023416&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2014305729,1419052095&fm=21&gp=0.jpg");
        return list;
    }

    private List<HomeNavInfo> getTestNav() {
        List<HomeNavInfo> list = new ArrayList<>();
        HomeNavInfo info = new HomeNavInfo();
        info.setTitle("求医问药");
        info.setShowDot(1);
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=1390048268,4118739760&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setTitle("我的病例");
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=3119963880,936101450&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setTitle("附近诊所");
        info.setIcon("http://img3.imgtn.bdimg.com/it/u=2832776744,1381723459&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setTitle("工具箱");
        info.setIcon("http://img5.imgtn.bdimg.com/it/u=3297870962,4077987313&fm=21&gp=0.jpg");
        list.add(info);
        return list;
    }
}
