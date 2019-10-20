package com.catchpig.aop

import luyao.util.ktx.ext.logd
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*

/**
 * 创建时间:2019/9/26 0026<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/9/26 0026<br/>
 * 描述:打印生命周期日志
 */
@Aspect
class LifecycleLogAspectJ {
    companion object {
        const val TAG = "[LIFE_CYCLE_LOG]"
    }

    /**
     * Presenter的生命周期
     */
    @Pointcut("execution(@androidx.annotation.CallSuper * com.catchpig.mvp.base.BasePresenter.on*(..))")
    fun presenterLifecycle(){

    }

    /**
     * Activity的生命周期
     */
    @Pointcut("execution(@androidx.annotation.CallSuper * com.catchpig.mvp.base.activity.BaseActivity.on*(..))")
    fun activityLifecycle(){

    }

    /**
     * Fragment的生命周期
     */
    @Pointcut("execution(@androidx.annotation.CallSuper * com.catchpig.mvp.base.fragment.BaseFragment.on*(..))")
    fun fragmentLifecycle(){

    }

    @After("presenterLifecycle() || activityLifecycle() || fragmentLifecycle()")
    fun lifecycleLog(joinPoint: JoinPoint){
        val className  = joinPoint.target::class.java.simpleName
        val methodName = joinPoint.signature.name
        "$className:$methodName".logd(TAG)
    }
}