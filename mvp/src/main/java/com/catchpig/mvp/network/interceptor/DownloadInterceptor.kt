package com.catchpig.mvp.network.interceptor

import com.catchpig.mvp.network.download.DownloadResponseBody
import com.catchpig.mvp.network.listener.DownloadProgressListener
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
/**
 * 下载拦截器
 * @author catchpig
 * @date 2020/11/20 10:25
 */
class DownloadInterceptor(private val downloadProgressListener: DownloadProgressListener):Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val originalResponse: Response = chain.proceed(chain.request())
        return originalResponse.newBuilder()
                .body(DownloadResponseBody(originalResponse.body()!!, downloadProgressListener!!))
                .build()
    }
}