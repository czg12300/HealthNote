
package com.jake.health.ui.helper;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.jake.health.R;
import com.jake.health.entity.HomeSheetInfo;
import com.jake.health.ui.activity.AddAnalysisActivity;
import com.jake.health.ui.adapter.BaseListAdapter;
import com.jake.health.ui.base.BaseFragmentActivity;
import com.jake.health.ui.widgt.materialdesign.FabButton;
import com.jake.health.utils.ToastUtil;
import com.jake.health.utils.ViewUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：首页floatButton的操作逻辑
 *
 * @author jakechen
 * @since 2016/4/27 10:58
 */
public class MainFabHelper implements AdapterView.OnItemClickListener {
    private MaterialSheetFab<FabButton> mMaterialSheetFab;

    private FabButton mFab;

    private CardView mCardView;

    private BaseFragmentActivity mActivity;

    public MainFabHelper(BaseFragmentActivity activity) {
        mActivity = activity;
        setupFab();
    }

    private void setupFab() {
        mFab = (FabButton) findViewById(R.id.fab_opt);
        mCardView = (CardView) findViewById(R.id.cv_fab_sheet);
        // mFab.getBackgroundTintList().getDefaultColor()
        ListView lvSheet = (ListView) findViewById(R.id.lv_sheet);
        lvSheet.setAdapter(new HomeFabSheetAdapter(mActivity, getSheetData()));
        lvSheet.setOnItemClickListener(this);
        mMaterialSheetFab = new MaterialSheetFab<>(mFab, mCardView, findViewById(R.id.overlay),
                Color.WHITE, mFab.getBackgroundTintList().getDefaultColor());
        mMaterialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
            }

            @Override
            public void onHideSheet() {
                // Restore status bar color
                // setStatusBarColor(statusBarColor);
            }
        });
    }

    private List<HomeSheetInfo> getSheetData() {
        List<HomeSheetInfo> list = new ArrayList<>();
        HomeSheetInfo info = new HomeSheetInfo();
        info.setIcon("http://img0.imgtn.bdimg.com/it/u=3736756504,4060954350&fm=21&gp=0.jpg");
        info.setName(getString(R.string.home_fab_sheet_dt));
        list.add(info);
        info = new HomeSheetInfo();
        info.setIcon("http://img3.imgtn.bdimg.com/it/u=415649817,3173582892&fm=21&gp=0.jpg");
        info.setName(getString(R.string.home_fab_sheet_bl));
        list.add(info);
        info = new HomeSheetInfo();
        info.setName(getString(R.string.home_fab_sheet_yy));
        info.setIcon("http://img4.imgtn.bdimg.com/it/u=155521134,3298666642&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeSheetInfo();
        info.setName(getString(R.string.home_fab_sheet_wbl));
        info.setIcon("http://img5.imgtn.bdimg.com/it/u=995063541,2109037826&fm=21&gp=0.jpg");
        list.add(info);
        return list;
    }

    private String getString(int id) {
        return mActivity.getString(id);
    }

    private View findViewById(int id) {
        return mActivity.findViewById(id);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HomeSheetInfo info = (HomeSheetInfo) parent.getAdapter().getItem(position);
        switch (position) {
            case 0:
                // TODO
                break;
            case 1:
                mActivity.goActivity(AddAnalysisActivity.class);
                break;
            case 2:
                break;
            case 3:
                break;
        }
        ToastUtil.show(info.getName());
        mMaterialSheetFab.hideSheet();
    }

    class HomeFabSheetAdapter extends BaseListAdapter<HomeSheetInfo> {
        public HomeFabSheetAdapter(Context context, List<HomeSheetInfo> list) {
            super(context, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflate(R.layout.item_home_fab_sheet);
                holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(convertView.getId(), holder);
            } else {
                holder = (ViewHolder) convertView.getTag(convertView.getId());
            }
            HomeSheetInfo info = mDataList.get(position);
            if (info != null) {
                ViewUtil.setText2TextView(holder.tvName, info.getName());
                if (mImageLoadListener != null) {
                    mImageLoadListener.loadImageByUrl(info.getIcon(), holder.ivIcon);
                }
            }
            return convertView;
        }

        final class ViewHolder {
            TextView tvName;

            ImageView ivIcon;
        }
    }
}
