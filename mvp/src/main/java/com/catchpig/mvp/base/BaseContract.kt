package com.catchpig.mvp.base

import android.app.Activity
import android.content.Context
import com.catchpig.mvp.base.activity.BaseActivity
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber

/**
 * 创建时间:2019/4/6 10:33<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 10:33<br></br>
 * 描述:
 */
interface BaseContract {
    interface View {
        fun applicationContext():Context
        fun activity():Activity
        fun baseActivity(): BaseActivity?
        /**
         * 出现loading动画
         * @param isDialog 是否是dialog形式的
         */
        fun loadingView(isDialog:Boolean)

        /**
         * 隐藏loading动画
         */
        fun hideLoadingView()

        /**
         * 关闭activity
         */
        fun closeActivity()
    }

    interface Presenter {
        fun onCreate()

        fun onStart()

        fun onResume()

        fun onPause()

        fun onStop()

        fun onDestroy()

        /**
         * 处理请求接口(线程安全,防止内存泄露)
         * @param
         */
        fun <T> execute(flowable: Flowable<T>, callback: ResourceSubscriber<T>):Disposable

        /**
         * 删除指定的Disposable
         */
        fun remove(disposable: Disposable)

    }
}
