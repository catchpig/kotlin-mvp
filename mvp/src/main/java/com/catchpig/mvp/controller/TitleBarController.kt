package com.catchpig.mvp.controller

import android.view.View
import com.catchpig.mvp.base.activity.BaseActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_title_bar.*

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
class TitleBarController:LayoutContainer {
    lateinit var baseActivity:BaseActivity
    constructor(baseActivity: BaseActivity){
        this.baseActivity = baseActivity
    }
    override val containerView: View?
        get() = baseActivity.title_bar
}