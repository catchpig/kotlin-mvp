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
        OkHttpClient
                .Builder()
                .connectTimeout(Config.TIME_OUT,TimeUnit.MILLISECONDS)
                .readTimeout(Config.TIME_OUT,TimeUnit.MILLISECONDS)
                .writeTimeout(Config.TIME_OUT,TimeUnit.MILLISECONDS)
                .addInterceptor(get())
                .build()
    }
    single {
        Retrofit
                .Builder()
                .baseUrl(Config.WANG_ANDROID_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
    }
    single {
        val retrofit:Retrofit = get()
        retrofit.create(WanAndroidService::class.java)
    }
}
