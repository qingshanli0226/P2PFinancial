package com.example.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {


    public static String MD5(String passStr){
        String result="";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(passStr.getBytes());
            byte[] digest = md5.digest();
            int i;
            StringBuffer sb = new StringBuffer();
            for (int q=0;q<digest.length;q++){
                i=digest[q];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    sb.append("0");
                    //添加成16进制
                    sb.append(Integer.toHexString(i));
                }
            }
            result=sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return result;
    }
}
