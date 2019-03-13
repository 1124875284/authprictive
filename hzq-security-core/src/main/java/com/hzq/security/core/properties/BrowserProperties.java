package com.hzq.security.core.properties;

import lombok.Data;

/**
 * 与浏览器相关的配置
 */
@Data
public class BrowserProperties {

    private String longinPage="/hzq-signIn.html";

    private LoginType loginType=LoginType.JSON;
    /**
     * 记住我  的时间
     */
    private int rememberMeSeconds=3600;


}
