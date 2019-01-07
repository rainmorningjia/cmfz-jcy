package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.exception.ManagerException;
import com.baizhi.cmfz.exception.SystemException;
import com.baizhi.cmfz.mapper.ManagerMapper;
import com.baizhi.cmfz.util.Md5Util;
import com.baizhi.cmfz.util.MybatisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @Title: ManagerServiceImpl
 * @ProjectName cmfz
 * @Date 2018/12/19--18:49
 */
@Service
@Transactional
public class ManagerServiceImpl implements  ManagerService {

    @Resource
    private ManagerMapper managerMapper;
    @Override
    public void loginManager(String name,String password ) {
                Manager manager=queryManagerByName(name);
                Subject subject= SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken(name,password);
            subject.login(token);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Manager queryManagerById(Integer id) {
        Manager manager=null;
        try{
            manager=managerMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new SystemException(e.getMessage());
        }
        return manager;
    }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Manager queryManagerByName(String name) {
        Manager manager = new Manager();
        try{

            manager.setName(name);
            manager=managerMapper.selectOne(manager);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("系统异常!");

        }
        return manager;
    }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Manager> queryAll(){
        List<Manager> list=managerMapper.selectAll();
        return list;
    }

    @Override
    public void inserManager(Manager manager) {
        String salt= Md5Util.getSalt();
        manager.setSalt(salt);
        String s=new SimpleHash("MD5",manager.getPassword(),salt,1024).toString();
        manager.setPassword(s);
        managerMapper.insert(manager);
    }
}
