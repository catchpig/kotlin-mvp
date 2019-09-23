package com.catchpig.mvp.base


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.catchpig.mvp.ext.io2main
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.ResourceObserver
import io.reactivex.subscribers.ResourceSubscriber
import luyao.util.ktx.ext.logd
import javax.inject.Inject


/**
 * 创建时间:2019/4/6 10:35<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 10:35<br></br>
 * 描述:
 */
open class BasePresenter: BaseContract.Presenter {
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

    override fun onCreate() {
        ON_CREATE.logd(TAG)
    }

    override fun onStart() {
        ON_START.logd(TAG)
    }

    override fun onResume() {
        ON_RESUME.logd(TAG)
    }

    override fun onPause() {
        ON_PAUSE.logd(TAG)
    }

    override fun onStop() {
        ON_STOP.logd(TAG)
    }

    override fun onDestroy() {
        ON_DESTROY.logd(TAG)
        mCompositeDisposable.clear()
    }

    override fun <T> execute(
            flowable: Flowable<T>,
            callback: ResourceSubscriber<T>,io2main:Boolean):Disposable {
        val disposable = if (io2main) {
            flowable.io2main().subscribeWith(callback)
        }else{
            flowable.subscribeWith(callback)
        }
        mCompositeDisposable.add(disposable)
        return disposable
    }

    override fun remove(disposable: Disposable) {
        mCompositeDisposable.remove(disposable)
    }
}
