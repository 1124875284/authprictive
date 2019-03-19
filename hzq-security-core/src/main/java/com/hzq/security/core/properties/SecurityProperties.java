package com.hzq.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hzq.security")
@Data
public class SecurityProperties {
   private BrowserProperties browser=new BrowserProperties();

   private ValidateCodeProperties code=new ValidateCodeProperties();

   private SocialProperties social = new SocialProperties();


}
