package com.baizhi.cmfz.mapper;

import com.baizhi.cmfz.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author Miles
 * @Title: AblumMapper
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--13:58
 */
public interface AlbumMapper extends BaseMapper<Album> {
    List<Album> queryAlbumByRow(@Param("num1") Integer num1, @Param("num2") Integer num2);
}
