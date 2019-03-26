package com.hzq.security.broweser;

import com.hzq.security.core.authentication.FormAuthenticationConfig;
import com.hzq.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.hzq.security.core.authorize.AuthorizeConfigManager;
import com.hzq.security.core.properties.SecurityProperties;
import com.hzq.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Security 的核心配置类
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
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

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    /**
     * 对于密码加密
     *
     * @return
     */


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
        formAuthenticationConfig.configure(http);
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
                .sessionManagement()
                //处理Session过期的地址
                    .invalidSessionStrategy(invalidSessionStrategy)
                //设置session最大数量
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                //当session达到最大数量时，阻止后面的用户登录
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)
                    .and()
                    .and()
                .logout()
                    .logoutUrl("/signOut")
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .logoutSuccessUrl("/hzq-logout.html")
                    .deleteCookies("JSESSIONID")
                .and()
                    .csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }
}
