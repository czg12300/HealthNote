
package com.jake.health.ui.widgt;

import com.jake.health.R;
import com.jake.health.ui.widgt.materialdesign.pullrefresh.MaterialProgressDrawable;
import com.jake.health.utils.DisplayUtil;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoadMoreListView extends ListView implements OnScrollListener {

    private static final String TAG = "LoadMoreListView";

    /**
     * Listener that will receive notifications every time the list scrolls.
     */
    private OnScrollListener mOnScrollListener;

    // Listener to process load more items when user reaches the end of the list
    private OnLoadMoreListener mOnLoadMoreListener;

    // To know if the list is loading more items
    private boolean mIsLoadingMore = false;

    private int mCurrentScrollState;

    private ImageView mIvProgressBar;

    private TextView mTvFinish;

    private MaterialProgressDrawable mDpdLoadMore;

    public LoadMoreListView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setBackgroundColor(Color.parseColor("#f9f9f9"));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -2);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.topMargin = DisplayUtil.dip(10);
        lp.bottomMargin = DisplayUtil.dip(10);
        mIvProgressBar = new ImageView(context);
        mDpdLoadMore = new MaterialProgressDrawable(context, mIvProgressBar);
        mTvFinish = new TextView(context);
        mTvFinish.setText(R.string.load_finish);
        mTvFinish.setTextColor(getResources().getColor(R.color.text_second));
        mTvFinish.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        layout.addView(mIvProgressBar, lp);
        lp = new RelativeLayout.LayoutParams(-1, -2);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.topMargin = DisplayUtil.dip(10);
        lp.bottomMargin = DisplayUtil.dip(10);
        layout.addView(mTvFinish, lp);
        mDpdLoadMore.setColorSchemeColors(getContext().getResources().getColor(
                R.color.title_background));
        mDpdLoadMore.setAlpha(255);
        addFooterView(layout);

        super.setOnScrollListener(this);
    }

    private void showProgressBar() {
        mIvProgressBar.setVisibility(VISIBLE);
        mTvFinish.setVisibility(GONE);
        mDpdLoadMore.start();
    }

    private void hideProgressBar() {
        mIvProgressBar.setVisibility(GONE);
        mDpdLoadMore.stop();
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

    /**
     * Set the listener that will receive notifications every time the list
     * scrolls.
     * 
     * @param l The scroll listener.
     */
    @Override
    public void setOnScrollListener(AbsListView.OnScrollListener l) {
        mOnScrollListener = l;
    }

    /**
     * Register a callback to be invoked when this list reaches the end (last
     * item be visible)
     * 
     * @param onLoadMoreListener The callback to run.
     */

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {

        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }

        if (mOnLoadMoreListener != null) {

            if (visibleItemCount == totalItemCount) {
                // mProgressBarLoadMore.setVisibility(View.GONE);
                // mLabLoadMore.setVisibility(View.GONE);
                return;
            }

            boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;

            if (!mIsLoadingMore && loadMore && mCurrentScrollState != SCROLL_STATE_IDLE) {
                // mProgressBarLoadMore.setVisibility(View.VISIBLE);
                showProgressBar();
                // mLabLoadMore.setVisibility(View.VISIBLE);
                mIsLoadingMore = true;
                onLoadMore();
            }

        }

    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {

        // bug fix: listview was not clickable after scroll
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            view.invalidateViews();
        }

        mCurrentScrollState = scrollState;

        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }

    }

    public void onLoadMore() {
        Log.d(TAG, "onLoadMore");
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    /**
     * Notify the loading more operation has finished
     */
    public void onLoadMoreComplete() {
        // mProgressBarLoadMore.setVisibility(View.GONE);
        hideProgressBar();
        mTvFinish.setVisibility(VISIBLE);
    }

    public void onLoadMoreSuccess() {
        mIsLoadingMore = false;
        // mProgressBarLoadMore.setVisibility(View.GONE);
        hideProgressBar();
    }

    /**
     * Interface definition for a callback to be invoked when list reaches the
     * last item (the user load more items in the list)
     */
    public interface OnLoadMoreListener {
        /**
         * Called when the list reaches the last item (the last item is visible
         * to the user)
         */
        public void onLoadMore();
    }

}
