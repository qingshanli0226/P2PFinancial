package com.example.p2pdemo.Activity

import android.app.AlertDialog
import android.content.Intent
import android.widget.Toast
import com.example.base.BaseActivity
import com.example.p2pdemo.Bean.UpdateBean
import com.example.p2pdemo.R
import com.example.p2pdemo.Manager.UpdateApkManager

class WeclomeActivity : BaseActivity(), UpdateApkManager.UpdateManagerListener {
    var isLock=false;
    override fun getUpdateApkInfo(updateBean: UpdateBean?) {
        //获取服务版本
        if(updateBean!=null){
            //如果当前版本和服务版本一样不更新
            if (updateBean.getVersion().equals(UpdateApkManager.getInstance().version)) {
                Toast.makeText(this, "当前已近是最新版本啦", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@WeclomeActivity,MainActivity::class.java))
                finish()

            } else {
                if(isLock==false){
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("是否更新! "+updateBean.version)
                    builder.setTitle("有版本")
                    builder.setPositiveButton("确定") { dialog, which ->
                        UpdateApkManager.getInstance().InstallApk()
                        dialog.dismiss()
                        startMainActivity()
                    }
                    builder.setNeutralButton(
                        "不再提示!"
                    ) { dialog, which ->
                        isLock=true
                        dialog.dismiss()
                        startMainActivity()

                    }
                    builder.setNegativeButton(
                        "取消"
                    ) { dialog, which -> dialog.dismiss()
                        startMainActivity()
                    }
                    builder.create().show()
                }else{
                    startActivity(Intent(this@WeclomeActivity,MainActivity::class.java))
                    finish()
                }
            }


        }else{
            Toast.makeText(this,"没有最新服务端版本",Toast.LENGTH_LONG).show()
             startMainActivity()

        }


    }



    override fun InitView() {
        setContentView(R.layout.activity_weclome)
    }

    override fun InitData() {
        UpdateApkManager.getInstance().init(this)

        UpdateApkManager.getInstance().registerUpApkListener(this)

    }

    override fun InitTitle() {
    }

    override fun onDestroy() {
        super.onDestroy()
        UpdateApkManager.getInstance().unregisterUpApkListener()
    }

    private fun startMainActivity(){
        startActivity(Intent(this@WeclomeActivity,MainActivity::class.java))
        finish()
    }
}