package com.niehao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接收分页条件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    private int current;// 页面
    private int size;
    private String sortField;
    private String sortOrder;

}
