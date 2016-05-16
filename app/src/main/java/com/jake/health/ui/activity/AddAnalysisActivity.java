
package com.jake.health.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.jake.health.R;
import com.jake.health.ui.helper.ChoosePictureHelper;
import com.jake.health.ui.widgt.ImageEditText;

/**
 * 描述：添加病理分析页面
 *
 * @author jakechen
 * @since 2016/5/16 10:22
 */
public class AddAnalysisActivity extends TitleActivity {
    private ChoosePictureHelper mChoosePictureHelper;

    private ImageEditText mIevContent;

    private ImageView imageView;

    @Override
    protected void initView() {
        setTitle(R.string.title_add_analysis);
        setContentView(R.layout.activity_add_analysis);
        mIevContent = (ImageEditText) findViewById(R.id.iev_content);
        mChoosePictureHelper = new ChoosePictureHelper(this);
        imageView = (ImageView) findViewById(R.id.iv_test);
        mChoosePictureHelper.setNeedCut(true);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mChoosePictureHelper.setCallback(new ChoosePictureHelper.Callback() {
            @Override
            public void handleResult(Bitmap bitmap) {
                if (bitmap != null) {
                    mIevContent.insertImage(bitmap);
                    imageView.setImageBitmap(bitmap);
                }
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
