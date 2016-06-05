
package com.jake.health.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.entity.AnalysisInfo;
import com.jake.health.ui.base.BaseListItemAdapter;
import com.jake.health.ui.base.BaseViewHolder;
import com.jake.health.utils.ViewUtil;

/**
 * 描述：病理分析
 *
 * @author jakechen
 * @since 2016/4/26 16:18
 */
public class AnalysisAdapter extends BaseListItemAdapter<AnalysisInfo, AnalysisAdapter.ViewHolder> {
    public AnalysisAdapter(Context context) {
        super(context);
    }


    @Override
    protected void onBindViewHolder(ViewHolder holder, int position) {
        if (position < getCount() - 1) {
            holder.vDivider.setVisibility(View.VISIBLE);
        } else {
            holder.vDivider.setVisibility(View.GONE);
        }
        AnalysisInfo info = mDataList.get(position);
        if (info != null) {
            loadImage(info.getAvatar(), holder.ivAvatar, true);
            ViewUtil.setText2TextView(holder.tvTitle, info.getTitle());
            ViewUtil.setText2TextView(holder.tvNickname, info.getNickname());
            ViewUtil.setText2TextView(holder.tvSummary, info.getSummary());
            ViewUtil.setText2TextView(holder.tvZanNum, info.getZanNumText());
            ViewUtil.setText2TextView(holder.tvDate, info.getDateText());
        }
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_analysis;
    }

    static final class ViewHolder extends BaseViewHolder {
        ImageView ivAvatar;

        TextView tvTitle;

        TextView tvSummary;

        TextView tvNickname;

        TextView tvZanNum;
        TextView tvDate;
        View vDivider;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
            tvTitle = (TextView) findViewById(R.id.tv_title);
            tvSummary = (TextView) findViewById(R.id.tv_summary);
            tvNickname = (TextView) findViewById(R.id.tv_nike_name);
            tvZanNum = (TextView) findViewById(R.id.tv_zan_num);
            tvDate = (TextView) findViewById(R.id.tv_date);
            vDivider = findViewById(R.id.v_divider);
        }
    }
}
