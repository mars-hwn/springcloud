package com.alonginfo.psmpportal.bannerGL.controller;

import com.alonginfo.psmpcore.util.UploadUtil;
import com.alonginfo.psmpportal.bannerGL.model.BannerGL;
import com.alonginfo.psmpportal.bannerGL.service.BannerGLService;
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
public class BannerGLController {
	private final static Logger log = LoggerFactory.getLogger(BannerGLController.class);

	@Autowired
	private BannerGLService bannerService;
	@Value("${files.directory.filesUrl:/Resources}")
	private String uploadUrl;
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@PostMapping(consumes = "multipart/form-data", value = "/bannerUploads")
	public Map<String,String> upload(MultipartFile file) {
		Map<String, String> upload = UploadUtil.upload(file, uploadUrl + "/banner");
		return upload;
	}
	/**
	 * @author xjj
	 * @Description: 查询banner信息列表
	 * @date 2018/12/27
	 */
	@GetMapping("/banners")
	public Map<String,Object> queryList (@RequestParam(value ="currentPage",defaultValue = "1") Integer currentPage,
                                     @RequestParam(value ="pageSize",defaultValue = "10") Integer pageSize,
                                     BannerGL banner){
		Page<BannerGL> page = new Page(currentPage,pageSize);
		List<BannerGL> list = this.bannerService.queryList(page,banner);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("list",list);
		resultMap.put("totle",page.getTotal());
		return resultMap;
	}

	/**
	 * @author xjj
	 * @Description: 查询banner信息详情
	 * @date 2018/12/27
	 */
	@GetMapping("/banners/{id}")
	public BannerGL queryOneBanner (@PathVariable String id){
		return this.bannerService.queryOneBanner(id);
	}

	/**
	 * @author xjj
	 * @Description: 新增banner信息
	 * @date 2018/12/27
	 */
	@PostMapping("/banners")
	public int createBanner (@RequestBody BannerGL banner){
		return this.bannerService.insertBanner(banner);
	}

	/**
	 * @author xjj
	 * @Description: 更新banner信息
	 * @date 2018/12/27
	 */
	@PutMapping("/banners")
	public int updateBanner (@RequestBody BannerGL banner){
		return this.bannerService.updateBanner(banner);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/banners")
	public int deleteBanners(@RequestBody String[] ids){
		int i = bannerService.deleteBanners(ids);
		return i;

	}
	/**
	 * @author xjj
	 * @Description: 删除banner信息
	 * @date 2018/12/27
	 */
	@DeleteMapping("/banners/{id}")
	public int deleteBanner (@PathVariable String id){
		return this.bannerService.deleteBanner(id);
	}
}