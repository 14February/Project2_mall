package com.cskaoyan.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils{

    private static final String SALT = "666";

    public static String getMd5(String content) throws NoSuchAlgorithmException
    {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] contentBytes = content.getBytes();
        byte[] digest = md5.digest(contentBytes);

        StringBuffer stringBuffer = new StringBuffer();
        for(byte b : digest)
        {
            int i = b & 0xff;
            String str = Integer.toHexString(i);
            if(str.length() == 1) stringBuffer.append(0);
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static String getMd5WithSalt(String content) throws NoSuchAlgorithmException
    {
        content = content + "&" + SALT + "}";
        return getMd5(content);
    }
}
