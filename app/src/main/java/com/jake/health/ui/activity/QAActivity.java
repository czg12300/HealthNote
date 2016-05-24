
package com.jake.health.ui.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.jake.health.R;
import com.jake.health.entity.FragmentHolder;
import com.jake.health.ui.adapter.CommonFragmentPagerAdapter;
import com.jake.health.ui.fragment.QACommonFragment;
import com.jake.health.ui.fragment.QAIncurableFragment;
import com.jake.health.ui.fragment.QASexFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：求医问药
 *
 * @author jakechen
 * @since 2016/4/29 10:25
 */
public class QAActivity extends TabPagerActivity {

    @Override
    protected void initViewPager(ViewPager viewPager) {
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void initTitle() {
        setTitle(R.string.title_qa);
    }

    @Override
    protected PagerAdapter createAdapter() {
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(
                getSupportFragmentManager());
        List<FragmentHolder> list = new ArrayList<>();
        list.add(new FragmentHolder(QACommonFragment.getTabTitle(), QACommonFragment.newInstance()));
        list.add(new FragmentHolder(QASexFragment.getTabTitle(), QASexFragment.newInstance()));
        list.add(new FragmentHolder(QAIncurableFragment.getTabTitle(), QAIncurableFragment
                .newInstance()));
        adapter.setList(list);
        return adapter;
    }

}
