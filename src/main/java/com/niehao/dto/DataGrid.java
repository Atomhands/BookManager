package com.niehao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 封装分页结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGrid {
    private List<?> data; // 数据页
    private long total; // 总条数
}
