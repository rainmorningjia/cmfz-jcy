package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Role;

import java.util.List;

/**
 * @author Miles
 * @Title: RoleService
 * @ProjectName cmfz-jcy
 * @Date 2019/1/4--18:31
 */
public interface RoleService {
    List<Role> queryRolesByUserName(String username);
}
