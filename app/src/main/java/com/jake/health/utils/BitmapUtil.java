package com.jake.health.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

/**
 * Created by Administrator on 2015/8/16.
 */
public final class BitmapUtil {
    public static Bitmap decodeResource(int drawableId) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeResource(DisplayUtil.getResources(), drawableId);
        } catch (OutOfMemoryError error) {
        }
        return bitmap;
    }

    public static Bitmap decodeResource(int drawableId, int width, int height) {
        return ThumbnailUtils.extractThumbnail(decodeResource(drawableId), width, height);
    }
    public static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength,
                                               int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h
                / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
}
