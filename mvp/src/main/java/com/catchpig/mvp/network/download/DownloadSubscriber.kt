package com.catchpig.mvp.network.download

import com.catchpig.mvp.network.listener.DownloadCallback
import com.catchpig.mvp.network.listener.DownloadProgressListener
import com.catchpig.utils.ext.logd
import io.reactivex.rxjava3.subscribers.ResourceSubscriber
import java.lang.ref.WeakReference
/**
 * 下载观察者
  * @author catchpig
 * @date 2020/11/20 10:25
 */
class DownloadSubscriber(downloadCallback: DownloadCallback):ResourceSubscriber<String>(), DownloadProgressListener {
    private val mWeakReference:WeakReference<DownloadCallback> by lazy {
        WeakReference(downloadCallback)
    }
    companion object{
        const val TAG = "DownloadSubscriber"
    }

    override fun onStart() {
        super.onStart()
        mWeakReference.get()?.onStart()
    }
    override fun onNext(t: String) {
        mWeakReference.get()?.onSuccess(t)
    }

    override fun onError(t: Throwable?) {
        if (t != null) {
            mWeakReference.get()?.onError(t)
        }
    }

    override fun onComplete() {
        mWeakReference.get()?.onComplete()
    }

    override fun update(read: Long, count: Long, done: Boolean) {
        "progress:$read/$count;$done".logd(TAG)
        mWeakReference.get()?.onProgress(read,count)
    }
}