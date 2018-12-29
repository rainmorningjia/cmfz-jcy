package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Article;

import java.util.List;

/**
 * @author Miles
 * @Title: ArticleService
 * @ProjectName cmfz-jcy
 * @Date 2018/12/29--16:04
 */
public interface ArticleService {
    public List<Article> queryAllArticle();
    public List<Article> queryArtilcesToTwoByTime();
    public void insertArticle(Article article);
    public void deleteArticle(Integer id);


}
