package com.catchpig.kotlin_mvp.mvp.main.model

import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.network.Result
import com.catchpig.kotlin_mvp.network.api.WanAndroidService
import io.reactivex.rxjava3.core.Flowable

class MainModel(private val wanAndroidService: WanAndroidService):MainContract.Model {
    override fun banner(): Flowable<Result<Any>> {
        return wanAndroidService.banner()
    }
}