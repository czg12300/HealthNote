
package com.jake.health.core;

import com.jake.health.config.HttpConfig;
import com.jake.health.core.response.BaseResponse;
import com.jake.health.utils.LogUtil;

import android.text.TextUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Set;

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

    private String mSvc;

    private boolean mIsPost = false;

    private boolean mIsCancel = false;

    private Class<? extends BaseResponse> mResponseClass;

    private Hashtable<String, String> mParams = new Hashtable<>();

    private HttpRequestTask(Class<? extends BaseResponse> responseClass, String svc,
                            boolean isPost) {
        mSvc = svc;
        mIsPost = isPost;
        mClient = new OkHttpClient();
        mResponseClass = responseClass;
    }

    private void appendGetParams(Request.Builder reqBuilder) throws UnsupportedEncodingException {
        reqBuilder.get();
        StringBuilder urlBuilder = new StringBuilder(parseSvc());
        urlBuilder.append("&");
        final Set<String> keys = mParams.keySet();
        for (String key : keys) {
            urlBuilder.append(key).append("=").append(URLEncoder.encode(mParams.get(key), "UTF-8"))
                    .append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        String url = urlBuilder.toString();
        LogUtil.d(TAG_REQUEST, "Get :" + url);
        reqBuilder.url(url);
    }

    private String parseSvc() {
        if (!TextUtils.isEmpty(mSvc)) {
            if (isHttpUrl(mSvc) || isHttpsUrl(mSvc)) {
                return mSvc;
            } else {
                return HttpConfig.SERVER + mSvc;
            }
        }
        return "";
    }

    public static boolean isHttpUrl(String url) {
        return (null != url) &&
                (url.length() > 6) &&
                url.substring(0, 7).equalsIgnoreCase("http://");
    }

    public static boolean isHttpsUrl(String url) {
        return (null != url) &&
                (url.length() > 7) &&
                url.substring(0, 8).equalsIgnoreCase("https://");
    }

    private void appendPostParams(Request.Builder reqBuilder) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : mParams.keySet()) {
            builder.addEncoded(key, mParams.get(key));
        }
        String url = parseSvc();
        LogUtil.d(TAG_REQUEST, "Post :" + url);
        reqBuilder.url(url);
        reqBuilder.post(builder.build());
    }

    public String removeParam(String name) {
        return mParams.remove(name);
    }

    public void clearParams() {
        mParams.clear();
        ;
    }

    public void addParam(String name, String value) {
        mParams.put(name, value);
    }

    public void addParams(Hashtable<String, String> params) {
        mParams.putAll(params);
    }

    public Object request() {
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

    public static HttpRequestTask obtainGet(Class<? extends BaseResponse> responseClass,
                                            String svc) {
        return new HttpRequestTask(responseClass, svc, false);
    }

    public static HttpRequestTask obtainPost(Class<? extends BaseResponse> responseClass,
                                             String svc) {
        return new HttpRequestTask(responseClass, svc, true);
    }
}
