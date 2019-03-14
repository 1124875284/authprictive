/**
 * 
 */
package com.hzq.security.core.properties;

import lombok.Data;

/**
 * 多种验证码
 * @author 黄志强
 *
 */
@Data
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();
	private SmsCodeProperties sms = new SmsCodeProperties();
	
}
