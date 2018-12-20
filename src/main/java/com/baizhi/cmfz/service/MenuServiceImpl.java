package com.baizhi.cmfz.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.entity.Tree;
import com.baizhi.cmfz.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @Title: MenuServiceImpl
 * @ProjectName cmfz-jcy
 * @Date 2018/12/19--21:47
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService{
    @Resource
    private MenuMapper menuMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Menu> queryAllParentManu() {
        Menu menu=new Menu();
        menu.setParentId(0);
        List<Menu> listm=menuMapper.select(menu);
        return listm;
    }

    @Override
    public List<Tree> queryAllChrildenMenu(Integer parentId) {
       List<Tree> treeList=new ArrayList<>();

        Menu menu=new Menu();
        menu.setParentId(parentId);
        System.out.println(parentId);
        List<Menu> listm=menuMapper.select(menu);
        for (Menu m:
                listm) {
            Tree tree=new Tree();
            tree.setId(m.getId());
            tree.setText(m.getName());
            tree.setUrl("#");
            treeList.add(tree);
        }
        for (Tree t:
             treeList) {
            System.out.println(t);
        }
        return treeList;
    }
}
