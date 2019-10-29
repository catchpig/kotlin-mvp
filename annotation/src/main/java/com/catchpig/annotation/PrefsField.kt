package com.catchpig.annotation

/**
 * 创建时间:2019/10/29 0029<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/29 0029<br/>
 * 描述:
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class PrefsField (
        /**
         * 字段名字,如果为空就取参数名称
         */
        val value:String = ""
)