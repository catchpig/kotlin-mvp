package com.catchpig.mvp.base

import android.app.Activity
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.catchpig.mvp.base.activity.BaseActivity
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber

/**
 * @author catchpig
 * @date 2019/4/6 10:33
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
        fun loadingView(isDialog:Boolean=true)

        /**
         * 隐藏loading动画
         */
        fun hideLoadingView()

        /**
         * 关闭activity
         */
        fun closeActivity()

        fun toast(text:String,isLong:Boolean= true)
    }

    interface Presenter:LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate()
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart()
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume()
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause()
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop()
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy()

        /**
         * 处理请求接口(线程安全,防止内存泄露)
         * @param callback
         * @param callback 回调函数
         * @param io2main 是否flowable在io线程中执行,callback在主线程中执行(默认为true)
         */
        fun <T> execute(flowable: Flowable<T>, callback: ResourceSubscriber<T>,io2main:Boolean = true):Disposable



        /**
         * 删除指定的Disposable
         */
        fun remove(disposable: Disposable)

    }
}
