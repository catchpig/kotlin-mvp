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
    
    |属性|类型|必须|默认|说明|
    |---|:---:|:---|:---|:---|
    |title_bar_back_icon|DrawableRes|是|无|标题栏的返回图标|
    |title_bar_background|ColorRes|是|无|标题栏的背景色|
    |title_bar_text_color|ColorRes|是|无|标题栏的文字颜色|
    |title_bar_show_line|boolean|否|false|标题栏的下方的线条是否显示|
    |loading_view_color|ColorRes|是|无|loading动画颜色|
    |loading_view_background|ColorRes|是|无|loading动画背景色|

    使用示例:
    ```
    <style name="AppThemeBarStyle" parent="Theme.AppCompat.Light.NoActionBar">
        <!--全局标题栏和状态栏配置-->
        <item name="title_bar_background">@color/colorPrimary</item>
        <item name="title_bar_back_icon">@drawable/back</item>
        <item name="title_bar_text_color">@color/white</item>
        <item name="title_bar_show_line">false</item>
        <item name="loading_view_color">@color/colorAccent</item>
        <item name="loading_view_background">@color/white</item>
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
    * 在app的build.gradle的android下添加如下代码:
    ```
    //启用实验性功能
    androidExtensions {
        experimental = true
    }
    ```
    * 只需要实现以下两个方法
    ```
    class UserAdapter(iPageControl: IPageControl):RecyclerAdapter<User>(iPageControl) {
        override fun layoutId(): Int {
            return R.layout.item_user
        }
    
        override fun bindViewHolder(holder: CommonViewHolder, m: User, position: Int) {
            //使用的experimental之后,可以直接holder.控件ID,不需要holder.itemView.控件ID
            holder.name.text = m.name
        }
    }
    ```
6. 注解使用
    * Title(修饰在Activity的类上)-标题
    
    |属性|类型|必须|默认|说明|
    |---|:---:|:---|:---|:---|
    |value|StringRes|是|无|标题内容|
    |backgroundColor|ColorRes|否|全局标题背景色|标题背景色|
    |textColor|ColorRes|否|全局标题文字颜色|标题文字颜色|
    |backIcon|DrawableRes|否|全局标题返回按钮图标|标题返回按钮图标|

    * TitleMenu(当有Title注解的时候才能使用此注解,也是修饰在Activity上)-标题右边按钮设置
    
    |属性|类型|必须|默认|说明|
    |---|:---:|:---|:---|:---|
    |rightFirstDrawable|DrawableRes|否|无|右边第一个图标按钮|
    |rightFirstText|StringRes|否|无|右边第一个文字按钮|
    |rightSecondDrawable|DrawableRes|否|无|右边第二个图标按钮|
    |rightSecondText|StringRes|否|无|右边第二个文字按钮|
    
    * StatusBar(修饰在Activity上)-状态栏
    
    |属性|类型|必须|默认|说明|
    |---|:---:|:---|:---|:---|
    |hide|boolean|否|false|隐藏状态栏|
    |enabled|boolean|否|false|状态栏是否可用|
    |transparent|boolean|否|false|状态栏透明|
    