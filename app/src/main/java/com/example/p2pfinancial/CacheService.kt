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

    inner class CacheBind : Binder() {
        fun getService(): CacheService {
            return this@CacheService
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
            .getBannerImg(hashMapOf(), "index", hashMapOf())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ResponseBody) {
                    val fromJson = Gson().fromJson<MainBean>(t.string(), MainBean::class.java)
                    iDataInterface?.onDataReceived(fromJson);
                }

                override fun onError(e: Throwable) {

                }

            })
    }


}
