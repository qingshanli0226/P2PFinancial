package com.example.p2pdemo.Fragment.MainFragment

import android.view.View
import com.example.base.BaseFragment
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.more.view.*
import kotlinx.android.synthetic.main.morefrag.*
import kotlinx.android.synthetic.main.morefrag.view.*
import java.util.logging.Handler

class MoreFragment: BaseFragment() {



    override fun setView(): Int {
        return R.layout.morefrag
    }

    override fun inItData() {
        val moreTitlebar = baseView.More_TitleBar
        moreTitlebar.setTitleName("更多")

        val handPass = baseView.Hand_Pass

    }

    override fun onConnected() {
    }

    override fun onDisConnected() {
    }


}