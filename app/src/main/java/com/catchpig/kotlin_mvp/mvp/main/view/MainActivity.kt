package com.catchpig.kotlin_mvp.mvp.main.view

import android.content.Intent
import android.view.View
import com.catchpig.kotlin_mvp.R
import com.catchpig.kotlin_mvp.app.KotlinMvpApp
import com.catchpig.kotlin_mvp.di.module.MainModule
import com.catchpig.kotlin_mvp.mvp.child.ChildActivity
import com.catchpig.kotlin_mvp.mvp.fullscreen.FullScreenActivity
import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.main.presenter.MainPresenter
import com.catchpig.kotlin_mvp.mvp.recycle.RecycleActivity
import com.catchpig.kotlin_mvp.mvp.transparent.TransparentActivity
import com.catchpig.annotation.ClickGap
import com.catchpig.annotation.MethodLog
import com.catchpig.annotation.StatusBar
import com.catchpig.annotation.TimeLog
import com.catchpig.annotation.enums.LEVEL
import com.catchpig.mvp.base.activity.BasePresenterActivity
import com.catchpig.utils.ext.startKtActivity

@StatusBar(enabled = true)
class MainActivity : BasePresenterActivity<MainPresenter>(),MainContract.View {
    override fun initParam() {
    }
    
    @TimeLog(LEVEL.V)
    override fun injectComponent() {
        KotlinMvpApp.getAppComponent().mianComponent(MainModule(this)).inject(this)
    }

    @MethodLog(LEVEL.I)
    override fun initView() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    @MethodLog(LEVEL.V)
    @ClickGap(2000)
    fun openChild(v:View){
        when (v.id) {
            R.id.is_title -> {
                startKtActivity<ChildActivity>()
            }
            R.id.transparent -> {
                startKtActivity<TransparentActivity>()
            }
            R.id.full_screen -> {
                startKtActivity<FullScreenActivity>()
            }
            R.id.recycle -> {
                startKtActivity<RecycleActivity>()
            }
            else -> {
            }
        }
    }
}
