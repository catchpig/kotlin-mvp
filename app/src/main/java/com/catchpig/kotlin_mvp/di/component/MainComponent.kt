package com.catchpig.kotlin_mvp.di.component

import com.catchpig.kotlin_mvp.di.module.MainModule
import com.catchpig.kotlin_mvp.mvp.main.view.MainActivity
import com.catchpig.mvp.di.scope.ActivityScope
import dagger.Subcomponent

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}