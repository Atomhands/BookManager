package com.niehao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResult {

    private boolean flag; // 执行逻辑
    private String message; // 响应信息
    private Object data; // 响应数据
    private int code; // 状态码

    private HttpResult(String message, Object data) {
        this.flag = true;
        this.message = message;
        this.data = data;
        this.code = 2000;
    }

    public static HttpResult OK(String message, Object data) {
        return new HttpResult(message, data);
    }
}

