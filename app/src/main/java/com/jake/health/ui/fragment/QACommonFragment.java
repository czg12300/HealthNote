
package com.jake.health.ui.fragment;

import com.jake.health.R;
import com.jake.health.entity.QAInfo;
import com.jake.health.ui.HealthApplication;
import com.jake.health.ui.adapter.BaseListAdapter;
import com.jake.health.ui.adapter.HomeAdapter;
import com.jake.health.ui.base.BaseListFragment;
import com.jake.health.ui.helper.TestHelper;

import java.util.List;

/**
 * 描述：常见病
 *
 * @author jakechen
 * @since 2016/4/29 11:24
 */
public class QACommonFragment extends BaseListFragment<QAInfo> {
    @Override
    protected BaseListAdapter<QAInfo> createAdapter() {
        return new HomeAdapter(getActivity());
    }

    @Override
    protected List<QAInfo> loadData() {
        return TestHelper.loadQAInfoList(getAdapter(), getPageIndex());
    }

    public static String getTabTitle() {
        return HealthApplication.getInstance().getString(R.string.tab_qa_common);
    }

    public static QACommonFragment newInstance() {
        return new QACommonFragment();
    }
}
