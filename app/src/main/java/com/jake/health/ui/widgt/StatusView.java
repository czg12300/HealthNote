package com.jake.health.ui.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jake.health.R;

/**
 * 描述：状态显示页面
 *
 * @author jake
 * @since 2015/9/19 15:00
 */
public class StatusView extends FrameLayout {
    public StatusView(Context context) {
        this(context, null);
    }

    private View mContentView;
    private MaterialProgressBar mProgressBar;
    private View mLoadView;
    private View mFailView;
    private View mNoDataView;
    private TextView tvNoData;

    public static interface StatusListener {
        void reLoadPageData();
    }

    private StatusListener mListener;

    public void setStatusListener(StatusListener listener) {
        mListener = listener;
    }

    public StatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_status_tip, this);
        mLoadView = findViewById(R.id.ll_loading);
        mProgressBar = (MaterialProgressBar) findViewById(R.id.pb_loading);
        tvNoData = (TextView) findViewById(R.id.tv_no_data);
        mNoDataView = findViewById(R.id.ll_no_data);
        mFailView = findViewById(R.id.ll_fail);
        mFailView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
                mListener.reLoadPageData();
            }
        });
    }

    public void showLoadingView() {
        if (mLoadView.getVisibility() != VISIBLE) {
            mContentView.setVisibility(GONE);
            mFailView.setVisibility(GONE);
            mNoDataView.setVisibility(GONE);
            mLoadView.setVisibility(VISIBLE);
            mProgressBar.show();
        }
    }

    public void showContentView() {
        if (mContentView.getVisibility() != VISIBLE) {
            mContentView.setVisibility(VISIBLE);
            mFailView.setVisibility(GONE);
            mLoadView.setVisibility(GONE);
            mProgressBar.hide();
            mNoDataView.setVisibility(GONE);
        }
    }

    public void showFailView() {
        if (mFailView.getVisibility() != VISIBLE) {
            mContentView.setVisibility(GONE);
            mFailView.setVisibility(VISIBLE);
            mLoadView.setVisibility(GONE);
            mNoDataView.setVisibility(GONE);
            mProgressBar.hide();
        }
    }

    public void showNoDataView() {
        if (mNoDataView.getVisibility() != VISIBLE) {
            mContentView.setVisibility(GONE);
            mFailView.setVisibility(GONE);
            mLoadView.setVisibility(GONE);
            mNoDataView.setVisibility(VISIBLE);
            mProgressBar.hide();
        }
    }

    public void setContentView(View vContent) {
        mContentView = vContent;
        addView(vContent);
    }

    public void setNoDataTip(CharSequence cs) {
        if (tvNoData != null) {
            tvNoData.setText(cs);
        }
    }

    public void setContentView(int layoutId) {
        setContentView(inflate(getContext(), layoutId, null));
    }
}