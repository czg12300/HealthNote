
package com.jake.health.ui.helper;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.jake.health.R;
import com.jake.health.ui.base.BaseFragmentActivity;
import com.jake.health.utils.ToastUtil;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * 描述：首页floatButton的操作逻辑 compile 'com.getbase:floatingactionbutton:1.10.1'
 * 
 * @author jakechen
 * @since 2016/4/27 10:58
 */
public class MainFabHelper implements View.OnClickListener {
    private FloatingActionsMenu mFloatingActionsMenu;

    private BaseFragmentActivity mActivity;

    private View mVBg;

    private FloatingActionButton mFabDt;

    private FloatingActionButton mFabBl;

    private FloatingActionButton mFabWbl;

    private FloatingActionButton mFabYy;

    public MainFabHelper(BaseFragmentActivity activity) {
        mActivity = activity;
        setupFab();
    }

    private void setupFab() {
        mFloatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.fam_menu);
        mVBg = findViewById(R.id.overlay);
        mFabDt = (FloatingActionButton) findViewById(R.id.fab_dt);
        mFabBl = (FloatingActionButton) findViewById(R.id.fab_bl);
        mFabYy = (FloatingActionButton) findViewById(R.id.fab_yy);
        mFabWbl = (FloatingActionButton) findViewById(R.id.fab_wbl);
        mVBg.setOnClickListener(this);
        mFabDt.setOnClickListener(this);
        mFabBl.setOnClickListener(this);
        mFabYy.setOnClickListener(this);
        mFabWbl.setOnClickListener(this);
        mFabDt.setIcon(R.drawable.img_qq);
        mFabBl.setIcon(R.drawable.img_wb);
        mFabYy.setIcon(R.drawable.img_wx);
        Drawable drawable=mActivity.getResources().getDrawable(R.drawable.img_tool);
        drawable.setColorFilter(ViewHelper.color2ColorFilter(Color.parseColor("#252525")));
        mFabWbl.setIconDrawable(drawable);
        mFloatingActionsMenu
                .setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
                    @Override
                    public void onMenuExpanded() {
                        mVBg.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onMenuCollapsed() {
                        mVBg.setVisibility(View.GONE);
                    }
                });
    }

    private View findViewById(int id) {
        return mActivity.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        if (v == mVBg) {
            mFloatingActionsMenu.collapse();
        } else if (v == mFabDt) {
            ToastUtil.show(mFabDt.getTitle());
            mFloatingActionsMenu.collapse();
        } else if (v == mFabBl) {
            ToastUtil.show(mFabBl.getTitle());
            mFloatingActionsMenu.collapse();
        } else if (v == mFabYy) {
            ToastUtil.show(mFabYy.getTitle());
            mFloatingActionsMenu.collapse();
        } else if (v == mFabWbl) {
            ToastUtil.show(mFabWbl.getTitle());
            mFloatingActionsMenu.collapse();
        }
    }
}
