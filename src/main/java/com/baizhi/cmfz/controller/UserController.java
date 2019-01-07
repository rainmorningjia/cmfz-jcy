package com.baizhi.cmfz.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.cmfz.entity.Province;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.goeasy.GoEasy;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Miles
 * @Title: UserController
 * @ProjectName cmfz-jcy
 * @Date 2018/12/25--14:01
 */
@Controller
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    FastFileStorageClient storageClient;
    @Resource
    private UserService userService;

    @RequestMapping("insertUser")
    public void insertUser(User user, MultipartFile file1) {
        try {
            InputStream inputStream = file1.getInputStream();
            String exe_name = FilenameUtils.getExtension(file1.getOriginalFilename());
            StorePath storePath = storageClient.uploadFile(inputStream, file1.getSize(), exe_name, null);
            user.setUrlname(storePath.getFullPath());

            String path = "E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\head_pic";
            File descFile = new File(path + "/" + file1.getOriginalFilename());
            String head_pic = file1.getOriginalFilename();
            user.setHead_pic(head_pic);
            file1.transferTo(descFile);
            userService.inserUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("queryUserWeek")
    public Map<String, Object> queryThreeWeekUser() {
        GoEasy goEasy = new GoEasy("http(s)://<REST Host>", "my_appkey");
        goEasy.publish("my_channel", "Hello, GoEasy!");

        Map<String, Object> map = new HashMap<>();
        List<User> users1 = userService.queryWeekUser(7);
        List<User> users2 = userService.queryWeekUser(14);
        List<User> users3 = userService.queryWeekUser(21);
        List<User> users4 = userService.queryWeekUser(28);
        List<User> users5 = userService.queryWeekUser(35);
        map.put("counts", new Integer[]{users1.size(), users2.size(), users3.size(), users4.size(), users5.size()});
        map.put("intervals", new String[]{"一周", "两周", "三周", "四周", "五周"});
        return map;
    }

    @RequestMapping("queryProviceMen")
    public List<Province> queryProvinceMen() {
        List<Province> provinces = userService.queryAllStudentPrivceMan();
        return provinces;
    }

    @RequestMapping("queryProviceWoMen")
    public List<Province> queryProvinceWoMen() {
        List<Province> provinces = userService.queryAllStudentProvinceWomen();
        return provinces;
    }

    @RequestMapping("queryUserWeekGo")
    public void queryThreeWeekUserGo() {
        Map<String, Object> map = new HashMap<>();
        List<User> users1 = userService.queryWeekUser(7);
        List<User> users2 = userService.queryWeekUser(14);
        List<User> users3 = userService.queryWeekUser(21);
        List<User> users4 = userService.queryWeekUser(28);
        List<User> users5 = userService.queryWeekUser(35);
        map.put("counts", new Integer[]{users1.size(), users2.size(), users3.size(), users4.size(), users5.size()});
        map.put("intervals", new String[]{"一周", "两周", "三周", "四周", "五周"});
        String content = JSONObject.toJSONString(map);
        GoEasy goEasy = new GoEasy("http://cdn-hangzhou.goeasy.io", "BC-01176ee4f6e24082997def06091268cf");
        goEasy.publish("dataanaysis", content);
        System.out.println(content);
    }

    @RequestMapping("queryProviceMenGo")
    public void queryProvinceMenGo() {
        List<Province> provinces = userService.queryAllStudentPrivceMan();
        String content = JSONObject.toJSONString(provinces);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-01176ee4f6e24082997def06091268cf");
        goEasy.publish("datamen", content);
    }

    @RequestMapping("queryProviceWoMenGo")
    public void queryProvinceWoMenGo() {
        List<Province> provinces = userService.queryAllStudentProvinceWomen();
        String content = JSONObject.toJSONString(provinces);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-01176ee4f6e24082997def06091268cf");
        goEasy.publish("datawomen", content);
    }

    @RequestMapping("queryUsersByRows")
    public Map<String, Object> queryUsersByRow(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userService.queryAllUser();
        List<User> users1 = userService.queryUserByRow(page, rows);
        map.put("total", users.size());
        map.put("rows", users1);
        return map;
    }

}
