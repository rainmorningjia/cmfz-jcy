package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Miles
 * @Title: UserService
 * @ProjectName cmfz
 * @Date 2018/12/19--18:45
 */
public interface ManagerService {
    public void loginManager(String name,String password);
    public Manager queryManagerById(Integer id);
    public  Manager queryManagerByName(String name);
    public List<Manager> queryAll();
}
