package com.baizhi.cmfz.controller;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Miles
 * @Title: ManagerController
 * @ProjectName cmfz
 * @Date 2018/12/19--19:27
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private ManagerService managerService;
    @RequestMapping("loginManager")
    public String loginManager(String name, String password, String code, HttpSession session){
        try {
            code=code.toLowerCase();
            String codes=(String) session.getAttribute("code");
            if(!code.equals(codes)){
                return "login";
            }else{
                managerService.loginManager(name,password);
                return "redirect:/main/main.jsp";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();

        }

    }
    @RequestMapping("loginManagerName")
    @ResponseBody
    public String loginManagerName(String name){
        try {
            Manager manager=managerService.queryManagerByName(name);
            if (manager==null){
                return "该用户已存在";
            }else {
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
