package com.alonginfo.psmpportal.ZlxxGL.controller;

import com.alonginfo.psmpcore.util.UploadUtil;
import com.alonginfo.psmpportal.ZlxxGL.model.Jctts;
import com.alonginfo.psmpportal.ZlxxGL.model.ZlxxGL;
import com.alonginfo.psmpportal.ZlxxGL.service.JcttsService;
import com.alonginfo.psmpportal.ZlxxGL.service.ZlxxGLService;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JcttsController {
	private final static Logger log = LoggerFactory.getLogger(JcttsController.class);
	@Autowired
	private JcttsService jcttsService;
	@Value("${files.directory.filesUrl:/Resources}")
	private String uploadUrl;
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@PostMapping(consumes = "multipart/form-data", value = "/jcttsUploads")
	public Map<String,String> upload(MultipartFile file) {
		Map<String, String> upload = UploadUtil.upload(file, uploadUrl + "/jctts");
		return upload;
	}
	/**
	 * @author xjj
	 * @Description: 查询jctts信息列表
	 * @date 2019/1/2
	 */
	@GetMapping("/jcttss")
	public Map<String,Object> queryList (@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
								  @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
								  Jctts jctts){

		Page<Jctts> page = new Page(currentPage,pageSize);
		List<Jctts> list = this.jcttsService.queryAll(page, jctts);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("list",list);
		resultMap.put("totle",page.getTotal());
		return resultMap;
	}

	/**
	 * @author xjj
	 * @Description: 查询Jctts信息详情
	 * @date 2019/1/2
	 */
	@GetMapping("/jcttss/{id}")
	public Jctts queryOneJctts (@PathVariable String id){
		return this.jcttsService.queryOneJctts(id);
	}

	/**
	 * @author xjj
	 * @Description: 新增Jctts信息
	 * @date 2019/1/2
	 */
	@PostMapping("/jcttss")
	public int createJctts (@RequestBody Jctts jctts){
		return this.jcttsService.insertJctts(jctts);
	}

	/**
	 * @author xjj
	 * @Description: 更新Jctts信息
	 * @date 2019/1/2
	 */
	@PutMapping("/jcttss")
	public int updateJctts (@RequestBody Jctts jctts){
		return this.jcttsService.updateJctts(jctts);
	}

	/**
	 * @author xjj
	 * @Description: 删除Jctts信息
	 * @date 2019/1/2
	 */
	@DeleteMapping("/jcttss/{id}")
	public int deletejctts (@PathVariable String id){

		return this.jcttsService.deleteJctts(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/jcttss")
	public int deletejcttss(@RequestBody String[] ids){
		return this.jcttsService.deleteJcttss(ids);
	}
}