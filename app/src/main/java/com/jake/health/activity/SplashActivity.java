
package com.jake.health.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.jake.health.MainActivity;

import org.w3c.dom.Text;

import cn.common.ui.activity.BaseWorkerFragmentActivity;

/**
 * 描述：闪屏
 *
 * @author jakechen
 * @since 2016/4/19 15:10
 */
public class SplashActivity extends BaseWorkerFragmentActivity {
    private static final int MSG_UI_LOGIN = 0;

    private static final int MSG_UI_MAIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("欢迎来到健康卫士");
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.parseColor("#3F51B5"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 38);
        setContentView(textView);
        sendEmptyUiMessageDelayed(MSG_UI_MAIN,500);
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case MSG_UI_LOGIN:

                break;
            case MSG_UI_MAIN:
                goActivity(MainActivity.class);
                break;
        }
    }
}
