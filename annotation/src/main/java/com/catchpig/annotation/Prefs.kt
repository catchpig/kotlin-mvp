package com.catchpig.annotation

import com.catchpig.annotation.enums.PrefsMode

/**
 * 创建时间:2019/10/29 0029<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/29 0029<br/>
 * 描述:SharedPreferences注解,可生成对应的文件
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Prefs (
        /**
         * 别名,如果为空则取类名
         */
        val value:String = "",
        /**
         * SharedPreferences模式
         */
        val mode:PrefsMode = PrefsMode.MODE_PRIVATE
)