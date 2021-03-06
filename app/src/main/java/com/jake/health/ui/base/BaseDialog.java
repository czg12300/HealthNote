
package com.jake.health.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.ui.widgt.MaterialProgressBar;

/**
 * 描述：dialog的父类
 * 作者：jake on 2015/8/28.
 */
public class BaseDialog extends Dialog implements View.OnClickListener {

    private static final int DEFAULT_INT = -1;

    private Window window = null;

    protected FrameLayout mFlContent;

    protected TextView mTvTitle;

    protected Button mBtnPositive;

    protected Button mBtnNegative;

    protected View mVButton;

    protected MaterialProgressBar mProgressBar;


    protected boolean isOptDismiss = true;

    public boolean isOptDismiss() {
        return isOptDismiss;
    }

    public void setOptDismiss(boolean optDismiss) {
        isOptDismiss = optDismiss;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindow(R.style.alpha_animation, 0.4f);
    }

    public BaseDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window = getWindow(); // 得到对话框
        super.setContentView(R.layout.dialog_base);
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnNegative = (Button) findViewById(R.id.btn_negative);
        mBtnPositive = (Button) findViewById(R.id.btn_positive);
        mProgressBar = (MaterialProgressBar) findViewById(R.id.mpb_progress_bar);
        mVButton = findViewById(R.id.rl_opt_button_layout);
        mBtnNegative.setOnClickListener(this);
        mBtnPositive.setOnClickListener(this);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    public void setPositiveBtnTextColor(int color) {
        mBtnPositive.setTextColor(color);
        mProgressBar.setProgressColors(color);
    }

    public void setNegativeBtnTextColor(int color) {
        mBtnNegative.setTextColor(color);
    }

    public void startPositionProgress() {
        if (mBtnPositive.getVisibility() == View.VISIBLE) {
            mBtnPositive.setVisibility(View.INVISIBLE);
            mProgressBar.show();
        }
    }

    public void stopPositionProgress() {
        mBtnPositive.setVisibility(View.VISIBLE);
        mProgressBar.hide();
    }

    public void setNegativeButton(int negative, int positive) {
        setNegativeButton(getContext().getString(negative), getContext().getString(positive));
    }

    public void setNegativeButton(String negative, String positive) {
        mBtnNegative.setText(negative);
        mBtnPositive.setText(positive);
        mVButton.setVisibility(View.VISIBLE);
        mBtnPositive.setVisibility(View.VISIBLE);
        mBtnNegative.setVisibility(View.VISIBLE);
    }

    public void setPositiveButton(CharSequence cs) {
        mBtnPositive.setText(cs);
        mVButton.setVisibility(View.VISIBLE);
        mBtnPositive.setVisibility(View.VISIBLE);
    }

    public void setPositiveButton(int strId) {
        setPositiveButton(getContext().getString(strId));
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getContext().getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title) {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(title);
    }

    protected void onNegativeBtnClick() {
    }

    protected void onPositiveBtnClick() {
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(getContext(), layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        mFlContent.addView(view);
    }

    // 设置窗口显示
    public void setWindow(int animStyle, float showDimAmount) {
        setWindow(DEFAULT_INT, DEFAULT_INT, animStyle, showDimAmount);
    }

    public void setWindow(int x, int y) {
        setWindow(x, y, DEFAULT_INT);
    }

    public void setWindow(int x, int y, int animStyle) {
        setWindow(x, y, animStyle, 1.0f);
    }

    public void setWindow(int x, int y, float showDimAmount) {
        setWindow(x, y, DEFAULT_INT, showDimAmount);
    }

    // 设置窗口显示
    public void setWindow(int x, int y, int animStyle, float showDimAmount) {
        if (animStyle != DEFAULT_INT) {
            window.setWindowAnimations(animStyle); // 设置窗口弹出动画
        }
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // //设置对话框背景为透明
        WindowManager.LayoutParams lp = window.getAttributes();
        // 根据x，y坐标设置窗口需要显示的位置
        if (x != DEFAULT_INT) {
            lp.x = x;
        }
        if (y != DEFAULT_INT) {
            lp.y = y;
        }
        lp.dimAmount = showDimAmount;
//        lp.flags=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        // wl.alpha = 0.6f; //设置透明度
        lp.gravity = Gravity.CENTER; //设置重力
//         window.setAttributes(lp);
        // hasSetWindow = true;
    }

    Handler handler = new Handler();

    public void dismissDelayed(long delayMillis) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, delayMillis);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnNegative) {
            onNegativeBtnClick();
        } else if (v == mBtnPositive) {
            onPositiveBtnClick();
        }
        if (isOptDismiss) {
            dismiss();
        }
    }
}
