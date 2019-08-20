package com.catchpig.mvp.base.activity

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.catchpig.mvp.R
import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.controller.TitleBarController
import com.catchpig.mvp.ext.getColorPrimary
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.view_root.*

/**
 * 创建时间:2019/4/4 00:09<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/4 00:09<br></br>
 * 描述:
 */
abstract class BaseActivity : AppCompatActivity(), BaseContract.View {
    override fun getBaseActivity(): BaseActivity? {
        return this
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_root)
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
    }


    override fun setContentView(view: View?) {
        layout_body?.let {
            it.addView(view, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
        }
    }

    override fun setContentView(layoutResID: Int) {
        setContentView(View.inflate(this,layoutResID,null))
    }


    protected abstract fun layoutId(): Int

}
