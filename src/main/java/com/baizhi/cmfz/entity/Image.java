package com.baizhi.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Miles
 * @Title: Image
 * @ProjectName cmfz-jcy
 * @Date 2018/12/20--11:26
 */
@Table(name = "cmfz_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date updatetime;
    private String imagepath;
    private String status;
    private String description;
}
