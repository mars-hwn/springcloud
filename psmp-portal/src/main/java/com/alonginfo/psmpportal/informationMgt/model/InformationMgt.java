package com.alonginfo.psmpportal.informationMgt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationMgt {
	private String zxid;
	private String zflid;       //分类编号
	private String num;         //排序
	private String title;       //标题
	private String plurl;       //图片
	private String tpgs;        //图片格式
	private String tpdx;        //图片大小
	private String content;     //内容
	private String source;      //来源
	private String inds;        //首页显示   0：显示  1：不显示
	private String indsStr;     //首页显示   0：显示  1：不显示
	private String state;       //状态  0：未发布  1：已发布
	private String stateStr;    //状态  0：未发布  1：已发布
	private String time;        //创建时间
	private String zflmc;

}
