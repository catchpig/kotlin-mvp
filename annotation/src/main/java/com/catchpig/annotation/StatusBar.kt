package com.catchpig.annotation


/**
 * 创建时间:2019/8/19 0019<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/19 0019<br/>
 * 描述:状态栏
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class StatusBar(
        /**
         * 隐藏状态栏
         */
        val hide: Boolean = false,
        /**
         * 状态栏注解是否可用
         */
        val enabled: Boolean = false,
        /**
         * 状态栏透明
         */
        val transparent: Boolean = false
)