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
import com.catchpig.kotlin_mvp.mvp.transparent.TransparentActivity
import com.catchpig.mvp.annotation.StatusBar
import com.catchpig.mvp.base.activity.BasePresenterActivity
@StatusBar(enabled = true)
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
        var intent = Intent()
        when (v.id) {
            R.id.is_title -> {
                intent.setClass(this,ChildActivity::class.java)
            }
            R.id.transparent -> {
                intent.setClass(this,TransparentActivity::class.java)
            }
            R.id.full_screen -> {
                intent.setClass(this,FullScreenActivity::class.java)
            }
            else -> {
            }
        }
        startActivity(intent)
    }
}
