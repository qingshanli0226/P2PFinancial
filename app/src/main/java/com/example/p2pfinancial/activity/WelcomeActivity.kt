package com.example.p2pfinancial.activity

import android.app.AlertDialog
import android.content.DialogInterface
import com.example.base.BaseActivity
import com.example.common.NetConnectManager
import com.example.p2pfinancial.DownloadManager
import com.example.p2pfinancial.R

class WelcomeActivity : BaseActivity(), NetConnectManager.INetConnectListener,
    DownloadManager.INewApkListener {


    override fun setLayout(): Int {
        return R.layout.activity_welcome
    }

    override fun initView() {

    }

    override fun initData() {
        super.initData()

        NetConnectManager.getInstance().registerNetConnectListener(this)
    }

    override fun onConnect() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("检测到新版本")
        builder.setMessage("是否进行版本更新?")
        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                DownloadManager.getInstance().registerNewApkListener(this@WelcomeActivity)
                DownloadManager.getInstance().init(this@WelcomeActivity)
            }
        })

        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })
        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        NetConnectManager.getInstance().unregisterNetConnectListener(this)
        DownloadManager.getInstance().unregisterNewApkListener(this)
    }

    override fun onDisConnect() {

    }

    override fun upApkVersions() {
        println("zjw_ : 版本更新完毕")
    }
}
