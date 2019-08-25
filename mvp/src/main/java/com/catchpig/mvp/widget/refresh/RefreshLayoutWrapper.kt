package com.catchpig.mvp.widget.refresh

import android.content.Context
import android.util.AttributeSet
import com.scwang.smart.refresh.layout.SmartRefreshLayout


/**
 * 创建时间:2017/12/20 16:44<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 廖斌<br></br>
 * 修改时间: 2017/12/21 20:10<br></br>
 * 描述: 刷新界面
 */

class RefreshLayoutWrapper(
    context: Context,
    attrs: AttributeSet
) : SmartRefreshLayout(context, attrs), IPageControl {
    companion object {

        /**
         * 预显示界面索引
         */
         const val NONE_PRE_PAGE_INDEX = -1
        /**
         * 未刷新状态
         */
         const val REFRESH_NORMAL = 0
        /**
         * 正在加载状态
         */
         const val REFRESH_LOADING = 1
        /**
         * 正在刷新状态
         */
         const val REFRESH_REFRESHING = 2
    }
    /**
     * 当前页面index
     */
    private var currentPageIndex: Int = 1

    override var nextPageIndex: Int = NONE_PRE_PAGE_INDEX

    /**
     * 一页的条目，默认16
     */
    private var pageSize = 16
    override var refreshStates: Int = NONE_PRE_PAGE_INDEX
        get() {
            if (isRefreshing) {
                return REFRESH_REFRESHING
            } else if (isLoading) {
                return REFRESH_LOADING
            }
            return REFRESH_NORMAL
        }

    /**
     * 设置单页加载数据数
     *
     * @param pageSize 单页数据项
     */
    fun setPageSize(pageSize: Int) {
        this.pageSize = pageSize
    }

    /**
     * 列表更新成功
     *
     * @param list 更新数据集合
     */
    override fun updateSuccess(list: List<*>?) {
        if (isRefreshing) {
            setEnableLoadMore(true)
            finishLoadMore(false)
        } else if (isLoading) {
            setEnableRefresh(true)

            if (list == null || list.size < pageSize) {
                finishLoadMore(true)
            }
        }
        updateCurrentPageIndex()
        finishUpdate(true)
    }

    /**
     * 列表更新失败
     */
    override fun updateError() {
        this.nextPageIndex = -1
        finishUpdate(false)
    }

    override fun resetPageIndex() {
        this.nextPageIndex = 1
    }

    override fun loadNextPageIndex() {
        this.nextPageIndex = this.currentPageIndex + 1
    }

    /**
     * 结束刷新或加载状态
     *
     * @param success 状态成功或失败
     */
    private fun finishUpdate(success: Boolean) {
        if (isRefreshing) {
            finishRefresh(success)
        } else if (isLoading) {
            finishLoadMore(success)
        }
    }

    /**
     * 当前页面索引更新
     */
    private fun updateCurrentPageIndex() {
        this.currentPageIndex = this.nextPageIndex
        this.nextPageIndex = NONE_PRE_PAGE_INDEX
    }


}
