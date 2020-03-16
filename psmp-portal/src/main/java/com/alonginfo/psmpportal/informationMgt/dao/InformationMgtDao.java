package com.alonginfo.psmpportal.informationMgt.dao;

import com.alonginfo.psmpportal.informationMgt.model.InformationMgt;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InformationMgtDao extends BaseMapper<InformationMgt> {
	/**
	 * @author liqiang
	 * @Description: 查询资讯信息列表
	 * @date 2018/12/27
	 */
	List<InformationMgt> queryList(Page<InformationMgt> page, InformationMgt informationMgt);
	/**
	 * @author liqiang
	 * @Description: 查询资讯信息详情
	 * @date 2018/12/27
	 */
	InformationMgt queryDetail (String id);
	/**
	 * @author liqiang
	 * @Description: 新增资讯信息
	 * @date 2018/12/27
	 */
	int createInformation (InformationMgt informationMgt);
	/**
	 * @author liqiang
	 * @Description: 更新资讯信息
	 * @date 2018/12/27
	 */
	int updateInformation (InformationMgt informationMgt);
	/**
	 * @author liqiang
	 * @Description: 删除资讯信息
	 * @date 2018/12/27
	 */
	int deleteInformation (String id);
	int deleteInformations(@Param("ids") String[] ids);

}
