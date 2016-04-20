package com.jake.health.ui.base;

import android.widget.ImageView;

/**
 * 描述：用于其他页面调用异步请求图片
 * 作者：jake on 2016/4/20 22:53
 */
public interface ImageLoadListener {
    void loadImageByUrl(String url,ImageView imageView);
}
