package com.catchpig.mvp.bean

/**
 * 下载信息
 * @author catchpig
 * @date 2020/11/20 10:34
 */
data class DownloadInfo(
        /**
         * 域名
         */
        val baseUrl:String,
        /**
         * 下载地址
         */
        val url:String,
        /**
         * 连接超时时间(单位:秒)
         */
        val connectTimeout:Long = 5
)