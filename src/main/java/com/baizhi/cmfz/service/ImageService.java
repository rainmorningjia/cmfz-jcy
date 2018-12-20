package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Image;

import java.util.List;

/**
 * @author Miles
 * @Title: ImagerService
 * @ProjectName cmfz-jcy
 * @Date 2018/12/20--11:33
 */
public interface ImageService {
    public List<Image> queryImageByRow(Integer num1,Integer num2);
    public List<Image> queryAllImage();
    public void updateImage(Image image);
    public void deleteImage(Integer id);
    public void insertImage(Image image);

}
