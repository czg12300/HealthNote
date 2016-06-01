
package com.jake.health.ui.adapter;

import com.jake.health.R;
import com.jake.health.core.ImageLoadManager;
import com.jake.health.entity.HospitalInfo;
import com.jake.health.utils.ViewUtil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：附近诊所
 *
 * @author jakechen
 * @since 2016/4/29 16:57
 */
public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    private List<HospitalInfo> mList;

    private Context mContext;

    public HospitalAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public void setDataList(List<HospitalInfo> list) {
        if (list != null && list.size() > 0) {
            mList = list;
        }
    }

    public void addALlDataList(List<HospitalInfo> list) {
        if (list != null && list.size() > 0) {
            mList.addAll(list);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HospitalInfo info = mList.get(position);
        if (info != null) {
            ImageLoadManager.load(mContext, info.getAvater(), holder.ivAvatar, true);
            ViewUtil.setText2TextView(holder.tvTitle, info.getTitle());
            ViewUtil.setText2TextView(holder.tvNikeName, info.getNikeName());
            ViewUtil.setText2TextView(holder.tvContent, info.getContent());
            ViewUtil.setText2TextView(holder.tvZanNum, info.getZanNumText());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;

        TextView tvTitle;

        TextView tvContent;

        TextView tvNikeName;

        TextView tvZanNum;

        View vDivider;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvNikeName = (TextView) itemView.findViewById(R.id.tv_nike_name);
            tvZanNum = (TextView) itemView.findViewById(R.id.tv_zan_num);
            vDivider = itemView.findViewById(R.id.v_divider);
        }
    }
}
