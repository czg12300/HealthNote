
package com.jake.health.ui.base;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jake.health.config.GlideConfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragmentActivity extends FragmentActivity implements IUi,
        ImageLoadListener {
    protected static final int REQUEST_CODE = 0x123f;

    protected static final int RESULT_CODE = 0x124f;

    /**
     * layout params
     */
    public static final int MATCH_PARENT = -1;

    /**
     * layout params
     */
    public static final int WRAP_CONTENT = -2;

    private Handler mUiHandler;

    private static class UiHandler extends Handler {
        private final WeakReference<BaseFragmentActivity> mActivityReference;

        public UiHandler(BaseFragmentActivity activity) {
            mActivityReference = new WeakReference<BaseFragmentActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivityReference.get() != null) {
                mActivityReference.get().handleUiMessage(msg);
            }
        }

        ;
    }

    private ArrayList<String> mSystemActions;

    private ArrayList<String> mLocalActions;

    private BroadcastReceiver mLocalReceiver;

    private BroadcastReceiver mSystemReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getInstance().addActivity(this);
        dealIntent(getIntent().getExtras());
        mUiHandler = new UiHandler(this);
        registerLocalReceiver();
        registerSystemReceiver();
    }

    @Override
    public void setupLocalActions(List<String> actions) {

    }

    private void registerLocalReceiver() {
        mLocalActions = new ArrayList<String>();
        setupLocalActions(mLocalActions);
        if (mLocalActions != null && mLocalActions.size() > 0) {
            IntentFilter filter = new IntentFilter();
            for (String action : mSystemActions) {
                filter.addAction(action);
            }
            ;
            mLocalReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    handleLocalBroadcast(context, intent);
                }
            };
            LocalBroadcastManager.getInstance(this).registerReceiver(mLocalReceiver, filter);
        } else {
            mLocalActions.clear();
            mLocalActions = null;
        }
    }

    private void registerSystemReceiver() {
        mSystemActions = new ArrayList<String>();
        setupSystemActions(mSystemActions);
        if (mSystemActions != null && mSystemActions.size() > 0) {
            IntentFilter filter = new IntentFilter();
            for (String action : mSystemActions) {
                filter.addAction(action);
            }
            mSystemReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    handleSystemBroadcast(context, intent);
                }
            };
            registerReceiver(mSystemReceiver, filter);
        } else {
            mSystemActions.clear();
            mSystemActions = null;
        }
    }

    @Override
    public void loadImageByUrl(String url, ImageView imageView) {
        loadImage(url, imageView);
    }

    @Override
    public void loadImageByUrl(String url, ImageView imageView, boolean isCircle) {
        loadImage(url, imageView, isCircle);
    }

    protected void loadImage(String url, ImageView view) {
        loadImage(url, view, false);
    }

    protected void loadImage(String url, ImageView view, boolean isCircle) {
        GlideConfig.loadImage(this, url, view, isCircle);
    }

    protected void dealIntent(Bundle data) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSystemReceiver != null) {
            unregisterReceiver(mSystemReceiver);
        }
        if (mLocalReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mLocalReceiver);
        }
        BaseApplication.getInstance().removeActivity(this.getClass().getSimpleName());
    }

    protected void sendUiMessage(Message msg) {
        mUiHandler.sendMessage(msg);
    }

    protected void sendUiMessageDelayed(Message msg, long delayMillis) {
        mUiHandler.sendMessageDelayed(msg, delayMillis);
    }

    protected void sendEmptyUiMessage(int what) {
        mUiHandler.sendEmptyMessage(what);
    }

    protected void sendEmptyUiMessageDelayed(int what, long delayMillis) {
        mUiHandler.sendEmptyMessageDelayed(what, delayMillis);
    }

    protected void removeUiMessages(int what) {
        mUiHandler.removeMessages(what);
    }

    protected Message obtainUiMessage() {
        return mUiHandler.obtainMessage();
    }

    @Override
    public void setupSystemActions(List<String> actions) {
    }

    @Override
    public void handleLocalBroadcast(Context context, Intent intent) {
    }

    @Override
    public void handleSystemBroadcast(Context context, Intent intent) {
    }

    @Override
    public void handleUiMessage(Message msg) {
    }

    /**
     * 获取资源文件的颜色值
     *
     * @param id
     * @return
     */
    protected int getResourceColor(int id) {
        return getResources().getColor(id);
    }

    /**
     * 获取资源文件的尺寸
     *
     * @param id
     * @return
     */
    protected float getDimension(int id) {
        return getResources().getDimension(id);
    }

    /**
     * 获取资源文件int数组
     *
     * @param id
     * @return
     */
    protected int[] getIntArray(int id) {
        return getResources().getIntArray(id);
    }

    /**
     * 获取资源文件string数组
     *
     * @param id
     * @return
     */
    protected String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    @Override
    public void goActivity(Class<?> clazz) {
        goActivity(clazz, null);
    }

    @Override
    public void goActivity(Class<?> clazz, Bundle bundle) {
        goActivity(clazz, bundle, 0);
    }

    @Override
    public void goActivity(Class<?> clazz, Bundle bundle, int flag) {
        Intent it = new Intent();
        it.setClass(this, clazz);
        if (bundle != null) {
            it.putExtras(bundle);
        }
        it.setFlags(flag);
        startActivity(it);
    }

    @Override
    public void goActivityForResult(Class<?> clazz) {
        goActivityForResult(clazz, null);
    }

    @Override
    public void goActivityForResult(Class<?> clazz, Bundle bundle) {
        goActivityForResult(clazz, bundle, REQUEST_CODE);
    }

    @Override
    public void goActivityForResult(Class<?> clazz, Bundle bundle, int requestCode) {
        Intent it = new Intent();
        it.setClass(this, clazz);
        if (bundle != null) {
            it.putExtras(bundle);
        }
        startActivityForResult(it, requestCode);

    }

    public void sendBroadcast(String action) {
        sendBroadcast(new Intent(action));
    }

    public View inflate(int layoutId) {
        return inflate(layoutId, null);
    }

    public View inflate(int layoutId, ViewGroup viewGroup) {
        return getLayoutInflater().inflate(layoutId, viewGroup);
    }
}
