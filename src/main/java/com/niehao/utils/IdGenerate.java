package com.niehao.utils;

import cn.hutool.core.lang.UUID;

public class IdGenerate {

    /**
     * 生产数据库主键
     *
     * @return
     */
    public static String uuid() {
        return UUID.fastUUID().toString().replace("-", "").toUpperCase();
    }

}
