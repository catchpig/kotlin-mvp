package com.catchpig.kotlin_mvp.mvp.main.view

import android.view.View
import com.catchpig.annotation.ClickGap
import com.catchpig.annotation.MethodLog
import com.catchpig.annotation.StatusBar
import com.catchpig.annotation.enums.LEVEL
import com.catchpig.kotlin_mvp.R
import com.catchpig.kotlin_mvp.mvp.child.ChildActivity
import com.catchpig.kotlin_mvp.mvp.fullscreen.FullScreenActivity
import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.recycle.RecycleActivity
import com.catchpig.kotlin_mvp.mvp.transparent.TransparentActivity
import com.catchpig.mvp.base.activity.BasePresenterActivity
import com.catchpig.utils.ext.startKtActivity
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.inject

@StatusBar(enabled = true)
class MainActivity : BasePresenterActivity<MainContract.Presenter>(),MainContract.View {
    override val mPresenter: MainContract.Presenter by inject{ parametersOf(this) }

    override fun initParam() {
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
