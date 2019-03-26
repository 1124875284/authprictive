package com.hzq.security.broweser.authacttion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzq.security.core.suppot.SimpleResponse;
import com.hzq.security.core.properties.LoginResponseType;
import com.hzq.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("hzqAuthenticationFailureHandler")
public class HzqAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {


        logger.info("登录失败");
        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        }else{
            super.onAuthenticationFailure(request, response, exception);
        }



    }
}
