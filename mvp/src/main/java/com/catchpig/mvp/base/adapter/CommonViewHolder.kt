package com.catchpig.mvp.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 * 创建时间:2019/7/21 0021<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/7/21 0021<br/>
 * 描述:
 */
class CommonViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView),LayoutContainer {
    override val containerView = itemView
}