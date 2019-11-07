package com.example.modulebase

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import butterknife.ButterKnife
//Activity基类
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flagFullScreen()
        setContentView(getLayoutId())
        initTitle()
        initData()
        initTab()
        AppManager.getInstance().add(this)
    }

    open fun flagFullScreen(){

    }


    open fun initTab(){}

     open fun initData(){}

     open fun initTitle(){}

    abstract fun  getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getInstance().remove(this)
    }
}
