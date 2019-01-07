package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Image;
import com.baizhi.cmfz.service.ImageService;
import com.github.pagehelper.PageHelper;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Miles
 * @Title: ImageController
 * @ProjectName cmfz-jcy
 * @Date 2018/12/20--14:12
 */

@RequestMapping("/image")
@RestController
public class ImageController {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private ImageService imagerService;

    @RequestMapping("queryImageByRow")
    public Map<String, Object> queryImages(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer total = imagerService.queryAllImage().size();
        List<Image> imageList = imagerService.queryImageByRow(page, rows);
        map.put("total", total);
        map.put("rows", imageList);
        return map;
    }

    @RequestMapping("updateImage")
    public void updateImage(Image image) {
        imagerService.updateImage(image);
    }

    @RequestMapping("deleteImage")
    public void deleteImage(String ids) {
        String[] idss = ids.split(",");
        for (String s :
                idss) {
            Integer id = Integer.parseInt(s);
            Image image=imagerService.queryImageById(id);
            String realPath="E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\imageslun";
            File file=new File(realPath+"/"+image.getImagepath());
            file.delete();
            imagerService.deleteImage(id);
        }

    }

    @RequestMapping("addImage")
    public String insertImage(Image image, MultipartFile file1) {
        try {
            InputStream inputStream=file1.getInputStream();
            String exa_name= FilenameUtils.getExtension(file1.getOriginalFilename());
            StorePath storePath=fastFileStorageClient.uploadFile(inputStream,file1.getSize(),exa_name,null);
            image.setGroupname(storePath.getGroup());
            image.setUrlname(storePath.getPath());
            String realPath="E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\imageslun";
            File descFile=new File(realPath+"/"+file1.getOriginalFilename());
            file1.transferTo(descFile);
            String  imagepath=file1.getOriginalFilename();
            Date time=new Date(new java.util.Date().getTime());
            image.setPublish_time(time);
            image.setImagepath(imagepath);
            imagerService.insertImage(image);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

    }
    @RequestMapping(value = "testi",produces = "text/html;charset=UTF-8")
    public List<Image> queryI(){
        PageHelper.startPage(1,3);
        List<Image> imageList=imagerService.queryAllImage();

        return imageList;
    }
}
