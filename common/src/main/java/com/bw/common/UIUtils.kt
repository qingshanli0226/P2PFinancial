package com.bw.common

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.Toast

class UIUtils(ctx:Context,handler: Handler) {

    companion object{
        //在整个应用执行过程中，需要提供的变量
        //需要使用的上下文对象，application实例

        var context:Context? = null
        var handler: Handler? = null
    }

    init {
        context = ctx
//        handler = handler
    }

    fun getContext() : Context? {
        return context
    }

    fun getHandler() : Handler? {
        return handler
    }

    //返回指定colorId对应的颜色值
    fun getColor(colorId:Int) : Int? {
        return getContext()?.resources?.getColor(colorId)
    }

    //加载指定viewId的视图对象，并返回
    open fun getView(viewId:Int) : View{
        var view:View = View.inflate(getContext(),viewId,null)
        return view
    }

    fun getStringArr(strArrId:Int): Array<String>? {
        var stringArray = getContext()?.resources?.getStringArray(strArrId)
        return stringArray
    }

    //将dp转化为px
    fun dp2px(dp:Int) : Int{
        //获取手机密度
        var density = getContext()?.resources?.displayMetrics?.density
        return (dp * density!! + 0.5).toInt()
    }

    fun px2dp(px:Int) : Int{
        //获取手机密度
        var density = getContext()?.resources?.displayMetrics?.density
        return (px / density!! + 0.5).toInt()
    }

    //保证runnable中的操作在主线程中执行
//    fun runOnUiThread(runnable: Runnable){
//        if (isInMainThread()){
//            runnable.run()
//        }else{
//            UIUtils().getHandler()?.post(runnable)
//        }
//    }

    //判断当前线程是否是主线程
//    fun isInMainThread() : Boolean{
//        var currentThreadId = android.os.Process.myTid()
//        return mainThreadId == currentThreadId
//    }

//    fun toast(message:String,isLengthLong:Boolean){
//        Toast.makeText(UIUtils().getContext(), message, if (isLengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
////        Toast.makeText(UIUtils().getContext(),message,isLengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
//    }

}