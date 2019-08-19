package com.catchpig.kotlin_mvp.app

import android.app.Application
import android.content.Context
import com.catchpig.kotlin_mvp.di.component.AppComponent
import com.catchpig.kotlin_mvp.di.component.DaggerAppComponent
import com.catchpig.mvp.di.module.AppModule
import com.catchpig.mvp.lifecycle.TitleBarLifecycleCallbacksImpl

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
class KotlinMvpApp:Application() {
    companion object {
        lateinit var application: Application
        fun getContext(): Context {
            return application
        }

        private var mAppComponent: AppComponent? = null
        fun getAppComponent(): AppComponent {
            if (mAppComponent == null) {
                mAppComponent =
                    DaggerAppComponent.builder().appModule(AppModule(application)).build()
            }
            return mAppComponent!!
        }
    }
    override fun onCreate() {
        super.onCreate()
        application = this
        registerActivityLifecycleCallbacks(TitleBarLifecycleCallbacksImpl())
    }
}