package com.hzq.security.broweser.controller;

import com.hzq.security.broweser.suppot.SimpleResponse;
import com.hzq.security.broweser.suppot.SocialUserInfo;
import com.hzq.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSerurityController {

    private Logger logger=LoggerFactory.getLogger(getClass());

    private RequestCache requestCache=new HttpSessionRequestCache();
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    /**
     * 用于做跳转
     */
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 当需要身份认证时，跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse  requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest=requestCache.getRequest(request,response);
        if (savedRequest!=null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是"+targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLonginPage());

            }
        }
        return new SimpleResponse("您访问的服务器需要身份认证，请引导用户到登录页");
    }
    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        SocialUserInfo userInfo=new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickName(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;

    }
}
