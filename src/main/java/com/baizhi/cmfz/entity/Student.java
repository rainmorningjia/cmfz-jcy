package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Miles
 * @Title: Student
 * @ProjectName cmfz-jcy
 * @Date 2018/12/24--17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student implements Serializable {
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "名字")
    private String name;
    @Excel(name = "生日",format = "YYYY年MM月dd日")
    private Date birthday;
    @Excel(name = "年龄")
    private Integer age;
}
