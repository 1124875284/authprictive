package com.hzq.security.broweser;

import com.hzq.security.core.properties.SecurityProperties;
import com.hzq.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security 的核心配置类
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 自定义登录成功的处理
     */
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    /**
     * 自定义登录失败的处理
     */
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;
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
     * 配置拦截信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        //表单
        http
                .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authention/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests() //表示下面这些都是是授权配置
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLonginPage(),
                        "/code/image").permitAll() //不需要验证的
                .anyRequest() //任何请求
                .authenticated() //都需要身份认证
                .and()
                .csrf().disable();
    }
}
