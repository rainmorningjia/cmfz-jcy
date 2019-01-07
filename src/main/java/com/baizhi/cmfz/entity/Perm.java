package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Table;

/**
 * @author Miles
 * @Title: Perm
 * @ProjectName cmfz-jcy
 * @Date 2019/1/4--17:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_perm")
public class Perm {
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String role;
    private String perm;
}
