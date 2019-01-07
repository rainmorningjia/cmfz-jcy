package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Error;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Miles
 * @Title: CmfzAccount
 * @ProjectName cmfz-jcy
 * @Date 2019/1/2--9:14
 */
@RequestMapping("/account")
@RestController
public class CmfzAccount {
    @Resource
    UserService userService;

    @RequestMapping("/login")
    public Object loginUser(User user) {
        try {
            userService.LoginUser(user);
            return user;
        } catch (Exception e) {
            if (e.getMessage().equals("该用户不存在！") || e.getMessage().equals("密码不正确！")) {
                Error error = new Error("-200", e.getMessage());
                return error;
            }
            return new Error("-100", "系统错误！");
        }
    }

    @RequestMapping("/regist")
    public Object registUser(User user) {
        if (userService.queryUserByPhone(user.getPhone()) != null) {
            return new Error("-200", "该手机号已存在！");
        } else {
            userService.inserUser(user);
            return user;
        }
    }
    @RequestMapping("modify")
    public Object modifyUser(User user){
        if (userService.queryUserByPhone(user.getPhone())!=null){
            return new Error("-200","该手机号已经存在");
        }else {
            User user1=userService.updataUser(user);
            return user1;
        }
    }

}
