package com.example.p2pfinancial.serviece

import android.app.DownloadManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import com.example.net.Constant
import com.example.net.RetrofitCreator
import com.example.p2pfinancial.bean.AllInvestBean2
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
        fun onAllReceived(allInvestBean: AllInvestBean2)
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

    fun getAllData() {
        RetrofitCreator.getNetApiService(Constant.BASE_URL)
            .getData(hashMapOf(), "product", hashMapOf())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: ResponseBody) {
                    val fromJson =
                        Gson().fromJson<AllInvestBean2>(t.string(), AllInvestBean2::class.java)
                    iDataInterface?.onAllReceived(fromJson)
                }

                override fun onError(e: Throwable) {

                }

            })
    }

    var downloadListener: IDownloadListener? = null

    interface IDownloadListener {
        fun downloadApk()
    }

    fun registerDownloadListener(iDataInterface: IDownloadListener) {
        this.downloadListener = iDataInterface
    }

    fun unregisterDownloadListener() {
        this.downloadListener = null
    }

    fun getNewApk() {
    println("zjw_ : 更新版本中")
        val request =
            DownloadManager.Request(Uri.parse("http://169.254.95.169:8080/P2PInvest/app_new.apk"))
        request.setTitle("版本更新中...")
        request.setAllowedOverRoaming(true)
        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
        downloadListener?.downloadApk()
    }

}
