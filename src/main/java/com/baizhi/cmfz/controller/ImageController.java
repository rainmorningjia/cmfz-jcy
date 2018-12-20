package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Image;
import com.baizhi.cmfz.service.ImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
        map.put("total",total);
        map.put("rows",imageList);
        return map;
    }

}
