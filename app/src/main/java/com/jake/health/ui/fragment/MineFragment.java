package com.jake.health.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Message;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jake.health.R;
import com.jake.health.entity.MineMenuInfo;
import com.jake.health.ui.activity.MainActivity;
import com.jake.health.ui.adapter.MineMenuAdapter;
import com.jake.health.ui.base.BaseApplication;
import com.jake.health.ui.base.BaseWorkerFragment;
import com.jake.health.ui.helper.ViewHelper;
import com.jake.health.ui.widgt.RoundImageView;
import com.jake.health.ui.widgt.ThemeUtils;

import java.util.ArrayList;
import java.util.List;


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

    @Override
    protected void initView() {
        setContentView(R.layout.fragment_mine);
        ThemeUtils.adjustStatusBar(findViewById(R.id.rl_top), getActivity());
        mIvBackMain = (ImageView) findViewById(R.id.iv_back_main);
        mIvAvatar = (ImageView) findViewById(R.id.iv_avatar);
        mIvBackMain.setImageDrawable(ViewHelper.createBackDrawable());
        mLvMenu = (ListView) findViewById(R.id.lv_menu_item);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mIvBackMain.setOnClickListener(this);
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
        loadImage("http://img3.imgtn.bdimg.com/it/u=287053482,141286521&fm=11&gp=0.jpg", mIvAvatar, true);
        mMineMenuAdapter.setDataAndNotifyDataSetChanged(getTestMenu());
    }

    private List<MineMenuInfo> getTestMenu() {
        List<MineMenuInfo> list = new ArrayList<>();
        MineMenuInfo info = new MineMenuInfo();
        info.setTitle("消息");
        info.setShowDot(1);
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=1390048268,4118739760&fm=21&gp=0.jpg");
        list.add(info);
        info = new MineMenuInfo();
        info.setTitle("关注");
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=1390048268,4118739760&fm=21&gp=0.jpg");
        list.add(info);
        info = new MineMenuInfo();
        info.setTitle("粉丝");
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=3119963880,936101450&fm=21&gp=0.jpg");
        list.add(info);
        info = new MineMenuInfo();
        info.setTitle("设置");
        info.setIcon("http://img3.imgtn.bdimg.com/it/u=2832776744,1381723459&fm=21&gp=0.jpg");
        list.add(info);
        return list;
    }
}
