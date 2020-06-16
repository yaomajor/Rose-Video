package com.javacv.video;

import com.javacv.video.utils.PropertyUtil;
import com.javacv.video.vo.ConfigFile;

/**
 * @ClassName PropertyUtilTest
 * @Description TODO
 * @Author 86133
 * @Date 2020/6/15 10:22
 * @Version 1.0
 **/
public class PropertyUtilTest {
    public static void main(String[] args) {
        ConfigFile cf = new ConfigFile();
        cf.setOvertime(PropertyUtil.getProperty("overtime"));
        cf.setServerAddress(PropertyUtil.getProperty("server_address"));
        cf.setServerFilePath(PropertyUtil.getProperty("server_filePath"));
        cf.setSharedFolder(PropertyUtil.getProperty("sharedFolder"));
        cf.setUserAccount(PropertyUtil.getProperty("USER_ACCOUNT"));
        cf.setUserPassword(PropertyUtil.getProperty("USER_PWS"));
        System.out.println(cf);
    }
}
