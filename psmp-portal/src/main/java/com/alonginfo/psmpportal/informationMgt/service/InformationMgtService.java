package com.alonginfo.psmpportal.informationMgt.service;

import com.alonginfo.psmpportal.informationMgt.dao.InformationMgtDao;
import com.alonginfo.psmpportal.informationMgt.model.InformationMgt;
import com.alonginfo.psmpportal.menuGL.service.MenuSerivce;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class InformationMgtService {
	@Autowired
	InformationMgtDao informationMgtDao;

	@Autowired
	private MenuSerivce menuSerivce;
	/**
	 * @author liqiang
	 * @Description: 查询资讯信息列表
	 * @date 2018/12/27
	 */
	public List<InformationMgt> queryList(Page<InformationMgt> page ,InformationMgt informationMgt){
		Map<String,String> indsMap = new HashMap<>();
		Map<String, String> menuMap = menuSerivce.queryMens(null);
		indsMap.put("0","显示");
		indsMap.put("1","不显示");
		List<InformationMgt> result = this.informationMgtDao.queryList(page,informationMgt);
		result.forEach(
				info ->{
					info.setIndsStr(indsMap.get(info.getInds()));
					info.setZflmc(menuMap.get(info.getZflid()));
					info.setPlurl("https://imgsa.baidu.com/exp/w=500/sign=9fa75d947eec54e741ec1a1e89399bfd/d009b3de9c82d1587227891b850a19d8bd3e42f7.jpg");
				}
		);
		return result;
	}

	/**
	 * @author liqiang
	 * @Description: 查询资讯信息详情
	 * @date 2018/12/27
	 */
	public InformationMgt queryDetail(String id){
		InformationMgt result = this.informationMgtDao.queryDetail(id);
		result.setIndsStr("0".equals(result.getInds()) ? "显示" : "不显示");
		if(result.getZflid()==null&&"".equals(result.getZflid()))return result;
		Map<String,String> map = menuSerivce.queryMens(result.getZflid());
		result.setZflmc(map.get(result.getZflid()));
		return result;
	}

	/**
	 * @author liqiang
	 * @Description: 新增资讯信息
	 * @date 2018/12/27
	 */
	public int createInformation(InformationMgt informationMgt){
		informationMgt.setZxid(UUID.randomUUID().toString().replace("-",""));
		return this.informationMgtDao.createInformation(informationMgt);
	}

	/**
	 * @author liqiang
	 * @Description: 更新资讯信息
	 * @date 2018/12/27
	 */
	public int updateInformation(InformationMgt informationMgt){
		return this.informationMgtDao.updateInformation(informationMgt);
	}

	/**
	 * @author liqiang
	 * @Description: 删除资讯信息
	 * @date 2018/12/27
	 */
	public int deleteInformation(String id){
		return this.informationMgtDao.deleteInformation(id);
	}

	/**
	 * 批量删除
	 * @author xjj
	 * @param ids
	 * @return
	 */
	public int deleteInformations(String[] ids){
		int i = this.informationMgtDao.deleteInformations(ids);
		return i;

	}
}
