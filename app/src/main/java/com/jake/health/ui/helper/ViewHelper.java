package com.jake.health.ui.helper;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.util.DisplayMetrics;

import com.jake.health.ui.HealthApplication;
import com.jake.health.utils.DisplayUtil;

import java.lang.reflect.Field;

/**
 * 描述：一些控件的操作
 * 作者：jake on 2016/4/21 22:06
 */
public class ViewHelper {
    /**
     * 创建返回按钮的图标
     * @return
     */
    public static DrawerArrowDrawable createBackDrawable() {
        DrawerArrowDrawable drawable = new DrawerArrowDrawable(HealthApplication.getInstance().getContext());
        drawable.setColor(Color.WHITE);
        drawable.setProgress(1);
        return drawable;
    }

    /**
     * 设置DrawerLayout的左边滑动位置
     * @param drawerLayout
     * @param dip
     */
    public static void setDrawerLeftEdgeSize(DrawerLayout drawerLayout, int dip) {
        if (drawerLayout != null) {
            try {
                Field field = drawerLayout.getClass().getDeclaredField("mLeftDragger");
                field.setAccessible(true);
                ViewDragHelper leftDragger = (ViewDragHelper) field.get(drawerLayout);
                Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
                edgeSizeField.setAccessible(true);
                int edgeSize = edgeSizeField.getInt(leftDragger);
                edgeSizeField.setInt(leftDragger, Math.max(edgeSize, DisplayUtil.dip(dip)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
