package com.example.day_022

import android.content.ContentValues
import android.graphics.Color
import android.graphics.Color.BLACK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.day_022.Fragment.FragThree
import com.example.day_022.Fragment.FragTwo
import com.example.day_022.Fragment.Fragfour
import com.example.day_022.Fragment.FragmentOne
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listFrag= mutableListOf<Fragment>()

        listFrag.add(FragmentOne())
        listFrag.add(FragTwo())
        listFrag.add(FragThree())
        listFrag.add(Fragfour())


        val adapters=FragAdapter(supportFragmentManager,listFrag)
        MPage.adapter=adapters


        MPage.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when(position){

                    0-> {
                        rb1.setTextColor(Color.YELLOW)
                        rb2.setTextColor(Color.BLACK)
                        rb3.setTextColor(Color.BLACK)
                        rb4.setTextColor(Color.BLACK)
                    }
                    1-> {
                        rb2.setTextColor(Color.YELLOW)
                        rb1.setTextColor(Color.BLACK)
                        rb3.setTextColor(Color.BLACK)
                        rb4.setTextColor(Color.BLACK)
                    }
                    2-> {
                        rb3.setTextColor(Color.YELLOW)
                        rb2.setTextColor(Color.BLACK)
                        rb1.setTextColor(Color.BLACK)
                        rb4.setTextColor(Color.BLACK)
                    }
                    3-> {
                        rb4.setTextColor(Color.YELLOW)
                        rb2.setTextColor(Color.BLACK)
                        rb3.setTextColor(Color.BLACK)
                        rb1.setTextColor(Color.BLACK)
                    }

                }
            }
        })


        val myServer = MyServer(this)
        val writableDatabase = myServer.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("name","张三")
        contentValues.put("age",20)
        writableDatabase.insert("User",null,contentValues)


        writableDatabase.delete("User","name=?", arrayOf("张三"))

        val contentValues1 = ContentValues()
        contentValues1.put("name","无敌")
        writableDatabase.update("User",contentValues1,"name=?", arrayOf("武1"))


        val rawQuery = writableDatabase.rawQuery("select * from User", null)
        while (rawQuery.moveToNext()){
            val ints = rawQuery.getInt(rawQuery.getColumnIndex("_id"))
            val string = rawQuery.getString(rawQuery.getColumnIndex("name"))
            val age = rawQuery.getInt(rawQuery.getColumnIndex("age"))

            val s = ints.toString() + "-" + string + "-" + age.toString()
            Log.e("##########",s)
        }


        val rawQuery1 = writableDatabase.rawQuery("select * from User where name like '无%'", null)
        while (rawQuery1.moveToNext()){
            val ints = rawQuery1.getInt(rawQuery1.getColumnIndex("_id"))
            val string = rawQuery1.getString(rawQuery1.getColumnIndex("name"))
            val age = rawQuery1.getInt(rawQuery1.getColumnIndex("age"))

            val s = ints.toString() + "-" + string + "-" + age.toString()
            Log.e("##########",s)
        }

        rg1.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId){
                R.id.rb1->MPage.currentItem=0
                R.id.rb2->MPage.currentItem=1
                R.id.rb3->MPage.currentItem=2
                R.id.rb4->MPage.currentItem=3

            }
        }
    }
}


