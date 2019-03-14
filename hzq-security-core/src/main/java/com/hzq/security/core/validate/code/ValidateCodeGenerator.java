package com.hzq.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图形验证码生成器
 */
public interface ValidateCodeGenerator {
    /**
     * 图形验证码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}