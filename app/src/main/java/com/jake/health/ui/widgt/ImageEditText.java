
package com.jake.health.ui.widgt;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：可以插入图片的EditText
 *
 * @author jakechen
 * @since 2016/5/16 9:46
 */
public class ImageEditText extends EditText {
    private List<Bitmap> bitmapList;

    public ImageEditText(Context context) {
        super(context);

    }

    public ImageEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void insertImage(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            // 改变EditText中的图片大小，根据字体大小进行改变
            setText(getText().toString());
            int start = getSelectionStart();
            getText().insert(start,"\n");
            start = getSelectionStart();
            SpannableString result = new SpannableString(getText().toString());
            ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
            result.setSpan(imageSpan, result.length()-1,  result.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            int start = mEditText.getSelectionStart();
//            mSpan1.setSpan(new ImageSpan(thumbnailBitmap) , mSpan1.length() - 1, mSpan1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        mSpan1.toString();
//            if(mEditText != null) {
//                Editable et = mEditText.getText();
                getText().insert(start, result);
//            setText(result);
//            setText("\n");
        }
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

}
