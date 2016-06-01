package com.jake.health.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.entity.HomeNavInfo;
import com.jake.health.ui.base.BaseListAdapter;
import com.jake.health.ui.base.BaseListItemAdapter;
import com.jake.health.ui.base.BaseViewHolder;
import com.jake.health.utils.ViewUtil;

/**
 * 描述：首页入口适配器
 *
 * @author jakechen
 * @since 2016/4/20 18:34
 */
public class HomeNavAdapter extends BaseListItemAdapter<HomeNavInfo, HomeNavAdapter.ViewHolder> {

    public HomeNavAdapter(Context context) {
        super(context);
    }


    @Override
    protected void onBindViewHolder(ViewHolder holder, int position) {
        HomeNavInfo info = mDataList.get(position);
        if (info != null) {
            ViewUtil.setText2TextView(holder.tvName, info.getTitle());
            if (info.getShowDot() > 0) {
                holder.vRedDot.setVisibility(View.VISIBLE);
            } else {
                holder.vRedDot.setVisibility(View.GONE);
            }
            loadImage(info.getIcon(), holder.ivIcon);
        }
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_home_nav;
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView tvName;
        ImageView ivIcon;
        View vRedDot;

        public ViewHolder(View itemView) {
            super(itemView);
            vRedDot = findViewById(R.id.v_red_dot);
            ivIcon = (ImageView) findViewById(R.id.iv_icon);
            tvName = (TextView) findViewById(R.id.tv_name);
        }
    }
}
