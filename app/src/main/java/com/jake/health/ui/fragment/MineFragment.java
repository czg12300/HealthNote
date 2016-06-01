
package com.jake.health.ui.fragment;

import com.jake.health.R;
import com.jake.health.core.ImageLoadManager;
import com.jake.health.ui.activity.MainActivity;
import com.jake.health.ui.adapter.MineMenuAdapter;
import com.jake.health.ui.base.BaseWorkerFragment;
import com.jake.health.ui.helper.TestHelper;
import com.jake.health.ui.helper.ViewHelper;
import com.jake.health.ui.widgt.ThemeUtils;

import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 描述：侧边栏我的信息
 *
 * @author jakechen
 * @since 2016/4/19 16:00
 */
public class MineFragment extends BaseWorkerFragment implements View.OnClickListener {
    private static final int MSG_UI_INIT_DATA = 0x001;

    private ImageView mIvBackMain;

    private ImageView mIvAvatar;

    private ListView mLvMenu;

    private MineMenuAdapter mMineMenuAdapter;

    private Button mBtnLoginOut;

    @Override
    protected void initView() {
        setContentView(R.layout.fragment_mine);
        ThemeUtils.adjustStatusBar(findViewById(R.id.rl_top), getActivity());
        mIvBackMain = (ImageView) findViewById(R.id.iv_back_main);
        mIvAvatar = (ImageView) findViewById(R.id.iv_avatar);
        mBtnLoginOut = (Button) findViewById(R.id.btn_login_out);
        mIvBackMain.setImageDrawable(ViewHelper.createBackDrawable());
        mLvMenu = (ListView) findViewById(R.id.lv_menu_item);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mIvBackMain.setOnClickListener(this);
        mBtnLoginOut.setOnClickListener(this);
        findViewById(R.id.rl_root).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mMineMenuAdapter = new MineMenuAdapter(getActivity(), this);
        mLvMenu.setAdapter(mMineMenuAdapter);
        sendEmptyUiMessage(MSG_UI_INIT_DATA);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.iv_back_main) {
            ((MainActivity) getActivity()).closeMine();
        } else if (id == R.id.btn_login_out) {
            ((MainActivity) getActivity()).loginOut();
        }

    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case MSG_UI_INIT_DATA:
                dealInitData();
                break;
        }
    }

    private void dealInitData() {
        ImageLoadManager.load(this, "http://img3.imgtn.bdimg.com/it/u=287053482,141286521&fm=11&gp=0.jpg", mIvAvatar,
                true);
        mMineMenuAdapter.setDataAndNotifyDataSetChanged(TestHelper.getTestMenu());
    }

}
