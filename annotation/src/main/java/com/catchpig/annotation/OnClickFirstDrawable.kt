package com.catchpig.annotation

import androidx.annotation.DrawableRes

/**
 * 创建时间:2019/10/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/18 0018<br/>
 * 描述:右边第一个图标按钮的点击事件
 *
 * 修饰的方法可以没有参数和一个参数,
 * 有参数的时候参数类型必须为View
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnClickFirstDrawable(
        /**
         * 图标内容
         */
        @DrawableRes val value: Int
)