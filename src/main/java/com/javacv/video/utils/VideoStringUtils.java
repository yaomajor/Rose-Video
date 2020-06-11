package com.javacv.video.utils;

import java.util.Random;

/**
 * @ClassName VideoStringUtils
 * @Description TODO
 * @Author 86133
 * @Date 2020/5/23 17:07
 * @Version 1.0
 **/
public class VideoStringUtils {
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
