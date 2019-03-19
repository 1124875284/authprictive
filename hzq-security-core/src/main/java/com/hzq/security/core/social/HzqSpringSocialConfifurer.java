package com.hzq.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author  黄志强
 */
public class HzqSpringSocialConfifurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;
    public HzqSpringSocialConfifurer(String filterProcessesUrl){
        this.filterProcessesUrl=filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {

        SocialAuthenticationFilter filter=(SocialAuthenticationFilter)super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
