package com.alonginfo.psmpportal.menuGL.service;

import com.alonginfo.psmpportal.menuGL.dao.MenuDao;
import com.alonginfo.psmpportal.menuGL.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xxj
 * @description 列表业务
 * @data 2019/1/2
 */
@Service
public class MenuSerivce {
    @Autowired
    private MenuDao menuDao;

    /**
     * 根据查询列表
     *
     * @return
     */
    public List<Menu> queryMenuAllByFlid(Integer value){
        return menuDao.queryMenuAllByFlid(value);
    }
    public Map<String,String> queryMens(String zflid){
        Map<String,String> menuMap=new HashMap<String,String>();
        List<Map<String, Object>> maps = menuDao.queryAll(zflid);
        for (Map<String, Object> map : maps) {
            String id=null;
            String flmc=null;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if("zflid".equals(entry.getKey())){
                    id=entry.getValue().toString();
                }else if("flmc".equals(entry.getKey())){
                    flmc=entry.getValue().toString();
                }

            }
            menuMap.put(id,flmc);
        }
        return menuMap;
    }
}
