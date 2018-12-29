package com.baizhi.cmfz.mapper;

import com.baizhi.cmfz.entity.Province;
import com.baizhi.cmfz.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Date;
import java.util.List;

/**
 * @author Miles
 * @Title: UserMapper
 * @ProjectName cmfz-jcy
 * @Date 2018/12/25--11:49
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> queryWeekUser(Integer date);
    List<Province> queryAllStudentPrivceMan();
    List<Province> queryAllStudentProvinceWomen();
    List<User> queryUserByRow(@Param("num1")Integer num1,@Param("num2")Integer num2);
}
