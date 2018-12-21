package com.baizhi.cmfz.entity;

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
public class Album implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private Double count;
    //封面图路径
    private String coverImg;
    private Double score;
    private String author;
    //广播员
    private String broadcast;
    //简介
    private String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date pubDate;
    @Transient
    private List<Chapter> children;
}
