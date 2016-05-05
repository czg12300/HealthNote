
package com.jake.health.ui.activity;

import android.graphics.Canvas;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.jake.health.R;
import com.jake.health.ui.adapter.HospitalAdapter;
import com.jake.health.ui.helper.DividerGridItemDecoration;
import com.jake.health.ui.helper.TestHelper;
import com.jake.health.ui.widgt.StatusView;

/**
 * 描述：附近诊所
 *
 * @author jakechen
 * @since 2016/4/29 16:51
 */
public class HospitalActivity extends TitleActivity {
    private static final int MSG_UI_INIT_DATA = 0x001;

    private StatusView mStatusView;

    private RecyclerView mRecyclerView;

    private HospitalAdapter mHospitalAdapter;

    @Override
    protected void initView() {
        setTitle(R.string.title_hospital);
        mStatusView = new StatusView(this);
        mRecyclerView = new RecyclerView(this);
        mStatusView.setContentView(mRecyclerView);
        setContentView(mStatusView);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mHospitalAdapter = new HospitalAdapter(this);
        mRecyclerView.setAdapter(mHospitalAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        sendEmptyUiMessage(MSG_UI_INIT_DATA);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mStatusView.setStatusListener(new StatusView.StatusListener() {
            @Override
            public void reLoadPageData() {

            }
        });
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case MSG_UI_INIT_DATA:
                mHospitalAdapter.setDataList(TestHelper.getTestHospital());
                mHospitalAdapter.notifyDataSetChanged();
                break;
        }
    }
}
