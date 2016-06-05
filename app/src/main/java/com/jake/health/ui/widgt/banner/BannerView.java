
package com.jake.health.ui.widgt.banner;

import com.jake.health.ui.widgt.NoConflictViewPager;
import com.jake.health.utils.CommonUtil;
import com.jake.health.utils.DisplayUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BannerView extends FrameLayout implements OnPageChangeListener, Handler.Callback {
    private static final int MSG_UI_UPDATE_PAGE = 0;

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_UI_UPDATE_PAGE:
                if (!isScroll && mPageSize > 1) {
                    int index = 1 + mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(index);// 切换当前显示的图片
                }
                break;
        }
        return true;
    }

    public static interface IBannerInfo {

    }

    private class MyPagerAdapter extends PagerAdapter {
        private ArrayList<View> mViews;

        public void clear() {
            if (mViews != null) {
                mViews.clear();
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            int len = mViews.size();
            int index = position;
            if (isLoop) {
                index = position % len;
            }
            container.removeView(mViews.get(index));
        }

        @Override
        public int getCount() {
            if (mViews == null) {
                return 0;
            }
            int len = mViews.size();
            if (isLoop) {
                len = Integer.MAX_VALUE;
            }
            return len;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index = position;
            int len = mViews.size();
            if (isLoop) {
                index = position % len;
            }
            if (mViews.get(index).getParent() != null) {
                container.removeView(mViews.get(index));
            }
            container.addView(mViews.get(index));
            return mViews.get(index);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public void setData(List<View> list) {
            if (CommonUtil.isListAvailable(list)) {
                clear();
                mViews = (ArrayList<View>) list;
            }
        }

        public void setDataWithNotifyDataSetChanged(List<View> list) {
            if (CommonUtil.isListAvailable(list)) {
                clear();
                mViews = (ArrayList<View>) list;
                notifyDataSetChanged();
            }
        }

    }

    public static interface IListener {
        void clickBannerItem(Object banner);

        void loadImageToBanner(Object banner, ImageView ivBanner);

    }

    public IListener clickListener;

    private DotView mDotView;

    private NoConflictViewPager mViewPager;

    private MyPagerAdapter mAdapter;

    private ScheduledExecutorService scheduledExecutorService;

    private Handler mHandler = new Handler(this);

    /**
     * 是否循环
     */
    private boolean isLoop = false;

    /**
     * 是否滚动状态
     */
    private boolean isScroll = false;

    public static final int STYLE_DOT_CENTER = 0;

    public static final int STYLE_DOT_RIGHT = 1;

    public static final int STYLE_DOT_LEFT = 2;

    private static final int DOT_RADIUS = DisplayUtil.dip(4);

    private static final int DOT_MARGIN = DisplayUtil.dip(5);

    private int mPageSize = 1;

    private int defaultResId = -1;
    private int autoSlideTimeSpit = 5;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mAdapter = new MyPagerAdapter();
        mViewPager = new NoConflictViewPager(context);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        addView(mViewPager, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public DotView getDotView() {
        return mDotView;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void setDuration(int time) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext(),
                    new AccelerateInterpolator());
            field.set(mViewPager, scroller);
            scroller.setmDuration(time);
        } catch (Exception e) {
        }
    }

    public class FixedSpeedScroller extends Scroller {
        private int mDuration = 1500;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }
    }

    private void updateDotView() {
        int radius = mDotView.getRadius();
        int margin = mDotView.getMargin();
        mDotView.setTotalCount(mPageSize);
        LayoutParams params = new LayoutParams((radius * 2 + margin) * mPageSize, radius * 4);
        switch (mStyle) {
            case STYLE_DOT_CENTER:
                params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                params.bottomMargin = DisplayUtil.dip(10);
                break;
            case STYLE_DOT_RIGHT:
                params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
                params.rightMargin = DisplayUtil.dip(10);
                break;
            case STYLE_DOT_LEFT:
                params.gravity = Gravity.BOTTOM | Gravity.LEFT;
                params.leftMargin = DisplayUtil.dip(10);
                break;
        }
        mDotView.setLayoutParams(params);
    }

    private int mStyle = STYLE_DOT_CENTER;

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (arg0 == 1) {
            isScroll = true;
        } else {
            isScroll = false;
        }
    }

    @Override
    public void onPageSelected(int i) {
        isScroll = false;
        switch (mStyle) {
            case STYLE_DOT_CENTER:
            case STYLE_DOT_RIGHT:
            case STYLE_DOT_LEFT:
                if (mDotView != null) {
                    mDotView.selectPosition(i % mPageSize);
                }
                break;
        }
        mHandler.removeMessages(MSG_UI_UPDATE_PAGE);
    }

    private boolean isStartedLoop = false;

    public void setStyle(int style) {
        mStyle = style;
        mDotView = new DotView(getContext());
        mDotView.setRadius(DOT_RADIUS);
        mDotView.setMargin(DOT_MARGIN);
        addView(mDotView);
    }

    public int getAutoSlideTimeSpit() {
        return autoSlideTimeSpit;
    }

    public void setAutoSlideTimeSpit(int autoSlideTimeSpit) {
        this.autoSlideTimeSpit = autoSlideTimeSpit;
    }

    public void startScroll() {
        if (isLoop && !isStartedLoop && mPageSize > 1) {
            if (scheduledExecutorService == null) {
                scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            } else {
                scheduledExecutorService.shutdownNow();
            }
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(MSG_UI_UPDATE_PAGE);
                }
            }, autoSlideTimeSpit, autoSlideTimeSpit, TimeUnit.SECONDS);
            isStartedLoop = true;
        }
    }

    public void stopScroll() {
        if (isLoop && scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            isStartedLoop = false;
        }
    }

    public void destroy() {
        stopScroll();
        scheduledExecutorService = null;
        if (mAdapter != null) {
            mAdapter.clear();
        }

    }

    public void setBannerListener(IListener banner) {
        this.clickListener = banner;
    }

    private ArrayList<?> mList;

    public void setBannerList(ArrayList<?> list) {
        this.mList = list;
    }

    public void notifyDataSetChanged() {
        notifyDataSetChanged(true);
    }

    public void notifyDataSetChanged(boolean isLoop) {
        if (CommonUtil.isListAvailable(mList)) {
            this.isLoop = isLoop;
            mPageSize = mList.size();
            List<View> viewList = new ArrayList<>();
            for (int i = 0; i < mList.size(); i++) {
                viewList.add(createImageView(mList.get(i)));
            }
            if (mPageSize > 1 && mPageSize < 4) {
                for (int i = 0; i < mList.size(); i++) {
                    viewList.add(createImageView(mList.get(i)));
                }
            }
            mAdapter.clear();
            if (mList.size() == 1) {
                mDotView.setVisibility(GONE);
                this.isLoop = false;
            } else {
                updateDotView();
            }
            mAdapter.setDataWithNotifyDataSetChanged(viewList);
            // mViewPager.setCurrentItem(100000 + mPageSize);
        }
    }

    private ImageView createImageView(Object object) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.clickBannerItem(v.getTag(getId()));
                }
            }
        });
        imageView.setTag(getId(), object);
        if (defaultResId > 0) {
            imageView.setImageResource(defaultResId);
        }
        if (clickListener != null) {
            clickListener.loadImageToBanner(object, imageView);
        }
        return imageView;
    }

    public void setDefaultImage(int resId) {
        defaultResId = resId;
    }
}
