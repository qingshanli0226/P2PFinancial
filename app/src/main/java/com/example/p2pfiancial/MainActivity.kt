package com.example.p2pfiancial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.p2pfiancial.common.BottomBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //未选中的图标
        mBottomBar.setUnselectedIcon(
            R.drawable.bottom01,
            R.drawable.bottom03,
            R.drawable.bottom05,
            R.drawable.bottom07
        )
        //选中的图标
        mBottomBar.setSelectedIcon(
            R.drawable.bottom02,
            R.drawable.bottom04,
            R.drawable.bottom06,
            R.drawable.bottom08
        )
        //标题
        mBottomBar.setTitle("首页", "投资", "我的资产", "更多")
        mBottomBar.show()

        mBottomBar.setOnCtlSelectListener(object : BottomBar.OnCtlTabSelectListener {
            override fun onTabReselect(position: Int) {

            }

            override fun onTabSelect(position: Int) {

            }
        })
    }
}
