package com.catchpig.kotlin_mvp.mvp.main.presenter

import com.catchpig.annotation.TimeLog
import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.network.Callback
import com.catchpig.kotlin_mvp.network.Result
import com.catchpig.mvp.base.BasePresenter
import com.catchpig.mvp.bean.DownloadInfo
import com.catchpig.mvp.network.listener.DownloadCallback

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
        val downloadInfo = DownloadInfo("https://ss1.bdstatic.com/","70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2583035764,1571388243&fm=26&gp=0.jpg")
        model.download(downloadInfo,object : DownloadCallback {
            override fun onStart() {

            }

            override fun onSuccess(path: String) {
                println(path)
            }

            override fun onComplete() {
            }

            override fun onProgress(readLength: Long, countLength: Long) {
            }

            override fun onError(t: Throwable) {
                println(t.message)
            }

        })
    }
}