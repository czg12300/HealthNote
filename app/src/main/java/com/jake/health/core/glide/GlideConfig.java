
package com.jake.health.core.glide;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jake.health.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * 描述：Glide图片加载器配置
 *
 * @author jakechen
 * @since 2016/4/18 18:06
 */
public class GlideConfig implements GlideModule {
    private static final int MEMORY_CACHE_SIZE = 60 * 1024 * 1024;

    private static final int DISK_CACHE_SIZE = 250 * 1024 * 1024;

    private static final int BITMAP_POOL_SIZE = 100;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // builder.setBitmapPool(new LruBitmapPool(BITMAP_POOL_SIZE));
        // builder.setMemoryCache(new LruResourceCache(MEMORY_CACHE_SIZE));
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        builder.setDiskCache(new DiskLruCacheFactory(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "jake/cache/.image", DISK_CACHE_SIZE));
        // builder.setDiskCache(new DiskLruCacheFactory(
        // Environment.getExternalStorageDirectory().getAbsolutePath(),"jake/cache/.image",DISK_CACHE_SIZE));
        // builder.setDiskCacheService(new FifoPriorityThreadPoolExecutor(10));
        // builder.setResizeService(new FifoPriorityThreadPoolExecutor(3));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

    public static void loadImage(Fragment fragment, String url, ImageView view, boolean isCircle) {
        if (view != null && !TextUtils.isEmpty(url)) {
            if (isCircle) {
                Glide.with(fragment).load(url).asBitmap().placeholder(getDefaultImageResources())
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
                Glide.with(fragment).load(url).placeholder(getDefaultImageResources())
                        .crossFade(800).into(view);
            }
        }
    }

    public static void loadImage(Context context, String url, ImageView view, boolean isCircle) {
        if (view != null && !TextUtils.isEmpty(url)) {
            if (isCircle) {
                Glide.with(context).load(url).asBitmap().placeholder(getDefaultImageResources())
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
                Glide.with(context).load(url).placeholder(getDefaultImageResources())
                        .crossFade(800).into(view);
            }
        }
    }

    private static int getDefaultImageResources() {
        return R.drawable.default_image;
    }
}
