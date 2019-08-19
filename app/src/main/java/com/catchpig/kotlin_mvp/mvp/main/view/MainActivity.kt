package com.catchpig.kotlin_mvp.mvp.main.view

import com.catchpig.kotlin_mvp.R
import com.catchpig.kotlin_mvp.app.KotlinMvpApp
import com.catchpig.kotlin_mvp.di.module.MainModule
import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.main.presenter.MainPresenter
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.base.activity.BasePresenterActivity
@Title("主页")
class MainActivity : BasePresenterActivity<MainPresenter>(),MainContract.View {
    override fun initParam() {
    }

    override fun injectComponent() {
        KotlinMvpApp.getAppComponent().mianComponent(MainModule(this)).inject(this)
    }

    override fun initView() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

}
