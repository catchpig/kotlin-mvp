package com.catchpig.kotlin_mvp.network.api

import com.catchpig.kotlin_mvp.network.Result
import io.reactivex.Flowable
import retrofit2.http.GET


interface WanAndroidService {
    @GET("banner/json")
    fun banner():Flowable<Result<Any>>
}