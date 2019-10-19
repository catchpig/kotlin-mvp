package com.catchpig.mvp.apt

import com.catchpig.mvp.base.activity.BaseActivity
import kotlinx.android.synthetic.main.view_root.*
import luyao.util.ktx.ext.logd

/**
 * 创建时间:2019/10/17 0017<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/17 0017<br/>
 * 描述:
 */
object BarUtils {
    private const val TAG = "BarUtils"
    fun inject(baseActivity: BaseActivity){
        val className = baseActivity.javaClass.name
        try {
            val compilerClass = Class.forName(className+"_MvpCompiler")
            compilerClass.let {
                val mvpCompiler = compilerClass.newInstance() as MvpCompiler
                mvpCompiler.inject(baseActivity)
            }
        }catch (exception:ClassNotFoundException){
            "$className:没有被编译时Title和TitleMenu注解修饰".let {
                it.logd(TAG)
            }
        }

    }
}