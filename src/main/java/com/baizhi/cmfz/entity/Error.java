package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Miles
 * @Title: Error
 * @ProjectName cmfz-jcy
 * @Date 2019/1/2--9:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error implements Serializable {
    private String error;
    private String errmsg;
}
