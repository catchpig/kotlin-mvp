package com.catchpig.mvp.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.catchpig.mvp.R
import com.catchpig.mvp.widget.refresh.IPageControl
import com.scwang.smart.refresh.layout.constant.RefreshState
import java.util.*


/**
 * 创建时间:2017/12/21  19:45<br></br>
 * 创建人: 廖斌<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2017年12月22日13:43:56<br></br>
 * 描述: RecyclerViewAdapter基类
 */

abstract class RecyclerAdapter<M>: RecyclerView.Adapter<CommonViewHolder>,IAdapterListControl<M> {
    companion object {
        /**
         * 头部类型
         */
        const val TYPE_HEADER = -1
        /**
         * 底部类型
         */
        const val TYPE_FOOTER = -2
        /**
         * 无数据类型
         */
        const val TYPE_EMPTY = -3
        /**
         * 正常的item
         */
        const val TYPE_NORMAL = 0
    }

    var mData: MutableList<M> = ArrayList()
    /**
     * 头部
     */
    var headerView: View? = null

    /**
     * 底部
     */
    var footerView: View? = null
    /**
     * 是否展示空页面
     */
    private var showEmpty: Boolean = false
    /**
     * 空页面layout
     */
    private var emptyLayout = R.layout.view_load_empty
    /**
     * 是否是第一次加载数据
     */
    private var firstLoad = true
    var mListener: OnItemClickListener<M>? = null

    private var pageControl: IPageControl? = null
    constructor():this(null)
    constructor(pageControl: IPageControl?) {
        this.pageControl = pageControl
    }

    override fun set(list: MutableList<M>?) {
        firstLoad = false
        if (list != null) {
            mData = list
            notifyDataSetChanged()
        }else{
            clear()
        }
    }
    override fun getItemCount(): Int {
        var size = if (mData == null) 0 else mData.size
        //有头部,item的个数+1
        if (headerView != null) {
            size++
        }
        //有底部,item的个数+1
        if (footerView != null) {
            size++
        }
        if (size == 0) {
            showEmpty = true
            size = 1
        } else {
            showEmpty = false
        }
        return size
    }



    protected lateinit var mRecyclerView: RecyclerView

    /**
     * 设置空页面
     */
    fun setEmptyLayout(@LayoutRes emptyLayout: Int) {
        this.emptyLayout = emptyLayout
    }

    private fun isNull(list: List<M>?): Boolean {
        return list == null
    }

    override fun get(position: Int): M? {
        if (isNull(mData)) {
            return null
        }
        if (position < 0 || position > mData.size - 1) {
            throw IllegalStateException("position必须大于0,且不能大于mData的个数")
        }
        return mData[position]
    }

    /**
     * 清空数据
     */
    fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }

    /**
     * list中添加更多的数据
     */
    override fun add(list: MutableList<M>?) {
        mData?.let {
            list?.let {
                it.addAll(it)
            }
            notifyDataSetChanged()
        }
    }

    override fun autoUpdateList(list: MutableList<M>?) {
        pageControl?.let {
            it.updateSuccess(list)
            when (it.getRefreshStatus()) {
                RefreshState.Refreshing -> {
                    set(list)
                }
                RefreshState.Loading -> {
                    add(list)
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0 && showEmpty) {
            //当前数据空位,展示空页面
            return TYPE_EMPTY
        }
        if (position == 0 && headerView != null) {
            //当前view是头部信息
            return TYPE_HEADER
        }
        return if (position == itemCount && footerView != null) {
            //当前view是底部信息
            TYPE_FOOTER
        } else getCenterViewType(position)

    }

    /**
     * 标准的item的类型
     *
     * @return 返回参数不能小于0
     */
    @IntRange(from = 0)
    fun getCenterViewType(position: Int): Int {
        return TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        //加载头部信息
        if (TYPE_HEADER == viewType) {
            headerView?.let {
                return CommonViewHolder(it)
            }

        }
        //加载底部信息
        if (TYPE_FOOTER == viewType) {
            footerView?.let {
                return CommonViewHolder(it)
            }

        }
        //加载空页面
        if (TYPE_EMPTY == viewType) {
            val v = inflate(emptyLayout, parent)
            return CommonViewHolder(v)
        }
        return CommonViewHolder(inflate(layoutId(), parent))
    }

    protected abstract fun layoutId(): Int

    /**
     * 获取需要viewHolder的view
     *
     * @param layoutId 布局文件
     */
    fun inflate(layoutId: Int, group: ViewGroup): View {
        val inflater = LayoutInflater.from(group.context)
        return inflater.inflate(layoutId, group, false)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        var index = position
        if (headerView != null) {
            //当前holder是头部就直接返回,不需要去设置viewholder的内容
            if (getItemViewType(position) == TYPE_HEADER) {
                return
            } else {
                /*
                 * 有头部的情况,需要要减1,否则取item的数据会取到当前数据的下一条,
                 * 取出最后一条数据的时候,会报下标溢出
                 */
                index--
            }
        }
        if (footerView != null) {
            //当前holder是底部就直接返回,不需要去设置viewholder的内容
            if (getItemViewType(position) == TYPE_FOOTER) {
                return
            }
        }
        //空页面状态,不需要设置holder的内容
        if (getItemViewType(position) == TYPE_EMPTY) {
            //第一次加载数据,不展示空页面
            if (firstLoad) {
                holder.itemView.visibility = View.INVISIBLE
            } else {
                holder.itemView.visibility = View.VISIBLE
            }
            return
        }
        val finalIndex = index
        val m = mData!![index]
        //设置item的点击回调事件
        holder.itemView.setOnClickListener(View.OnClickListener {
            mListener?.let {
                it.itemClick(mRecyclerView.id, m, finalIndex)
            }
        })
        bindViewHolder(holder, m, position)
    }

    /**
     * 绑定viewHolder的数据
     */
    abstract fun bindViewHolder(holder: CommonViewHolder, m: M, position: Int)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val gridManager = manager
            gridManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER)
                        gridManager.spanCount
                    else
                        1
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: CommonViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp = holder.itemView.layoutParams
        if (lp != null
            && lp is StaggeredGridLayoutManager.LayoutParams
            && holder.layoutPosition === 0
        ) {
            lp.isFullSpan = true
        }
    }

    /**
     * item点击事件
     */
    interface OnItemClickListener<M> {
        /**
         * @param id RecyclerView.getId()
         * @param m item下的实体
         * @param position item所在的位置
         */
        fun itemClick(@IdRes id: Int, m: M, position: Int)
    }


}
