package com.catchpig.mvp.widget.refresh

import com.catchpig.mvp.widget.refresh.RefreshLayoutWrapper
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * 创建时间:2017/12/20 18:13<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2017/12/20 18:13<br></br>
 * 描述:下拉刷新和上拉加载更多的封装类
 */

abstract class OnRefreshListener : OnRefreshLoadMoreListener {
    /**
     * 下拉刷新
     */
    override fun onRefresh(refreshlayout: RefreshLayout) {
        val refreshLayoutWrapper = refreshlayout as RefreshLayoutWrapper
        //将页码变为1
        refreshLayoutWrapper.resetPageIndex()
        refreshlayout.setEnableLoadMore(false)
        update(refreshLayoutWrapper)
    }

    /**
     * 上拉加载更多
     */
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        val refreshLayoutWrapper = refreshLayout as RefreshLayoutWrapper
        //请求列表的参数页码加1
        refreshLayoutWrapper.loadNextPageIndex()
        refreshLayout.setEnableRefresh(false)
        update(refreshLayoutWrapper)
    }

    /**
     * 下拉刷新和上拉加载更多的回调接口
     */
    abstract fun update(refreshLayout: RefreshLayout)
}