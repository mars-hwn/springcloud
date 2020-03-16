package com.alonginfo.psmpportal.caseGL.dao;

import com.alonginfo.psmpportal.caseGL.model.CaseGL;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CaseGLDao extends BaseMapper<CaseGL> {
    int insertCase(CaseGL caseGl);

    List<CaseGL> queryList(Page<CaseGL> page,
                           CaseGL caseGl);

    CaseGL queryOneCase(String caid);

    int updateCase(CaseGL caseGl);

    int deleteCase(String caid);

    int deleteCases(@Param("ids") String[] ids);

    List<CaseGL> queryByNums(@Param("num") String num);
}