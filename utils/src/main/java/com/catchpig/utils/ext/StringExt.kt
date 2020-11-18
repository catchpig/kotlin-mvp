package com.catchpig.utils.ext

import java.util.regex.Pattern

/**
 * 创建时间:2019/8/31 0031<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/31 0031<br/>
 * 描述:字符串工具扩展
 */
/**
 * 判断不为null
 */
fun CharSequence?.isNotNull():Boolean{
    return this!=null
}

/**
 * 判断字符串是否为纯数字
 */
fun CharSequence?.isNumber():Boolean{
    if (this == null) {
        return false
    }
    var pattern = Pattern.compile("[0-9]*")
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

