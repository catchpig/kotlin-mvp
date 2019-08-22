package com.catchpig.kotlin_mvp.app

import com.catchpig.kotlin_mvp.di.component.AppComponent
import com.catchpig.kotlin_mvp.di.component.DaggerAppComponent
import com.catchpig.mvp.app.BaseApplication
import com.catchpig.mvp.di.module.AppModule

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
class KotlinMvpApp:BaseApplication() {
    companion object {
        private var mAppComponent: AppComponent? = null
        fun getAppComponent(): AppComponent {
            if (mAppComponent == null) {
                mAppComponent =
                    DaggerAppComponent.builder().appModule(AppModule(application)).build()
            }
            return mAppComponent!!
        }
    }
}