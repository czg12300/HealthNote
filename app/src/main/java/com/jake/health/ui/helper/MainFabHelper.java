
package com.jake.health.ui.helper;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.jake.health.R;
import com.jake.health.ui.widgt.materialdesign.FabButton;
import com.jake.health.utils.ToastUtil;

/**
 * 描述：首页floatButton的操作逻辑
 *
 * @author jakechen
 * @since 2016/4/27 10:58
 */
public class MainFabHelper implements View.OnClickListener {
    private MaterialSheetFab<FabButton> mMaterialSheetFab;

    private FabButton mFab;

    private CardView mCardView;

    private Activity mActivity;

    public MainFabHelper(Activity activity) {
        mActivity = activity;
        setupFab();
    }

    private void setupFab() {
        mFab = (FabButton) findViewById(R.id.fab_opt);
        mCardView = (CardView) findViewById(R.id.cv_fab_sheet);
        mMaterialSheetFab = new MaterialSheetFab<>(mFab, mCardView, findViewById(R.id.overlay),
                mFab.getBackgroundTintList().getDefaultColor(), mFab.getBackgroundTintList()
                        .getDefaultColor());
        mMaterialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
            }

            @Override
            public void onHideSheet() {
                // Restore status bar color
                // setStatusBarColor(statusBarColor);
            }
        });
        findViewById(R.id.tv_sheet_item_recording).setOnClickListener(this);
        findViewById(R.id.fab_sheet_item_reminder).setOnClickListener(this);
        findViewById(R.id.fab_sheet_item_photo).setOnClickListener(this);
    }

    private View findViewById(int id) {
        return mActivity.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        ToastUtil.show("哈哈");
        mMaterialSheetFab.hideSheet();
        // mFab.show();
    }
}
