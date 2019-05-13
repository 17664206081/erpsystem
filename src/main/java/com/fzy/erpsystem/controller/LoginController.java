package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.UserMapper;
import com.fzy.erpsystem.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: LoginController
 * @description:
 * @author: fzy
 * @date: 2019/05/10 11:01:20
 **/
@RestController
public class LoginController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/doLogin")
    public Map login(@RequestBody User user){
        Map<String,String> map=new HashMap<>();
        User dbUser = userMapper.findUserByUserName(user.getUserName(), user.getPassword());
        if(Objects.isNull(dbUser)){
            map.put("code","400");
        }else {
            map.put("code","200");
        }
        return map;
    }

}
