package com.alonginfo.psmpportal.bannerGL.dao;

import com.alonginfo.psmpportal.bannerGL.model.BannerGL;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BannerGLDao extends BaseMapper<BannerGL> {
        int insertBanner(BannerGL banner);
        List<BannerGL> queryList(Page<BannerGL> page,
                                 BannerGL banner);
        BannerGL queryOneBanner(String bnid);
        int updateBanner(BannerGL banner);
        int deleteBanner(String buid);
        List<BannerGL> queryByNums(@Param("num") String num);
        int deleteBanners(@Param("ids") String[] ids);

}
