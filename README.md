# kotlin-mvp
[![](https://jitpack.io/v/catch-pig/kotlin-mvp.svg)](https://jitpack.io/#catch-pig/kotlin-mvp)

###### 最低兼容:19
## Gradle
在Project的build.gradle中添加:
   ```
   allprojects {
    	repositories {
    		maven { url 'https://jitpack.io' }
    	}
    }
   ```
在module的build.gradle的添加"
```
apply plugin: 'kotlin-kapt' // 使用 kapt 注解处理工具
```
添加依赖:
```
implementation 'com.github.catch-pig:kotlin-mvp:last_version'
kapt "com.google.dagger:dagger-compiler:2.23.2"
kapt "com.google.dagger:dagger-android-processor:2.23.2"
```
## 使用

1. Application
     * 继承BaseApplication
     * 如果不继承BaseApplication,请在onCreate中添加如下代码:
        ```
        registerActivityLifecycleCallbacks(BarLifecycleCallbacksImpl())
        ```
2. 在需要使用状态栏、标题栏、加载动画的主题中配置全局参数:
    
    |属性|类型|默认|说明|
    |---|:---:|:---|:---|
    |title_bar_back_icon|DrawableRes|无|标题栏的返回图标|
    |title_bar_background|ColorRes|无|标题栏的背景色|
    |title_bar_text_color|ColorRes|无|标题栏的文字颜色|
    |title_bar_show_line|boolean|false|标题栏的下方的线条是否显示|
    |loading_view_color|ColorRes|无|loading动画颜色|
    |loading_view_background|ColorRes|无|loading动画背景色|

    使用示例:
    ```
    <style name="AppThemeBarStyle" parent="Theme.AppCompat.Light.NoActionBar">
        <!--全局标题栏和状态栏配置-->
        <item name="title_bar_background">@color/colorPrimary</item>
        <item name="title_bar_back_icon">@drawable/back</item>
        <item name="title_bar_text_color">@color/white</item>
        <item name="title_bar_show_line">false</item>
        <!--全局标题栏和状态栏配置-->
    </style>
    ```
3. Activity
    * 使用MVP的继承BasePresenterActivity
    * 不使用MVP的继承BaseActivity
4. Fragment
    * 使用MVP的继承BasePresenterFragment
    * 不使用MVP的继承BaseFragment
5. 如果使用RecycleView的时候,Adapter可以继承RecycleAdapter来使用
    在app的build.gradle的android下添加如下代码:
    ```
    //启用实验性功能
    androidExtensions {
        experimental = true
    }
    ```
