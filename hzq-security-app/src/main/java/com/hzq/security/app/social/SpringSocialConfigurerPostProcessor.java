/**
 * 
 */
package com.hzq.security.app.social;

import com.hzq.security.core.properties.SecurityConstants;
import com.hzq.security.core.social.suppot.HzqSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(StringUtils.equals(beanName, "hzqSocialSecurityConfig")){
			HzqSpringSocialConfigurer config = (HzqSpringSocialConfigurer)bean;
			config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
			return config;
		}
		return bean;
	}

}
