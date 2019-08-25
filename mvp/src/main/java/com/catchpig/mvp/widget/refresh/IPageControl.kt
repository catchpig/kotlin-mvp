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

    var nextPageIndex:Int

    var refreshStatus:RefreshState

    fun resetPageIndex()

    fun loadNextPageIndex()

    fun updateSuccess(list: List<*>?)

    fun updateError()
}
