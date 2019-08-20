package com.catchpig.kotlin_mvp.mvp.child

import com.catchpig.kotlin_mvp.R
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.base.activity.BaseActivity
@Title("子页面")
class ChildActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_child
    }
}
