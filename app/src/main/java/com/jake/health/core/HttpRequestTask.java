
package com.jake.health.core;

import com.jake.health.R;
import com.jake.health.config.AppConfig;
import com.jake.health.config.DataCache;
import com.jake.health.config.HttpConfig;
import com.jake.health.core.response.BaseResponse;
import com.jake.health.ui.HealthApplication;
import com.jake.health.utils.LogUtil;
import com.jake.health.utils.NetworkUtil;
import com.jake.health.utils.ToastUtil;
import com.jake.health.utils.UrlEncodeUtil;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述：http网络数据请求
 *
 * @author jakechen
 * @since 2016/5/24 11:37
 */
public final class HttpRequestTask {
    private static final String TAG_REQUEST = "request";

    private static final String TAG_RESPONSE = "response";

    private OkHttpClient mClient;

    private boolean mIsPost = false;

    private boolean mIsCancel = false;

    private Class<? extends BaseResponse> mResponseClass;

    private Hashtable<String, Hashtable<String, String>> mParams = new Hashtable<>();

    private HttpRequestTask(Class<? extends BaseResponse> responseClass, boolean isPost) {
        mIsPost = isPost;
        mClient = new OkHttpClient();
        mResponseClass = responseClass;
    }

    private void appendPostParams(Request.Builder reqBuilder) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.addEncoded("publicParams", obtainPublicParams());
        builder.addEncoded("privateParams", obtainPrivateParams());
        builder.addEncoded("securityCode", obtainSecurityCode());
        String url = HttpConfig.SERVER;
        LogUtil.d(TAG_REQUEST, "Post :" + url);
        reqBuilder.url(url);
        reqBuilder.post(builder.build());
    }

    private void appendGetParams(Request.Builder reqBuilder) throws UnsupportedEncodingException {
        reqBuilder.get();
        StringBuilder urlBuilder = new StringBuilder(HttpConfig.SERVER);
        urlBuilder.append("publicParams=");
        urlBuilder.append(UrlEncodeUtil.encode(obtainPublicParams()));
        urlBuilder.append("&privateParams=");
        urlBuilder.append(UrlEncodeUtil.encode(obtainPrivateParams()));
        urlBuilder.append("&securityCode=");
        urlBuilder.append(UrlEncodeUtil.encode(obtainSecurityCode()));
        String url = urlBuilder.toString();
        LogUtil.d(TAG_REQUEST, "Get :" + url);
        reqBuilder.url(url);
    }

    /**
     * 创建请求安全码
     */
    private String obtainSecurityCode() {
        String code = "qsggdsaghsadhahda";
        return code;
    }

    /**
     * 创建请求参数
     * 
     * @return
     */
    private String obtainPrivateParams() {
        JSONArray array = new JSONArray();
        try {
            for (String key : mParams.keySet()) {
                JSONObject obj = new JSONObject();
                obj.put("method", key);
                Hashtable<String, String> table = mParams.get(key);
                for (String tableKey : table.keySet()) {
                    obj.put(tableKey, table.get(tableKey));
                }
                array.put(obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array.toString();
    }

    /**
     * 创建公共参数
     * 
     * @return
     */
    private String obtainPublicParams() {
        JSONObject object = new JSONObject();
        try {
            object.put("clientType", 1);
            object.put("userId", DataCache.getUserId());
            object.put("systemVersion", android.os.Build.VERSION.SDK_INT);
            object.put("systemVersionName", android.os.Build.VERSION.RELEASE);
            object.put("clientVersion", AppConfig.getVersion());
            object.put("imei", AppConfig.getIMEI());
            object.put("token", DataCache.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public Hashtable<String, String> removeParam(String name) {
        return mParams.remove(name);
    }

    public void clearParams() {
        mParams.clear();
    }

    public void addParam(String method, Hashtable<String, String> params) {
        mParams.put(method, params);
    }

    public Object request() {
        if (!NetworkUtil
                .isNetworkAvailable(HealthApplication.getInstance().getApplicationContext())) {
            ToastUtil.show(R.string.network_not_connect);
            return null;
        }
        Request.Builder reqBuilder = new Request.Builder();
        try {
            if (mIsPost) {
                appendPostParams(reqBuilder);
            } else {
                appendGetParams(reqBuilder);
            }
            Response response = mClient.newCall(reqBuilder.build()).execute();
            if (response.isSuccessful() && !mIsCancel) {
                BaseResponse baseResponse = mResponseClass.newInstance();
                String result = response.body().string();
                LogUtil.d(TAG_RESPONSE, result);
                if (baseResponse.parseJson(result)) {
                    return baseResponse;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cancel() {
        mIsCancel = true;
    }

    public static HttpRequestTask obtainGet(Class<? extends BaseResponse> responseClass) {
        return new HttpRequestTask(responseClass, false);
    }

    public static HttpRequestTask obtainPost(Class<? extends BaseResponse> responseClass) {
        return new HttpRequestTask(responseClass, true);
    }
}
