
package com.jake.health.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jake.health.R;
import com.jake.health.ui.base.BaseWorkerFragmentActivity;
import com.jake.health.ui.widgt.ChangeThemeUtils;
import com.jake.health.utils.BaseToastUtil;

public class MainActivity extends BaseWorkerFragmentActivity {
    private ImageView mIvTitleMenu;

    private DrawerLayout mDrawerLayout;

    private FloatingActionButton mFabOpt;

    private DrawerArrowDrawable mDrawerArrowDrawable;

    private TextView mTvTitle;

    private GridView mGvNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitleBar();
        initView();
        initEvent();
        initData();
    }

    private void initData() {
        mTvTitle.setText("健康小秘");
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFabOpt = (FloatingActionButton) findViewById(R.id.fab_opt);
        mGvNav = (GridView) findViewById(R.id.gv_nav);
    }

    private void initTitleBar() {
        ChangeThemeUtils.adjustStatusBar(findViewById(R.id.ll_root_title), this);
        mTvTitle = ((TextView) findViewById(R.id.tv_title_text));
    }

    private void initEvent() {
        mFabOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseToastUtil.show("哈哈");
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
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            private float lastPosition = 0;

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float position = Math.min(1f, Math.max(0, slideOffset));
                changeTitle(position);
                Log.d("tag", "position" + position);
                if (position == 1f) {
                    mDrawerArrowDrawable.setVerticalMirror(true);
                } else if (position == 0f) {
                    mDrawerArrowDrawable.setVerticalMirror(false);
                }
                mDrawerArrowDrawable.setProgress(position);

            }

            private void changeTitle(float position) {
                if (position > lastPosition) {
                    if (1 - position > 0.5) {
                        mTvTitle.setAlpha(1 - position);
                        mTvTitle.setText("健康小秘");
                    } else {
                        mTvTitle.setAlpha(position);
                        mTvTitle.setText("个人中心");
                    }
                } else {
                    if (position > 0.5) {
                        mTvTitle.setText("个人中心");
                        mTvTitle.setAlpha(position);
                    } else {
                        mTvTitle.setAlpha(1 - position);
                        mTvTitle.setText("健康小秘");
                    }
                }
                lastPosition = position;
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
}
