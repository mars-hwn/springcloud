package com.alonginfo.psmpportal.ZlxxGL.service;

import com.alonginfo.psmpportal.ZlxxGL.dao.JcttsDao;
import com.alonginfo.psmpportal.ZlxxGL.model.Jctts;
import com.alonginfo.psmpportal.menuGL.service.MenuSerivce;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author xjj
 * @description jctts业务类
 * @data 2019/1/2
 */
@Service
public class JcttsService {
    @Autowired
    private JcttsDao jcttsDao;
    @Autowired
    private MenuSerivce menuSerivce;

    /**
     * 查询列表
     * @param page
     * @param jctts
     * @return
     */
    public List<Jctts> queryAll(Page<Jctts> page, Jctts jctts){
        List<Jctts> jctts1 = jcttsDao.queryAll(page,jctts);
        Map<String, String> mens = menuSerivce.queryMens(null);
        jctts1.forEach(
                info ->{
                    info.setZflmc(mens.get(info.getZflid()));
                }
        );
        return jctts1;
    }

    /**
     * 增加
     * @param jctts
     * @return
     */
    public int insertJctts(Jctts jctts){
        jctts.setId(UUID.randomUUID().toString().replace("-",""));
        boolean numAndSetNum = isNumAndSetNum(jctts.getNum());
        if(!numAndSetNum)jctts.setNum("5");
        int i = jcttsDao.insertJctts(jctts);
        return i;
    }

    /**
     * 修改
     * @param jctts
     * @return
     */
    public int updateJctts(Jctts jctts){
        isNumAndSetNum(jctts);
        int i = jcttsDao.updateJctts(jctts);
        return i;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteJctts(String id){
        int i = jcttsDao.deleteJctts(id);
        return i;
    }

    /**
     * 批量删除jctts
     * @param ids
     * @return
     */
    public int deleteJcttss(String[] ids){
        int i = jcttsDao.deleteJcttss(ids);
        return i;
    }
    /**
     * 指定id查询详细
     * @param id
     * @return
     */
    public Jctts queryOneJctts(String id){
        Jctts jctts = jcttsDao.queryOneJctts(id);
        Map<String, String> queryMens = menuSerivce.queryMens(jctts.getZflid());
        if(queryMens==null&&queryMens.size()<0)return jctts;
        jctts.setZflmc(queryMens.get(jctts.getZflid()));
        return jctts;
    }

    /**
     * 查询num并设置num
     * @param num
     * @return
     */
     boolean isNumAndSetNum(String num){
        if(num!=null && !"".equals(num)){
            List<Jctts> jctts = jcttsDao.queryByNums(num);
            if(jctts!=null && jctts.size()>=0) {
                for (Jctts jctt : jctts) {
                    String jcttNum = jctt.getNum();
                    String n = "1".equals(jcttNum) ? "2" : "2".equals(jcttNum) ? "3" : "5";
                    jctt.setNum(n);
                    jcttsDao.updateJctts(jctt);
                }
                return true;
            }
        }
        return false;
     }
    boolean isNumAndSetNum(Jctts jctt1){
        String num = jctt1.getNum();
        Jctts oneJctts = jcttsDao.queryOneJctts(jctt1.getId());
        if(oneJctts.getNum().equals(num))return false;
        if(num!=null && !"".equals(num)){
            List<Jctts> jctts = jcttsDao.queryByNums(num);
            if(jctts!=null && jctts.size()>=0) {
                for (Jctts jctt : jctts) {
                    String jcttNum = jctt.getNum();
                    String n = "1".equals(jcttNum) ? "2" : "2".equals(jcttNum) ? "3" : "5";
                    jctt.setNum(n);
                    jcttsDao.updateJctts(jctt);
                }
                return true;
            }
        }
        return false;
    }
}
