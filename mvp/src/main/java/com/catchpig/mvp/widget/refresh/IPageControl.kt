package com.catchpig.mvp.widget.refresh

import com.scwang.smart.refresh.layout.constant.RefreshState

/**
 * 创建时间:2017/12/21  19:49<br></br>
 * 创建人: 廖斌<br></br>
 * 修改人: 廖斌<br></br>
 * 修改时间: 2017/12/21  19:49<br></br>
 * 描述: 页码关联
 */
interface IPageControl {
    /**
     * 每页的数据量
     */
    var pageSize:Int
    /**
     * 下一页的页码
     */
    var nextPageIndex:Int

    /**
     * 当前的刷新状态
     */
    fun getRefreshStatus():RefreshState

    /**
     * 重置当前的页码
     */
    fun resetPageIndex()

    /**
     * 加载下一页的页码
     */
    fun loadNextPageIndex()

    /**
     * 更新数据成功
     */
    fun updateSuccess(list: MutableList<*>?)

    /**
     * a更新数据失败
     */
    fun updateError()
}
