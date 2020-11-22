package com.catchpig.mvp.di

import com.catchpig.mvp.config.Config
import com.catchpig.mvp.gson.DateJsonDeserializer
import com.catchpig.mvp.network.download.DownloadService
import com.catchpig.mvp.network.interceptor.DownloadInterceptor
import com.catchpig.mvp.network.download.DownloadManager
import com.catchpig.mvp.network.listener.DownloadProgressListener
import com.catchpig.utils.ext.logd
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author catchpig
 * @date 2019/4/6 10:24
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

    factory {
        OkHttpClient
                .Builder()
                .connectTimeout(Config.TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(Config.TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(Config.TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(get())
                .build()
    }

    factory {
        Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(get())
    }
}

const val DOWNLOAD_NAME = "download"
val downloadModule = module {
    single(named(DOWNLOAD_NAME)) { (downloadProgressListener: DownloadProgressListener)->
        DownloadInterceptor(downloadProgressListener)
    } bind Interceptor::class

    single(named(DOWNLOAD_NAME)) {(downloadProgressListener: DownloadProgressListener, timeout:Long)->
        OkHttpClient
                .Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(get())
                .addInterceptor(get(named(DOWNLOAD_NAME)){ parametersOf(downloadProgressListener)})
                .build()
    }

    single(named(DOWNLOAD_NAME)) {(baseUrl:String,timeout:Long,downloadProgressListener: DownloadProgressListener)->
        Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(get(named(DOWNLOAD_NAME)){ parametersOf(downloadProgressListener,timeout)})
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DownloadService::class.java)
    }

    single {
        DownloadManager()
    }

}