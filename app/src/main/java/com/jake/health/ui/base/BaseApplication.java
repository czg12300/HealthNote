
package com.jake.health.ui.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;


import com.jake.health.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by jakechen on 2015/8/5.
 */
public abstract class BaseApplication extends Application {
    public static BaseApplication mInstance;

    private HashMap<String, WeakReference<Activity>> mActivityMap;

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        onRelease();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = getChildInstance();
        mActivityMap = new HashMap<String, WeakReference<Activity>>();
//        LeakCanary.install(this);
        onConfig();
    }

    protected abstract void onConfig();

    protected abstract void onRelease();

    public int getDefaultImageResources() {
        return R.drawable.test_avatar;
    }

    public void addActivity(Activity activity) {
        if (activity != null) {
            mActivityMap.put(activity.getClass().getSimpleName(), new WeakReference<Activity>(
                    activity));
        }
    }

    public void removeActivity(String activityName) {
        if (mActivityMap != null && mActivityMap.containsKey(activityName)) {
            mActivityMap.remove(activityName);
        }
    }

    public void exitApp() {
        if (mActivityMap != null && mActivityMap.size() > 0) {
            for (String key : mActivityMap.keySet()) {
                if (mActivityMap.get(key) != null) {
                    mActivityMap.get(key).get().finish();
                }
            }
        }
        mActivityMap.clear();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public Context getContext() {
        return getApplicationContext();
    }

    protected abstract BaseApplication getChildInstance();

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }
}
