package com.hzq.security.core.validate.code.sms;

import com.hzq.security.core.properties.SecurityProperties;
import com.hzq.security.core.validate.code.ValidateCode;
import com.hzq.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author  黄志强
 *
 * 短信验证码
 */
@Component("smsValidateCodeGenerator")
@Data
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public ValidateCode generate(ServletWebRequest request) {

        String code=RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }

}
