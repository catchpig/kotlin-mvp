package com.catchpig.mvp.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.catchpig.mvp.manager.ActivityManager

class ActivityManagerLifeCycleCallbacksImpl: Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        ActivityManager.removeActivity(activity)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        ActivityManager.addActivity(activity)
    }

    override fun onActivityResumed(p0: Activity) {
    }
}