package com.catchpig.annotation.enums

/**
 * 创建时间:2019/10/29 0029<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/29 0029<br/>
 * 描述:SharedPreferences模式
 */
enum class PrefsMode(val value:Int) {
    MODE_PRIVATE(0x0000),
    MODE_WORLD_READABLE(0x0001),
    MODE_WORLD_WRITEABLE(0x0002),
    MODE_MULTI_PROCESS(0x0004),
}