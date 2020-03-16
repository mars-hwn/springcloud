package com.alonginfo.psmpportal.caseGL.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @author xjj
 * @description 案例信息实体类
 * @data 2018/12/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseGL {
    /**
     * 案例信息
     */

    private String caid;

    /**
     * 分类编号
     */

    private String zflid;

    /**
     * 排序
     */

    private String num;

    /**
     * 标题
     */

    private String title;

    /**
     * 图片
     */

    private String plurl;


    private String tpgs;


    private String tpdx;

    /**
     * 来源
     */
    private String source;

    /**
     * 首页显示   0  显示  1 不显示
     */

    private String hp;
    /**
     * 首页显示   0  显示  1 不显示
     */

    private String hpStr;

    /**
     * 状态  0  未发布（显示发布按钮）  1  已发布
     */

    private String state;

    /**
     * 创建时间
     */

    private String time;

    /**
     * 内容
     */

    private String content;
    private String zflmc;
}