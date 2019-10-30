package com.catchpig.kotlin_mvp.mvp.recycle

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.catchpig.annotation.TimeLog
import com.catchpig.kotlin_mvp.R
import com.catchpig.mvp.base.activity.BaseActivity
import com.catchpig.mvp.widget.refresh.OnRefreshListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subscribers.ResourceSubscriber
import kotlinx.android.synthetic.main.activity_recycle.*
import kotlinx.android.synthetic.main.layout_header.view.*
import luyao.util.ktx.ext.logd
import luyao.util.ktx.ext.longToast
import java.util.concurrent.TimeUnit

class RecycleActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_recycle
    }

    @TimeLog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var userAdapter = UserAdapter(refresh)
        userAdapter.onItemClickListener { id, m, position ->
            "dada".logd("adsd")
        }
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycle_view.layoutManager = linearLayoutManager
        recycle_view.adapter = userAdapter
        userAdapter.addHeaderView(R.layout.layout_header)
        userAdapter.headerView {
            header_name.text = "我是头部"
        }
        UserSharedPrefs.setName("niha")
        longToast(UserSharedPrefs.getName()!!)
        refresh.setOnRefreshLoadMoreListener(object :OnRefreshListener(){
            override fun update(refreshLayout: RefreshLayout) {
                Flowable.timer(3,TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object:ResourceSubscriber<Long>(){
                            override fun onComplete() {
                                "onComplete".logd()
                            }

                            override fun onNext(t: Long?) {
                                "onNext".logd()
                                var data:MutableList<User> = ArrayList()
                                var count = if (refresh.nextPageIndex==3) {
                                    15
                                }else{
                                    16
                                }
                                for (i in 1..count){
                                    data.add(User("姓名$i"))
                                }
                                userAdapter.autoUpdateList(data)
                            }

                            override fun onError(t: Throwable?) {
                                "onError".logd()
                                refresh.updateError()
                            }
                        })
            }
        })
        refresh.autoRefresh()
    }
}
