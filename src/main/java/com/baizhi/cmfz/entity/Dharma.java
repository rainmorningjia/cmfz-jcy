package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Miles
 * @Title: dharma
 * @ProjectName cmfz-jcy
 * @Date 2018/12/29--15:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_dharma")
//大师
public class Dharma {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private String head_pic;
    private String status;
}
