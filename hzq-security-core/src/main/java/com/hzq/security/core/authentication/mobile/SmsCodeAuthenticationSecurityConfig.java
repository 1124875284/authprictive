package com.hzq.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 短信验证配置类
 *
 * @author huangzhiqiang
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final AuthenticationFailureHandler myAuthenticationFailureHandler;
    private final AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final UserDetailsService userDetailsService;

    private final PersistentTokenRepository persistentTokenRepository;

    public SmsCodeAuthenticationSecurityConfig(AuthenticationFailureHandler myAuthenticationFailureHandler, AuthenticationSuccessHandler myAuthenticationSuccessHandler, UserDetailsService userDetailsService, PersistentTokenRepository persistentTokenRepository) {
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.userDetailsService = userDetailsService;
        this.persistentTokenRepository = persistentTokenRepository;
    }

    @Override
    public void configure(HttpSecurity http) {

        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        String key = UUID.randomUUID().toString();
        smsCodeAuthenticationFilter.setRememberMeServices(new PersistentTokenBasedRememberMeServices(key, userDetailsService, persistentTokenRepository));

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}