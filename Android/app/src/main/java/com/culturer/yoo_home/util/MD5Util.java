package com.culturer.yoo_home.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class MD5Util {
    public final static String encrypt(String plaintext){
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9', 'a','b','c','d','e','f',};
        byte[] btInput = plaintext.getBytes();
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j*2];
            int k= 0 ;
            for (int i=0;i<j;i++){
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 &0xf ];
                str[k++] = hexDigits[ byte0 &0xf ];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
