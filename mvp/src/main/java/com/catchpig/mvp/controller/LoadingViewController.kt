package com.catchpig.mvp.controller

import android.app.Dialog
import android.view.*
import android.widget.FrameLayout
import com.catchpig.mvp.R
import com.catchpig.mvp.base.activity.BaseActivity
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.layout_loading.view.loading_frame

/**
 *
 * @author TLi2
 **/
class LoadingViewController(private val baseActivity: BaseActivity,private val layoutBody:FrameLayout) {
    private var dialog:Dialog? = null
    fun loadingView(){
        layoutBody.loading_frame.visibility = View.VISIBLE
    }

    fun loadingDialog(){
        dialog = Dialog(baseActivity, R.style.loading_dialog_theme)
        dialog?.run {
            setCancelable(false)
            setContentView(R.layout.layout_loading)
            loading_frame.visibility = View.VISIBLE
            ImmersionBar.with(baseActivity,this).transparentBar().init()
            show()
            window?.run {
                var layoutParams = attributes
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                decorView.setPadding(0,0,0,0)
                attributes = layoutParams
            }
        }
    }

    fun hideLoading(){
        dialog?.let {
            it.dismiss()
        }
        layoutBody.loading_frame.visibility = View.GONE
    }
}