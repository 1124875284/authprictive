/**
 * 
 */
package com.hzq.security;

import com.hzq.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author huangzhiqiang
 *
 */
@Component
public class DemoAuthorizeConifgProvider implements AuthorizeConfigProvider {

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		//demo项目授权配置
		config.antMatchers("/user").hasRole("ADMIN");
		return false;
	}

}
