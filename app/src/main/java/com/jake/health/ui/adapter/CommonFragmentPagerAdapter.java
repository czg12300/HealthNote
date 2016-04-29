
package com.jake.health.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jake.health.entity.FragmentHolder;

import java.util.List;

/**
 * 描述：通用的fragment pagerAdapter
 *
 * @author jakechen
 * @since 2016/4/29 11:19
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<FragmentHolder> list;

    public List<FragmentHolder> getList() {
        return list;
    }

    public void setList(List<FragmentHolder> list) {
        this.list = list;
    }

    public CommonFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list != null && list.get(position) != null ? list.get(position).getFragment() : null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list != null && list.get(position) != null ? list.get(position).getTitle() : null;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

}
