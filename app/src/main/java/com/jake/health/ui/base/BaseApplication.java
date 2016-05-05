
package com.jake.health.ui.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.jake.health.R;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by jakechen on 2015/8/5.
 */
public abstract class BaseApplication extends Application {
    public static BaseApplication mInstance;

    private static RefWatcher watcher;

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
        watcher = LeakCanary.install(this);
        onConfig();
    }

    public static void setWatcher(Object obj) {
        watcher.watch(obj);
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
            watcher.watch(activity);
            if (activity instanceof FragmentActivity){
                FragmentActivity fragmentActivity= (FragmentActivity) activity;
                List<Fragment> fragments= fragmentActivity.getSupportFragmentManager().getFragments();
                if (fragments!=null&&fragments.size()>0){
                    for (int i=0;i<fragments.size();i++){
                    watcher.watch(fragments.get(i));}
                }
            }
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
