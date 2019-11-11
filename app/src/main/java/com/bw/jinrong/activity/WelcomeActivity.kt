package com.bw.jinrong.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import com.alibaba.fastjson.JSON
import com.bw.base.BaseWelcome
import com.bw.common.AppNetConfig
import com.bw.common.UIUtils
import com.bw.common.UpdateInfo
import com.bw.jinrong.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import kotlinx.android.synthetic.main.activity_welcome.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL



class WelcomeActivity : BaseWelcome() {

    var handler = @SuppressLint("HandlerLeak")
    object :Handler(){
        override fun handleMessage(msg: Message?) {
//            super.handleMessage(msg)
            when(msg?.what){
                TO_MAIN -> {
                    finish()
                    startActivity(Intent(this@WelcomeActivity,MainActivity::class.java))
                }
                DOWNLOAD_VERSION_SUCCESS ->{
                    //获取当前应用的版本信息
                    val version = getVersion()
                    //更新页面显示的版本信息
                    tv_welcome_version.text = version
                    //比较服务器获取的最新的版本跟本应用的版本是否一致
                    if (version == updateInfo?.version){
//                        UIUtils().toast("当前应用已经是最新版本",false)
                        toMain()
                    }else{
                        AlertDialog.Builder(this@WelcomeActivity)
                            .setTitle("下载最新版本")
                            .setMessage(updateInfo?.desc)
                            .setPositiveButton("确定") { dialog, which ->
                                //下载服务器保存的应用数据
                                downloadAPK()
                            }
                            .setNegativeButton("取消"){dialog, which ->
                                toMain()
                            }
                            .show()
                    }
                }
                DOWNLOA_APK_SUCCESS -> {
//                    UIUtils().toast("联网下载数据失败",false)
                    toMain()
                }
                DOWNLOAD_APK_FALL -> {
//                    UIUtils().toast("下载应用数据成功",false)
                    dialog?.dismiss()
                    //安装下载好的应用
                    installAPK()
                    //结束当前的welcomeActivity的显示
                    finish()
                }
            }
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_welcome)
//    }

    override fun installAPK() {
        var intent:Intent = Intent("android.intent.action.INSTALL_PACKAGE")
        intent.data = Uri.parse("file:" + apkFile?.absolutePath)
        startActivity(intent)
    }

    override fun downloadAPK() {
        //初始化水平进度条的dialog
        dialog = ProgressDialog(this)
        dialog?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        dialog?.setCancelable(false)
        dialog?.show()
        //初始化数据要保持的位置
        var filesDir:File
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
            filesDir = this.getExternalFilesDir("")
        }else{
            filesDir = this.filesDir
        }
        apkFile = File(filesDir,"update.apk")

        //启动一个分线程联网下载数据
        Thread{
            run {
                val path = updateInfo?.apkUrl
                var ism:InputStream? = null
                var fos:FileOutputStream? = null
                var conn:HttpURLConnection? = null
                try {
                    var url:URL = URL(path)
                    conn = url.openConnection() as HttpURLConnection?

                    conn?.requestMethod = "GET"
                    conn?.connectTimeout = 5000
                    conn?.readTimeout = 5000

                    conn?.connect()

                    if (conn?.responseCode == 200){
                        //设置dialog的最大值
                        dialog?.max = conn.contentLength
                        ism = conn.inputStream
                        fos = FileOutputStream(apkFile)

                        val buffer = ByteArray(1024)
                        var len:Int = 0

                        while ((len.and(ism.read(buffer)) ) != -1){
                            //更新dialog的进度
                            dialog?.incrementProgressBy(len)
                            fos.write(buffer,0,len)

                            SystemClock.sleep(1)
                        }

                        handler.sendEmptyMessage(DOWNLOA_APK_SUCCESS)
                    }else{
                        handler.sendEmptyMessage(DOWNLOAD_APK_FALL)
                    }
                } catch (e:Exception){
                    e.printStackTrace()
                } finally {
                    if (conn != null){
                        conn.disconnect()
                    }
                    if (ism != null){
                        try {
                            ism.close()
                        } catch (e:IOException){
                            e.printStackTrace()
                        }
                    }
                    if (fos != null){
                        try {
                            fos.close()
                        } catch (e:IOException){
                            e.printStackTrace()
                        }
                    }
                }


            }
        }.start()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun getVersion(): String {
        var version:String = "未知版本"
        var manager:PackageManager = packageManager
        try {
            val packageInfo = manager.getPackageInfo(packageName, 0)
            version = packageInfo.versionName
        }catch (e:PackageManager.NameNotFoundException){
            //如果找不到对应的应用信息，就返回"未知版本"
        }
        return version
    }

    override fun updateAPKFile() {
        //获取系统当前时间
        startTime = System.currentTimeMillis()

        //判断手机是否可以联网
        var connect:Boolean = isConnect()
        //没有移动网络
        if (!connect){
//            UIUtils().toast("当前没有移动数据网络",false)
            toMain()
        }else{//有移动网络
            //联网获取服务器的最新版本数据
            var client:AsyncHttpClient = AsyncHttpClient()
            val url = AppNetConfig().UPDATE
            client.post(url,object :AsyncHttpResponseHandler(){
                override fun onSuccess(content: String?) {
//                    super.onSuccess(content)
                    //解析json数据
                    updateInfo = JSON.parseObject(content,UpdateInfo::class.java)
                    handler.sendEmptyMessage(DOWNLOAD_VERSION_SUCCESS)
                }

                override fun onFailure(error: Throwable?, content: String?) {
//                    super.onFailure(error, content)
//                    UIUtils().toast("联网请求数据失败",false)
                    toMain()
                }
            })
        }
    }

    override fun toMain() {
        val currentTime = System.currentTimeMillis()
        var delayTime:Long = 3000 - (currentTime - startTime!!)
        if (delayTime < 0){
            delayTime = 0
        }

        handler.sendEmptyMessageDelayed(TO_MAIN,delayTime)
    }

    override fun setAnimation() {
        //0:完全透明 1:完全不透明
        var alphaAnimation:AlphaAnimation = AlphaAnimation(0.0f,1.0f)
        alphaAnimation.duration = 3000
        alphaAnimation.interpolator = AccelerateInterpolator()

        rl_welcome.startAnimation(alphaAnimation)
    }

    override fun isConnect(): Boolean {

        var connected:Boolean = false

        var manager:ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (networkInfo != null){
            connected = networkInfo.isConnected
        }

        return connected
    }

    override fun initData() {

    }

}
