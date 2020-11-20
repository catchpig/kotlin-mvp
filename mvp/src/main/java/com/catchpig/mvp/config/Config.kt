package com.catchpig.mvp.config

import com.google.gson.GsonBuilder

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:配置
 */
object Config {
    const val TIME_OUT = 5000L
    //时间格式化规则
    const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    //没有赋值
    const val NO_ASSIGNMENT = -1

    const val APP_MODULE_TAG = "AppModule"

    val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()
}