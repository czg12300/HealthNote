package com.jake.health.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 描述：
 * 作者：jake on 2016/6/1 20:56
 */
public abstract class BaseListItemAdapter<T, H extends BaseViewHolder> extends BaseListAdapter<T> {
    public BaseListItemAdapter(Context context) {
        super(context);
    }

    public BaseListItemAdapter(Context context, Fragment fragment) {
        super(context, fragment);
    }

    public BaseListItemAdapter(Context context, List<T> list) {
        super(context, list);
    }

    public BaseListItemAdapter(Context context, Fragment fragment, List<T> list) {
        super(context, fragment, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        H holder = null;
        if (convertView == null) {
            convertView = inflate(getItemLayoutId());
            holder = createViewHolder(convertView);
            convertView.setTag(convertView.getId(), holder);
        } else {
            holder = (H) convertView.getTag(convertView.getId());
        }
        onBindViewHolder(holder, position);
        return convertView;
    }

    protected abstract void onBindViewHolder(H holder, int position);

    protected abstract H createViewHolder(View view);

    protected abstract int getItemLayoutId();
}
