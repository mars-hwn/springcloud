package com.alonginfo.psmpportal.ZlxxGL.service;

import com.alonginfo.psmpportal.ZlxxGL.dao.ZlxxGLDao;
import com.alonginfo.psmpportal.ZlxxGL.model.ZlxxGL;
import com.alonginfo.psmpportal.bannerGL.model.BannerGL;
import com.alonginfo.psmpportal.menuGL.service.MenuSerivce;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author xjj
 * @description 资料信息业务类
 * @data 2018/12/28
 */
@Service
public class ZlxxGLService {
    @Autowired
    private ZlxxGLDao zlxxGLDao;
    @Autowired
    private MenuSerivce menuSerivce;
    /**
     * 增加资料信息
     *
     * @param zlxxGL
     * @return
     */
    public int insertZlxx(ZlxxGL zlxxGL) {
        zlxxGL.setId(UUID.randomUUID().toString().replace("-", ""));
        boolean numAndSetNum = isNumAndSetNum(zlxxGL.getNum());
        if (!numAndSetNum) zlxxGL.setNum("5");
        return zlxxGLDao.insertZlxx(zlxxGL);
    }

    /**
     * 资料信息列表分页
     *
     * @param page
     * @param zlxxGL
     * @return
     */
    public List<ZlxxGL> queryList(Page<ZlxxGL> page, ZlxxGL zlxxGL) {
        List<ZlxxGL> result = zlxxGLDao.queryList(page, zlxxGL);
        Map<String, String> mens = menuSerivce.queryMens(null);
        Map<String,String> lineMap = new HashMap<>();
        lineMap.put("0","下线");
        lineMap.put("1","上线");
        result.forEach(
                info ->{
                    info.setLineStr(lineMap.get(info.getLine()));
                    info.setZflmc(mens.get(info.getZflid()));
                }
        );
        return result;
    }

    /**
     * 修改指定资料信息
     *
     * @param zlxxGL
     * @return
     */
    public int updateZlxx(ZlxxGL zlxxGL) {
        isNumAndSetNum(zlxxGL);
        return zlxxGLDao.updateZlxx(zlxxGL);
    }

    /**
     * 查询案例资料详情
     *
     * @param id
     * @return
     */
    public ZlxxGL queryOneZlxx(String id) {
        ZlxxGL zlxxGL = zlxxGLDao.queryOneZlxx(id);
        Map<String, String> mens = menuSerivce.queryMens(zlxxGL.getZflid());
        if(mens==null && mens.size()<0)return zlxxGL;
        zlxxGL.setZflmc(mens.get(zlxxGL.getZflid()));
        return zlxxGL;
    }

    /**
     * 删除指定资料信息
     *
     * @param id
     * @return
     */
    public int deleteZlxx(String id) {
        return zlxxGLDao.deleteZlxx(id);
    }

    /**
     * 批量删除资料信息
     * @param ids
     * @return
     */
    public int deleteZlxxs(String[] ids){
        int i = zlxxGLDao.deleteZlxxs(ids);
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
            List<ZlxxGL> zlxxGLS = zlxxGLDao.queryByNums(num);
            if (zlxxGLS != null && zlxxGLS.size() >= 0) {
                for (ZlxxGL zlxx : zlxxGLS) {
                    String m = zlxx.getNum();
                    String n = "1".equals(m) ? "2" : "2".equals(m) ? "3" : "5";
                    zlxx.setNum(n);
                    zlxxGLDao.updateZlxx(zlxx);

                }
                return true;
            }

        }
        return false;
    }
    boolean isNumAndSetNum(ZlxxGL zlxx) {
        String num = zlxx.getNum();
        ZlxxGL oneZlxx = zlxxGLDao.queryOneZlxx(zlxx.getId());
        if(oneZlxx.getNum().equals(num))return false;
        if (num != null && !"".equals(num)) {
            List<ZlxxGL> zlxxGLS = zlxxGLDao.queryByNums(num);
            if (zlxxGLS!=null&&zlxxGLS.size() >= 0){
                for (ZlxxGL zlxx1 : zlxxGLS) {
                    String m = zlxx1.getNum();
                    String n = "1".equals(m) ? "2" : "2".equals(m) ? "3" : "5";
                    zlxx1.setNum(n);
                    zlxxGLDao.updateZlxx(zlxx1);

                }
                return true;
            }

        }
        return false;
    }
}
