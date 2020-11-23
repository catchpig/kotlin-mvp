package com.catchpig.mvp.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleObserver
import com.catchpig.mvp.base.BaseContract
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope

/**
 * @author catchpig
 * @date 2019/4/6 11:25
 */
abstract class BasePresenterFragment<P : BaseContract.Presenter> : BaseFragment(), LifecycleObserver,KoinScopeComponent  {
    override val scope: Scope by lazy { fragmentScope() }
    abstract val presenter: P

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initParam()
        lifecycle.addObserver(presenter)
        initView()
    }

    protected abstract fun initParam()


    protected abstract fun initView()

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}
