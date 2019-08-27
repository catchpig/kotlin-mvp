package com.catchpig.mvp.manager

import android.app.Activity
import java.util.*

object ActivityManager {
    private var activities = LinkedList<Activity>()
    /**
     * 添加activity
     */
    fun addActivity(activity: Activity){
        activities.add(activity)
    }

    /**
     * 删除activity
     */
    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }

    /**
     * 删除除最上层之外的所有activity
     */
    fun finishAllActivitesExceptTop(){
        var topActivity = activities[activities.size-1]
        var iterator = activities.iterator()
        while (iterator.hasNext()){
            val activity = iterator.next()
            if (activity != topActivity) {
                activity.finish()
                iterator.remove()
            }
        }
    }
}