package com.baizhi.cmfz.controller;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    @ResponseBody
    public String loginManager(String name,String password){
        try {
            managerService.loginManager(name,password);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();

        }

    }
    @ResponseBody
    @RequestMapping("test")

    public List<Manager> queryTest(){
        List<Manager> list=managerService.queryAll();
        return list;
    }
}
