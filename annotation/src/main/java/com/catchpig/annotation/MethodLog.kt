package com.catchpig.annotation

import com.catchpig.annotation.enums.LEVEL

/**
 * 创建时间:2020/11/9 1126<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2020/11/9 1126<br/>
 * 描述:防止重复点击注解
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.CONSTRUCTOR)
annotation class MethodLog (
    /**
     * 日志等级
     */
    val value: LEVEL = LEVEL.D
)