package com.hzq.security.broweser;

import com.hzq.security.core.authentication.AbstractChannelSecurityConfig;
import com.hzq.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.hzq.security.core.properties.SecurityConstants;
import com.hzq.security.core.properties.SecurityProperties;
import com.hzq.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Security 的核心配置类
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SpringSocialConfigurer hzqSocialSecurityConfig;
    /**
     * 对于密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我的一些配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
    /**
     * 配置拦截信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        //表单
        http.apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(hzqSocialSecurityConfig)
                    .and()
                 .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    //用来登录
                    .userDetailsService(userDetailsService)
                    .and()
                    .authorizeRequests() //表示下面这些都是是授权配置
                        .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                            securityProperties.getBrowser().getLonginPage(),
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                                securityProperties.getBrowser().getSignUpUrl(),
                                "user/regist","www.pinzhi365.com/qqLogin/callback.do")
                    .permitAll() //不需要验证的
                    .anyRequest() //任何请求
                    .authenticated() //都需要身份认证
                    .and()
                    .csrf().disable();
    }
}
