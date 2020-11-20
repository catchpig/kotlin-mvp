package com.catchpig.kotlin_mvp.mvp.main

import com.catchpig.kotlin_mvp.network.Result
import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.bean.DownloadInfo
import com.catchpig.mvp.network.listener.DownloadCallback
import io.reactivex.Flowable

/**
 * @author catchpig
 * @date 2019/8/18 00:18
 */
interface MainContract {
    interface View:BaseContract.View{

    }
    interface Presenter:BaseContract.Presenter{

    }
    interface Model{
        fun banner():Flowable<Result<Any>>

        fun download(downloadInfo: DownloadInfo, downloadCallback: DownloadCallback)
    }
}