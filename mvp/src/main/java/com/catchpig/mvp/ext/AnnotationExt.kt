package com.catchpig.mvp.ext

/**
 * 创建时间:2019/9/23 0023<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/9/23 0023<br/>
 * 描述:
 */

/**
 * 只在当前类中获取注解类
 */
inline fun <reified A : Annotation> Any.annotation():A?{
    val cls = this::class.java
    val returnCls = A::class.java
    return if(cls.isAnnotationPresent(returnCls)){
        cls.getAnnotation(returnCls)
    }else{
        null
    }
}