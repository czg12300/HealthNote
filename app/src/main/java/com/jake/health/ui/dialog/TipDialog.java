
package com.jake.health.ui.dialog;

import com.jake.health.R;
import com.jake.health.ui.base.BaseDialog;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * 描述：通用弹窗
 *
 * @author jakechen
 * @since 2016/4/25 15:36
 */
public class TipDialog extends BaseDialog {

    private TextView mTvContent;

    public TipDialog(Context context) {
        super(context);
        mTvContent = new TextView(context);
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources()
                .getDimension(R.dimen.text_size_content));
        mTvContent.setTextColor(Color.parseColor("#363636"));
        setContentView(mTvContent);
    }

    public void setTip(String tip) {
        mTvContent.setText(tip);
    }

    public void show(String tip) {
        mTvContent.setText(tip);
        show();
    }
}
