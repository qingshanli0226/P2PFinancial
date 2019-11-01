package com.bw.jinrong.controller.common

import android.os.Bundle
import android.support.v4.app.Fragment
import com.bw.jinrong.controller.ui.LoadingPage

class BaseFragment : Fragment() {

    lateinit var loadingPage: LoadingPage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}