package com.alonginfo.psmpportal.caseGL.controller;

import com.alonginfo.psmpcore.util.UploadUtil;
import com.alonginfo.psmpportal.caseGL.model.CaseGL;
import com.alonginfo.psmpportal.caseGL.service.CaseGLService;
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
public class CaseGLController {
	private final static Logger log = LoggerFactory.getLogger(CaseGLController.class);

	@Autowired
	private CaseGLService caseGLService;
	@Value("${files.directory.filesUrl:/Resources}")
	private String uploadUrl;
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@PostMapping(consumes = "multipart/form-data", value = "/caseUploads")
	public Map<String,String> upload(MultipartFile file) {
		Map<String, String> upload = UploadUtil.upload(file, uploadUrl + "/case");
		return upload;
	}
	/**
	 * @author xjj
	 * @Description: 查询案例信息列表
	 * @date 2018/12/27
	 */
	@GetMapping("/cases")
	public Map<String,Object> queryList (@RequestParam(value ="currentPage",defaultValue = "1") Integer currentPage,
								   @RequestParam(value ="pageSize",defaultValue = "10") Integer pageSize,
								   CaseGL caseGl){
		Page<CaseGL> page = new Page(currentPage,pageSize);
		List<CaseGL> list = this.caseGLService.queryList(page,caseGl);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("list",list);
		resultMap.put("totle",page.getTotal());
		return resultMap;
	}
	/**
	 * @author xjj
	 * @Description: 查询案例信息详情
	 * @date 2018/12/27
	 */
	@GetMapping("/cases/{id}")
	public CaseGL queryOneCase (@PathVariable String id){
		return this.caseGLService.queryOneCase(id);
	}

	/**
	 * @author xjj
	 * @Description: 新增案例信息
	 * @date 2018/12/27
	 */
	@PostMapping("/cases")
	public int createCase (@RequestBody CaseGL caseGl){
		return this.caseGLService.insertCase(caseGl);
	}

	/**
	 * @author xjj
	 * @Description: 更新案例信息
	 * @date 2018/12/27
	 */
	@PutMapping("/cases")
	public int updateCase (@RequestBody CaseGL caseGl){
		return this.caseGLService.updateCase(caseGl);
	}

	/**
	 * @author xjj
	 * @Description: 删除案例信息
	 * @date 2018/12/27
	 */
	@DeleteMapping("/cases/{id}")
	public int deleteCase (@PathVariable String id){
		return this.caseGLService.deleteCase(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/cases")
	public int deleteCase(@RequestBody String[] ids){
		int i = this.caseGLService.deleteCases(ids);
		return i;
	}
}