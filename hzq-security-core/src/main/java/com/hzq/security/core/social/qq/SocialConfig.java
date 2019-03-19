/**
 * 
 */
package com.hzq.security.core.social.qq;

import javax.sql.DataSource;

import com.hzq.security.core.properties.SecurityProperties;
import com.hzq.security.core.social.HzqSpringSocialConfifurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 配置 JdbcUsersConnectionRepository
 * @author huangzhiqiang
 *
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SecurityProperties securityProperties;

	@Autowired(required =false)
	private ConnectionSignUp connectionSignUp;

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("hzq_");
		if (connectionSignUp!=null){
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}
	
	@Bean
	public SpringSocialConfigurer hzqSocialSecurityConfig() {
		String filterProcessesUrl=securityProperties.getSocial().getFilterProcessesUrl();
		HzqSpringSocialConfifurer confifurer=new HzqSpringSocialConfifurer(filterProcessesUrl);
		confifurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		return confifurer;
	}

	/**
	 * 1、在注册过程中拿到Social的信息
	 * 2、注册完成 把业务系统的用户id传给social
	 */
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
		return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
	}
}
