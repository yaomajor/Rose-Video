# Rose-Video
通过JavaCV+SpringBoot实现的视频录制以及推拉流点播插件

## video视频插件
#### 开发背景：
有客户进行仓储服务切换，了解到该大客户的库内包装流程需要进行视频录制，遂进行相关资料查询，进行开发。

设计细节：

1. 实现客户端插件
2. 在WMS系统中添加功能调用插件实现接口
3. 搭建文件服务器，实现客户端的录制推流和查看时的拉流播放

#### 技术内容：
1. javacv：通过对OPEN-CV/FFMEGE等视频处理开源库进行跨平台封装实现摄像头视频处理等功能
2. springboot:作为web后端框架进行达成jar，当作插件运行
3. Nginx:使用nginx作为流媒体服务器进行视频录制和视频播放的推拉流。PS：搭建服务器时添加rtmp协议的实现
4. thymeleaf：插件页面模板展示

接口列表：
1. [打开摄像头] http://localhost:8090/video/open
2. [开始录制] http://localhost:8090/video/start?fileName=123
3. [关闭录制] http://localhost:8090/video/stop
4. [查询远程视频列表] http://localhost:8090/video/getVideoList
5. [配置信息页面] http://localhost:8090/video/readConfig

