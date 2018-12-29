package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Image;
import com.baizhi.cmfz.mapper.ImageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @Title: ImagerServiceImpl
 * @ProjectName cmfz-jcy
 * @Date 2018/12/20--11:35
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageMapper imageMapper;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Image> queryImageByRow(Integer page,Integer rows) {
        List<Image> imageList=imageMapper.queryImageByRow((page-1)*rows,page*rows );
        return imageList;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Image> queryAllImage(){
        List<Image> imageList=imageMapper.selectAll();
        return imageList;
    }

    @Override
    public void deleteImage(Integer id) {
        imageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateImage(Image image) {
        imageMapper.updateByPrimaryKey(image);
    }

    @Override
    public Image queryImageById(Integer id) {
        Image image=imageMapper.selectByPrimaryKey(id);
        return image;
    }

    @Override
    public void insertImage(Image image) {
        imageMapper.insert(image);
    }

    @Override
    public List<Image> queryImagesByTimeToFive() {
        List<Image> imageList=imageMapper.queryImageOrderByTimeToFive();
        return imageList;
    }
}
