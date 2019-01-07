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
 * @Title: User
 * @ProjectName cmfz
 * @Date 2018/12/19--17:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private String nickname;
    private String password;
    private String salt;
    private String sign;
    private String head_pic;
    private String dharma;
    private Integer sex;
    private String province;
    private String city;
    private String address;
    private Integer status;
    private String phone;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reg_date;
    private String urlname;
}
