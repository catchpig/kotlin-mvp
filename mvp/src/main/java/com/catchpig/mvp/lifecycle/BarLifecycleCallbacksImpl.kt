package com.catchpig.mvp.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.catchpig.mvp.annotation.StatusBar
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.annotation.TitleMenu
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.controller.StatusBarController
import com.catchpig.mvp.controller.TitleBarController
import com.catchpig.mvp.utils.AnnotationUtils

/**
 * 创建时间:2019/8/19 0019<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/19 0019<br/>
 * 描述:
 */
class BarLifecycleCallbacksImpl:Application.ActivityLifecycleCallbacks {
    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityDestroyed(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        if(activity is BaseActivity){
            //获取Title注解
            val title = AnnotationUtils.annotation(activity::class.java, Title::class.java)
            //获取TitleMenu注解
            val titleMenu = AnnotationUtils.annotation(activity::class.java, TitleMenu::class.java)
            //获取StatusBar注解
            val statusBar = AnnotationUtils.annotation(activity::class.java,StatusBar::class.java)

            //初始化控制器
            val titleBarController = TitleBarController(activity,title,titleMenu)
            val statusBarController = StatusBarController(activity,title,statusBar)
            titleBarController.initTitleBar()
            statusBarController.checkStatusBar()
        }
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }
}