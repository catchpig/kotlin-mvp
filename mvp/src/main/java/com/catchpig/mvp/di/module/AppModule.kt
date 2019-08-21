package com.catchpig.mvp.di.module

import android.app.Application
import com.catchpig.mvp.config.Config
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import luyao.util.ktx.ext.logd
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * 创建时间:2019/4/6 10:24<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 10:24<br></br>
 * 描述:
 */
@Module
class AppModule(private val mApplication: Application) {
    companion object{
        const val TAG = "AppModule"
    }
    @Singleton
    @Provides
    fun providesApplication(): Application {
        return mApplication
    }
    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().setDateFormat(Config.DATE_FORMAT).create()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            message.logd(TAG)
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}
