package com.baizhi.cmfz.entity;

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
    private String id;
    private String title;
    private String  size;
    //时长
    private Double duration;
    private String url;
    private Date uploadDate;
    private Integer albumId;

}
