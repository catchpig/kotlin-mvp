package com.catchpig.kotlin_mvp.di

import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.main.model.MainModel
import com.catchpig.kotlin_mvp.mvp.main.presenter.MainPresenter
import com.catchpig.kotlin_mvp.mvp.main.view.MainActivity
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @author catchpig
 * @date 2019/8/18 00:18
 */
val mainModule = module() {
    scope<MainActivity> {
        scoped { (view:MainContract.View)->
            MainPresenter(view,get())
        } bind MainContract.Presenter::class

        scoped {
            MainModel(get(),get())
        } bind MainContract.Model::class
    }
}