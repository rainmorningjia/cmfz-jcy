package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Miles
 * @Title: Manager
 * @ProjectName cmfz
 * @Date 2018/12/19--18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cmfz_manager")//数据库中的表名

public class Manager implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private String password;
}
