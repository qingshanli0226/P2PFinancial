package com.example.day_022.Fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.RadioButton
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.day_022.MainActivity
import com.example.day_022.MyBraodcast
import com.example.day_022.R
import kotlinx.android.synthetic.main.onef.view.*

class FragmentOne:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.onef, container, false);


        val helos = view.helo
//        val valueAnimator = ValueAnimator.ofFloat(0f, 360f)
//        valueAnimator.duration = 1500
//        valueAnimator.start()


        val ofFloat = ValueAnimator.ofFloat(R.drawable.ss.toFloat())
        ofFloat.setTarget(helos)
        ofFloat.duration=1500
        ofFloat.start()



        val back = view.GoBack


        //返回
        var count:Int=0
        back.setOnClickListener {
            ++count
            val intent1 = Intent("cast")
            intent1.putExtra("cast","22")
            startActivity(intent1)

//            activity!!.sendBroadcast(intent1)

            //消除activity
            if(count>=2){
                activity!!.finish()
                System.exit(0)
            }
        }




        return view;
    }
}


