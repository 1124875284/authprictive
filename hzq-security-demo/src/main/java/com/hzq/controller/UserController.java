package com.hzq.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hzq.dto.User;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getAllUser(@PageableDefault(page = 2,size = 17,sort = "username,asc") Pageable pageable){
        List<User> list=new ArrayList<>(3);
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @GetMapping("/{id:\\d+}")//表示只能是数字
    @JsonView(User.UserDetailView.class)
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
    public void deleteUser(@PathVariable String id){
        System.out.println("id = [" + id + "]");
    }
}
