package com.catchpig.mvp.app

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import com.catchpig.mvp.lifecycle.BarLifecycleCallbacksImpl

/**
 * 创建时间:2019/8/22 0022<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/22 0022<br/>
 * 描述:
 */
open class BaseApplication:Application() {
    companion object {
        lateinit var application: Application
        fun getContext(): Context {
            return application
        }
    }
    @CallSuper
    override fun onCreate() {
        super.onCreate()
        application = this
        registerActivityLifecycleCallbacks(BarLifecycleCallbacksImpl())
//        registerActivityLifecycleCallbacks(KtxLifeCycleCallBack())
    }
}