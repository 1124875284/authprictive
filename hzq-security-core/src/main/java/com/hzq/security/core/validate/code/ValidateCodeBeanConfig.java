package com.hzq.security.core.validate.code;

import com.hzq.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 做可配置的图片验证码类
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenrator")
    public ValidateCodeGeneratorService imageCodeGenrator(){
        ValidateCodeGeneratorServiceImpl codeGenerator=new ValidateCodeGeneratorServiceImpl();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
}
