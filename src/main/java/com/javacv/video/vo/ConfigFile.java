package com.javacv.video.vo;

import java.io.Serializable;

/**
 * @ClassName ConfigFile
 * @Description TODO
 * @Author 86133
 * @Date 2020/6/15 9:26
 * @Version 1.0
 **/
public class ConfigFile implements Serializable {

    private String serverAddress;//文件服务器IP（//10.224.230.43）

    private String serverFilePath;//文件地质路径（E:/Nginx_win/record）

    private String sharedFolder;//共享文件夹名称（/record/）

    private String userDomain;//域名（）

    private String userAccount;//共享电脑账号（491341）

    private String userPassword;//共享电脑密码（wyj09180013!@）

    private String overtime;//录制视频的超长时长（10）


    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServerFilePath() {
        return serverFilePath;
    }

    public void setServerFilePath(String serverFilePath) {
        this.serverFilePath = serverFilePath;
    }

    public String getSharedFolder() {
        return sharedFolder;
    }

    public void setSharedFolder(String sharedFolder) {
        this.sharedFolder = sharedFolder;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return "ConfigFile{" +
                "serverAddress='" + serverAddress + '\'' +
                ", serverFilePath='" + serverFilePath + '\'' +
                ", sharedFolder='" + sharedFolder + '\'' +
                ", userDomain='" + userDomain + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", overtime='" + overtime + '\'' +
                '}';
    }
}
