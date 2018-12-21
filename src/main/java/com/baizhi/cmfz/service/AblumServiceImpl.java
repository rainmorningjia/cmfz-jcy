package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.mapper.AlbumMapper;
import com.baizhi.cmfz.mapper.ChapterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @Title: AblumServiceImpl
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--14:02
 */
@Service
@Transactional
public class AblumServiceImpl implements AlbumService {
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private AlbumMapper albumMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> queryAllAlbum() {
        List<Album> albumList = albumMapper.selectAll();
        for (Album a :
                albumList) {
            Chapter chapter = new Chapter();
            chapter.setAlbumId(a.getId());
            List<Chapter> chapterList = chapterMapper.select(chapter);
           a.setChildren(chapterList);
        }
        return albumList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> queryAllAb() {
        List<Album> albumList=albumMapper.selectAll();
        return albumList;
    }



    @Override
    public void insertAlbum(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public void deleteAlbumById(Integer id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Album queryAlbumById(Integer id) {
        Album album = albumMapper.selectByPrimaryKey(id);
        return album;
    }

    @Override
    public void updateAlbum(Album album) {
        albumMapper.updateByPrimaryKey(album);
    }
}