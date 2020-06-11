package com.javacv.video.utils;

import java.io.File;

/**
 * @ClassName ListFile
 * @Description TODO
 * @Author 86133
 * @Date 2020/5/28 15:55
 * @Version 1.0
 **/
public class ListFile {
    public static void listFile(File dir, String spance)
    {
        File[] files=dir.listFiles();   //列出所有的子文件
        for(File file :files)
        {
            if(file.isFile())//如果是文件，则输出文件名字
            {
                System.out.println(spance+file.getName());
            }else if(file.isDirectory())//如果是文件夹，则输出文件夹的名字，并递归遍历该文件夹
            {
                System.out.println(spance+file.getName());
                listFile(file,"|--"+spance);//递归遍历
            }
        }
    }
}
