package com.javacv.video.service;

import com.javacv.video.vo.ConfigFile;
import com.javacv.video.vo.Video;

import java.util.List;

public interface IVideoService {
    /**
     * 打开摄像头
     * @return
     */
    Boolean openCamera();

    /**
     * 开始录制
     */
    void startRecord(String fileName);

    /**
     * 结束录制
     */
    void stopRecord();

    /**
     * 从Nginx获取视频列表
     */
    List<Video> getVideoList();

    /**
     * 从远程服务获取单个视频
     */
    Video getVideoByCode(String name);

    /**
     * 读取配置文件内容
     */
    ConfigFile readConfig();

    /**
     * 更新配置文件中的内容
     */
    void updateConfig(ConfigFile configFile);
}
