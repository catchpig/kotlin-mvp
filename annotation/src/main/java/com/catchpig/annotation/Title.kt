package com.catchpig.annotation

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import javax.annotation.Resource
import javax.annotation.Resources

/**
 * 创建时间:2019/8/19 0019<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/19 0019<br/>
 * 描述:标题栏
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Title(
        /**
         * 标题内容
         */
        @StringRes val value: Int,
        /**
         * 标题背景色
         */
        @ColorRes val backgroundColor: Int = -1,
        /**
         * 标题文字颜色
         */
        @ColorRes val textColor: Int = -1,
        /**
         * 返回按钮图标
         */
        @DrawableRes val backIcon: Int = -1
)