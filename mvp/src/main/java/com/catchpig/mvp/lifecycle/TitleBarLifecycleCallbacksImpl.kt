package com.catchpig.mvp.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.controller.TitleBarController
import com.catchpig.mvp.ext.getColorPrimary

/**
 * 创建时间:2019/8/19 0019<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/19 0019<br/>
 * 描述:
 */
class TitleBarLifecycleCallbacksImpl:Application.ActivityLifecycleCallbacks {
    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityDestroyed(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        if(p0 is BaseActivity){
            var titleBarController = TitleBarController(p0)
        }
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }
}