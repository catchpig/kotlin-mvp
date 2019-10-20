package com.catchpig.mvp.apt

import com.catchpig.mvp.base.activity.BaseActivity
import luyao.util.ktx.ext.logd

/**
 * 创建时间:2019/10/17 0017<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/17 0017<br/>
 * 描述:
 */
object KotlinMvpCompiler {
    private const val TAG = "KotlinMvpCompiler"
    fun inject(baseActivity: BaseActivity){
        val className = baseActivity.javaClass.name
        try {
            val compilerClass = Class.forName(className+"_MvpCompiler")
            compilerClass.let {
                val mvpCompiler = compilerClass.newInstance() as MvpCompiler
                mvpCompiler.inject(baseActivity)
            }
        }catch (exception:ClassNotFoundException){
            "$className:没有被(com.catchpig.annotation)下编译时注解修饰".let {
                it.logd(TAG)
            }
        }

    }
}