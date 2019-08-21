package com.catchpig.mvp.ext

import android.content.Context
import android.content.res.TypedArray
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.catchpig.mvp.R
import luyao.util.ktx.ext.logd
import luyao.util.ktx.ext.toast

/**
 * 创建时间:2019/8/19 0019<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/19 0019<br/>
 * 描述:全局主题的配置信息
 */
/**
 * 获取主题色
 */
@ColorRes
fun Context.getColorPrimary():Int{
    var typedValue = TypedValue()
    theme.resolveAttribute(R.attr.colorPrimary, typedValue,true)
    return typedValue.resourceId
}

/**
 * 获取标题栏背景色
 */
@ColorRes
fun Context.getTitleBackground():Int{
    var typedValue = TypedValue()
    theme.resolveAttribute(R.attr.title_bar_background, typedValue,true)
    return if (typedValue.resourceId==0) {
        android.R.color.white
    }else{
        typedValue.resourceId
    }
}
/**
 * 获取返回按钮图标
 */
@DrawableRes
fun Context.getTitleBackIcon(): Int{
    var typedValue = TypedValue()
    theme.resolveAttribute(R.attr.title_bar_back_icon, typedValue,true)
    return typedValue.resourceId
}

/**
 * 获取标题文字颜色
 */
fun Context.getTitleTextColor(): Int{
    var typedValue = TypedValue()
    theme.resolveAttribute(R.attr.title_bar_text_color, typedValue,true)
    return typedValue.data
}

fun Context.showTitleLine():Boolean{
    var attrs = intArrayOf(R.attr.title_bar_show_line)
    val typedArray = theme.obtainStyledAttributes(attrs)
    return typedArray.getBoolean(0,false)
}