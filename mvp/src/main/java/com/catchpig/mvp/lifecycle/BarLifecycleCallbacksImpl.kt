package com.catchpig.mvp.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.catchpig.annotation.StatusBar
import com.catchpig.annotation.Title
import com.catchpig.mvp.apt.BarUtils
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.controller.StatusBarController
import com.catchpig.mvp.ext.annotation

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
            BarUtils.inject(activity)
            //获取Title注解
//            val title = activity.annotation<Title>()
            //获取TitleMenu注解
//            val titleMenu = activity.annotation<TitleMenu>()
            //获取StatusBar注解
//            val statusBar = activity.annotation<StatusBar>()

            //初始化控制器
//            val statusBarController = StatusBarController(activity,title,statusBar)
//            if (title != null) {
//                val titleBarController = TitleBarController(activity,title,titleMenu)
//                activity.title_bar_view_stub.setOnInflateListener { _, _ ->
//                    titleBarController.initTitleBar()
//                }
//                activity.title_bar_view_stub.inflate()
//            }
//            statusBarController.checkStatusBar()
        }
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }
}