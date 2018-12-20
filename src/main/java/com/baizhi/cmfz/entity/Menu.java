package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Miles
 * @Title: Menu
 * @ProjectName cmfz-jcy
 * @Date 2018/12/19--21:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_menu")
public class Menu {
    @Id
    private Integer id;
    private String name;
    private String iconCls;
    private String url;
    private Integer parentId;
}
