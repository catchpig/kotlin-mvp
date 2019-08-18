package com.catchpig.kotlin_mvp.mvp.main.view

import com.catchpig.kotlin_mvp.R
import com.catchpig.kotlin_mvp.app.KotlinMvpApp
import com.catchpig.kotlin_mvp.di.module.MainModule
import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.main.presenter.MainPresenter
import com.catchpig.mvp.base.activity.BasePresenterActivity

class MainActivity : BasePresenterActivity<MainPresenter>(),MainContract.View {
    override fun initParam() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun injectComponent() {
        KotlinMvpApp.getAppComponent().mianComponent(MainModule(this)).inject(this)
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

}
