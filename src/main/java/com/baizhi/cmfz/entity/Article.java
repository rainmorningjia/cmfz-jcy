package com.baizhi.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author Miles
 * @Title: Article
 * @ProjectName cmfz-jcy
 * @Date 2018/12/29--15:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_article")
public class Article {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private String insert_img;
    private String content;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pub_date;
    private Integer type;
    private Integer guruid;
}
