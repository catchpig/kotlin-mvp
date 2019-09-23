package com.catchpig.mvp.base.activity

import android.os.Bundle
import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.base.BasePresenter
import javax.inject.Inject

/**
 * 创建时间:2019/4/6 11:07<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/6 11:07<br></br>
 * 描述:
 */
abstract class BasePresenterActivity<P : BaseContract.Presenter> : BaseActivity() {
    @Inject
    lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        initParam()
        lifecycle.addObserver(mPresenter)
        initView()
    }

    protected abstract fun initParam()
    protected abstract fun injectComponent()
    protected abstract fun initView()
}
