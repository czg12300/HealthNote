
package com.jake.health.ui.fragment;

import com.jake.health.R;
import com.jake.health.entity.AnalysisInfo;
import com.jake.health.entity.QAInfo;
import com.jake.health.ui.HealthApplication;
import com.jake.health.ui.adapter.AnalysisAdapter;
import com.jake.health.ui.base.BaseListAdapter;
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
public class AnalysisCommonFragment extends BaseListFragment<AnalysisInfo> {
    @Override
    protected BaseListAdapter<AnalysisInfo> createAdapter() {
        return new AnalysisAdapter(getActivity());
    }

    @Override
    protected List<AnalysisInfo> loadData() {
        return TestHelper.loadAnalysisInfoList(getAdapter(), getPageIndex());
    }

    public static String getTabTitle() {
        return HealthApplication.getInstance().getString(R.string.tab_analysis_common);
    }

    public static AnalysisCommonFragment newInstance() {
        return new AnalysisCommonFragment();
    }
}
