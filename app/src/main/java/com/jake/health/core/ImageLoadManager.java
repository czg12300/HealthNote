package com.jake.health.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * 描述：图片加载器
 * 作者：jake on 2016/6/1 21:02
 */
public final class ImageLoadManager {
    private static int defaultImageId = -1;

    public static int getDefaultImageId() {
        return defaultImageId;
    }

    public static void setDefaultImageId(int drawableId) {
        defaultImageId = drawableId;
    }


    public static void load(Fragment fragment, String url, ImageView view) {
        load(fragment, url, view, false);
    }

    public static void load(Context context, String url, ImageView view) {
        load(context, url, view, false);
    }

    public static void load(Context context, String url, ImageView view, boolean isCircle) {
        load(url, view, isCircle, Glide.with(context));
    }

    public static void load(Fragment fragment, String url, ImageView view, boolean isCircle) {
        load(url, view, isCircle, Glide.with(fragment));
    }

    /**
     * 发起请求
     *
     * @param url
     * @param view
     * @param isCircle
     * @param requestManager
     */
    private static void load(String url, final ImageView view, boolean isCircle, RequestManager requestManager) {
        if (view != null && !TextUtils.isEmpty(url)) {
            if (isCircle) {
                requestManager.load(url).asBitmap().placeholder(defaultImageId)
                        .into(new BitmapImageViewTarget(view) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory
                                        .create(view.getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                view.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            } else {
                requestManager.load(url).placeholder(defaultImageId)
                        .crossFade(800).into(view);
            }
        }
    }


}
