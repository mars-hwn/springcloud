package com.alonginfo.psmpcore.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5的加密类
 **/
public class MD5Util {
    public static String encrypt(String source) {
        return encodeMd5(source.getBytes());
    }

    /**
     * salt 尾部添加字符实现 加密
     * */
    public static String encrypt(String source,String salt){
        StringBuffer sb = new StringBuffer();
        sb.append(source).append(salt);
        return encodeMd5(sb.toString().getBytes());
    }

    private static String encodeMd5(byte[] source) {
        try {
            return encodeHex(MessageDigest.getInstance("MD5").digest(source));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    private static String encodeHex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10)
                buffer.append("0");
            buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        //System.out.println(encrypt("123456","42517362"));
        System.out.println(System.currentTimeMillis());
    }
}
