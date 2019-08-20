package com.catchpig.mvp.controller

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.catchpig.mvp.R
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.config.Config
import com.catchpig.mvp.ext.getTitleBackIcon
import com.catchpig.mvp.ext.getColorPrimary
import com.catchpig.mvp.ext.getTitleTextColor
import kotlinx.android.synthetic.main.layout_title_bar.*
import kotlinx.android.synthetic.main.layout_title_bar.view.*

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:标题栏处理器
 */
class TitleBarController : View.OnClickListener{


    private val baseActivity:BaseActivity
    private val title:Title?
    constructor(baseActivity: BaseActivity,title: Title?){
        this.baseActivity = baseActivity
        this.title = title
        initTitleBar()
    }
    private fun initTitleBar(){
        if (title==null) {
            baseActivity.title_bar.visibility = View.GONE
        }else{
            baseActivity.title_bar.visibility = View.VISIBLE
            initListener()
            baseActivity.title_bar.run {
                //设置背景色
                drawBackground(this)
                //设置文字颜色
                drawTextColor(title_bar.title_text)
                //设置返回按钮图标
                drawBackIcon(title_bar.back_icon)
                title_text.text = title.value
            }
        }
    }

    /**
     * 初始化监听器
     */
    private fun initListener(){
        baseActivity.title_bar.back_icon.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when(view!!.id){
            //返回
            R.id.back_icon -> baseActivity.finish()
        }
    }
    /**
     * 设置背景色
     */
    private fun drawBackground(titleBar:FrameLayout){
        if (title!!.backgroundColor== Config.NO_ASSIGNMENT) {
            titleBar.setBackgroundResource(baseActivity.getColorPrimary())
        }else{
            titleBar.setBackgroundResource(title.backgroundColor)
        }
    }

    /**
     * 设置文字颜色
     */
    private fun drawTextColor(text:TextView){
        if (title!!.textColor==Config.NO_ASSIGNMENT) {
            text.setTextColor(baseActivity.getTitleTextColor())
        }else{
            text.setTextColor(ContextCompat.getColor(baseActivity.applicationContext,title.textColor))
        }
    }

    /**
     * 设置返回按钮图标
     */
    private fun drawBackIcon(backIcon:ImageView){
        if (title!!.backIcon==Config.NO_ASSIGNMENT) {
            backIcon.setImageResource(baseActivity.getTitleBackIcon())
        }else{
            backIcon.setImageResource(title.backIcon)
        }
    }
}