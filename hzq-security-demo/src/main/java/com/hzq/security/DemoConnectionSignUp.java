package com.hzq.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author huangzhiqiang
 * 自动完成注册功能
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户，并返回用户用户唯一标示
        return connection.getDisplayName();
    }
}
