package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Table;

/**
 * @author Miles
 * @Title: Role
 * @ProjectName cmfz-jcy
 * @Date 2019/1/4--17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_role")
public class Role {
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username;
    private String roles;
}
