package com.catchpig.kotlin_mvp.di.component

import com.catchpig.kotlin_mvp.di.module.MainModule
import com.catchpig.mvp.di.module.AppModule
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun mianComponent(mainModule: MainModule):MainComponent
}