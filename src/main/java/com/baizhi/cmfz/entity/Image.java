package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer id;
    private String title;
    private Date updatetime;
    private String imagepath;
    private Integer status;
    private String description;
}
