package com.baizhi.cmfz.mapper;

import com.baizhi.cmfz.entity.Image;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author Miles
 * @Title: ImageMapper
 * @ProjectName cmfz-jcy
 * @Date 2018/12/20--11:30
 */
public interface ImageMapper extends BaseMapper<Image> {
    public List<Image> queryImageByRow(@Param("num1")Integer page, @Param("num2")Integer num2);
}
