package com.catchpig.mvp.ext

import com.catchpig.mvp.config.Config


/**
 * json转实体类
 */
fun <T> String.jsonToClass(cls:Class<T>):T{
    return Config.gson.fromJson(this,cls)
}

/**
 * json转字符串
 */
fun Any.jsonToString():String{
    return Config.gson.toJson(this)
}


