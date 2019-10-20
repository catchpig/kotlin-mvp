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
import com.catchpig.annotation.StatusBar
import com.catchpig.annotation.TimeLog
import com.catchpig.aop.TimeLogAspectJ
import com.catchpig.mvp.base.activity.BasePresenterActivity

@StatusBar(enabled = true)
class MainActivity : BasePresenterActivity<MainPresenter>(),MainContract.View {
    override fun initParam() {
    }
    
    @TimeLog
    override fun injectComponent() {
        KotlinMvpApp.getAppComponent().mianComponent(MainModule(this)).inject(this)
    }


    override fun initView() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }
    @ClickGap(2000)
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
            R.id.recycle -> {
                intent.setClass(this,RecycleActivity::class.java)
            }
            else -> {
            }
        }
        startActivity(intent)
    }
}
