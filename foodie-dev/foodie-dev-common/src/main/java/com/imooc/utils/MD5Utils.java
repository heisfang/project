package com.imooc.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newStr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newStr;
    }

}
