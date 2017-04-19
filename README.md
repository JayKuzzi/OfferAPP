# Android个人学习、开发代码。以上为博客中有关Demo
# OfferApp为个人应聘使用。应用在上层库中，本库为博客Demo
# 就读大三，致力进军IT行业，并一直为此付诸行动。
# 近期会更新技术文档到自己的博客当中。
# 博客：http://www.jianshu.com/users/19513cee1eb8

# 面试APP演示截图

![image](https://github.com/JayKuzzi/Android/blob/master/OfferAPP/app/appshot/主页.png)
![image](https://github.com/JayKuzzi/Android/blob/master/OfferAPP/app/appshot/侧滑.png)
![image](https://github.com/JayKuzzi/Android/blob/master/OfferAPP/app/appshot/功能.png)
![image](https://github.com/JayKuzzi/Android/blob/master/OfferAPP/app/appshot/项目.png)
![image](https://github.com/JayKuzzi/Android/blob/master/OfferAPP/app/appshot/音乐.png)
![image](https://github.com/JayKuzzi/Android/blob/master/OfferAPP/app/appshot/关于.png)


dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:24.2.1'
    compile 'com.android.support:design:24.2.1'


    compile 'com.android.support:support-v4:24.2.1'
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:cardview-v7:24.1.1'
    compile 'jp.wasabeef:recyclerview-animators:2.2.5'
    compile files('libs/thinkpage_sdk_v_1_0_1.jar')
    compile 'me.grantland:autofittextview:0.2.+'
    compile 'com.nineoldandroids:library:2.4.0'


}
