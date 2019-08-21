package com.catchpig.mvp.annotation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.catchpig.mvp.config.Config.NO_ASSIGNMENT

/**
 * 创建时间:2019/8/19 0019<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/19 0019<br/>
 * 描述:标题栏按钮
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class TitleMenu (
        /**
         * 右边第一个按钮图标
         */
        @DrawableRes val rightFirstDrawable:Int = NO_ASSIGNMENT,
        /**
         * 右边第一个按钮文字
         */
        @StringRes val rightFirstText:Int = NO_ASSIGNMENT,
        /**
         * 右边第二个按钮图标
         */
        @DrawableRes val rightSecondDrawable:Int = NO_ASSIGNMENT,
        /**
         * 右边第二个按钮文字
         */
        @StringRes val rightSecondText:Int = NO_ASSIGNMENT
)