package com.example.p2pfinancial

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.net.Constant
import com.example.net.RetrofitCreator
import com.example.p2pfinancial.bean.MainBean
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class CacheService : Service() {


    override fun onBind(intent: Intent): IBinder {
        return CacheBind()
    }

    class CacheBind : Binder() {
        fun getService(): CacheService {
            return CacheService()
        }
    }

    var iDataInterface: IDataInterface? = null

    interface IDataInterface {
        fun onDataReceived(bean: MainBean)
    }

    fun registerListener(iDataInterface: IDataInterface) {
        this.iDataInterface = iDataInterface
    }

    fun unregisterListener() {
        this.iDataInterface = null;
    }

    fun getData() {
        RetrofitCreator.getNetApiService(Constant.BASE_URL)
            .getBannerImg(null, "/index", null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody> {
                override fun onComplete() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSubscribe(d: Disposable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: ResponseBody) {
                    iDataInterface?.onDataReceived(t as MainBean);
                }

                override fun onError(e: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }

    fun doHttpClient() {
        RetrofitCreator.getNetApiService(Constant.BASE_URL).getData(null, "/index", null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: ResponseBody) {
                    val fromJson = Gson().fromJson<MainBean>(t.string(), MainBean::class.java)
                    println("zjw_ : ${fromJson.toString()}")
                }

                override fun onError(e: Throwable) {

                }
            })
    }
}
