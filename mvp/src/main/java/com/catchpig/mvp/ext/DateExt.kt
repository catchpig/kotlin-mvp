package com.catchpig.mvp.ext

import com.catchpig.mvp.config.Config
import java.text.SimpleDateFormat
import java.util.*

/**
 * 创建时间:2019/9/27 0027<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/9/27 0027<br/>
 * 描述:
 */

/**
 * 时间转化为字符串
 * @param format 转化格式
 * @return
 */
fun Date.format(format:String = Config.DATE_FORMAT):String{
    val dateFormat = SimpleDateFormat(format)
    return dateFormat.format(this)
}