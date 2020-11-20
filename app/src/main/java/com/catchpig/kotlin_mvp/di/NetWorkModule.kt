package com.catchpig.kotlin_mvp.di

import com.catchpig.kotlin_mvp.Config
import com.catchpig.kotlin_mvp.network.api.WanAndroidService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        get(Retrofit.Builder::class)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.WANG_ANDROID_URL)
                .build()
                .create(WanAndroidService::class.java)
    }
}
