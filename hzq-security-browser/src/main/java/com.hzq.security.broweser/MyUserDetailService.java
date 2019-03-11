package com.hzq.security.broweser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(getClass());
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("登录用户名"+s);
        //TODO 根据用户名查找用户信息
        //TODO  根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode("123");
        logger.info("数据库中存的密码为"+password);
        return new User(s,password,
                true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
