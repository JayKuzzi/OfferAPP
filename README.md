# Android个人学习、开发代码。OfferApp为个人应聘使用。
# 就读大三，致力进军IT行业，并一直为此付诸行动。
# 近期更新技术文档到博客。
http://www.jianshu.com/users/19513cee1eb8
# APP演示截图
![image](https://github.com/JayKuzzi/OfferAPP/blob/master/app/appshot/主页.png)
![image](https://github.com/JayKuzzi/OfferAPP/blob/master/app/appshot/侧滑.png)
![image](https://github.com/JayKuzzi/OfferAPP/blob/master/app/appshot/功能.png)
![image](https://github.com/JayKuzzi/OfferAPP/blob/master/app/appshot/项目.png)
![image](https://github.com/JayKuzzi/OfferAPP/blob/master/app/appshot/音乐.png)
![image](https://github.com/JayKuzzi/OfferAPP/blob/master/app/appshot/关于.png)

WX：ZAWZZ123

一、登陆数据库 强制下线demo  想把这两个demo合起来使用
**问题1:**保存用户名：每次都得输入登录很麻烦 解决：用SharedPreferences来存储数据
**问题2:**强制下线后点击确定 页面关闭 ，不符合逻辑啊，应该是返回登录界面  解决：采用了自定义的类的管理类
**问题3:**思考广播接收在什么时候进行注册，因为没必要每个Activity都需要注册，所以采用动态注册，关键动态在哪里注册，解决在前台的onResume的注册，在后台的时候注销涉及到内存泄漏，不在onDestroy注销是因为，activity不一定被关闭了。
**问题4:**防止内存泄漏 在查询完毕后关闭数据库，但做其他操作报错 解决：在onDestroy的时候关闭数据库db.close(); 
二、自定义View、网络图片请求 xml请求
**问题5:**自定义view的时候 代码乱  解决：进行了类的封装。
**问题6:**进行网络图片的加载，网络json数据解析的时候都遇到了代码乱的问题 ，请求三张图片 每次都写一遍代码很乱， 封装HttpTool 方便修改复用请求的线程中直接更新ui进行了报错， 解决：运用了Handler 。
三、pad模式设计 结合碎片实现新闻综合demo
**问题7:**使用限定符sw600dp 是平板模式，如何在手机屏幕旋转时实现平板模式，解决：屏幕旋转时，activity重建 使用限定符号land 加载横屏布局
**问题8:**如何实现逻辑的问题，解决全局变量，点击tittle后如果能找到news_content_layout，就是平板模式，将变量设为true,在NewsAdapter中onCreateViewHolder做点击事件，如果是平板通过findFragmentById找到NewsContentFragment调用其中的方法刷新新闻，如果是手机模式直接打开新的activity 【这个demo也用来fragment和活动生命周期】
**问题9:**运行时权限：在换首页壁纸的时候需要读取内存卡外存权限直接在配置文件中注册报错了，解决：在安卓6.0后，这些危险权限需要你自己去检测并处理，要提示用户是否赋于权限做法1.在需要权限的地方检测，如果没有权限，则申请，如果有则直接运行 2、通过重写onRequestPermissionsResult匹配请求权限码，匹配上检测用户是否授权 授权了则执行 否则不执行