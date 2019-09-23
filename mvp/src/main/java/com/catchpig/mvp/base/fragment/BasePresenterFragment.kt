package com.catchpig.mvp.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleObserver
import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.base.BasePresenter
import javax.inject.Inject

/**
 * 创建时间:2019/4/6 11:25<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 11:25<br></br>
 * 描述:
 */
abstract class BasePresenterFragment<P : BaseContract.Presenter> : BaseFragment(), LifecycleObserver {
    @Inject
    lateinit var mPresenter: P
    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        injectComponent()
        initParam()
        lifecycle.addObserver(mPresenter)
        initView()
    }

    protected abstract fun initParam()

    protected abstract fun injectComponent()

    protected abstract fun initView()
}
