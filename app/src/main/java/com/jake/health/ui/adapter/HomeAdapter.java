
package com.jake.health.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.entity.QAInfo;
import com.jake.health.ui.base.BaseListAdapter;
import com.jake.health.ui.base.BaseListItemAdapter;
import com.jake.health.ui.base.BaseViewHolder;
import com.jake.health.utils.ViewUtil;

/**
 * 描述：首页数据
 *
 * @author jakechen
 * @since 2016/4/26 16:18
 */
public class HomeAdapter extends BaseListItemAdapter<QAInfo, HomeAdapter.ViewHolder> {
    public HomeAdapter(Context context) {
        super(context);
    }


    @Override
    protected void onBindViewHolder(ViewHolder holder, int position) {
        if (position < getCount() - 1) {
            holder.vDivider.setVisibility(View.VISIBLE);
        } else {
            holder.vDivider.setVisibility(View.GONE);
        }
        QAInfo info = mDataList.get(position);
        if (info != null) {
            loadImage(info.getAvater(), holder.ivAvatar, true);
            ViewUtil.setText2TextView(holder.tvTitle, info.getTitle());
            ViewUtil.setText2TextView(holder.tvNikeName, info.getNikeName());
            ViewUtil.setText2TextView(holder.tvContent, info.getContent());
            ViewUtil.setText2TextView(holder.tvZanNum, info.getZanNumText());
        }
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_home;
    }

    static final class ViewHolder extends BaseViewHolder {
        ImageView ivAvatar;

        TextView tvTitle;

        TextView tvContent;

        TextView tvNikeName;

        TextView tvZanNum;
        View vDivider;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
            tvTitle = (TextView) findViewById(R.id.tv_title);
            tvContent = (TextView) findViewById(R.id.tv_content);
            tvNikeName = (TextView) findViewById(R.id.tv_nike_name);
            tvZanNum = (TextView) findViewById(R.id.tv_zan_num);
            vDivider = findViewById(R.id.v_divider);
        }
    }
}
