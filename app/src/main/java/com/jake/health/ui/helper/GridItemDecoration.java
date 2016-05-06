
package com.jake.health.ui.helper;

import com.jake.health.utils.DisplayUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * 描述：网格的recycleview分割线
 *
 * @author jakechen
 * @since 2016/5/6 15:18
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        Log.d("tag","getItemOffsets");
        int offset = DisplayUtil.dip(10);
        int position = parent.getChildAdapterPosition(view);
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
            int orientation = staggeredGridLayoutManager.getOrientation();
            int spanCount = staggeredGridLayoutManager.getSpanCount();
            int top = 0;
            int left = 0;
            int right = 0;
            int bottom = 0;
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                int temp = position % spanCount;
                if (temp == 0) {
                    left = offset;
                    right = offset;
                    bottom = offset;
                } else if (temp < spanCount - 1 || temp == spanCount - 1) {
                    right = offset;
                    bottom = offset;
                }
                if (position < spanCount) {
                    top=offset;
                }
                outRect.set(left, top, right, bottom);
            } else {
                int temp = position % spanCount;
                if (temp == 0) {
                    right = offset;
                    top = offset;
                    bottom = offset;
                } else if (temp < spanCount - 1 || temp == spanCount - 1) {
                    right = offset;
                    bottom = offset;
                }
                if (position < spanCount) {
                    left=offset;
                }
                outRect.set(left, top, right, bottom);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        c.drawColor(Color.parseColor("#333333"));
    }
}
