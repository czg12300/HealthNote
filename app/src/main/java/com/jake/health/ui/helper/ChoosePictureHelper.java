
package com.jake.health.ui.helper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.jake.health.utils.BitmapUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：用于按比例选取图片通过相册和拍照
 *
 * @author jakechen
 */
public class ChoosePictureHelper {

    private static final int RESULT_CAMERA = 3211;

    private static final int RESULT_LOAD_IMAGE = 3212;

    private static final int PHOTO_REQUEST_CUT = 3213;

    private Activity mActivity;

    private String picSavePath = "/choosePic";

    private boolean isSavePhoto = false;
    private int cutWidth = 100;
    private int cutHeight = 100;
    private Callback mCallback;

    private boolean needCut = false;
    private Uri cutUri;
    private File takeFile;
    public ChoosePictureHelper(Activity activity) {
        this(activity, null);
    }

    public ChoosePictureHelper(Activity activity, Callback callback) {
        mActivity = activity;
        mCallback = callback;
    }

    private File getFile() {
        File dir = new File(Environment.getExternalStorageDirectory() + picSavePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        File file = new File(dir, dateFormat.format(date) + ".png");
        return file;
    }

    // 删除SD卡拍的图片，保留剪辑的图片
    private void deleteFile(File file) {
        if (file != null) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                if (file.exists()) {
                    if (file.isFile()) {
                        file.delete();
                    }
                    // 如果它是一个目录
                    else if (file.isDirectory()) {
                        // 声明目录下所有的文件 files[];
                        File files[] = file.listFiles();
                        for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                            deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                        }
                    }
                    file.delete();
                }
            }
            takeFile = null;
        }
    }

    private void savePic(Bitmap photo) {
        ByteArrayOutputStream baos = null;
        try {
            FileOutputStream fos = new FileOutputStream(getFile());
            baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] photodata = baos.toByteArray();
            fos.write(photodata);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void handleResultPic(Uri uri) {
        if (needCut) {
            startCutPic(uri);
        } else {
            handleCallback(uri.getPath());
        }
    }

    private void handleCallback(String path) {
        Bitmap result = null;
        deleteFile(takeFile);
        if (needCut) {
            result = BitmapFactory.decodeFile(path);
        } else {
            result = dealBitmap(path);
        }
        if (mCallback != null) {
            mCallback.handleResult(result);
        }
        if (isSavePhoto) {
            savePic(result);
        }
    }

    private Bitmap dealBitmap(String path) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;// 只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);
        int sWidth = mActivity.getResources().getDisplayMetrics().widthPixels;
        int sHeight = mActivity.getResources().getDisplayMetrics().widthPixels;

        float w = newOpts.outWidth;
        float h = newOpts.outHeight;
        int inSampleSize = 1;
        if (w > sWidth || h > sHeight) {
            inSampleSize = BitmapUtil.computeInitialSampleSize(newOpts, sWidth, sWidth * sHeight);
        }
        newOpts.inSampleSize = inSampleSize;
        newOpts.inJustDecodeBounds = false;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;// 该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;// 当系统内存不够时候图片自动被回收
        Bitmap result = null;
        try {
            bitmap = BitmapFactory.decodeFile(path, newOpts);
            if (bitmap != null) {
                result = Bitmap.createBitmap(bitmap);
                if (bitmap != null && !bitmap.isRecycled() && bitmap != result) {
                    bitmap.recycle();
                    bitmap = null;
                    System.gc();
                }
                return result;
            }
        } catch (OutOfMemoryError error) {
            return null;
        }
        return null;
    }

    private void startCutPic(Uri uri) {
        cutUri = Uri.fromFile(getFile());
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", cutWidth);
        intent.putExtra("outputY", cutHeight);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cutUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        mActivity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 拍照
     */
    public void takeShot() {
        takeFile = getFile();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(takeFile));
        mActivity.startActivityForResult(i, RESULT_CAMERA);
    }

    /**
     * 从相册选择头像
     */
    public void chooseFromSystem() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_CAMERA:// 当选择拍照时调用
                if (resultCode == mActivity.RESULT_OK) {
                    handleResultPic(Uri.fromFile(takeFile));
                }
                break;
            case RESULT_LOAD_IMAGE:// 当选择从本地获取图片时
                // 做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
                if (resultCode == mActivity.RESULT_OK) {
                    if (data != null) {
                        String path = null;
                        Cursor cursor = null;
                        try {
                            Uri uri = data.getData();
                            cursor = mActivity.getContentResolver().query(uri, new String[] {
                                MediaStore.Images.Media.DATA
                            }, null, null, null);
                            int column_index = cursor
                                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            cursor.moveToFirst();
                            path = cursor.getString(column_index);
                        } catch (Exception e) {
                        } finally {
                            if (cursor != null) {
                                cursor.close();
                            }
                        }
                        handleResultPic(Uri.fromFile(new File(path)));

                    }
                }
                break;
            case PHOTO_REQUEST_CUT:// 返回的结果
                if (resultCode == mActivity.RESULT_OK) {
                    if (data != null) {
                        if (cutUri != null) {
                            handleCallback(cutUri.getPath());
                        }
                    }
                }

                break;
        }
    }

    public int getCutHeight() {
        return cutHeight;
    }

    public void setCutHeight(int cutHeight) {
        this.cutHeight = cutHeight;
    }

    public int getCutWidth() {
        return cutWidth;
    }

    public void setCutWidth(int cutWidth) {
        this.cutWidth = cutWidth;
    }

    public boolean isSavePhoto() {
        return isSavePhoto;
    }

    public void setIsSavaPhoto(boolean isSavaPhoto) {
        this.isSavePhoto = isSavaPhoto;
    }

    public String getPicSavePath() {
        return picSavePath;
    }

    public void setPicSavePath(String picSavePath) {
        this.picSavePath = picSavePath;
    }

    public boolean isNeedCut() {
        return needCut;
    }

    public void setNeedCut(boolean needCut) {
        this.needCut = needCut;
    }

    public Callback getCallback() {
        return mCallback;
    }

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    public static interface Callback {
        void handleResult(Bitmap bitmap);
    }

}
