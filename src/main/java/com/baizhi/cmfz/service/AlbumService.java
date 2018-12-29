package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;

import java.util.List;

/**
 * @author Miles
 * @Title: AblumService
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--13:59
 */
public interface AlbumService {
    List<Album> queryAllAlbumsAndChapter();
    List<Album> queryAllAb();
    List<Album> queryAllAlbums(Integer page,Integer rows);
    void insertAlbum(Album album);
    void deleteAlbumById(Integer id);
    Album queryAlbumById(Integer id);
    void updateAlbum(Album  album);
    List<Album> queryAlbumsSix();
}
