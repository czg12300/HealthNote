package com.jake.health.ui.base;

import android.view.View;

/**
 * 描述：ViewHolder的基类
 * 作者：jake on 2016/6/1 21:31
 */
public class BaseViewHolder {
    private View mItemView;

    public BaseViewHolder(View itemView) {
        this.mItemView = itemView;
    }

    protected View findViewById(int id) {
        return mItemView.findViewById(id);
    }
}
