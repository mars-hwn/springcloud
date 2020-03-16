package com.alonginfo.psmpportal.bannerGL.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xjj
 * @description banner信息实体类
 * @data 2018/12/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerGL {
        private String bnid; //id
        private String title;  //主题
        private String plurl;  //图片路径
        private String tpgs;   //图片格式
        private String tpdx;    //图片大小
        private String line;    // 上/下线
        private String lineStr;
        private String state;   //0  未发布（显示发布按钮）  1  已发布
        private String zflid;  //分类id
        private String num;    //排序
        private String time;      //创建时间
        private String zflmc;  //置换zflid
}
