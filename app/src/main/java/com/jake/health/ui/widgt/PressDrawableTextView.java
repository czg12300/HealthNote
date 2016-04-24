package com.jake.health.ui.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

/**
 * 描述：有点击效果的ImageView
 * 作者：jake on 2016/4/24 19:54
 */
public class PressDrawableTextView extends TextView {
    public PressDrawableTextView(Context context) {
        super(context);
    }

    public PressDrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PressDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        setAlpha((isPressed() || isFocused() || isSelected()) ? 0.7f : 1);
    }
}
