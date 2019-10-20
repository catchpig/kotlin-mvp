package com.catchpig.aop

import com.catchpig.annotation.ClickGap
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

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
    @Pointcut("execution(@com.catchpig.annotation.ClickGap * *(..))")
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