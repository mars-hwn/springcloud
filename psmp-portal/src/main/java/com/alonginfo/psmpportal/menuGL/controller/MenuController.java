package com.alonginfo.psmpportal.menuGL.controller;

import com.alonginfo.psmpportal.menuGL.model.Menu;
import com.alonginfo.psmpportal.menuGL.service.MenuSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xjj
 * @description 列表控制层
 * @data 2019/1/2
 */
@RestController
public class MenuController {
    @Autowired
    private MenuSerivce menuSerivce;

    /**
     * 查询指定所有列表
     * @return
     */
    @GetMapping("/menus/{value}")
    public List<Menu> queryList (@PathVariable Integer value){
        List<Menu> menus = menuSerivce.queryMenuAllByFlid(value);
        return menus;
    }

}
