
package com.jake.health.ui.activity;

import com.jake.health.R;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * 描述：使用tab+viewpager的基类
 *
 * @author jakechen
 * @since 2016/4/29 10:25
 */
public abstract class TabPagerActivity extends TitleActivity {
    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    @Override
    protected void initView() {
        initTitle();
        setContentView(R.layout.activity_tab_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        initViewPager(mViewPager);
        mViewPager.setAdapter(createAdapter());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    protected void initViewPager(ViewPager viewPager) {
    }

    protected abstract PagerAdapter createAdapter();

    @Override
    protected void initEvent() {
        super.initEvent();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected void pageSelected(int position) {
    }

    protected abstract void initTitle();
}
