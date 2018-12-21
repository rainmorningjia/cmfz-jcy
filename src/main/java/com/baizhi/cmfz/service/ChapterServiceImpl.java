package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.mapper.ChapterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @Title: ChapterServiceImpl
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--14:05
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Chapter> queryChapterByAblum(Integer id) {
        Chapter chapter = new Chapter();
        chapter.setAlbumId(id);
        List<Chapter> chapterList = chapterMapper.select(chapter);

        return chapterList;
    }

    @Override
    public void insertChapter(Chapter chapter) {
        chapterMapper.insert(chapter);
    }

    @Override
    public void deleteChapter(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Chapter queryChapterById(String id) {

        Chapter chapter = chapterMapper.selectByPrimaryKey(id);
        return chapter;
    }


    @Override
    public void updateChapter(Chapter chapter) {
        chapterMapper.updateByPrimaryKey(chapter);
    }
}
