
package com.jake.health.ui.base;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragmentActivity extends FragmentActivity implements IUi,ImageLoadListener {
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

    private ArrayList<String> mActions;

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getInstance().addActivity(this);
        dealIntent(getIntent().getExtras());
        mUiHandler = new UiHandler(this);
        mActions = new ArrayList<String>();
        setupBroadcastActions(mActions);
        if (mActions != null && mActions.size() > 0) {
            IntentFilter filter = new IntentFilter();
            for (String action : mActions) {
                filter.addAction(action);
            }
            mReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    handleBroadcast(context, intent);
                }
            };
            registerReceiver(mReceiver, filter);
        }
    }

    @Override
    public void loadImageByUrl(String url, ImageView imageView) {
        loadImage(url,imageView);
    }

    protected void loadImage(String url, ImageView view) {
        loadImage(url,view,false);
    }

    protected void loadImage(String url, ImageView view, boolean isCircle) {
        if (view != null && !TextUtils.isEmpty(url)) {
            if (isCircle) {
                Glide.with(this).load(url).asBitmap()
                        .placeholder(BaseApplication.getInstance().getDefaultImageResources()).into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });
            } else {
                Glide.with(this).load(url)
                        .placeholder(BaseApplication.getInstance().getDefaultImageResources())
                        .crossFade(800).into(view);
            }
        }
    }
    protected void dealIntent(Bundle data) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
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
    public void setupBroadcastActions(List<String> actions) {
    }

    @Override
    public void handleBroadcast(Context context, Intent intent) {
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
        Intent it = new Intent();
        it.setClass(this, clazz);
        if (bundle != null) {
            it.putExtras(bundle);
        }
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