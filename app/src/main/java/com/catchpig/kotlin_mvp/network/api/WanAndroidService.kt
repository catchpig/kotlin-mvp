package com.catchpig.kotlin_mvp.network.api

import com.catchpig.kotlin_mvp.network.Result
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET


interface WanAndroidService {
    @GET("banner/json")
    fun banner():Flowable<Result<Any>>
}