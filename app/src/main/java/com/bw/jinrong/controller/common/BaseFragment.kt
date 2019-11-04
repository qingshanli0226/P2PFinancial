package com.bw.jinrong.controller.common

import android.os.Bundle
import android.support.v4.app.Fragment

class BaseFragment : Fragment() {

    lateinit var loadingPage: LoadingPage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}