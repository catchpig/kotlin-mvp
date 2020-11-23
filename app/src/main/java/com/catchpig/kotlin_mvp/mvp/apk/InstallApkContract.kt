package com.catchpig.kotlin_mvp.mvp.apk

import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.bean.DownloadInfo
import com.catchpig.mvp.listener.DownloadCallback

/**
 *
 * @author catchpig
 * @date 2020/11/20 15:52
 */
interface InstallApkContract {
    interface View:BaseContract.View{
        /**
         * 设置进度
         * @param progress Int
         */
        fun setDownloadProgress(progress:Int)
    }
    interface Presenter:BaseContract.Presenter{
        fun download()
    }
    interface Model{
        /**
         * 下载
         * @param downloadInfo DownloadInfo
         * @param downloadCallback DownloadCallback
         */
        fun download(downloadInfo: DownloadInfo, downloadCallback: DownloadCallback)
    }
}