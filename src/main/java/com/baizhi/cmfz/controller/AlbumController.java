package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
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
 * @Title: AblumController
 * @ProjectName cmfz-jcy
 * @Date 2018/12/21--14:29
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    @Resource
    private AlbumService albumService;

    @RequestMapping(value = "queryAllAlbum", produces = "text/html;charset=utf-8")
    public Map<String ,Object> queryAllAlbum(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        Integer total=albumService.queryAllAb().size();
        List<Album> albumList = albumService.queryAllAlbums(page, rows);
        map.put("total",total);
        map.put("rows",albumList);
        return map;
    }

    @RequestMapping(value = "queryAllAlb", produces = "text/html;charset=utf-8")
    public List<Album> queryAllAl() {
        List<Album> albumList = albumService.queryAllAb();
        return albumList;
    }

    @RequestMapping("insertAlbum")
    public String insertAlbum(Album album, MultipartFile file1) {
        try {
            String realPath = "E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\imageAlbum";
            File descFile = new File(realPath + "/" + file1.getOriginalFilename());
            file1.transferTo(descFile);
            String coverImage = file1.getOriginalFilename();
            album.setCoverImg(coverImage);
            albumService.insertAlbum(album);
            Date time = new Date(new java.util.Date().getTime());
            album.setPub_date(time);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    @RequestMapping("queryAlbum")
    public Album queryAlbum(Integer id) {
        Album album = albumService.queryAlbumById(id);
        return album;
    }

    @RequestMapping("deleteAlbum")
    public void deleteAlb(Integer id) {
        String realPath = "E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\imageAlbum";
        String imgName = albumService.queryAlbumById(id).getCoverImg();
        File file = new File(realPath + "/" + imgName);
        file.delete();
        albumService.deleteAlbumById(id);
    }

}
