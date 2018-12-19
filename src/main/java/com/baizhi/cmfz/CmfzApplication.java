package com.baizhi.cmfz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Miles
 * @Title: CmfzApplication
 * @ProjectName cmfz
 * @Date 2018/12/19--17:57
 */
@SpringBootApplication
@MapperScan(basePackages = "com.baizhi.cmfz.mapper")
public class CmfzApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmfzApplication.class,args);
    }
}
