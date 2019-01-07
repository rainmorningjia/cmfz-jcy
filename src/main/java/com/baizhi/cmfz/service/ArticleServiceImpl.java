package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @Title: ArticleServiceImpl
 * @ProjectName cmfz-jcy
 * @Date 2018/12/29--16:07
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Article> queryAllArticle() {
        List<Article> articles=articleMapper.selectAll();
        return articles;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Article> queryArtilcesToTwoByTime() {
        List<Article> articles=articleMapper.queryArtilcesToTwoByTime();
        return articles;
    }

    @Override
    public void insertArticle(Article article) {

    }

    @Override
    public void deleteArticle(Integer id) {

    }

    @Override
    public Article queryOneById(Integer id) {
        Article article=articleMapper.selectByPrimaryKey(id);
        return  article;
    }
}
