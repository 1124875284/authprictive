package com.hzq.security.broweser.authacttion.session;

import com.hzq.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 并发登录导致session失效时，默认的处理策略
 * 场景：如果用户在俩台客户端登录  会是前一台客户端session失效
 */
public class HzqExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    public HzqExpiredSessionStrategy(SecurityProperties securityPropertie) {
        super(securityPropertie);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent eventØ) throws IOException, ServletException {
        onSessionInvalid(eventØ.getRequest(), eventØ.getResponse());
    }
    @Override
    protected boolean isConcurrency() {
        return true;
    }

}
