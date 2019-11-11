package com.example.p2pfiancial.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.p2pfiancial.R
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener


class BottomBar @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(mContext, attrs, defStyleAttr) {
    private val mCtl: CommonTabLayout?
    private var unSelectedIcon: IntArray? = null
    private var selectedIcon: IntArray? = null
    private var title: Array<out String>? = null
    private var arrayList: ArrayList<CustomTabEntity> = ArrayList()

    init {
        val bottomBar: View? =
            LayoutInflater.from(mContext).inflate(R.layout.bottombar, this)
        mCtl = bottomBar!!.findViewById<CommonTabLayout>(R.id.mCtl_bottomBar)
    }

    /**
     * 自定义属性
     */
    fun getCustomBottomBar(): CommonTabLayout? {
        return mCtl
    }

    /**
     * 未选中的图片
     */
    fun setUnselectedIcon(vararg args: Int) {
        this.unSelectedIcon = args
    }

    /**
     * 选中的图片
     */
    fun setSelectedIcon(vararg args: Int) {
        this.selectedIcon = args
    }

    /**
     * 标题
     */
    fun setTitle(vararg args: String) {
        this.title = args
    }

    //显示
    fun show() {
        if (selectedIcon != null) {
            for (i in 0 until selectedIcon!!.size) {
                arrayList.add(TabEntity(unSelectedIcon!![i], selectedIcon!![i], this.title!![i]))
            }

            mCtl?.setTabData(arrayList)
        }
    }

    //设置选择监听
    fun setOnCtlSelectListener(listener: OnCtlTabSelectListener) {
        mCtl!!.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {
                listener.onTabReselect(position)
            }

            override fun onTabSelect(position: Int) {
                listener.onTabSelect(position)
            }
        })
    }

    /**
     * 点击监听
     */
    interface OnCtlTabSelectListener {
        fun onTabReselect(position: Int)
        fun onTabSelect(position: Int)
    }


    /**
     * 用于吐司
     */
    private fun toast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    inner class TabEntity(private val unSelectedIcon: Int, private val selectedIcon: Int, private val title: String) :
        CustomTabEntity {
        override fun getTabUnselectedIcon(): Int {
            return unSelectedIcon
        }

        override fun getTabSelectedIcon(): Int {
            return selectedIcon
        }

        override fun getTabTitle(): String {
            return title
        }
    }
}