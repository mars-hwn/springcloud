package com.alonginfo.psmpportal.caseGL.service;

import com.alonginfo.psmpportal.caseGL.dao.CaseGLDao;
import com.alonginfo.psmpportal.caseGL.model.CaseGL;
import com.alonginfo.psmpportal.menuGL.service.MenuSerivce;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xjj
 * @description 案例信息业务类
 * @data 2018/12/28
 */
@Service
public class CaseGLService {
    @Autowired
    private CaseGLDao caseGlDao;
    @Autowired
    private MenuSerivce menuSerivce;
    /**
     * 增加案例信息
     * @param caseGl
     * @return
     */
    public int insertCase(CaseGL caseGl) {
        caseGl.setCaid(UUID.randomUUID().toString().replace("-",""));
        boolean numAndSetNum = isNumAndSetNum(caseGl.getNum());
        if(!numAndSetNum)caseGl.setNum("5");
        return caseGlDao.insertCase(caseGl);
    }
    /**
     * 案例信息列表分页
     * @param page
     * @param caseGl
     * @return
     */
    public List<CaseGL> queryList(Page<CaseGL> page, CaseGL caseGl){
        Map<String,String> hpMap = new HashMap<>();
        hpMap.put("0","显示");
        hpMap.put("1","不显示");
        List<CaseGL> list = caseGlDao.queryList(page, caseGl);
        Map<String, String> mens = menuSerivce.queryMens(null);
        list.forEach(
                info->{
                    info.setHpStr(hpMap.get(info.getHp()));
                    info.setZflmc(mens.get(info.getZflid()));
                }

        );
        return list;
    }

    /**
     * 修改指定案例信息
     * @param caseGl
     * @return
     */
    public int updateCase(CaseGL caseGl){
        isNumAndSetNum(caseGl);
        return caseGlDao.updateCase(caseGl);
    }

    /**
     * 查询案例信息详情
     * @param caid
     * @return
     */
    public CaseGL queryOneCase(String caid){
        CaseGL result = caseGlDao.queryOneCase(caid);
        result.setHpStr("0".equals(result.getHp()) ? "显示" : "不显示");
        Map<String, String> queryMens = menuSerivce.queryMens(result.getZflid());
        if(queryMens==null&&queryMens.size()<0)return result;
        result.setZflmc(queryMens.get(result.getZflid()));
        return result;
    }

    /**
     * 删除指定案例信息
     * @param caid
     * @return
     */
    public int deleteCase(String caid){
        return caseGlDao.deleteCase(caid);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int deleteCases(String[] ids){
        int i = this.caseGlDao.deleteCases(ids);
        return i;

    }
    /**
     * 查询num并改变num
     *
     * @param num
     * @return
     */
    boolean isNumAndSetNum(String num) {
        if (num != null && !"".equals(num)) {
            List<CaseGL> caseGLS = caseGlDao.queryByNums(num);
            if (caseGLS != null && caseGLS.size() >= 0) {
                for (CaseGL caseGL : caseGLS) {
                    String m = caseGL.getNum();
                    String n = "1".equals(m) ? "2" : "2".equals(m) ? "3" : "5";
                    caseGL.setNum(n);
                    caseGlDao.updateCase(caseGL);
                }
                return true;
            }

        }
        return false;
    }
    /**
     * 查询num并改变num
     *
     * @param num
     * @return
     */
    boolean isNumAndSetNum(CaseGL caseGL1) {
        String num = caseGL1.getNum();
        CaseGL oneCase = caseGlDao.queryOneCase(caseGL1.getCaid());
        if(oneCase.getNum().equals(num))return false;
        if (num != null && !"".equals(num)) {
            List<CaseGL> caseGLS = caseGlDao.queryByNums(num);
            if (caseGLS != null && caseGLS.size() >= 0) {
                for (CaseGL caseGL : caseGLS) {
                    String m = caseGL.getNum();
                    String n = "1".equals(m) ? "2" : "2".equals(m) ? "3" : "5";
                    caseGL.setNum(n);
                    caseGlDao.updateCase(caseGL);

                }
                return true;
            }

        }
        return false;
    }
}
