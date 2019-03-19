/**
 * 
 */
package com.hzq.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 *
 * qq 的有关配置
 * @author huangzhiqiang
 *
 */
public class QQProperties extends SocialProperties {
	
	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
}
