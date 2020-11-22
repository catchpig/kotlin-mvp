package com.catchpig.kotlin_mvp.mvp.main

import com.catchpig.kotlin_mvp.network.Result
import com.catchpig.mvp.base.BaseContract
import io.reactivex.rxjava3.core.Flowable

/**
 * @author catchpig
 * @date 2019/8/18 00:18
 */
interface MainContract {
    interface View:BaseContract.View{

    }
    interface Presenter:BaseContract.Presenter{

    }
    interface Model{
        fun banner():Flowable<Result<Any>>
    }
}