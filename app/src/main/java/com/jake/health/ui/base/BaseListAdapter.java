
package com.jake.health.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jake.health.core.ImageLoadManager;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {
    private Context mContext;
    protected Fragment mFragment;
    private LayoutInflater mInflater;

    protected List<T> mDataList;

    public Context getContext() {
        return mContext;
    }

    public LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    public int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    public float getDimension(int id) {
        return getContext().getResources().getDimension(id);
    }

    public BaseListAdapter(Context context) {
        this(context, null, null);
    }

    public BaseListAdapter(Context context, Fragment fragment) {
        this(context, fragment, null);
    }

    public BaseListAdapter(Context context, List<T> list) {
        this(context, null, list);
    }

    public BaseListAdapter(Context context, Fragment fragment, List<T> list) {
        mFragment = fragment;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        if (isAvailable(list)) {
            mDataList = list;
        } else {
            mDataList = new ArrayList<T>();
        }
    }

    public void setData(List<T> list) {
        if (list != null && list.size() > 0) {
            mDataList = list;
        }
    }

    public void clearAllData() {
        mDataList.clear();
    }

    public void setDataAndNotifyDataSetChanged(List<T> list) {
        if (list != null && list.size() > 0) {
            mDataList = list;
            notifyDataSetChanged();
        }
    }

    public List<T> getDataList() {
        return mDataList;
    }

    protected boolean isAvailable(List<T> list) {
        return list != null && list.size() > 0;
    }

    protected boolean isAvailable(T t) {
        return t != null;
    }

    public void addAll(List<T> list) {
        if (list != null && list.size() > 0) {
            mDataList.addAll(list);
        }
    }

    public void addAll(int position, List<T> list) {
        if (list != null && list.size() > 0) {
            mDataList.addAll(position, list);
        }
    }

    public void addAllAndNotifyDataSetChanged(List<T> list) {
        if (list != null && list.size() > 0) {
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addData(T t) {
        if (t != null) {
            mDataList.add(t);
            notifyDataSetChanged();
        }
    }

    protected void loadImage(String url, ImageView view) {
        loadImage(url, view, false);
    }

    protected void loadImage(String url, ImageView view, boolean isCircle) {
        if (mFragment != null) {
            ImageLoadManager.load(mFragment, url, view, isCircle);
        } else {
            ImageLoadManager.load(mContext, url, view, isCircle);
        }
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected View inflate(int layoutId, ViewGroup viewGroup) {
        return getLayoutInflater().inflate(layoutId, viewGroup);
    }

    protected View inflate(int layoutId) {
        return inflate(layoutId, null);
    }
}
