package com.catchpig.mvp.ext

import com.catchpig.mvp.config.Config


/**
 * json转实体类
 */
inline fun <reified T> String.jsonToClass():T{
    return Config.gson.fromJson(this,T::class.java)
}

/**
 * json转字符串
 */
fun Any.jsonToString():String{
    return Config.gson.toJson(this)
}


