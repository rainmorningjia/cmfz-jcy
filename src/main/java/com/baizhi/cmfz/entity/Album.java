package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Miles
 * @Title: 专辑
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--12:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_album")
@ExcelTarget("zhuanji" )
public class Album implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "标题", width = 25,needMerge = true)
    private String title;
    @Excel(name = "集数",needMerge = true)
    private Double count;
    //封面图路径
    @Excel(name = "封面图片", type = 2 ,width = 20 , height = 30,needMerge = true)
    private String coverImg;
    @Excel(name = "评分",needMerge = true)
    private Double score;
    @Excel(name = "作者",needMerge = true)
    private String author;
    //广播员
    @Excel(name = "播音",needMerge = true)
    private String broadcast;
    //简介
    @Excel(name = "简介",width = 20,needMerge = true)
    private String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "上传日期",format = "YYYY年MM月dd日",needMerge = true)
    private Date pub_date;
    @Transient
    @ExcelCollection(name = "章节")
    private List<Chapter> children;
    @Transient
    @ExcelIgnore
    private String state;

}
