package com.alonginfo.psmpportal.menuGL.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xjj
 * @description 列表实体类
 * @data 2019/1/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer value;
    private String label;
    private List<Menu> children;

}
