package com.bw.jinrong.controller.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

class LoadingPage : Fragment() {

    //定义四种不同的显示状态
    var STATE_LOADING:Int = 1
    var STATE_ERROR:Int = 2
    var STATE_EMPTY:Int = 3
    var STATE_SUCCESS:Int = 4

    //默认情况下，当前状态为正在加载
    var state_current:Int =STATE_LOADING

    //提供四种不同的界面
    lateinit var view_loading:View
    lateinit var view_error:View
    lateinit var view_empty:View
    lateinit var view_success:View
//    lateinit var params:Layoutpar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}