package com.jake.health.ui.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jake.health.R;
import com.jake.health.ui.widgt.materialdesign.pullrefresh.MaterialProgressDrawable;

/**
 * 描述：material design 进度条
 * 作者：jake on 2016/4/28 20:39
 */
public class MaterialProgressBar extends ImageView {
    private MaterialProgressDrawable mDpdProgressBar;

    public MaterialProgressBar(Context context) {
        this(context, null);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDpdProgressBar = new MaterialProgressDrawable(context, this);
        mDpdProgressBar.setAlpha(255);
        setImageDrawable(mDpdProgressBar);
        setProgressColors(getContext().getResources().getColor(
                R.color.title_background));
    }

    public void show() {
        setVisibility(VISIBLE);
        mDpdProgressBar.start();
    }

    public void hide() {
        setVisibility(GONE);
        mDpdProgressBar.stop();
    }

    public void setProgressColors(int... colors) {
        mDpdProgressBar.setColorSchemeColors(colors);
    }
}
