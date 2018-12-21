package com.baizhi.cmfz.controller;
import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.entity.Image;
import com.baizhi.cmfz.service.ChapterService;
import com.baizhi.cmfz.util.MybatisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.sql.Date;

/**
 * @author Miles
 * @Title: ChapterController
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--14:34
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;
    @RequestMapping("insertChapter")
    public String insertChapter(Chapter chapter, MultipartFile file1){
        try {
            chapter.setId(MybatisUtils.getUUID());
            String realPath="E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\audio";
            File descFile=new File(realPath+"/"+file1.getOriginalFilename());
            file1.transferTo(descFile);
            String  coverImage=file1.getOriginalFilename();
            chapter.setUrl(coverImage);
            Date time=new Date(new java.util.Date().getTime());
            chapter.setUploadDate(time);
            chapterService.insertChapter(chapter);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

    }
    @RequestMapping("deleteChapter")
    public String delete(String ids){
        String[] idS = ids.split(",");
        for (String s :
                idS) {
            Chapter chapter=chapterService.queryChapterById(s);
            String realPath="E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\audio";
            File file=new File(realPath+"/"+chapter.getUrl());
            file.delete();
            chapterService.deleteChapter(s);
        }
        return "success";
    }
}
