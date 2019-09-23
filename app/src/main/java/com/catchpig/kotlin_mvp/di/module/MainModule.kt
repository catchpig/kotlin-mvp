package com.catchpig.kotlin_mvp.di.module

import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.main.presenter.MainPresenter
import com.catchpig.mvp.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
@Module
class MainModule(private val view: MainContract.View) {
    @ActivityScope
    @Provides
    fun providesMainPresenter():MainPresenter{
        return MainPresenter(view)
    }
}