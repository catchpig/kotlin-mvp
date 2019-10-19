package com.catchpig.annotation

import androidx.annotation.StringRes

/**
 * 创建时间:2019/10/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/18 0018<br/>
 * 描述:右边第一个文字按钮的点击事件
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnClickFirstText(
        /**
         * 文字内容
         */
        @StringRes val value:Int
)