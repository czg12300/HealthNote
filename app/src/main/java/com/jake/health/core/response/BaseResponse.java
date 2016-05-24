
package com.jake.health.core.response;

import java.io.Serializable;

/**
 * 描述：接口请求返回
 *
 * @author jakechen
 * @since 2016/5/24 15:08
 */
public abstract class BaseResponse implements Serializable {

    public abstract boolean parseJson(String json);
}
