package com.catchpig.mvp.aop

import com.catchpig.mvp.annotation.ClickGap
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

/**
 * 创建时间:2019/8/19 0019<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/19 0019<br/>
 * 描述: 防止重复点击
 */
@Aspect
class ClickGapAspectJ {
    private var lastTimeMillis:Long = 0
    @Pointcut("execution(@com.catchpig.mvp.annotation.ClickGap * *(..))")
    fun pointcut(){

    }

    @Around("pointcut() && @annotation(clickGap)")
    fun clickGap(proceedingJoinPoint:ProceedingJoinPoint,clickGap: ClickGap){
        val currentTimeMillis = System.currentTimeMillis()
        if((currentTimeMillis-lastTimeMillis) > clickGap!!.value){
            proceedingJoinPoint.proceed()
        }
        lastTimeMillis = currentTimeMillis
    }
}