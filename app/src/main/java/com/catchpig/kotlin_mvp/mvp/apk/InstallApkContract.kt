package com.catchpig.kotlin_mvp.mvp.apk

import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.listener.DownloadCallback
import com.catchpig.mvp.listener.MultiDownloadCallback

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
        fun setDownloadProgress1(progress:Int)
    }
    interface Presenter:BaseContract.Presenter{
        fun download()
    }
    interface Model{
        /**
         * 下载
         * @param url String
         * @param downloadCallback DownloadCallback
         */
        fun download(url: String, downloadCallback: DownloadCallback)

        /**
         * 批量文件下载
         * @param urls MutableList<String>
         * @param multiDownloadCallback MultiDownloadCallback
         */
        fun multiDownload(urls:MutableList<String>,multiDownloadCallback: MultiDownloadCallback)
    }
}