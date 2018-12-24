package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author Miles
 * @Title: User
 * @ProjectName cmfz
 * @Date 2018/12/19--17:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private Date birthday;
}
