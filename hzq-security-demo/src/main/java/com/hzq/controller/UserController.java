package com.hzq.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hzq.dto.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    //	@Autowired
//	private AppSingUpUtils appSingUpUtils;

//	@Autowired
//	private SecurityProperties securityProperties;

    /**
     * userDetails 只想要用户的部分信息
     * Authentication  用户的所有信息
     * @return
     */
    @GetMapping("/me")
    public Object getCurrentUser(Authentication user, HttpServletRequest request){
        //return SecurityContextHolder.getContext().getAuthentication();
        //使用JWT获取的用户信息
// 		String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
//
//		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
//					.parseClaimsJws(token).getBody();
//
//		String company = (String) claims.get("company");
//
//		System.out.println(company);

        return user;
    }

    /**
     * 注册
     * @param user
     */
    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request){
        //不管是注册用户还是绑定用户，都会有一个唯一标示
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
//		appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "查询所有用户")
    public List<User> getAllUser(@PageableDefault(page = 2,size = 17,sort = "username,asc") Pageable pageable){
        List<User> list=new ArrayList<>(3);
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @GetMapping("/{id:\\d+}")//表示只能是数字
    @JsonView(User.UserDetailView.class)
    @ApiOperation(value = "查询用户详情")
    public User getInfo(@PathVariable String id){
        User user=new User();
        user.setUsername("tom");
        user.setPassword("123");
        return user;
    }

    /**
     *
     * @param user
     * @param erros 与@Valid 配合使用， 如果传入的参数与对象里加的注解有冲突的时候，就会放到erros
     * @return
     */
    @PostMapping
    @ApiOperation(value = "添加用户")
    public User addUser(@Valid @RequestBody User user, BindingResult erros){
        if(erros.hasErrors()){
            erros.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getBirthday());
        System.out.println(user.getUsername());
        return user;
    }
    @PutMapping("/{id:\\d+}")
    public User updateUser(@Valid @RequestBody User user, BindingResult erros){
        if(erros.hasErrors()){
            erros.getAllErrors().stream().forEach(error-> {
                FieldError filedError=(FieldError) error;
                String messqge=filedError.getField()+ error.getDefaultMessage();
                System.out.println(messqge);

            });
        }
        System.out.println(user.getBirthday());
        System.out.println(user.getUsername());
        return user;
    }

    @DeleteMapping("/{id:\\d+}")//表示只能是数字
    public void deleteUser(@ApiParam("用户id") @PathVariable String id){
        System.out.println("id = [" + id + "]");
    }

    @GetMapping("he")
    public String tt(){
        return "hello";
    }
}
