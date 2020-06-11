package com.javacv.video.utils;

import com.javacv.video.vo.Video;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SmbFileList
 * @Description TODO
 * @Author 86133
 * @Date 2020/5/29 9:01
 * @Version 1.0
 **/
@Component
@PropertySource("classpath:video.properties")
public class SmbFileList {
    private static String USER_DOMAIN;  //域账号,没有可以不填
    private static String USER_ACCOUNT;  //账号
    private static String USER_PWS;  //密码
    private static String serverAddress;
    private static String sharedFolder;

    @Value("${USER_DOMAIN}")
    public void setUSER_DOMAIN(String USER_DOMAIN) {
        this.USER_DOMAIN = USER_DOMAIN;
    }
    @Value("${USER_ACCOUNT}")
    public void setUSER_ACCOUNT(String USER_ACCOUNT) {
        this.USER_ACCOUNT = USER_ACCOUNT;
    }
    @Value("${USER_PWS}")
    public void setUSER_PWS(String USER_PWS) {
        this.USER_PWS = USER_PWS;
    }
    @Value("${server_address}")
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
    @Value("${sharedFolder}")
    public void setSharedFolder(String sharedFolder) {
        this.sharedFolder = sharedFolder;
    }

    public static List<Video> getFileList(){
        String url = "rmp:"+serverAddress+sharedFolder;
        // 域服务器验证
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(USER_DOMAIN, USER_ACCOUNT, USER_PWS);
        List<Video> list = new ArrayList<>();
        try{
            SmbFile remoteFile = new SmbFile(url,auth);
            if(remoteFile.exists()){//判断是否有这个文件夹
                SmbFile[] files = remoteFile.listFiles();
                Integer id = 1;
                for (SmbFile f : files) {
                    System.out.println(f.getName());
                    Video video = new Video();
                    video.setId(id);
                    video.setName(f.getName());
                    video.setLength(f.length());
                    video.setCreateTime(Long.toString(f.createTime()));
                    list.add(video);
                    id++;
                }
            }
            return list;
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    /**
     * 这里使用Nginx+rtmp配置实现远程服务器视频点播
     * @param name
     * @return
     */
    public static Video getFileByCode(String name){
        String url = "rmp:"+serverAddress+sharedFolder+name+".flv";
        // 域服务器验证
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(USER_DOMAIN, USER_ACCOUNT, USER_PWS);
        //远程视频文件名称拼接
        String videoUrl = "rtmp:"+serverAddress+"/vod/"+name+".flv";
        try{
            SmbFile remoteFile = new SmbFile(url,auth);
            if(remoteFile.exists()){//判断是否有这个文件夹
                Video video = new Video();
                video.setName(remoteFile.getName());
                video.setLength(remoteFile.length());
                video.setCreateTime(Long.toString(remoteFile.createTime()));
                video.setVideoUrl(videoUrl);
                return video;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
