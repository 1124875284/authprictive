package com.hzq.security.broweser.suppot;

import lombok.Data;

/**
 * @author huangzhiqiang
 */
@Data
public class SocialUserInfo {
    /**
     * 第三方用户id
     */
    private String providerId;

    /**
     * openID
     */
    private String providerUserId;

    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String headimg;

}
