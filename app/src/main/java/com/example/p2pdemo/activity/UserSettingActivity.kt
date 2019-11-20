package com.example.p2pdemo.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Path
import android.provider.MediaStore
import android.view.View
import com.example.modulebase.BaseActivity
import com.example.modulecommon.Constructor
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.activity_usersetting.*
import kotlinx.android.synthetic.main.common_title.*

class UserSettingActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_usersetting
    }

    override fun initTitle() {
        tv_title.setText(R.string.tab_text3)
        iv_title_setting.visibility = View.INVISIBLE
        iv_title_black.visibility = View.VISIBLE
    }

    override fun initData() {
        //更换头像
        setting_tv_change.setOnClickListener {
            var items:Array<String> = arrayOf("图库","相机")
            //弹出对话框进行选择
            AlertDialog.Builder(this)
                .setTitle("选择来源")
                .setItems(items,object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        when(which){
                            //图库
                            0 -> startActivityForResult(Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI),Constructor.RESULT_PICTURE_CODE)
                            1 -> startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE),Constructor.RESULT_CAMERA_CODE)
                        }
                    }

                })
                .setCancelable(true)
                .show()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //相机
        if (requestCode == Constructor.RESULT_CAMERA_CODE && resultCode == RESULT_OK && data != null){
            var bitmap:Bitmap = data.extras!!.get("data") as Bitmap
            setting_iv_icon.setImageBitmap(bitmap)

            //保存到本地(未实现)

            //相册
        }else if (requestCode == Constructor.RESULT_PICTURE_CODE && resultCode == RESULT_OK && data != null){
            var uri = data.data

        }

    }
}