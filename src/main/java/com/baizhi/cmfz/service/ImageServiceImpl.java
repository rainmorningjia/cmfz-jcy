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
    public void updateIImage(Image image) {
        imageMapper.updateByPrimaryKey(image);
    }
}
