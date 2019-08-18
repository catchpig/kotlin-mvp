package com.catchpig.mvp.base.module

import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * 创建时间:2019/4/6 12:52<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 12:52<br></br>
 * 描述:
 */
@Module
abstract class BaseFragmentModule<V : BaseContract.View>(protected var mView: V) {
    @FragmentScope
    @Provides
    fun providesView(): V {
        return mView
    }
}
