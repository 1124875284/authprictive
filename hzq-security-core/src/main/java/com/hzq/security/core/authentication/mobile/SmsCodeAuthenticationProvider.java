package com.hzq.security.core.authentication.mobile;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 具体校验的逻辑
 * @author huangzhiqiang
 */
@Data
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //未认证
        SmsCodeAuthenticationToken authenticationToken= (SmsCodeAuthenticationToken) authentication;

        UserDetails user=userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (user==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //已认证
        SmsCodeAuthenticationToken authenticationResult=new SmsCodeAuthenticationToken(user,user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
