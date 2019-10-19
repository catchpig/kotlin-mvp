package com.catchpig.mvp.apt

import android.view.ViewStub
import com.catchpig.mvp.base.activity.BaseActivity

/**
 * 创建时间:2019/10/17 0017<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/17 0017<br/>
 * 描述:
 */
interface MvpCompiler {
    /**
     * 初始化标题栏
     */
    fun inject(activity: BaseActivity)

}