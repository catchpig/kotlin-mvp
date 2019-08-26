package com.catchpig.kotlin_mvp.mvp.recycle

import com.catchpig.kotlin_mvp.R
import com.catchpig.mvp.base.adapter.CommonViewHolder
import com.catchpig.mvp.base.adapter.RecyclerAdapter
import com.catchpig.mvp.widget.refresh.IPageControl
import kotlinx.android.synthetic.main.item_user.view.*

/**
 *
 * @author TLi2
 **/
class UserAdapter(iPageControl: IPageControl):RecyclerAdapter<User>(iPageControl) {
    override fun layoutId(): Int {
        return R.layout.item_user
    }

    override fun bindViewHolder(holder: CommonViewHolder, m: User, position: Int) {
        holder.itemView.name.text = m.name
    }
}