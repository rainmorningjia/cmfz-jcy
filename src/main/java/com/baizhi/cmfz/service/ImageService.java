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
    List<Image> queryImageByRow(Integer num1, Integer num2);

    List<Image> queryAllImage();

    void updateImage(Image image);

    void deleteImage(Integer id);

    void insertImage(Image image);

    Image queryImageById(Integer id);
    List<Image> queryImagesByTimeToFive();
}
