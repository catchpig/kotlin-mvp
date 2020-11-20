package com.catchpig.mvp.network.download

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Streaming
import retrofit2.http.Url
/**
 * 下载网络接口服务
  * @author catchpig
 * @date 2020/11/20 10:25
 */
interface DownloadService {
    /**
     * 可以断点续传
     * @param url 下载地址
     */
    @Streaming
    @GET
    fun download(@Url url:String):Flowable<ResponseBody>
}