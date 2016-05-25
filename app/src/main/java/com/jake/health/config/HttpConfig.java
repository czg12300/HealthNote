
package com.jake.health.config;

/**
 * 描述：全局控制变量
 *
 * @author jakechen
 * @since 2016/5/24 14:49
 */
public interface HttpConfig {
    boolean isDebug = true;

    String TEST_SERVER = "http://www.baidu.com?";

    String RELEASE_SERVER = "http://www.baidu.com?";

    String SERVER = isDebug ? TEST_SERVER : RELEASE_SERVER;
}
