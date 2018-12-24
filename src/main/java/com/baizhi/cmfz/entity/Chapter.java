package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Miles
 * @Title: 章节
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--13:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_chapter")
public class Chapter {
    @Id
    @Excel(name = "编号")
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "大小")
    private String  size;
    //时长
    @Excel(name = "时长")
    private String duration;
    private String url;
    @Excel(name = "上传时间", format = "YYYY年MM月dd日")
    private Date uploadDate;
    @ExcelIgnore
    private Integer albumId;

}
