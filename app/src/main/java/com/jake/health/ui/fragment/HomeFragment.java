
package com.jake.health.ui.fragment;

import android.graphics.Color;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.jake.health.R;
import com.jake.health.entity.HomeNavInfo;
import com.jake.health.entity.QAInfo;
import com.jake.health.ui.activity.AnalysisActivity;
import com.jake.health.ui.activity.HospitalActivity;
import com.jake.health.ui.activity.MomentsActivity;
import com.jake.health.ui.activity.QAActivity;
import com.jake.health.ui.adapter.BaseListAdapter;
import com.jake.health.ui.adapter.HomeAdapter;
import com.jake.health.ui.adapter.HomeNavAdapter;
import com.jake.health.ui.base.BaseListFragment;
import com.jake.health.ui.helper.TestHelper;
import com.jake.health.ui.widgt.ZoomOutPageTransformer;
import com.jake.health.ui.widgt.banner.BannerView;
import com.jake.health.utils.DisplayUtil;
import com.jake.health.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：首页
 *
 * @author jakechen
 * @since 2016/4/28 18:15
 */
public class HomeFragment extends BaseListFragment<QAInfo> {
    private static final int MSG_UI_TOP_DATA = 0x001;

    private GridView mGvNav;

    private HomeNavAdapter mHomeNavAdapter;

    private BannerView mBannerTop;

    private HomeAdapter mHomeAdapter;

    @Override
    protected BaseListAdapter<QAInfo> createAdapter() {
        mHomeAdapter = new HomeAdapter(getActivity());
        return mHomeAdapter;
    }

    @Override
    protected void addHeaderView(ListView listView) {
        super.addHeaderView(listView);
        listView.addHeaderView(inflate(R.layout.header_main));
    }

    @Override
    protected void initView() {
        super.initView();
        mGvNav = (GridView) findViewById(R.id.gv_nav);
        mBannerTop = (BannerView) findViewById(R.id.banner_top);
        mBannerTop.setStyle(BannerView.STYLE_DOT_RIGHT);
        mBannerTop.getDotView().setRadius(DisplayUtil.dip(2));
        mBannerTop.getDotView().setNormalColor(Color.parseColor("#60000000"));
        mBannerTop.getDotView().setSelectColor(Color.parseColor("#afffffff"));
        mBannerTop.getViewPager().setPageTransformer(true, new ZoomOutPageTransformer());
        mBannerTop.setDuration(300);
        mHomeNavAdapter = new HomeNavAdapter(getActivity());
        mGvNav.setAdapter(mHomeNavAdapter);
        setLoadViewBackgroundColor(Color.parseColor("#f9f9f9"));
    }

    @Override
    protected void onListItemClick(QAInfo qaInfo) {
        super.onListItemClick(qaInfo);
        ToastUtil.show(qaInfo.getTitle());
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        getActivity().findViewById(R.id.title_layout).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slide2Top();
                    }
                });
        mBannerTop.setBannerListener(new BannerView.IListener() {
            @Override
            public void clickBannerItem(Object banner) {
                String url = (String) banner;
                ToastUtil.show(url);
            }

            @Override
            public void loadImageToBanner(Object banner, ImageView ivBanner) {
                String url = (String) banner;
                loadImage(url, ivBanner);
            }
        });
        mGvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeNavInfo info = (HomeNavInfo) parent.getAdapter().getItem(position);
                switch (info.getType()) {
                    case HomeNavInfo.TYPE_QA:
                        goActivity(QAActivity.class);
                        break;
                    case HomeNavInfo.TYPE_ANALYSIS:
                        goActivity(AnalysisActivity.class);
                        break;
                    case HomeNavInfo.TYPE_MOMENTS:
                        goActivity(MomentsActivity.class);
                        break;
                    case HomeNavInfo.TYPE_HOSPITAL:
                        goActivity(HospitalActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case MSG_UI_TOP_DATA:
                mBannerTop.notifyDataSetChanged();
                mHomeNavAdapter.notifyDataSetChanged();
                mBannerTop.startScroll(5);
                break;
        }
    }

    private boolean isFirstIn = true;

    @Override
    protected List<QAInfo> loadData() {
//        if (isFirstIn) {
            mBannerTop.setBannerList(TestHelper.getTestBanner());
            mHomeNavAdapter.setData(TestHelper.getTestNav());
            sendEmptyUiMessage(MSG_UI_TOP_DATA);
            isFirstIn = false;
//        }

        return TestHelper.loadQAInfoList(getAdapter(), getPageIndex());
    }

}
