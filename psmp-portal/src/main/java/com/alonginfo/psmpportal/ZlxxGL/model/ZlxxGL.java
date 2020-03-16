package com.alonginfo.psmpportal.ZlxxGL.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @author xjj
 * @description 资料信息实体类
 * @data 2018/12/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZlxxGL {
    /**
     * id
     */

    private String id;

    /**
     * 分类
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

    /**
     * 图片规格
     */
    private String tpgs;

    /**
     * 图片大小
     */
    private String tpdx;

    /**
     * 状态  0  未发布（显示发布按钮）  1  已发布
     */

    private String state;

    /**
     * 附件
     */

    private String fjurl;

    /**
     * 来源
     */
    private String source;
    /**
     * 上/下线
     */

    private String line;

    /**
     * 创建时间
     */

    private String time;

    /**
     * 内容
     */

    private String content;
    private String lineStr;
    private String zflmc;

}