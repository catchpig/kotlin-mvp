package com.catchpig.mvp.controller

import android.view.View
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.ext.getBackIcon
import com.catchpig.mvp.ext.getColorPrimary
import com.catchpig.mvp.utils.AnnotationUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_title_bar.*
import kotlinx.android.synthetic.main.layout_title_bar.view.*

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
class TitleBarController {
    lateinit var baseActivity:BaseActivity
    init {

    }
    constructor(baseActivity: BaseActivity){
        this.baseActivity = baseActivity
        initTitleBar()
    }
    private fun initTitleBar(){
        var title:Title? = AnnotationUtils.annotation(baseActivity::class.java,Title::class.java)

        baseActivity.title_bar.run {
            setBackgroundResource(baseActivity.getColorPrimary())
            back.setImageResource(baseActivity.getBackIcon())
            title?.let {
                this.title.text = title.value
            }
        }
    }
}