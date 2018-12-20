package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.entity.Tree;

import java.util.List;

/**
 * @author Miles
 * @Title: MenuService
 * @ProjectName cmfz-jcy
 * @Date 2018/12/19--21:45
 */
public interface MenuService {
    public List<Menu> queryAllParentManu();
    public List<Tree> queryAllChrildenMenu(Integer parentId);
}
