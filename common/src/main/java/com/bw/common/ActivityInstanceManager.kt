package com.bw.common

import android.app.Activity

class ActivityInstanceManager {

    //为什么使用MutableList因为进行频繁添加删除操作
    var activity_list:MutableList<Activity> = mutableListOf()

    //添加Activity
    fun addActivity(instance:Activity){
        activity_list.add(instance)
    }

    //删除Activity
    fun removeActivity(instance: Activity){
        if (activity_list.contains(instance)){
            activity_list.remove(instance)
        }
    }

    //关闭应用中所有的打开的Activity
    fun finishAllActivity(){
        for (item in activity_list){
            item.finish()
        }
    }

}