package com.javacv.video.vo;

/**
 * @ClassName Video
 * @Description TODO
 * @Author 86133
 * @Date 2020/6/2 8:35
 * @Version 1.0
 **/
public class Video {
    /**
     * 序号
     */
    private Integer id;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件大小
     */
    private Long length;

    /**
     * 文件创建时间
     */
    private String createTime;

    /**
     * 远程文件rtmp地址
     * @return
     */
    private String videoUrl;

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", createTime='" + createTime + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
