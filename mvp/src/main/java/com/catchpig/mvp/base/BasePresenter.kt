package com.catchpig.mvp.base


import androidx.annotation.CallSuper
import com.catchpig.mvp.ext.io2main
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber


/**
 * 创建时间:2019/4/6 10:35<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 10:35<br></br>
 * 描述:
 */
open class BasePresenter: BaseContract.Presenter {
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    @CallSuper
    override fun onCreate() {
    }

    @CallSuper
    override fun onStart() {
    }

    @CallSuper
    override fun onResume() {
    }

    @CallSuper
    override fun onPause() {
    }

    @CallSuper
    override fun onStop() {
    }

    @CallSuper
    override fun onDestroy() {
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
