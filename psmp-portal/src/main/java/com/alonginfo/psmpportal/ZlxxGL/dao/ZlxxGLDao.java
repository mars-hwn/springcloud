package com.alonginfo.psmpportal.ZlxxGL.dao;

import com.alonginfo.psmpportal.ZlxxGL.model.ZlxxGL;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ZlxxGLDao extends BaseMapper<ZlxxGL> {
    int insertZlxx(ZlxxGL zlxxGL);
    List<ZlxxGL> queryList(Page<ZlxxGL> page,ZlxxGL zlxxGL);
    ZlxxGL queryOneZlxx(String id);
    int updateZlxx(ZlxxGL zlxxGL);
    int deleteZlxx(String id);
    List<ZlxxGL> queryByNums(@Param("num") String num);
    int deleteZlxxs(@Param("ids") String[] ids);
}
