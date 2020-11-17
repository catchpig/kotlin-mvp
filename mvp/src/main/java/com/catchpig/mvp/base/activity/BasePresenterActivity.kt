package com.catchpig.mvp.base.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import com.catchpig.mvp.base.BaseContract
import org.koin.androidx.scope.activityScope
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.inject

/**
 * 创建时间:2019/4/6 11:07<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 11:07<br></br>
 * 描述:
 */
abstract class BasePresenterActivity<P : BaseContract.Presenter> : BaseActivity(),KoinScopeComponent {

    override val scope: Scope by lazy { activityScope() }
    abstract val mPresenter:P

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
        lifecycle.addObserver(mPresenter)
        initView()
    }

    protected abstract fun initParam()
    protected abstract fun initView()
}
