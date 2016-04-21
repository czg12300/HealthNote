package com.jake.health.ui.helper;

import android.graphics.Color;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;

import com.jake.health.ui.HealthApplication;

/**
 * 描述：一些控件的操作
 * 作者：jake on 2016/4/21 22:06
 */
public class ViewHelper {
    public static DrawerArrowDrawable createBackDrawable(){
        DrawerArrowDrawable drawable = new DrawerArrowDrawable(HealthApplication.getInstance().getContext());
        drawable.setColor(Color.WHITE);
        drawable.setProgress(1);
        return drawable;
    }
}
