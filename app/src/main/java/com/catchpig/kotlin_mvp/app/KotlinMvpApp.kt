package com.catchpig.kotlin_mvp.app

import android.app.Application
import com.catchpig.kotlin_mvp.R
import com.catchpig.kotlin_mvp.di.networkModule
import com.catchpig.kotlin_mvp.di.mainModule
import com.catchpig.mvp.di.appModule
import com.catchpig.mvp.di.downloadModule
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author catchpig
 * @date 2019/8/18 00:18
 */
class KotlinMvpApp:Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.DEBUG)
            androidLogger(Level.DEBUG)
            androidContext(this@KotlinMvpApp)
            modules(appModule, downloadModule, networkModule, mainModule)
        }
    }
    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout -> //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.c_333)
            MaterialHeader(context)
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter(context).setDrawableSize(20f)
        }
    }
}