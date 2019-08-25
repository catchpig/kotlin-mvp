package com.catchpig.kotlin_mvp.mvp.recycle

import android.os.Bundle
import com.catchpig.kotlin_mvp.R
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.widget.refresh.OnRefreshListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_recycle.*
import java.util.concurrent.TimeUnit

class RecycleActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_recycle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refresh.setOnRefreshLoadMoreListener(object :OnRefreshListener(){
            override fun update(refreshLayout: RefreshLayout) {
                Flowable.timer(10,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe{
                    refresh.finishRefresh()
                }
            }
        })
        refresh.autoRefresh()
    }
}
