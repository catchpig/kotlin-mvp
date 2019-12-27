package com.catchpig.mvp.di.module

import android.app.Application
import com.catchpig.mvp.config.Config
import com.catchpig.mvp.gson.DateJsonDeserializer
import com.catchpig.utils.ext.logd
import com.google.gson.*
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
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
    fun providesGson(dateJsonDeserializer: DateJsonDeserializer): Gson {
        return GsonBuilder().setDateFormat(Config.DATE_FORMAT).registerTypeAdapter(Date::class.java,dateJsonDeserializer).create()
    }
    @Singleton
    @Provides
    fun providesDateJsonDeserializer():DateJsonDeserializer{
        return DateJsonDeserializer()
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
