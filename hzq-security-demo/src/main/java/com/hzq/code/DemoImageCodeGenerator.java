package com.hzq.code;

import com.hzq.security.core.validate.code.ImageCode;
import com.hzq.security.core.validate.code.ValidateCodeGeneratorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义配置跟高级的图形验证码
 */
//@Component("imageCodeGenrator")
public class DemoImageCodeGenerator implements ValidateCodeGeneratorService {
        @Override
        public ImageCode createImageCode(ServletWebRequest request) {
            System.out.println("别的验证码生成");
            return null;
        }
}
