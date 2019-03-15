package com.hzq.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 具体生成验证码生 的具体操作
 * 如果只是生成验证码的逻辑变了 需要实现 ValidateCodeGenerator  这个接口
 */
public interface ValidateCodeGenerator {
    /**
     * 图形验证码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
