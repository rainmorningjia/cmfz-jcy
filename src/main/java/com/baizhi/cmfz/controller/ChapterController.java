package com.baizhi.cmfz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.service.AlbumService;
import com.baizhi.cmfz.service.ChapterService;
import com.baizhi.cmfz.util.AudioUtil;
import com.baizhi.cmfz.util.MybatisUtils;;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Miles
 * @Title: ChapterController
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--14:34
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;
    @Resource
    private AlbumService albumService;
    @Autowired
    private FastFileStorageClient storageClient;
    @RequestMapping("insertChapter")
    @ResponseBody
    public String insertChapter(Chapter chapter, MultipartFile file1) {
        try {
            chapter.setId(MybatisUtils.getUUID());
            String realPath = "E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\audio";
            //获取文件全名
            String coverImage = file1.getOriginalFilename();
            //获取文件名
            String filename=file1.getName();
            //获取文件扩展名
            String ext_name= FilenameUtils.getExtension(coverImage);
            //输出流
            InputStream inputStream=file1.getInputStream();
            StorePath storePath=storageClient.uploadFile(inputStream,file1.getSize(),ext_name,null);
            //将文件路径设为组名和文件名
            chapter.setUrl(storePath.getGroup()+"|"+storePath
                    .getPath());
            Date time = new Date(new java.util.Date().getTime());
            chapter.setUploadDate(time);
            Album album = albumService.queryAlbumById(chapter.getAlbumId());
            album.setCount(album.getCount() + 1);
            albumService.updateAlbum(album);
            //怎么获取时长？
            int size=(int)file1.getSize()/(1024*1024);
            chapter.setSize(size+"M");
            chapterService.insertChapter(chapter);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    @RequestMapping("deleteChapter")
    @ResponseBody
    public String delete(String ids) {
        String[] idS = ids.split(",");
        for (String s :
                idS) {
            Chapter chapter = chapterService.queryChapterById(s);
            String realPath = "E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\audio";
            File file = new File(realPath + "/" + chapter.getUrl());
            file.delete();
            chapterService.deleteChapter(s);
        }
        return "success";
    }

    @RequestMapping("downT")
    @ResponseBody
    public String down() {
        return "success";
    }

    @RequestMapping("downAudio")
    public void downAudio(String filePath, HttpServletResponse responses) {
        String[] ss = filePath.split(",");
        try {
            for (int j=0;j<ss.length;j++) {
                String s=ss[j];
                String realPath = "E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\audio";
                byte[] bs = FileUtils.readFileToByteArray(new File(realPath + "/" + s));
                responses.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(s, "UTF-8"));
                ServletOutputStream outputStream = responses.getOutputStream();
                outputStream.write(bs);

                if (outputStream != null) outputStream.flush();
                if (outputStream == null&&j==ss.length-1) outputStream.close();
            }
        } catch (Exception e) {
        }


    }
    @RequestMapping("importAlbumExcel")
    public void importAlbumExcel(HttpServletResponse response){
        List<Album> albumList=albumService.queryAllAlbumsAndChapter();
        for (Album a:
             albumList) {
            a.setCoverImg("E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\imageAlbum\\"+a.getCoverImg());
        }
        Workbook workbook= ExcelExportUtil.exportExcel(new ExportParams("专辑","专辑","章节"),Album.class,albumList);
        try {
            String filename="albumpoi";
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(filename+".xls","UTF-8"));
            workbook.write(response.getOutputStream());
            if (response.getOutputStream() != null) response.getOutputStream().flush();
            if (response.getOutputStream() == null) response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
