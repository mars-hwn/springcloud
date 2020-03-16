package com.alonginfo.psmpportal.ZlxxGL.dao;

import com.alonginfo.psmpportal.ZlxxGL.model.Jctts;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JcttsDao {
    List<Jctts> queryAll(Page<Jctts> page,Jctts jctts);
    int insertJctts(Jctts jctts);
    int updateJctts(Jctts jctts);
    int deleteJctts(String id);
    Jctts queryOneJctts(String id);
    List<Jctts> queryByNums(@Param("num") String num);
    int deleteJcttss(@Param("ids") String[] ids);
}
