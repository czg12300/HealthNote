
package com.jake.health.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ScrollView;

import com.jake.health.R;
import com.jake.health.ui.helper.ChoosePictureHelper;
import com.jake.health.ui.widgt.ImageEditText;
import com.jake.health.ui.widgt.ResizeLayout;
import com.jake.health.utils.ToastUtil;

/**
 * 描述：添加病理分析页面
 *
 * @author jakechen
 * @since 2016/5/16 10:22
 */
public class AddAnalysisActivity extends TitleActivity {
    private ChoosePictureHelper mChoosePictureHelper;

    private ImageEditText mIevContent;
private ResizeLayout mResizeLayout;
private ScrollView sv_edit;
    @Override
    protected void initView() {
        setTitle(R.string.title_add_analysis);
        setContentView(R.layout.activity_add_analysis);
        mIevContent = (ImageEditText) findViewById(R.id.iev_content);
        mResizeLayout= (ResizeLayout) findViewById(R.id.resize);
        sv_edit= (ScrollView) findViewById(R.id.sv_edit);
        mChoosePictureHelper = new ChoosePictureHelper(this);
//        mChoosePictureHelper.setNeedCut(true);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mChoosePictureHelper.setCallback(new ChoosePictureHelper.Callback() {
            @Override
            public void handleResult(Bitmap bitmap) {
                if (bitmap != null) {
                    mIevContent.insertImage(bitmap);
                }
            }
        });
        mResizeLayout.setOnResizeListener(new ResizeLayout.OnResizeListener() {
            @Override
            public void onKeyboardHide() {

            }

            @Override
            public void onKeyboardShow(int height) {
//                sv_edit.scrollTo(0,mIevContent.getBottom()+height);
//                sv_edit.requestLayout();
//                mIevContent.requestFocus();
            }
        });
        mIevContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ToastUtil.show(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.btn_take_shot) {
            mChoosePictureHelper.takeShot();
        } else if (id == R.id.btn_pic) {
            mChoosePictureHelper.chooseFromSystem();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mChoosePictureHelper.onActivityResult(requestCode, resultCode, data);
    }
}
