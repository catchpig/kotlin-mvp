package com.catchpig.mvp.ext

import com.catchpig.mvp.config.Config
import java.text.SimpleDateFormat
import java.util.*

/**
 * 创建时间:2019/8/31 0031<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/31 0031<br/>
 * 描述:
 */
/**
 * 判断不为null
 */
fun CharSequence?.isNotNull():Boolean{
    return this!=null
}

/**
 * 字符串转化为时间
 */
fun String.toDate(format:String=Config.DATE_FORMAT):Date{
    val dateFormat = SimpleDateFormat(format)
    return dateFormat.parse(this)
}