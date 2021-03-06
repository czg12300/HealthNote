
package com.jake.health.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.entity.QAInfo;
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
            loadImage(info.getAvatar(), holder.ivAvatar, true);
            ViewUtil.setText2TextView(holder.tvQuestion, info.getTitle());
            ViewUtil.setText2TextView(holder.tvNikeName, info.getNikeName());
            ViewUtil.setText2TextView(holder.tvHotAnswer, info.getContent());
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

        TextView tvQuestion;

        TextView tvHotAnswer;

        TextView tvNikeName;

        TextView tvOtherAnswerNum;
        TextView tvZanNum;
        View vDivider;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
            tvQuestion = (TextView) findViewById(R.id.tv_question);
            tvHotAnswer = (TextView) findViewById(R.id.tv_hot_answer);
            tvNikeName = (TextView) findViewById(R.id.tv_nike_name);
            tvZanNum = (TextView) findViewById(R.id.tv_zan_num);
            tvOtherAnswerNum = (TextView) findViewById(R.id.tv_other_answer_num);
            vDivider = findViewById(R.id.v_divider);
        }
    }
}
