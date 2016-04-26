
package com.jake.health.ui.helper;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.util.DisplayMetrics;

import com.jake.health.R;
import com.jake.health.ui.HealthApplication;
import com.jake.health.utils.DisplayUtil;

import java.lang.reflect.Field;

/**
 * 描述：一些控件的操作 作者：jake on 2016/4/21 22:06
 */
public class ViewHelper {
    /**
     * 创建返回按钮的图标
     * 
     * @return
     */
    public static DrawerArrowDrawable createBackDrawable() {
        DrawerArrowDrawable drawable = new DrawerArrowDrawable(HealthApplication.getInstance()
                .getContext());
        drawable.setColor(Color.WHITE);
        drawable.setProgress(1);
        return drawable;
    }

    /**
     * 设置DrawerLayout的左边滑动位置
     * 
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

    public static void changeTextInputLayoutLabelColor(TextInputLayout layout, int color) {
        if (layout != null) {
            try {
                Field field = layout.getClass().getDeclaredField("mFocusedTextColor");
                field.setAccessible(true);
                field.set(layout, createColorStateList(color, 0, 0, 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public static void changeTextInputLayoutLabelColor(TextInputLayout layout) {
        changeTextInputLayoutLabelColor(layout,HealthApplication.getInstance().getResources().getColor(R.color.edit_text_bg_focus));

    }
    public static ColorStateList createColorStateList(int normal, int pressed, int select,
            int unable) {
        if (pressed == 0) {
            pressed = normal;
        }
        if (select == 0) {
            select = normal;
        }
        if (unable == 0) {
            unable = normal;
        }
        int[] colors = new int[] {
                pressed, select, unable, normal
        };
        int[][] states = new int[4][];
        states[0] = new int[] {
                android.R.attr.state_pressed, android.R.attr.state_enabled
        };
        states[1] = new int[] {
                android.R.attr.state_enabled, android.R.attr.state_selected
        };
        states[2] = new int[] {
            -android.R.attr.state_enabled
        };
        states[3] = new int[] {
            android.R.attr.state_enabled
        };
        return new ColorStateList(states, colors);
    }

    /**
     * 创建StateListDrawable
     */
    public static StateListDrawable createStateListDrawable(Drawable normal, Drawable pressed,
            Drawable select, Drawable unable) {
        if (normal == null) {
            return null;
        }
        StateListDrawable bg = new StateListDrawable();
        if (pressed != null) {
            bg.addState(new int[] {
                    android.R.attr.state_pressed, android.R.attr.state_enabled
            }, pressed);
        }
        if (select != null) {
            bg.addState(new int[] {
                    android.R.attr.state_selected, android.R.attr.state_enabled
            }, select);
        }
        if (unable != null) {
            bg.addState(new int[] {
                -android.R.attr.state_enabled
            }, unable);
        }
        bg.addState(new int[] {}, normal);
        return bg;
    }
}
