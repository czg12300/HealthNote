package com.jake.health.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.ui.base.BaseTitleActivity;
import com.jake.health.ui.helper.ViewHelper;
import com.jake.health.ui.widgt.ThemeUtils;

/**
 * 描述：用于title的activity父类
 * 作者：jake on 2016/4/21 23:29
 */
public abstract class TitleActivity extends BaseTitleActivity {
    protected ImageView mIvBack;
    protected TextView mTvTitle;
    protected ImageView mIvOpt;

    @Override
    protected View getTitleLayoutView() {
        View vTitle = inflate(R.layout.title_bar);
        mIvBack = (ImageView) vTitle.findViewById(R.id.iv_title_back);
        mTvTitle = (TextView) vTitle.findViewById(R.id.tv_title_text);
        mIvOpt = (ImageView) vTitle.findViewById(R.id.iv_title_opt);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
                finish();
            }
        });
        mIvBack.setImageDrawable(ViewHelper.createBackDrawable());
        mIvBack.setVisibility(View.VISIBLE);
        ThemeUtils.adjustStatusBar(vTitle, this);
        return vTitle;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mFlContent.addView(inflate(R.layout.view_top_shadow));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mFlContent.addView(inflate(R.layout.view_top_shadow));
    }

    protected void showBackButton() {
        mIvBack.setVisibility(View.VISIBLE);
    }

    protected void hideBackButton() {
        mIvBack.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    protected void onBack() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBack();
    }
}
