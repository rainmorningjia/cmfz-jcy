package com.baizhi.cmfz.mapper;

import com.baizhi.cmfz.entity.Article;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author Miles
 * @Title: ArticleMapper
 * @ProjectName cmfz-jcy
 * @Date 2018/12/29--15:55
 */
public interface ArticleMapper extends BaseMapper<Article> {
        public List<Article> queryArtilcesToTwoByTime();
}
