package com.bw.common

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.Toast

class UIUtils {

    fun getContext() : Context{
        return MyApplication.context
    }

    fun getHandler() : Handler{
        return MyApplication.handler
    }

    //返回指定colorId对应的颜色值
    fun getColor(colorId:Int) : Int{
        return getContext().resources.getColor(colorId)
    }

    //加载指定viewId的视图对象，并返回
    open fun getView(viewId:Int) : View{
        var view:View = View.inflate(getContext(),viewId,null)
        return view
    }

    fun getStringArr(strArrId:Int): Array<String> {
        var stringArray = getContext().resources.getStringArray(strArrId)
        return stringArray
    }

    //将dp转化为px
    fun dp2px(dp:Int) : Int{
        //获取手机密度
        var density = getContext().resources.displayMetrics.density
        return (dp * density + 0.5).toInt()
    }

    fun px2dp(px:Int) : Int{
        //获取手机密度
        var density = getContext().resources.displayMetrics.density
        return (px / density + 0.5).toInt()
    }

    //保证runnable中的操作在主线程中执行
    fun runOnUiThread(runnable: Runnable){
        if (isInMainThread()){
            runnable.run()
        }else{
            UIUtils().getHandler().post(runnable)
        }
    }

    //判断当前线程是否是主线程
    fun isInMainThread() : Boolean{
        var currentThreadId = android.os.Process.myTid()
        return MyApplication.mainThreadId == currentThreadId
    }

    fun toast(message:String,isLengthLong:Boolean){
        Toast.makeText(UIUtils().getContext(), message, if (isLengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
//        Toast.makeText(UIUtils().getContext(),message,isLengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

}