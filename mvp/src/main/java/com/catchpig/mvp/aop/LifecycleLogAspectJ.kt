package com.catchpig.mvp.aop

import luyao.util.ktx.ext.logd
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

/**
 * 创建时间:2019/9/26 0026<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/9/26 0026<br/>
 * 描述:
 */
@Aspect
class LifecycleLogAspectJ {
    companion object {
        const val ON_CREATE = "onCreate"
        const val ON_START = "onStart"
        const val ON_RESUME = "onResume"
        const val ON_PAUSE = "onPause"
        const val ON_STOP = "onStop"
        const val ON_DESTROY = "onDestroy"
    }
    @Before("execution(* com.catchpig.mvp.base.BasePresenter.onCreate(..))")
    fun onCreate(joinPoint: JoinPoint){
        val className  = joinPoint.target::class.java.simpleName
        ON_CREATE.logd(className)
    }
}