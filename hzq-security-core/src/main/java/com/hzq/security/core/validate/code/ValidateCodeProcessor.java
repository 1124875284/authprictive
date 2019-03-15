/**
 * 
 */
package com.hzq.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 * 处理整个验证码的业务流程（包括生成发送）
 *
 * 如果发送的逻辑变了，只需要实现 @ValidateCodeProcessor  接口 就可以
 * @author huangzhiqnag
 *
 */
public interface ValidateCodeProcessor {
	
	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	/**
	 * 创建校验码
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 *
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);

}
