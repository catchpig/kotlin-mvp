package com.catchpig.annotation

/**
 * 创建时间:2019/10/20 0020<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/20 0020<br/>
 * 描述:方法和构造方法的耗时打印
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.CONSTRUCTOR)
annotation class TimeLog(
        /**
         * 日志等级
         */
        val value:LEVEL = LEVEL.D
)
/**
 * 日志等级
 */
enum class LEVEL{
        V, D, I, W, E
}