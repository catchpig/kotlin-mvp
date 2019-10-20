package com.catchpig.annotation

/**
 * 创建时间:2019/9/26 0026<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/9/26 0026<br/>
 * 描述:防止重复点击注解
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ClickGap(
        /**
         * 重复点击间隔时间
         */
        val value: Int = 800
)