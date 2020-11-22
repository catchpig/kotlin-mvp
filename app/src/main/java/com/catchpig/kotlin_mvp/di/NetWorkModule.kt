package com.catchpig.kotlin_mvp.di

import com.catchpig.kotlin_mvp.Config
import com.catchpig.kotlin_mvp.network.api.WanAndroidService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        get(Retrofit.Builder::class)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.WANG_ANDROID_URL)
                .build()
                .create(WanAndroidService::class.java)
    }
}
