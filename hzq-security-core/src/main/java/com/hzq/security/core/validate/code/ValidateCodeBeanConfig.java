package com.hzq.security.core.validate.code;

import com.hzq.security.core.properties.SecurityProperties;
import com.hzq.security.core.validate.code.image.ImageCodeGenerator;
import com.hzq.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.hzq.security.core.validate.code.sms.SmsCodeSender;
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
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenrator(){
        ImageCodeGenerator codeGenerator=new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){

        return new DefaultSmsCodeSender();
    }
}
