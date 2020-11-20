package com.catchpig.kotlin_mvp.di

import com.catchpig.kotlin_mvp.mvp.apk.InstallApkContract
import com.catchpig.kotlin_mvp.mvp.apk.model.InstallApkModel
import com.catchpig.kotlin_mvp.mvp.apk.presenter.InstallApkPresenter
import com.catchpig.kotlin_mvp.mvp.apk.view.InstallApkActivity
import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.main.model.MainModel
import com.catchpig.kotlin_mvp.mvp.main.presenter.MainPresenter
import com.catchpig.kotlin_mvp.mvp.main.view.MainActivity
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @author catchpig
 * @date 2019/8/18 00:18
 */
val scopeModule = module {
    scope<MainActivity> {
        scoped { (view:MainContract.View)->
            MainPresenter(view,get())
        } bind MainContract.Presenter::class

        scoped {
            MainModel(get())
        } bind MainContract.Model::class
    }

    scope<InstallApkActivity> {
        scoped { (view:InstallApkContract.View)->
            InstallApkPresenter(view, get())
        } bind InstallApkContract.Presenter::class

        scoped {
            InstallApkModel(get())
        } bind InstallApkContract.Model::class
    }
}