
package com.jake.health.ui.activity;

import com.jake.health.R;
import com.jake.health.entity.FragmentHolder;
import com.jake.health.ui.adapter.CommonFragmentPagerAdapter;
import com.jake.health.ui.fragment.AnalysisCommonFragment;
import com.jake.health.ui.fragment.AnalysisIncurableFragment;
import com.jake.health.ui.fragment.AnalysisInfectiousFragment;
import com.jake.health.ui.fragment.QACommonFragment;
import com.jake.health.ui.fragment.QAIncurableFragment;
import com.jake.health.ui.fragment.QASexFragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：病理分析
 *
 * @author jakechen
 * @since 2016/4/29 10:25
 */
public class AnalysisActivity extends TabPagerActivity {

    @Override
    protected void initViewPager(ViewPager viewPager) {
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void initTitle() {
        setTitle(R.string.title_analysis);
    }

    @Override
    protected PagerAdapter createAdapter() {
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(
                getSupportFragmentManager());
        List<FragmentHolder> list = new ArrayList<>();
        list.add(new FragmentHolder(AnalysisCommonFragment.getTabTitle(), AnalysisCommonFragment
                .newInstance()));
        list.add(new FragmentHolder(AnalysisInfectiousFragment.getTabTitle(),
                AnalysisInfectiousFragment.newInstance()));
        list.add(new FragmentHolder(AnalysisIncurableFragment.getTabTitle(),
                AnalysisIncurableFragment.newInstance()));
        adapter.setList(list);
        return adapter;
    }

}
