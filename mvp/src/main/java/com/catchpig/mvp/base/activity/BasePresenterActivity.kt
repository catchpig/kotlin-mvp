package com.catchpig.mvp.base.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import com.catchpig.mvp.base.BaseContract
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope

/**
 * @author catchpig
 * @date 2019/4/6 11:07
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
