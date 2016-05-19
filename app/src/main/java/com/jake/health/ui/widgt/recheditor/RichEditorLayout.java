package com.jake.health.ui.widgt.recheditor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.jake.health.R;

/**
 * 描述：富编辑器
 *
 * @author jakechen
 * @since 2016/5/19 17:58
 */
public class RichEditorLayout extends LinearLayout {
    public RichEditorLayout(Context context) {
        super(context);
        init();
    }


    public RichEditorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RichEditorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        inflate(getContext(), R.layout.item_hospital, this);
    }
}
