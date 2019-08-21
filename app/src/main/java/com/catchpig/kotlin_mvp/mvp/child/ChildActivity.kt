package com.catchpig.kotlin_mvp.mvp.child

import android.annotation.SuppressLint
import android.view.View
import com.catchpig.kotlin_mvp.R
import com.catchpig.mvp.annotation.Title
import com.catchpig.mvp.annotation.TitleMenu
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.listener.OnMenuFirstDrawableClickListener
import com.catchpig.mvp.listener.OnMenuFirstTextClickListener
import com.catchpig.mvp.listener.OnMenuSecondDrawableClickListener
import com.catchpig.mvp.listener.OnMenuSecondTextClickListener
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import luyao.util.ktx.ext.toast
import java.util.concurrent.TimeUnit

@Title(R.string.child_title)
@TitleMenu(rightFirstText = R.string.more,rightFirstDrawable = R.drawable.more)
class ChildActivity : BaseActivity(),
        OnMenuFirstTextClickListener,
        OnMenuFirstDrawableClickListener,
        OnMenuSecondTextClickListener,
        OnMenuSecondDrawableClickListener {
    override fun clickSecondText(v: View) {
        toast("第二个文字按钮点击生效")
    }

    override fun clickSecondDrawable(v: View) {
        toast("第二个图标按钮点击生效")
    }

    override fun clickFirstDrawable(v: View) {
        toast(" 第一个图标按钮点击生效")

    }

    override fun clickFirstText(v: View) {
        toast("第一个文字按钮点击生效")

    }

    /**
     * dialog形式的loading
     */
    fun loadingDialog(v: View){
        loadingView(true)
        Flowable.timer(5,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
            hideLoadingView()
        }
    }

    /**
     * 标题栏以下的loading
     */
    fun loadingExtTitle(v: View){
        loadingView(false)
        Flowable.timer(5,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
            hideLoadingView()
        }
    }
    override fun layoutId(): Int {
        return R.layout.activity_child
    }
}
