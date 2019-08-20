package com.catchpig.mvp.controller

import com.catchpig.mvp.R
import com.catchpig.mvp.annotation.StatusBar
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.config.Config
import com.catchpig.mvp.ext.getColorPrimary
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:状态栏处理器
 */
class StatusBarController {
    private val baseActivity: BaseActivity
    private val title: Title?
    private val statusBar: StatusBar?

    constructor(baseActivity: BaseActivity, title: Title?, statusBar: StatusBar?) {
        this.baseActivity = baseActivity
        this.title = title
        this.statusBar = statusBar
        checkStatusBar()
    }

    private fun checkStatusBar() {
        //状态栏注解设置为不可用
        if (statusBar!=null&&statusBar.enabled) {
            return
        }
        baseActivity.immersionBar {
            if (statusBar == null) {
                statusBarView(R.id.top_view)
                autoStatusBarDarkModeEnable(true,0.2f)
                //设置状态栏颜色
                if (title==null || title.backgroundColor==Config.NO_ASSIGNMENT) {
                    statusBarColor(baseActivity.getColorPrimary())
                }else{
                    statusBarColor(title.backgroundColor)
                }
            }else{
                checkStatusBarHide(this)
            }
        }
    }

    /**
     * 检查状态栏是否隐藏
     */
    private fun checkStatusBarHide(immersionBar: ImmersionBar){
        if (statusBar!!.hide) {
            //状态栏隐藏
            immersionBar.hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
        }else{
            checkStatusBarTransparent(immersionBar)
        }
    }

    /**
     * 检查状态栏是否透明
     */
    private fun checkStatusBarTransparent(immersionBar: ImmersionBar){
        if (statusBar!!.transparent) {
            immersionBar.transparentStatusBar()
        }else{
            immersionBar.statusBarView(R.id.top_view)
            immersionBar.autoStatusBarDarkModeEnable(true,0.2f)
            immersionBar.statusBarColor(baseActivity.getColorPrimary())
        }
    }
}