package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Perm;
import com.baizhi.cmfz.mapper.PermMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @Title: PermServiceImpl
 * @ProjectName cmfz-jcy
 * @Date 2019/1/4--18:37
 */
@Service
@Transactional
public class PermServiceImpl implements PermService {
    @Resource
    private PermMapper permMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Perm> queryPermsByRole(String role) {
        Perm perm=new Perm();
        perm.setRole(role);
        List<Perm> permList=permMapper.select(perm);
        return permList;
    }
}
