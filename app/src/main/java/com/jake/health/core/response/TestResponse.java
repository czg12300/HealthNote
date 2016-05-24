
package com.jake.health.core.response;


/**
 * 描述：测试返回
 *
 * @author jakechen
 * @since 2016/5/24 18:06
 */
public class TestResponse extends BaseResponse {
    String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public boolean parseJson(String json) {
        setJson(json);
        return true;
    }
}
