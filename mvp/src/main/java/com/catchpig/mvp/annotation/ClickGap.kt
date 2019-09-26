package com.catchpig.mvp.annotation

/**
 *
 * @author TLi2
 **/
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class ClickGap(
        val value: Int = 500
)