package com.catchpig.mvp.ext

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 被观察者在io线程中执行,观察者在主线程中执行
 */
fun <T> Flowable<T>.io2main():Flowable<T>{
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}