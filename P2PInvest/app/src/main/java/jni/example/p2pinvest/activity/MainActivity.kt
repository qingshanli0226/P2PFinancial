package jni.example.p2pinvest.activity

import androidx.fragment.app.Fragment

import android.util.Log
import jni.example.lib_core.base.BaseActivity
import jni.example.lib_core.mvp.presenter.IPresenter
import jni.example.p2pinvest.R

import java.util.ArrayList

import jni.example.p2pinvest.fragment.Fragment_Asset
import jni.example.p2pinvest.fragment.Fragment_Home
import jni.example.p2pinvest.fragment.Fragment_Invest
import jni.example.p2pinvest.fragment.Fragment_more
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<IPresenter>() {
    var list = ArrayList<Fragment>()
    private var currentFragment: Fragment? = null

    override fun showDialog() {

    }

    override fun hidedialog() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        list.add(Fragment_Home())
        list.add(Fragment_Invest())
        list.add(Fragment_Asset())
        list.add(Fragment_more())
        supportFragmentManager.beginTransaction().add(R.id.frame,list.get(0)).commit()
        currentFragment = list.get(0)
        main_myview.setListener {index: Int ->
            Log.d("lhf","$index")
            switchFragment(index)
        }
    }

    override fun initData() {

    }

    override fun setWindow() {

    }


    private fun switchFragment(tabIndex: Int) {
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val fragment = list[tabIndex]


        //判断当前显示的fragment和要切换的Fragment是否是一个，如果不是的话
        if (currentFragment !== fragment) {
            fragmentTransaction.hide(currentFragment!!) //肯定不为空，因为之前已经设置了默认值

            //如果要切换的fragment没有添加到系统中，首先添加到系统中
            if (!fragment.isAdded) {
                fragmentTransaction.add(R.id.frame, fragment).commit()
            } else {
                //如果之前已经提交过，则直接显示
                fragmentTransaction.show(fragment).commit()
            }
            currentFragment = fragment
        }
    }
}
