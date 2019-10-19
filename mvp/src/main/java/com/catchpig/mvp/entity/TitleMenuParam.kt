package com.catchpig.mvp.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * 创建时间:2019/10/17 0017<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/17 0017<br/>
 * 描述:
 */
data class TitleMenuParam(
        /**
         * 右边第一个按钮图标
         */
        @DrawableRes val rightFirstDrawable:Int = -1,
        /**
         * 右边第一个按钮文字
         */
        @StringRes val rightFirstText:Int = -1,
        /**
         * 右边第二个按钮图标
         */
        @DrawableRes val rightSecondDrawable:Int = -1,
        /**
         * 右边第二个按钮文字
         */
        @StringRes val rightSecondText:Int = -1
)