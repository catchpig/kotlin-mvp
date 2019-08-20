package com.catchpig.kotlin_mvp.mvp.main.view

import android.content.Intent
import android.view.View
import com.catchpig.kotlin_mvp.R
import com.catchpig.kotlin_mvp.app.KotlinMvpApp
import com.catchpig.kotlin_mvp.di.module.MainModule
import com.catchpig.kotlin_mvp.mvp.child.ChildActivity
import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.main.presenter.MainPresenter
import com.catchpig.mvp.annotation.StatusBar
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.base.activity.BasePresenterActivity
import luyao.util.ktx.ext.startKtxActivity
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
    fun openChild(v:View){
        var intent = Intent(this,ChildActivity::class.java)
        startActivity(intent)
    }
}
