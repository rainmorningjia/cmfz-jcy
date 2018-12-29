package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.entity.Image;
import com.baizhi.cmfz.service.AlbumService;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.service.ImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Miles
 * @Title: Cmfz_firstpage
 * @ProjectName cmfz-jcy
 * @Date 2018/12/29--16:46
 */
@RestController
@RequestMapping("/first_page")
public class Cmfz_firstpageController {
    @Resource
    private ImageService imageService;
    @Resource
    private AlbumService albumService;
    @Resource
    private ArticleService articleService;

    @RequestMapping("all")
    public Map<String, Object> queryOne() {
        List<Image> imageList = imageService.queryImagesByTimeToFive();
        List<Album> albumList=albumService.queryAlbumsSix();
        List<Article> articleList=articleService.queryArtilcesToTwoByTime();
        Map<String,Object> map=new HashMap<>();
        map.put("header",imageList);
        map.put("album",albumList);
        map.put("articel",articleList);
        return map;
    }
    @RequestMapping("wen")
    public List<Album> queryAllAlbum(){
        List<Album> albumList=albumService.queryAllAb();
        return albumList;
    }
    @RequestMapping("si")
    public List<Article> queryAllArticle(){
        List<Article> articleList=articleService.queryAllArticle();
        return articleList;
    }
}
