
package com.jake.health.ui.activity;

import android.graphics.Canvas;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.jake.health.R;
import com.jake.health.core.HttpRequestTask;
import com.jake.health.core.response.TestResponse;
import com.jake.health.ui.adapter.HospitalAdapter;
import com.jake.health.ui.helper.DividerGridItemDecoration;
import com.jake.health.ui.helper.GridItemDecoration;
import com.jake.health.ui.helper.TestHelper;
import com.jake.health.ui.widgt.StatusView;
import com.jake.health.utils.ToastUtil;

/**
 * 描述：附近诊所
 *
 * @author jakechen
 * @since 2016/4/29 16:51
 */
public class HospitalActivity extends TitleActivity {
    final static String url = "http://gcapi.sy.kugou.com/index.php?r=GameCenter/index&platform=1&clienttype=2&userid=0&clientversion=1&gcClientVersion=410&token=&clientAppid=1005&systemVersion=21&jsonparam=[{\"i\":40}]";

    private static final int MSG_UI_INIT_DATA = 0x001;

    private static final int MSG_BACK_INIT_DATA = 0x001;

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
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL,
                false));
        mRecyclerView.addItemDecoration(new GridItemDecoration());
        mHospitalAdapter = new HospitalAdapter(this);
        mRecyclerView.setAdapter(mHospitalAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        sendEmptyUiMessage(MSG_UI_INIT_DATA);
        sendEmptyBackgroundMessage(MSG_BACK_INIT_DATA);
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
    public void handleBackgroundMessage(Message msg) {
        super.handleBackgroundMessage(msg);
        switch (msg.what) {
            case MSG_BACK_INIT_DATA:
                HttpRequestTask requestTask = HttpRequestTask.obtainGet(TestResponse.class, url);
                TestResponse response = (TestResponse) requestTask.request();
                Message ui = obtainUiMessage();
                ui.what = MSG_UI_INIT_DATA;
                if (response != null) {
                    ui.obj = response;
                }
                ui.sendToTarget();
                break;
        }
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case MSG_UI_INIT_DATA:
                mHospitalAdapter.setDataList(TestHelper.getTestHospital());
                mHospitalAdapter.notifyDataSetChanged();
                if (msg.obj != null) {
                    TestResponse response = (TestResponse) msg.obj;
                    ToastUtil.show(response.getJson());
                }
                break;
        }
    }
}
