package com.catchpig.kotlin_mvp.app

import android.content.Context
import com.catchpig.kotlin_mvp.R
import com.catchpig.kotlin_mvp.di.component.AppComponent
import com.catchpig.kotlin_mvp.di.component.DaggerAppComponent
import com.catchpig.mvp.app.BaseApplication
import com.catchpig.mvp.di.module.AppModule
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator

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
    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.c_333)
                return MaterialHeader(context)
            }
        })
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter {
                //指定为经典Footer，默认是 BallPulseFooter
                return ClassicsFooter(context).setDrawableSize(20f)
            }
        })
    }
}