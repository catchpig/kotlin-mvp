package com.catchpig.kotlin_mvp.mvp.apk.model

import com.catchpig.kotlin_mvp.mvp.apk.InstallApkContract
import com.catchpig.mvp.bean.DownloadInfo
import com.catchpig.mvp.manager.DownloadManager
import com.catchpig.mvp.listener.DownloadCallback

/**
 *
 * @author catchpig
 * @date 2020/11/20 15:51
 */
class InstallApkModel(private val downloadManager: DownloadManager):InstallApkContract.Model {
    override fun download(downloadInfo: DownloadInfo, downloadCallback: DownloadCallback) {
        downloadManager.download(downloadInfo,downloadCallback)
    }
}