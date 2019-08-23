# kotlin-mvp
[![](https://jitpack.io/v/catch-pig/kotlin-mvp.svg)](https://jitpack.io/#catch-pig/kotlin-mvp)

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

