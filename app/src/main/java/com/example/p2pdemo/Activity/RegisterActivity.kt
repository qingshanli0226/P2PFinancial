package com.example.p2pdemo.Activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.base.BaseActivity
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity:BaseActivity() {
    override fun InitView() {
        setContentView(R.layout.activity_register)
    }

    override fun InitData() {

        Register_TitleBar.setTitleName(resources.getString(R.string.register))
        Register_TitleBar.setImgLeftShow(R.drawable.left)

    }

    override fun InitTitle() {

        Register_register.setOnClickListener {
            val phone = Register_phone.text.toString().trim()
            val userName = Register_userName.text.toString().trim()
            val pass = Register_pass.text.toString().trim()
            val surePass = Register_surePass.text.toString().trim()

            if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(userName)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(surePass)){
                Toast.makeText(this,"信息不能为空",Toast.LENGTH_SHORT).show()
            }else if(!pass.equals(surePass)){
                Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show()
                Register_pass.setText("")
                Register_surePass.setText("")
            }else{

            }


        }
    }


}