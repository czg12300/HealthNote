package com.jake.health.ui.widgt;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 描述：解决冲突
 * 作者：Administrator on 2016/6/2 23:06
 */
public class NoConflictSwipeRefreshLayout extends SwipeRefreshLayout {
    private float xDistance, yDistance, xLast, yLast;
    public NoConflictSwipeRefreshLayout(Context context) {
        super(context);
    }

    public NoConflictSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                // 解决与viewpager冲突的问题
                if (xDistance > yDistance) {
                    return false;
                }
        }
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
