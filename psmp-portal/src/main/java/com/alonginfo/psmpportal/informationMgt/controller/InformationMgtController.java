package com.alonginfo.psmpportal.informationMgt.controller;

import com.alonginfo.psmpcore.util.UploadUtil;
import com.alonginfo.psmpportal.informationMgt.model.InformationMgt;
import com.alonginfo.psmpportal.informationMgt.service.InformationMgtService;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class InformationMgtController {

	@Autowired
	InformationMgtService informationMgtService;
	@Value("${files.directory.filesUrl:/Resources}")
	private String uploadUrl;
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@PostMapping(consumes = "multipart/form-data", value = "/informationUploads")
	public Map<String,String> upload(MultipartFile file) {
		Map<String, String> upload = UploadUtil.upload(file, uploadUrl + "/information");
		return upload;
	}
	/**
	 * @author liqiang
	 * @Description: 查询资讯信息列表
	 * @date 2018/12/27
	 */
	@GetMapping("/informations")
	public Map<String,Object> queryList (@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
	                                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
	                                       InformationMgt informationMgt){
		Page<InformationMgt> page = new Page(currentPage,pageSize);
		List<InformationMgt> list = this.informationMgtService.queryList(page,informationMgt);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("list",list);
		resultMap.put("totle",page.getTotal());
		return resultMap;
	}

	/**
	 * @author liqiang
	 * @Description: 查询资讯信息详情
	 * @date 2018/12/27
	 */
	@GetMapping("/informations/{id}")
	public InformationMgt queryDetail (@PathVariable String id){
		return this.informationMgtService.queryDetail(id);
	}

	/**
	 * @author liqiang
	 * @Description: 新增资讯信息
	 * @date 2018/12/27
	 */
	@PostMapping("/informations")
	public int createInformation (@RequestBody InformationMgt informationMgt){
		return this.informationMgtService.createInformation(informationMgt);
	}

	/**
	 * @author liqiang
	 * @Description: 更新资讯信息
	 * @date 2018/12/27
	 */
	@PutMapping("/informations")
	public int updateInformation (@RequestBody InformationMgt informationMgt){
		return this.informationMgtService.updateInformation(informationMgt);
	}

	/**
	 * @author liqiang
	 * @Description: 删除资讯信息
	 * @date 2018/12/27
	 */
	@DeleteMapping("/informations/{id}")
	public int deleteInformation (@PathVariable String id){
		return this.informationMgtService.deleteInformation(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/informations")
	public int deleteInformations(@RequestBody String[] ids){
		return this.informationMgtService.deleteInformations(ids);
	}
}