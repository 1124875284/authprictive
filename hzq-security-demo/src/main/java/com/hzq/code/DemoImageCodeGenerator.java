package com.hzq.code;

import com.hzq.security.core.validate.code.image.ImageCode;
import com.hzq.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义配置跟高级的图形验证码
 */
//@Component("imageCodeGenrator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
