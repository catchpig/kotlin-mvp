package com.catchpig.aop

import luyao.util.ktx.ext.logd
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * 创建时间:2019/10/20 0020<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/20 0020<br/>
 * 描述:方法和构造函数的耗时打印
 */
@Aspect
class TimeLogAspectJ {
    companion object{
        const val TAG = "[TimeLog]"
    }

    @Pointcut("execution(@com.catchpig.annotation.TimeLog * *(..))")
    fun methodTime(){

    }

    @Pointcut("execution(@com.catchpig.annotation.TimeLog *.new(..))")
    fun constructorTime(){

    }

    @Around("methodTime() || constructorTime()")
    fun clickGap(proceedingJoinPoint: ProceedingJoinPoint){
        val beforeTimeOfMethod = System.currentTimeMillis()
        proceedingJoinPoint.proceed()
        val afterTimeOfMethod = System.currentTimeMillis()
        val className = proceedingJoinPoint.target::class.java.simpleName
        val method = proceedingJoinPoint.signature.name
        var params = StringBuffer("")
        proceedingJoinPoint.args.forEach {
            if (it != null) {
                params.append(",${it.javaClass.simpleName}")
            }else{
                params.append(",null")
            }
        }
        params.delete(0,1)
        val time = afterTimeOfMethod-beforeTimeOfMethod
        "${className}.${method}(${params})耗时:${time}毫秒".logd(TAG)
    }
}