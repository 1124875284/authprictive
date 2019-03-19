/**
 * 
 */
package com.hzq.security.core.properties;

import lombok.Data;

/**
 * @author huangzhiqiang
 *
 */
@Data
public class SocialProperties {

	private String filterProcessesUrl="/auth";

	private QQProperties qq = new QQProperties();


	
}
