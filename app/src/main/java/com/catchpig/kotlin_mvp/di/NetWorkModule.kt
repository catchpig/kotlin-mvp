package com.catchpig.kotlin_mvp.di

import com.catchpig.kotlin_mvp.Config
import com.catchpig.kotlin_mvp.network.api.WanAndroidService
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient
                .Builder()
                .connectTimeout(com.catchpig.mvp.config.Config.TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(com.catchpig.mvp.config.Config.TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(com.catchpig.mvp.config.Config.TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(get<Interceptor>())
                .build()
    }
    single {
        Retrofit
                .Builder()
                .client(get())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .baseUrl(Config.WANG_ANDROID_URL)
                .build()
    }
    single {
        get<Retrofit>().create(WanAndroidService::class.java)
    }
}
