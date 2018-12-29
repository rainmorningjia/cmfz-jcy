package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Province;
import com.baizhi.cmfz.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author Miles
 * @Title: UserService
 * @ProjectName cmfz-jcy
 * @Date 2018/12/25--11:49
 */
public interface UserService {
    List<User> queryWeekUser(Integer date1);
    List<Province> queryAllStudentPrivceMan();
    List<Province> queryAllStudentProvinceWomen();
    void inserUser(User user);
    List<User> queryUserByRow(Integer page,Integer rows);
    List<User> queryAllUser();

}
