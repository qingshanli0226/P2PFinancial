package com.example.sixp2p.Fragment.MainFragment

import android.view.View
import com.example.base.BaseFragment
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.my_assets_fragment.view.*

class MyAssetsFragment: BaseFragment() {



    override fun setView(): Int {
        return R.layout.my_assets_fragment
    }


    override fun inItData() {
        val assTitlebar = baseView.MyAss_TitleBar
        assTitlebar.setTitleName("我的资产")
        assTitlebar.setImgLeftShow(R.drawable.left)
        assTitlebar.setImgRightShow(R.drawable.my_setting_icon)

    }

    override fun onConnected() {
    }

    override fun onDisConnected() {
    }

}