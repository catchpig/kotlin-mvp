package com.catchpig.mvp.ext

import android.content.Context
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.catchpig.mvp.R
import luyao.util.ktx.ext.logd

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
    typedValue.resourceId.toString().logd("getColorPrimary")
    return typedValue.resourceId
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