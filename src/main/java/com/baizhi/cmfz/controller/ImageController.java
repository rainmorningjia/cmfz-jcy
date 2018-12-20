package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Image;
import com.baizhi.cmfz.service.ImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
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
            imagerService.deleteImage(id);
        }

    }

    @RequestMapping("addImage")
    public String insertImage(Image image, MultipartFile file1) {
        try {
            String realPath="E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\imageslun";
            File descFile=new File(realPath+"/"+file1.getOriginalFilename());
            file1.transferTo(descFile);
            String  imagename=file1.getOriginalFilename();
            image.setImagepath(imagename);
            imagerService.insertImage(image);
            Date time=new Date(new java.util.Date().getTime());
            image.setUpdatetime(time);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

    }
}
