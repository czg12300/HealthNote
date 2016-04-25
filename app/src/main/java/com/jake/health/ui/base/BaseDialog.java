
package com.jake.health.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jake.health.R;

/**
 * 描述：dialog的父类 Created by jakechen on 2015/8/28.
 */
public class BaseDialog extends Dialog implements View.OnClickListener {

    private static final int DEFAULT_INT = -1;

    private Window window = null;

    protected FrameLayout mFlContent;

    protected TextView mTvTitle;

    protected Button mBtnPositive;

    protected Button mBtnNegative;

    protected View mVButton;

    public BaseDialog(Context context) {
        super(context);
        window = getWindow(); // 得到对话框
        super.setContentView(R.layout.dialog_common);
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnNegative = (Button) findViewById(R.id.btn_negative);
        mBtnPositive = (Button) findViewById(R.id.btn_positive);
        mVButton = findViewById(R.id.rl_opt_button_layout);
        mBtnNegative.setOnClickListener(this);
        mBtnPositive.setOnClickListener(this);
    }

    public void setPositiveButton(int negative, int positive) {
        setNegaticeButton(getContext().getString(negative), getContext().getString(negative));
    }

    public void setNegaticeButton(String negative, String positive) {
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
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
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
        // wl.alpha = 0.6f; //设置透明度
        // wl.gravity = Gravity.BOTTOM; //设置重力
        // window.setAttributes(lp);
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
        dismiss();
    }
}
