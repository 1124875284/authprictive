package com.hzq.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图形验证码生成器
 */
public interface ValidateCodeGeneratorService {
    /**
     * 图形验证码
     * @param request
     * @return
     */
    ImageCode createImageCode(ServletWebRequest request);
}
