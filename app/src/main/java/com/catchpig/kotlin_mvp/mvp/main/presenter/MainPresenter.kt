package com.catchpig.kotlin_mvp.mvp.main.presenter

import com.catchpig.annotation.TimeLog
import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.network.Callback
import com.catchpig.kotlin_mvp.network.Result
import com.catchpig.mvp.base.BasePresenter

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
class MainPresenter @TimeLog constructor(private val view:MainContract.View,private val model:MainContract.Model): BasePresenter(),MainContract.Presenter {
    override fun onCreate() {
        super.onCreate()
        execute(model.banner(), object :Callback<Result<Any>>(){
            override fun onNext(result: Result<Any>?) {
                view.toast(result!!.errorCode.toString())
            }
        })
    }
}