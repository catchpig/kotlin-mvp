package com.catchpig.mvp.entity

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * 创建时间:2019/10/17 0017<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/17 0017<br/>
 * 描述:
 */
data class TitleParam(
        /**
         * 标题内容
         */
        @StringRes val value:Int,
        /**
         * 标题背景色
         */
        @ColorRes val backgroundColor:Int = -1,
        /**
         * 标题文字颜色
         */
        @ColorRes val textColor:Int = -1,
        /**
         * 返回按钮图标
         */
        @DrawableRes val backIcon:Int = -1
)