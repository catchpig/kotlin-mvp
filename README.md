# kotlin-mvp
[![](https://jitpack.io/v/catch-pig/kotlin-mvp.svg)](https://jitpack.io/#catch-pig/kotlin-mvp)

## 最低兼容:19
## Gradle
### 1. 在Project的build.gradle中添加
```
allprojects {
     repositories {
 	    maven { url 'https://jitpack.io' }
     }
 }
```
    
### 2. [AspectJX](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)的使用请参考官方文档
    
```
dependencies {
    classpath 'com.github.franticn:gradle_plugin_android_aspectjx:2.0.6'
}
```
### 3. 在app的build.gradle的添加
```
apply plugin: 'kotlin-kapt' // 使用 kapt 注解处理工具

apply plugin: 'android-aspectjx'
```
### 4. 添加依赖
```
implementation "com.github.catch-pig.kotlin-mvp:mvp:last_version"
kapt "com.github.catch-pig.kotlin-mvp:compiler:last_version"
kapt "com.google.dagger:dagger-compiler:2.23.2"
kapt "com.google.dagger:dagger-android-processor:2.23.2"
```
## 使用

### 1. Application
   * 继承BaseApplication
   * 如果不继承BaseApplication,请在onCreate中添加如下代码:
   > ***registerActivityLifecycleCallbacks(ActivityManagerLifeCycleCallbacksImpl())***
### 2. 在需要使用状态栏、标题栏、加载动画的主题中配置全局参数:
    
   |属性|类型|必须|默认|说明|
   |---|:---:|:---|:---|:---|
   |title_bar_back_icon|DrawableRes|是|无|标题栏的返回图标|
   |title_bar_background|ColorRes|是|无|标题栏的背景色|
   |title_bar_text_color|ColorRes|是|无|标题栏的文字颜色|
   |title_bar_show_line|boolean|否|false|标题栏的下方的线条是否显示|
   |loading_view_color|ColorRes|是|无|loading动画颜色|
   |loading_view_background|ColorRes|是|无|loading动画背景色|
   |recycle_view_empty_layout|LayoutRes|否|[emptyLayout](./mvp/src/main/res/layout/view_load_empty.xml)|列表空页面|
    
 > 使用示例:
    
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
### 3. Activity
  * 使用MVP的继承BasePresenterActivity
  * 不使用MVP的继承BaseActivity
### 4. Fragment
  * 使用MVP的继承BasePresenterFragment
  * 不使用MVP的继承BaseFragment
### 5. 如果使用RecycleView的时候,Adapter可以继承RecycleAdapter来使用
  > 在app的build.gradle的android下添加如下代码:
   ```
   //启用实验性功能
   androidExtensions {
       experimental = true
   }
   ```
    
  > 只需要实现以下两个方法
  
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
### 6. 注解使用
#### 6.1 [Title](./annotation/src/main/java/com/catchpig/annotation/Title.kt)(修饰在Activity的类上)-标题
    
 |属性|类型|必须|默认|说明|
 |---|:---:|:---|:---|:---|
 |value|StringRes|是|无|标题内容|
 |backgroundColor|ColorRes|否|全局标题背景色|标题背景色|
 |textColor|ColorRes|否|全局标题文字颜色|标题文字颜色|
 |backIcon|DrawableRes|否|全局标题返回按钮图标|标题返回按钮图标|

#### 6.2 [OnClickFirstDrawable](./annotation/src/main/java/com/catchpig/annotation/OnClickFirstDrawable.kt)(修饰在方法上)-标题上第一个图标按钮的点击事件
    
     value:按钮的文字资源
    
#### 6.3 [OnClickFirstText](./annotation/src/main/java/com/catchpig/annotation/OnClickFirstText.kt)(修饰在方法上)-标题上第一个文字按钮的点击事件
    
     value:按钮的图标资源
    
#### 6.4 [OnClickSecondDrawable](./annotation/src/main/java/java/com/catchpig/annotation/OnClickSecondDrawable.kt)(修饰在方法上)-标题上第二个图标按钮的点击事件
    
     value:按钮的文字资源
    
#### 6.5 [OnClickSecondText](./annotation/src/main/java/com/catchpig/annotation/OnClickSecondText.kt)(修饰在方法上)-标题上第二个文字按钮的点击事件

     value:按钮的图标资源
    
#### 6.6 [StatusBar](./annotation/src/main/java/com/catchpig/annotation/StatusBar.kt)(修饰在Activity上)-状态栏
    
|属性|类型|必须|默认|说明|
|---|:---:|:---|:---|:---|
|hide|boolean|否|false|隐藏状态栏|
|enabled|boolean|否|false|状态栏是否可用|
|transparent|boolean|否|false|状态栏透明|
    
### 刷新分页
    
#### 使用RefreshLayoutWrapper+RecyclerAdapter控件实现刷新功能
        
  + ***RefreshLayoutWrapper继承于[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout),具体使用请看SmartRefreshLayout官方文档,默认每页数据量为16,如果想修改每页数据量,可使用如下方法更改:***
        
    ```
    RefreshLayoutWrapper.pageSize = 16
    ```
  + ***RefreshLayoutWrapper实现了[IPageControl](./mvp/src/main/java/com/catchpig/mvp/widget/refresh/IPageControl.kt),可以通过调用接口内的方法类获取刷新控件的状态和更改状态***
    
    ```
    //每页的数据量
    iPageControl.pageSize = 16
    //下一页的页码
    iPageControl.nextPageIndex = 1
    //获取刷新的状态
    iPageControl.getRefreshStatus()
    //重置当前页码为1
    iPageControl.resetPageIndex()
    //加载下一页码
    iPageControl.loadNextPageIndex()
    //更新数据成功
    iPageControl.updateSuccess(list)
    //更新数据失败
    iPageControl.updateError()
    ```
  + ***RecyclerAdapter在实例化的时候传入IPageControl,
        获取数据成功之后,只需要调用autoUpdateList(list)方法,
        可以自动RefreshLayoutWrapper页码和刷新状态变化***
        
   + ***数据更新失败可以调用RecyclerAdapter.updateFailed()***
        