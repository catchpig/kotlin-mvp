package com.catchpig.mvp.base

import com.catchpig.mvp.base.activity.BaseActivity
import io.reactivex.Flowable
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
        fun getBaseActivity(): BaseActivity?
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
         */
        fun <T> execute(flowable: Flowable<T>, callback: ResourceSubscriber<T>)

    }
}
