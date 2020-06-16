package com.javacv.video.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @ClassName: PropertyUtil
 * @author weilong
 * @date 2019年5月14日
 */
public class PropertyUtil {
    private static Properties props;
    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        props = new Properties();
        InputStream inputStream=null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(new File("src/main/resources/video.properties")));
            props.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("properties文件未找到");
        } catch (IOException e) {
            System.out.println("出现IOException");
        } finally {
            try {
                if(null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("properties文件流关闭出现异常");
            }
        }
        System.out.println("加载properties文件内容完成...........");
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    public static void setProperty(String key,String value) {
        try {
            if(null == props) {
                loadProps();
            }
            //此处文件相对路径和项目目录同级，否则使用绝对路径
            FileOutputStream oFile = new FileOutputStream(new File("src/main/resources/video.properties"));
            props.setProperty(key, value);
            props.store(oFile, new Date().toString());
            oFile.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}