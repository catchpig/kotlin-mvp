package com.catchpig.mvp.ext

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 创建时间:2019/9/21 0021<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/9/21 0021<br/>
 * 描述:RxJava扩展类
 */

/**
 * 被观察者在io线程中执行,观察者在主线程中执行
 */
fun <T> Flowable<T>.io2main():Flowable<T>{
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}