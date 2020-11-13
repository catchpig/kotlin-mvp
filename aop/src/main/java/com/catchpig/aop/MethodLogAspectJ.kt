package com.catchpig.aop

import com.catchpig.annotation.MethodLog
import com.catchpig.utils.ext.log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * 创建时间:2019/10/20 0020<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/20 0020<br/>
 * 描述:打印方法和构造函数的方法和参数值
 */
@Aspect
class MethodLogAspectJ {
    companion object{
        const val TAG = "[MethodLog]"
    }

    @Pointcut("execution(@com.catchpig.annotation.MethodLog * *(..))")
    fun methodLog(){

    }

    @Pointcut("execution(@com.catchpig.annotation.MethodLog *.new(..))")
    fun constructorTime(){

    }

    @Around("(methodLog() || constructorTime()) && @annotation(methodLog)")
    fun aroundMethodLog(proceedingJoinPoint: ProceedingJoinPoint,methodLog: MethodLog){
        proceedingJoinPoint.proceed()
        val className = proceedingJoinPoint.target::class.java.simpleName
        val method = proceedingJoinPoint.signature.name
        var params = StringBuffer("")
        proceedingJoinPoint.args.forEach {
            if (it != null) {
                params.append(",${it}")
            }else{
                params.append(",null")
            }
        }
        params.delete(0,1)
        val level = methodLog.value
        log(level,TAG,"${className}.${method}(${params})")
    }
}