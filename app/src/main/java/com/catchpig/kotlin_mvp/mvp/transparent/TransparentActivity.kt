package com.catchpig.kotlin_mvp.mvp.transparent

import com.catchpig.kotlin_mvp.R
import com.catchpig.annotation.StatusBar
import com.catchpig.mvp.base.activity.BaseActivity

@StatusBar(transparent = true)
class TransparentActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_transparent
    }
}
