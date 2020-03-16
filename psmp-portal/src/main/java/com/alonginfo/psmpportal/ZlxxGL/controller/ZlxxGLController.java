package com.alonginfo.psmpportal.ZlxxGL.controller;

import com.alonginfo.psmpcore.util.UploadUtil;
import com.alonginfo.psmpportal.ZlxxGL.model.ZlxxGL;
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
public class ZlxxGLController {
	private final static Logger log = LoggerFactory.getLogger(ZlxxGLController.class);
	@Autowired
	private ZlxxGLService zlxxGLService;
	@Value("${files.directory.filesUrl:/Resources}")
	private String uploadUrl;
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@PostMapping(consumes = "multipart/form-data", value = "/materialUploads")
	public Map<String,String> upload(MultipartFile file) {
		Map<String, String> upload = UploadUtil.upload(file, uploadUrl + "/material");
		return upload;
	}
	/**
	 * @author xjj
	 * @Description: 查询资料信息列表
	 * @date 2018/12/27
	 */
	@GetMapping("/materials")
	public Map<String,Object> queryList (@RequestParam(value ="currentPage",defaultValue = "1") Integer currentPage,
								   @RequestParam(value ="pageSize",defaultValue = "10") Integer pageSize,
								   ZlxxGL zlxx){
		Page<ZlxxGL> page = new Page(currentPage,pageSize);
		List<ZlxxGL> list = this.zlxxGLService.queryList(page,zlxx);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("list",list);
		resultMap.put("totle",page.getTotal());
		return resultMap;
	}

	/**
	 * @author xjj
	 * @Description: 查询资料信息详情
	 * @date 2018/12/27
	 */
	@GetMapping("/materials/{id}")
	public ZlxxGL queryOneZlxx (@PathVariable String id){
		return this.zlxxGLService.queryOneZlxx(id);
	}

	/**
	 * @author xjj
	 * @Description: 新增资料信息
	 * @date 2018/12/27
	 */
	@PostMapping("/materials")
	public int createZlxx (@RequestBody ZlxxGL zlxx){
		return this.zlxxGLService.insertZlxx(zlxx);
	}

	/**
	 * @author xjj
	 * @Description: 更新资料信息
	 * @date 2018/12/27
	 */
	@PutMapping("/materials")
	public int updateIZlxx (@RequestBody ZlxxGL zlxx){
		return this.zlxxGLService.updateZlxx(zlxx);
	}

	/**
	 * @author xjj
	 * @Description: 删除资料信息
	 * @date 2018/12/27
	 */
	@DeleteMapping("/materials/{id}")
	public int deleteZlxx (@PathVariable String id){
		return this.zlxxGLService.deleteZlxx(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/materials")
	public int deleteZlxxs(@RequestBody String[] ids){
		return this.zlxxGLService.deleteZlxxs(ids);

	}
}