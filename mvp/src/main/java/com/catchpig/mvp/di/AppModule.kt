package com.catchpig.mvp.di

import com.catchpig.mvp.config.Config
import com.catchpig.mvp.gson.DateJsonDeserializer
import com.catchpig.utils.ext.logd
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.*

/**
 * 创建时间:2019/4/6 10:24<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 10:24<br></br>
 * 描述:
 */

val appModule = module {
    single {
        DateJsonDeserializer()
    }
    single {
        GsonBuilder().setDateFormat(Config.DATE_FORMAT).registerTypeAdapter(Date::class.java,get()).create()
    }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            message.logd(Config.APP_MODULE_TAG)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor
    } bind Interceptor::class
}