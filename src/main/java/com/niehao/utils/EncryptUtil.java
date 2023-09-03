package com.niehao.utils;

import cn.hutool.crypto.digest.DigestUtil;

public class EncryptUtil {

    public static String md5(String word) {
        return DigestUtil.md5Hex(word);
    }

}
