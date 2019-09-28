package com.catchpig.mvp.base.adapter

/**
 * 创建时间:2017/12/21  19:49<br></br>
 * 创建人: 廖斌<br></br>
 * 修改人: 廖斌<br></br>
 * 修改时间: 2017/12/21  19:49<br></br>
 * 描述: 数据填充接口
 */
interface IAdapterListControl<T> {

    fun set(list: MutableList<T>?)

    fun add(list: MutableList<T>?)
    /**
     * 自动更新数据
     */
    fun autoUpdateList(list: MutableList<T>?)

    operator fun get(index: Int): T?
    /**
     * 更新失败
     */
    fun updateFailed()
}
