package com.bwei.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {


//MD5加密
    public static String MD5(String sourceStr){
        String result = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i=0;
            StringBuffer buffer =new StringBuffer();
            for (int f = 0; f < b.length; f++) {
                if (i<0){
                    i+=256;
                }
                if (i<16){
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            result=buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

}
