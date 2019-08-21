package com.catchpig.mvp.controller

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.catchpig.mvp.R
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.annotation.TitleMenu
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.config.Config
import com.catchpig.mvp.ext.*
import kotlinx.android.synthetic.main.layout_title_bar.*
import kotlinx.android.synthetic.main.layout_title_bar.view.*
import kotlinx.android.synthetic.main.view_root.*
import kotlinx.android.synthetic.main.view_root.view.*
import kotlinx.android.synthetic.main.view_root.view.line
import luyao.util.ktx.ext.toast

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:标题栏处理器
 */
class TitleBarController(private val baseActivity: BaseActivity,private val title: Title?,private val titleMenu: TitleMenu?) : View.OnClickListener{

    fun initTitleBar(){
        if (title==null) {
            baseActivity.title_bar.visibility = View.GONE
            baseActivity.line.visibility = View.GONE
        }else{
            baseActivity.title_bar.visibility = View.VISIBLE
            initListener()
            baseActivity.title_bar.run {
                //设置背景色
                drawBackground(this)
                //设置文字颜色
                drawTextColor(this)
                //设置返回按钮图标
                drawBackIcon(back_icon)
                title_text.setText(title.value)
                titleMenu?.let {
                    drawTitleMenu(this,it)
                }
            }
            drawLine(baseActivity.line)
        }
    }
    private fun drawLine(line:View){
        if (baseActivity.showTitleLine()) {
            line.visibility = View.VISIBLE
        }else{
            line.visibility = View.GONE
        }
    }

    /**
     * 绘制右边按钮
     */
    private fun drawTitleMenu(titleBar: FrameLayout,titleMenu: TitleMenu){
        //右边第一个文字按钮
        titleBar.rightFirstText.run {
            if (titleMenu.rightFirstText!=Config.NO_ASSIGNMENT) {
                visibility = View.VISIBLE
                setText(titleMenu.rightFirstText)
            }else{
                visibility = View.GONE
            }
        }
        //右边第二个文字按钮
        titleBar.rightSecondText.run {
            if (titleMenu.rightSecondText!=Config.NO_ASSIGNMENT) {
                visibility = View.VISIBLE
                setText(titleMenu.rightSecondText)
            }else{
                visibility = View.GONE
            }
        }
        //右边第一个图标按钮
        titleBar.rightFirstDrawable.run {
            if (titleMenu.rightFirstDrawable != Config.NO_ASSIGNMENT) {
                visibility = View.VISIBLE
                setImageResource(titleMenu.rightFirstDrawable)
            }else{
                visibility = View.GONE
            }
        }
        //右边第二个图标按钮
        titleBar.rightSecondDrawable.run {
            if (titleMenu.rightSecondDrawable != Config.NO_ASSIGNMENT) {
                visibility = View.VISIBLE
                setImageResource(titleMenu.rightSecondDrawable)
            }else{
                visibility = View.GONE
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
        val backgroundColor = if (title!!.backgroundColor== Config.NO_ASSIGNMENT) {
            baseActivity.getTitleBackground()
        }else{
            title.backgroundColor
        }
        titleBar.setBackgroundResource(backgroundColor)
    }

    /**
     * 获取color颜色
     */
    private fun getColorInt(@ColorRes color:Int):Int{
        return ContextCompat.getColor(baseActivity.applicationContext,color)
    }

    /**
     * 设置文字颜色
     */
    private fun drawTextColor(titleBar:FrameLayout){
        var textColor = if (title!!.textColor==Config.NO_ASSIGNMENT) {
            baseActivity.getTitleTextColor()
        }else{
            getColorInt(title.textColor)
        }
        titleBar.title_text.setTextColor(textColor)
        titleBar.rightFirstText.setTextColor(textColor)
        titleBar.rightSecondText.setTextColor(textColor)
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