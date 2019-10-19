package com.catchpig.mvp.entity

/**
 * 创建时间:2019/10/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/18 0018<br/>
 * 描述:
 */
data class StatusBarParam (
        /**
         * 隐藏状态栏
         */
        val hide:Boolean = false,
        /**
         * 状态栏注解是否可用
         */
        val enabled:Boolean = false,
        /**
         * 状态栏透明
         */
        val transparent:Boolean = false
)