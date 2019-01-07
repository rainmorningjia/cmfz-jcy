package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Province;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.mapper.UserMapper;
import com.baizhi.cmfz.util.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * @author Miles
 * @Title: UserServiceImpl
 * @ProjectName cmfz-jcy
 * @Date 2018/12/25--13:08
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryWeekUser(Integer date) {
            List<User> users=userMapper.queryWeekUser(date);
            return users;
    }

    @Override
    public List<Province> queryAllStudentPrivceMan() {
        List<Province> provinces=userMapper.queryAllStudentPrivceMan();
        return provinces;
    }

    @Override
    public List<Province> queryAllStudentProvinceWomen() {
        List<Province> provinces=userMapper.queryAllStudentProvinceWomen();
        return provinces;
    }

    @Override
    @Transactional
    public void inserUser(User user) {
        String password="123456";
        String salt= Md5Util.getSalt();
        String passwordn=Md5Util.encryption(password+salt);
        user.setPassword(passwordn);
        user.setSalt(salt);
        userMapper.insert(user);
    }

    @Override
    public List<User> queryUserByRow(Integer page, Integer rows) {
        List<User> users=userMapper.queryUserByRow((page-1)*rows,rows);
        return users;
    }

    @Override
    public List<User> queryAllUser() {
        List<User> users=userMapper.selectAll();
        return users;
    }

    @Override
    public void LoginUser(User user) {
        if (userMapper.selectByPrimaryKey(user.getId())==null){
            throw new RuntimeException("该用户不存在！");
        }
        if (!Md5Util.encryption(user.getPassword()+userMapper.selectByPrimaryKey(user.getId()).getSalt()).equals(userMapper.selectByPrimaryKey(user.getId()).getPassword())){
            throw new RuntimeException("密码不正确！");
        }
    }

    @Override
    public User queryUserByID(Integer id) {
        User user=userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public User queryUserByNickName(String nickName) {
        User usero=new User();
        usero.setNickname(nickName);
        User user=userMapper.selectOne(usero);
        return user;
    }

    @Override
    public User queryUserByPhone(String phone) {
        User user=new User();
        user.setPhone(phone);
        User user1=userMapper.selectOne(user);
        return user1;
    }

    @Override
    @Transactional
    public User updataUser(User user) {
        userMapper.updateByPrimaryKey(user);
        User user1=queryUserByID(user.getId());
        return user1;
    }
}
