package com.catchpig.kotlin_mvp.mvp.child

import android.view.View
import com.catchpig.annotation.OnClickSecondDrawable
import com.catchpig.annotation.OnClickSecondText
import com.catchpig.annotation.StatusBar
import com.catchpig.annotation.Title
import com.catchpig.kotlin_mvp.R
import com.catchpig.mvp.base.activity.BaseActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

@Title(R.string.child_title)
@StatusBar
class ChildActivity : BaseActivity(){
    @OnClickSecondDrawable(R.drawable.more)
    fun clickFirstDrawable(v: View) {
        toast(" 第一个图标按钮点击生效")

    }
    @OnClickSecondText(R.string.more)
    fun clickFirstText(v: View) {
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
