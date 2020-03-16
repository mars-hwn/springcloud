package com.alonginfo.psmpportal.menuGL.dao;

import com.alonginfo.psmpportal.menuGL.model.Menu;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuDao {

    List<Menu> queryMenuAllByFlid(Integer value);
    List<Map<String, Object>> queryAll(@Param("zflid") String zflid);
}
