
package com.jake.health.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.entity.QAInfo;
import com.jake.health.utils.ViewUtil;

/**
 * 描述：首页数据
 *
 * @author jakechen
 * @since 2016/4/26 16:18
 */
public class HomeAdapter extends BaseListAdapter<QAInfo> {
    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflate(R.layout.item_home);
            holder.ivAvatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tvNikeName = (TextView) convertView.findViewById(R.id.tv_nike_name);
            holder.tvZanNum = (TextView) convertView.findViewById(R.id.tv_zan_num);
            holder.vDivider = convertView.findViewById(R.id.v_divider);
            convertView.setTag(convertView.getId(), holder);
        } else {
            holder = (ViewHolder) convertView.getTag(convertView.getId());
        }
        if (position < getCount() - 1) {
            holder.vDivider.setVisibility(View.VISIBLE);
        } else {
            holder.vDivider.setVisibility(View.GONE);
        }
        QAInfo info = mDataList.get(position);
        if (info != null) {
            if (mImageLoadListener != null) {
                mImageLoadListener.loadImageByUrl(info.getAvater(), holder.ivAvatar, true);
            }
            ViewUtil.setText2TextView(holder.tvTitle, info.getTitle());
            ViewUtil.setText2TextView(holder.tvNikeName, info.getNikeName());
            ViewUtil.setText2TextView(holder.tvContent, info.getContent());
            ViewUtil.setText2TextView(holder.tvZanNum, info.getZanNumText());
        }
        return convertView;
    }

    final class ViewHolder {
        ImageView ivAvatar;

        TextView tvTitle;

        TextView tvContent;

        TextView tvNikeName;

        TextView tvZanNum;
        View vDivider;
    }
}
