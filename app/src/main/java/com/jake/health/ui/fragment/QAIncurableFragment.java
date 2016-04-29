
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
 * 描述：疑难杂症
 *
 * @author jakechen
 * @since 2016/4/29 11:24
 */
public class QAIncurableFragment extends BaseListFragment<QAInfo> {
    @Override
    protected BaseListAdapter<QAInfo> createAdapter() {
        return new HomeAdapter(getActivity());
    }

    @Override
    protected List<QAInfo> loadData() {
        return TestHelper.loadQAInfoList(getAdapter(), getPageIndex());
    }
    public static String getTabTitle(){
        return HealthApplication.getInstance().getString(R.string.tab_qa_incurable);
    }
    public static QAIncurableFragment newInstance() {
        return new QAIncurableFragment();
    }
}
