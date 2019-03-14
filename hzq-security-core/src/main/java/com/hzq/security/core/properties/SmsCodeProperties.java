/**
 * 
 */
package com.hzq.security.core.properties;

import lombok.Data;

/**
 * @author 黄志强
 *
 */
@Data
public class SmsCodeProperties {
	
	private int length = 6;
	private int expireIn = 60;
	
	private String url;



}
