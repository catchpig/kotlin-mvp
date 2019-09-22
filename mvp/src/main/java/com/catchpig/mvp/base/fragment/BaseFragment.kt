package com.catchpig.mvp.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.base.activity.BaseActivity
import luyao.util.ktx.ext.longToast

/**
 * 创建时间:2019/4/4 23:14<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/4 23:14<br></br>
 * 描述:
 */
abstract class BaseFragment : Fragment(), BaseContract.View {
    override fun baseActivity(): BaseActivity? {
        if (activity is BaseActivity) {
            return activity as BaseActivity
        }
        return null
    }

    override fun activity(): Activity {
        return activity!!
    }

    override fun applicationContext(): Context {
        return activity().applicationContext
    }

    @CallSuper
    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    @LayoutRes
    protected abstract fun layoutId():Int

    override fun loadingView(isDialog: Boolean) {
        baseActivity()?.let {
            it.loadingView(isDialog)
        }
    }

    override fun hideLoadingView() {
        baseActivity()?.let {
            it.hideLoadingView()
        }
    }

    override fun closeActivity() {
        activity?.let {
            it.finish()
        }
    }

    override fun toast(text: String, isLong: Boolean) {
        baseActivity()?.let {
            it.toast(text,isLong)
        }
    }
}
