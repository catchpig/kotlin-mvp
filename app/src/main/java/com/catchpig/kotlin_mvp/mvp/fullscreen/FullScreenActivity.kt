package com.catchpig.kotlin_mvp.mvp.fullscreen

import com.catchpig.kotlin_mvp.R
import com.catchpig.mvp.annotation.StatusBar
import com.catchpig.mvp.base.activity.BaseActivity

@StatusBar(hide = true)
class FullScreenActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_full_screen
    }

}
