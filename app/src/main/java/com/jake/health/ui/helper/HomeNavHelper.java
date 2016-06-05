package com.jake.health.ui.helper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.core.ImageLoadManager;
import com.jake.health.entity.HomeNavInfo;
import com.jake.health.ui.activity.AnalysisActivity;
import com.jake.health.ui.activity.HospitalActivity;
import com.jake.health.ui.activity.MomentsActivity;
import com.jake.health.ui.activity.QAActivity;
import com.jake.health.ui.base.BaseFragment;
import com.jake.health.utils.ViewUtil;

import java.util.List;

/**
 * 描述：首页4大入口
 *
 * @author jakechen
 * @since 2016/6/5 17:33
 */

public class HomeNavHelper implements View.OnClickListener {
    private BaseFragment mFragment;
    private ImageView mIvLeft;
    private ImageView mIvRightTop;
    private ImageView mIvRightBottomLeft;
    private ImageView mIvRightBottomRight;
    private TextView mTvLeft;
    private TextView mTvRightTop;
    private TextView mTvRightBottomLeft;
    private TextView mTvRightBottomRight;
    private TextView mTvLeftHint;
    private TextView mTvRightTopHint;
    private TextView mTvRightBottomLeftHint;
    private TextView mTvRightBottomRightHint;
    private View mRedDotLeft;
    private View mRedDotRightTop;
    private View mRedDotRightBottomLeft;
    private View mRedDotRightBottomRight;
    private View mVAll;
    private View mVLeft;
    private View mVRightTop;
    private View mVRightBottomLeft;
    private View mVRightBottomRight;

    public HomeNavHelper(BaseFragment fragment) {
        mFragment = fragment;
        findViews();
        setListener();
    }


    public void setData(List<HomeNavInfo> navList) {
        if (navList != null && navList.size() == 4) {
            mVAll.setVisibility(View.VISIBLE);
            updateView(navList.get(0), mVLeft, mIvLeft, mTvLeft, mTvLeftHint, mRedDotLeft);
            updateView(navList.get(1), mVRightTop, mIvRightTop, mTvRightTop, mTvRightTopHint, mRedDotRightTop);
            updateView(navList.get(2), mVRightBottomLeft, mIvRightBottomLeft, mTvRightBottomLeft, mTvRightBottomLeftHint, mRedDotRightBottomLeft);
            updateView(navList.get(3), mVRightBottomRight, mIvRightBottomRight, mTvRightBottomRight, mTvRightBottomRightHint, mRedDotRightBottomRight);

        } else {
            mVAll.setVisibility(View.GONE);
        }

    }

    private void updateView(HomeNavInfo info, View view, ImageView iv, TextView tv, TextView tvHint, View vRedDot) {
        if (info != null) {
            view.setTag(info);
            ImageLoadManager.load(mFragment, info.getIcon(), iv);
            ViewUtil.setText2TextView(tv, info.getTitle());
            ViewUtil.setText2TextView(tvHint, info.getHint());
            if (info.isShowRedDot()) {
                vRedDot.setVisibility(View.VISIBLE);
            } else {
                vRedDot.setVisibility(View.GONE);
            }
        }
    }

    private void findViews() {
        mVAll = findViewById(R.id.rl_nav);
        mIvLeft = (ImageView) findViewById(R.id.iv_left);
        mIvRightTop = (ImageView) findViewById(R.id.iv_right_top);
        mIvRightBottomLeft = (ImageView) findViewById(R.id.iv_right_bottom_left);
        mIvRightBottomRight = (ImageView) findViewById(R.id.iv_right_bottom_right);

        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvRightTop = (TextView) findViewById(R.id.tv_right_top);
        mTvRightBottomLeft = (TextView) findViewById(R.id.tv_right_bottom_left);
        mTvRightBottomRight = (TextView) findViewById(R.id.tv_right_bottom_right);

        mTvLeftHint = (TextView) findViewById(R.id.tv_left_hint);
        mTvRightTopHint = (TextView) findViewById(R.id.tv_right_top_hint);
        mTvRightBottomLeftHint = (TextView) findViewById(R.id.tv_right_bottom_left_hint);
        mTvRightBottomRightHint = (TextView) findViewById(R.id.tv_right_bottom_right_hint);

        mVLeft = findViewById(R.id.rl_left);
        mVRightTop = findViewById(R.id.rl_right_top);
        mVRightBottomLeft = findViewById(R.id.rl_right_bottom_left);
        mVRightBottomRight = findViewById(R.id.rl_right_bottom_right);

        mRedDotLeft = mVLeft.findViewById(R.id.v_red_dot);
        mRedDotRightTop = mVRightTop.findViewById(R.id.v_red_dot);
        mRedDotRightBottomLeft = mVRightBottomLeft.findViewById(R.id.v_red_dot);
        mRedDotRightBottomRight = mVRightBottomRight.findViewById(R.id.v_red_dot);
    }

    private void setListener() {
        mVLeft.setOnClickListener(this);
        mVRightTop.setOnClickListener(this);
        mVRightBottomLeft.setOnClickListener(this);
        mVRightBottomRight.setOnClickListener(this);
    }

    private View findViewById(int id) {
        return mFragment.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        HomeNavInfo info = (HomeNavInfo) v.getTag();
        if (info != null) {
            switch (info.getType()) {
                case HomeNavInfo.TYPE_QA:
                    mFragment.goActivity(QAActivity.class);
                    break;
                case HomeNavInfo.TYPE_ANALYSIS:
                    mFragment.goActivity(AnalysisActivity.class);
                    break;
                case HomeNavInfo.TYPE_MOMENTS:
                    mFragment.goActivity(MomentsActivity.class);
                    break;
                case HomeNavInfo.TYPE_HOSPITAL:
                    mFragment.goActivity(HospitalActivity.class);
                    break;
            }
        }
    }
}
