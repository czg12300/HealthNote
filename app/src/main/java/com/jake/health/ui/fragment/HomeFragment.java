
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
import com.jake.health.ui.adapter.BaseListAdapter;
import com.jake.health.ui.adapter.HomeAdapter;
import com.jake.health.ui.adapter.HomeNavAdapter;
import com.jake.health.ui.base.BaseListFragment;
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
    protected void initEvent() {
        super.initEvent();
        getActivity().findViewById(R.id.title_layout).setOnClickListener(new View.OnClickListener() {
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
                if (info != null) {
                    ToastUtil.show(info.getTitle());
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
        if (isFirstIn) {
            mBannerTop.setBannerList(getTestBanner());
            mHomeNavAdapter.setData(getTestNav());
            sendEmptyUiMessage(MSG_UI_TOP_DATA);
            isFirstIn = false;
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<QAInfo> list = getTestQA();
        if (mHomeAdapter.getCount() > 50&&getPageIndex()>1) {
            list.remove(list.size() - 1);
            list.remove(list.size() - 2);
        }
        return list;
    }

    private List<QAInfo> getTestQA() {
        List<QAInfo> list = new ArrayList<>();
        QAInfo info = new QAInfo();
        info.setAvater("http://img3.imgtn.bdimg.com/it/u=1011027398,356111671&fm=21&gp=0.jpg");
        info.setNickName("漂亮的不像实力派");
        info.setTitle("科比来自哪里?");
        info.setZanNum(12340);
        info.setContent("科比童年科比童年科比·布莱恩特于1978年8月23日出生在美国宾夕法尼亚州费城，是前NBA球员及前洛杉矶火花队主教练乔·布莱恩特（Joe “Jellybean” Bryant）科比父子科比父子和帕梅拉·考克斯·布莱恩特（Pamela Cox Bryant）三个孩子中最小的一个也是唯一的儿子。[4-5]  他的父母在他出生前为他取名Kobe--一种日本牛排[3]  的名字， 是在一家餐馆的菜单上看到的。[4]  科比有两个姐姐，西莉亚和沙雅。科比的父亲乔，在NBA效力8个赛季");
        list.add(info);
        info = new QAInfo();
        info.setAvater("http://img1.imgtn.bdimg.com/it/u=3767588862,2668021829&fm=21&gp=0.jpg");
        info.setNickName("我是科比");
        info.setTitle("科比来自与宇宙银河太阳系地球美洲美国洛杉矶的哪里?");
        info.setZanNum(140);
        info.setContent("在位于费城郊区高中时期的科比的劳尔梅里恩的劳尔梅里恩高中，科比凭借惊人的高中生涯赢得了全美国的认可。作为一个新人，科比就可以在学校（三年级和四年级）篮球队出任首发。");
        list.add(info);
        for (int i = 0; i < 8; i++) {
            info = new QAInfo();
            info.setAvater("http://img1.imgtn.bdimg.com/it/u=435937637,1527161840&fm=21&gp=0.jpg");
            info.setNickName("我是批量new出来的");
            info.setZanNum(990 + i);
            info.setTitle("我是批量new出来的科比来自与宇宙银河太阳系地球美洲美国洛杉矶的哪里?");
            info.setContent("在位于费城郊区高中时期的科比的劳尔梅里恩的劳尔梅里恩高中，科比凭借惊人的高中生涯赢得了全美国的认可。作为一个新人，科比就可以在学校（三年级和四年级）篮球队出任首发。");
            list.add(info);
        }
        return list;
    }

    private ArrayList<?> getTestBanner() {
        ArrayList<String> list = new ArrayList<>();
        list.add("http://img0.imgtn.bdimg.com/it/u=535245040,1392341624&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=513166044,2711533450&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=2877222865,4042023416&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2014305729,1419052095&fm=21&gp=0.jpg");
        return list;
    }

    private List<HomeNavInfo> getTestNav() {
        List<HomeNavInfo> list = new ArrayList<>();
        HomeNavInfo info = new HomeNavInfo();
        info.setTitle("求医问药");
        info.setShowDot(1);
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=1390048268,4118739760&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setTitle("病理分析");
        info.setIcon("http://img5.imgtn.bdimg.com/it/u=3297870962,4077987313&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setTitle("七嘴八舌");
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=3119963880,936101450&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setTitle("附近诊所");
        info.setIcon("http://img3.imgtn.bdimg.com/it/u=2832776744,1381723459&fm=21&gp=0.jpg");
        list.add(info);

        return list;
    }
}
