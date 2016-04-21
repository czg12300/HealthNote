package com.jake.health.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.entity.HomeNavInfo;
import com.jake.health.entity.MineMenuInfo;
import com.jake.health.ui.base.ImageLoadListener;
import com.jake.health.utils.ViewUtil;

/**
 * 描述：个人信息适配器
 *
 * @author jakechen
 * @since 2016/4/20 18:34
 */
public class MineMenuAdapter extends BaseListAdapter<MineMenuInfo> {

    public MineMenuAdapter(Context context, ImageLoadListener listener) {
        super(context, listener);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflate(R.layout.item_mine_menu);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.vRedDot = convertView.findViewById(R.id.v_red_dot);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(convertView.getId(), holder);
        } else {
            holder = (ViewHolder) convertView.getTag(convertView.getId());
        }
        MineMenuInfo info = mDataList.get(position);
        if (info != null) {
            ViewUtil.setText2TextView(holder.tvName, info.getTitle());
            if (info.getShowDot() > 0) {
                holder.vRedDot.setVisibility(View.VISIBLE);
            } else {
                holder.vRedDot.setVisibility(View.GONE);
            }
            if (mImageLoadListener != null) {
                mImageLoadListener.loadImageByUrl(info.getIcon(), holder.ivIcon);
            }
        }
        return convertView;
    }

    final class ViewHolder {
        TextView tvName;
        ImageView ivIcon;
        View vRedDot;
    }
}
