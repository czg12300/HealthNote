package com.jake.health.ui.fragment;

import android.widget.ImageView;

import com.jake.health.R;
import com.jake.health.ui.base.BaseWorkerFragment;
import com.jake.health.ui.widgt.ChangeThemeUtils;


/**
 * 描述：主页
 *
 * @author jakechen
 * @since 2016/4/19 16:00
 */
public class MainFragment extends BaseWorkerFragment {
    private ImageView ivTitleMenu;
    @Override
    protected void initView() {
        setContentView(R.layout.fragment_main);
        initTitleBar();

    }

    private void initTitleBar() {
        ChangeThemeUtils.adjustStatusBar(findViewById(R.id.ll_root_title),getActivity());

    }
}
