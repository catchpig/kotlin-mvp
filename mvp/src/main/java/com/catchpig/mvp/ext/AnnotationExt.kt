package com.catchpig.mvp.ext

/**
 * @author catchpig
 * @date 2019/9/23 00:23
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