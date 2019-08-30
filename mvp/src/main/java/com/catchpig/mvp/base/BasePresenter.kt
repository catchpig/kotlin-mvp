package com.catchpig.mvp.base


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.ResourceObserver
import io.reactivex.subscribers.ResourceSubscriber
import luyao.util.ktx.ext.logd


/**
 * 创建时间:2019/4/6 10:35<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 10:35<br></br>
 * 描述:
 */
open class BasePresenter<V : BaseContract.View>(protected var mView: V) : BaseContract.Presenter, LifecycleObserver {
    companion object {
        const val TAG = "BasePresenter"
        const val ON_CREATE = "onCreate"
        const val ON_START = "onStart"
        const val ON_RESUME = "onResume"
        const val ON_PAUSE = "onPause"
        const val ON_STOP = "onStop"
        const val ON_DESTROY = "onDestroy"
    }

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        ON_CREATE.logd(TAG)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        ON_START.logd(TAG)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        ON_RESUME.logd(TAG)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        ON_PAUSE.logd(TAG)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
        ON_STOP.logd(TAG)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        ON_DESTROY.logd(TAG)
        mCompositeDisposable.clear()
    }

    override fun <T> execute(
            flowable: Flowable<T>,
            callback: ResourceSubscriber<T>):Disposable {
        var disposable = flowable.subscribeWith(callback)
        mCompositeDisposable.add(disposable)
        return disposable
    }

    override fun remove(disposable: Disposable) {
        mCompositeDisposable.remove(disposable)
    }
}
