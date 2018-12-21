package com.baizhi.cmfz.service;
import com.baizhi.cmfz.entity.Chapter;

import java.util.List;

/**
 * @author Miles
 * @Title: ChapterService
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--14:03
 */
public interface ChapterService {
    List<Chapter> queryChapterByAblum(Integer id);
    void insertChapter(Chapter chapter);
    void deleteChapter(String id);
    Chapter queryChapterById(String id);
    void updateChapter(Chapter chapter);
}
