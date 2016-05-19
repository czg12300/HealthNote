
package com.jake.health.ui.widgt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;

import com.jake.health.utils.MD5Util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 描述：可以插入图片的EditText
 *
 * @author jakechen
 * @since 2016/5/16 9:46
 */
public class ImageEditText extends EditText {
    private List<Bitmap> bitmapList;

    private LinkedHashMap<String, WeakReference<ImageWear>> mCacheBitmapPool;

    private static final String IMAGE_TAG = "#ImageTag#";

    public ImageEditText(Context context) {
        super(context);
        init();
    }

    public ImageEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setGravity(Gravity.TOP | Gravity.LEFT);
    }

    // float oldY = 0;
    //
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // oldY = event.getY();
                // requestFocus();
                break;
            case MotionEvent.ACTION_MOVE:
                // float newY = event.getY();
                // if (Math.abs(oldY - newY) > 20) {
                // clearFocus();
                // }
                break;
            case MotionEvent.ACTION_UP:
                // boolean shouldInsert = getText().length() > 0
                // && getText().toString().contains(IMAGE_TAG);
                // shouldInsert = shouldInsert && (getSelectionStart() ==
                // getText().length() - 1);
                // if (isFocused() && shouldInsert) {
                // getText().append("\n");
                // }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void insertImage(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            if (mCacheBitmapPool == null) {
                mCacheBitmapPool = new LinkedHashMap<>();
            }
            final String cacheKey = createCacheKey(bitmap.getWidth(), bitmap.getHeight());
            mCacheBitmapPool.put(cacheKey, new WeakReference<ImageWear>(new ImageWear(bitmap)));
            int start = getSelectionStart();
            ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
            SpannableString result = new SpannableString(cacheKey);
            result.setSpan(imageSpan, 0, result.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getText().insert(start, result);
        }
    }

    private String createCacheKey(int width, int height) {
        String key = "<#" + System.currentTimeMillis() + width + height + ">#";
        return MD5Util.getMd5(key);
    }

    public String encode2Html() {
        String result = getText().toString();
        for (String key : mCacheBitmapPool.keySet()) {
            if (result.contains(key)) {
                if (mCacheBitmapPool.containsValue(key)) {
                    ImageWear imageWear = mCacheBitmapPool.get(key).get();
                    if (imageWear != null && imageWear.getBitmap() != null) {
                    }
                }
            }
        }
        return result;
    }

    public List<Bitmap> getBitmapList() {
        if (bitmapList != null) {
            bitmapList.clear();
        } else {
            bitmapList = new ArrayList<>();
        }
        Spanned spanned = getEditableText();
        ImageSpan[] imageSpans = spanned.getSpans(0, spanned.length(), ImageSpan.class);
        for (ImageSpan span : imageSpans) {
            if (span.getDrawable() instanceof BitmapDrawable) {
                BitmapDrawable drawable = (BitmapDrawable) span.getDrawable();
                bitmapList.add(drawable.getBitmap());
            }
        }
        return bitmapList;
    }

    public static final class ImageWear {
        String url;

        WeakReference<Bitmap> bitmap;

        public ImageWear(Bitmap bitmap) {
            this.bitmap = new WeakReference<Bitmap>(bitmap);
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Bitmap getBitmap() {
            return bitmap.get();
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = new WeakReference<Bitmap>(bitmap);
        }
    }
}
