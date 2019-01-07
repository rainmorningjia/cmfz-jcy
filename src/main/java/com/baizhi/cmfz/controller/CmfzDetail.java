package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.service.AlbumService;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.service.ChapterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Miles
 * @Title: Cmfz_detail
 * @ProjectName cmfz-jcy
 * @Date 2019/1/2--8:58
 */
@RestController
@RequestMapping("/detail")
public class CmfzDetail {
    @Resource
    ArticleService articleService;
    @Resource
    AlbumService albumService;
    @Resource
    ChapterService chapterService;
    @RequestMapping("/si")
    public Article queryOneArticle(Integer id){
        Article article=articleService.queryOneById(id);
        return article;
    }
    @RequestMapping("/wen")
    public Map<String,Object> queryOne(Integer id){
        Album album=albumService.queryAlbumById(id);
        List<Chapter> chapterList=chapterService.queryChapterByAblum(id);
        Map<String,Object> map=new HashMap<>();
        map.put("introduction",album);
        map.put("list",chapterList);
        return map;
    }
}
