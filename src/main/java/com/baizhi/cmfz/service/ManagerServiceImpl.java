package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.exception.ManagerException;
import com.baizhi.cmfz.exception.SystemException;
import com.baizhi.cmfz.mapper.ManagerMapper;
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
                if (manager==null) {
                    throw new ManagerException("该用户不存在!");
                }
                if(!manager.getPassword().equals(password)){
                    throw new ManagerException("密码错误！");
                }


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


}
