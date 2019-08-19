package com.catchpig.mvp.utils

import com.catchpig.mvp.annotation.Title

/**
 * @author litao
 * @date 2017/4/2 18:18
 */
object AnnotationUtils {
    /**
     * 只在当前类中获取注解类
     *
     * @param cls 使用了注解的class
     * @param aCls 注解的class
     */
    fun <A : Annotation> annotation(cls: Class<*>, aCls: Class<A>): A? {
        return if (cls.isAnnotationPresent(aCls)) {
            cls.getAnnotation(aCls)
        } else {
            null
        }
    }

    /**
     * 循环获取注解类(当前类中无A注解,就到他的父类中找A注解,递归虚招)
     *
     * @param cls 使用了注解的class
     * @param aCls 注解的class
     */
    fun <A : Annotation> annotationRecycle(cls: Class<*>, aCls: Class<A>): A? {
        var a: A? = null
        if (cls.isAnnotationPresent(aCls)) {
            a = cls.getAnnotation(aCls)
        }
        return a ?: cls.getSuperclass()?.let { annotationRecycle(it, aCls) }
    }
}
