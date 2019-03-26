package com.hzq.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService,SocialUserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(getClass());

    /**
     * 表单登录
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名"+username);
        return buildUser(username);
    }
    /**
     * social 根据社交网站的openId查出来的UserId
     * 根据UserId构建一个UserDetails 的实现返回
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户id："+userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {
        String password = passwordEncoder.encode("123");
        logger.info("数据库中存的密码为"+password);
        return new SocialUser(userId,password,
                true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
    }
}
