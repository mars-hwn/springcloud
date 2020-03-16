package com.alonginfo.psmpportal.bannerGL.service;

import com.alonginfo.psmpportal.bannerGL.dao.BannerGLDao;
import com.alonginfo.psmpportal.bannerGL.model.BannerGL;
import com.alonginfo.psmpportal.caseGL.model.CaseGL;
import com.alonginfo.psmpportal.menuGL.service.MenuSerivce;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xjj
 * @description banner信息业务类
 * @data 2018/12/28
 */
@Service
public class BannerGLService {
    @Autowired
    private BannerGLDao bannerDao;
    @Autowired
    private MenuSerivce menuSerivce;
    /**
     * 增加banner信息
     * @param bannerGL
     * @return
     */
    public int insertBanner(BannerGL bannerGL){
        bannerGL.setBnid(UUID.randomUUID().toString().replace("-",""));
        boolean numAndSetNum = isNumAndSetNum(bannerGL.getNum());
        if(!numAndSetNum)bannerGL.setNum("5");
        return bannerDao.insertBanner(bannerGL);
    }

    /**
     * banner信息列表分页
     * @param page
     * @param bannerGL
     * @return
     */
    public List<BannerGL> queryList(Page<BannerGL> page,
                                    BannerGL bannerGL){
        List<BannerGL> result = bannerDao.queryList(page,bannerGL);
        Map<String,String> lineMap = new HashMap<>();
        Map<String, String> mens = menuSerivce.queryMens(null);
        lineMap.put("0","下线");
        lineMap.put("1","上线");
        result.forEach(
                info ->{
                    info.setLineStr(lineMap.get(info.getLine()));
                    info.setZflmc(mens.get(info.getZflid()));
                    info.setPlurl("https://imgsa.baidu.com/exp/w=500/sign=9fa75d947eec54e741ec1a1e89399bfd/d009b3de9c82d1587227891b850a19d8bd3e42f7.jpg");
                }
        );
        return result;
    }

    /**
     * 修改指定banner信息
     * @param bannerGL
     * @return
     */
    public int updateBanner(BannerGL bannerGL){
        isNumAndSetNum(bannerGL);
        int i = bannerDao.updateBanner(bannerGL);
        return i;

    }

    /**
     * 查询banner信息详情
     * @param bnid
     * @return
     */
    public BannerGL queryOneBanner(String bnid){
        BannerGL banner = bannerDao.queryOneBanner(bnid);
        Map<String, String> queryMens = menuSerivce.queryMens(banner.getZflid());
        if(queryMens==null&&queryMens.size()<0)return banner;
        banner.setZflmc(queryMens.get(banner.getZflid()));
        return banner;

    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int deleteBanners(String[] ids){
        int i = bannerDao.deleteBanners(ids);
        return i;
    }
    /**
     * 删除指定banner信息
     * @param buid
     * @return
     */
    public int deleteBanner(String buid){
        return bannerDao.deleteBanner(buid);
    }
    /**
     * 查询num并改变num
     *
     * @param num
     * @return
     */
    boolean isNumAndSetNum(BannerGL bannerGL) {
        String num = bannerGL.getNum();
        BannerGL banner = bannerDao.queryOneBanner(bannerGL.getBnid());
        if(banner.getNum().equals(num))return false;
        if (num != null && !"".equals(num)) {
            List<BannerGL> bannerGLS = bannerDao.queryByNums(num);
            if (bannerGLS != null && bannerGLS.size() >= 0) {
                for (BannerGL bannerGL1 : bannerGLS) {
                    String m = bannerGL1.getNum();
                    String n = "1".equals(m) ? "2" : "2".equals(m) ? "3" : "5";
                    bannerGL1.setNum(n);
                    bannerDao.updateBanner(bannerGL1);
                }
                return true;
            }

        }
        return false;
    }
    boolean isNumAndSetNum(String num) {
        if (num != null && !"".equals(num)) {
            List<BannerGL> bannerGLS = bannerDao.queryByNums(num);
            if (bannerGLS != null && bannerGLS.size() >= 0) {
                for (BannerGL bannerGL : bannerGLS) {
                    String m = bannerGL.getNum();
                    String n = "1".equals(m) ? "2" : "2".equals(m) ? "3" : "5";
                    bannerGL.setNum(n);
                    bannerDao.updateBanner(bannerGL);
                }
                return true;
            }
        }
        return false;
    }
}
