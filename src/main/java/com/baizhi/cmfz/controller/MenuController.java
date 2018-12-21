package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.entity.Tree;
import com.baizhi.cmfz.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @Title: MenuController
 * @ProjectName cmfz-jcy
 * @Date 2018/12/19--21:50
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;
    @RequestMapping( "queryAllParentMenu" )
    @ResponseBody
    public List<Menu> queryAllParentMenu(){
        List<Menu> listm=menuService.queryAllParentManu();
        return listm;
    }
    @RequestMapping(value = "queryAllChriMenu",produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public List<Tree> queryAllChriMenu(Integer parentId){
        List<Tree> treeList=menuService.queryAllChrildenMenu(parentId);

        return treeList;
    }
}
