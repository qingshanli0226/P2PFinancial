package com.bw.jinrong.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import com.bw.base.BaseWelcomeActivity
import com.bw.common.UIUtils
import com.bw.common.WelcomUtils
import com.bw.jinrong.R
import kotlinx.android.synthetic.main.activity_welcome.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL



class WelcomeActivity : BaseWelcomeActivity() {

    var handler = @SuppressLint("HandlerLeak")
    object :Handler(){
        override fun handleMessage(msg: Message?) {
//            super.handleMessage(msg)
            when(msg?.what){
                WelcomUtils().TO_MAIN -> {
                    startActivity(Intent(this@WelcomeActivity,MainActivity::class.java))
                    finish()
                }
                WelcomUtils().DOWNLOAD_VERSION_SUCCESS ->{
                    //获取当前应用的版本信息
                    val version = getVersion()
                    //更新页面显示的版本信息
                    tv_welcome_version.text = version
                    //比较服务器获取的最新的版本跟本应用的版本是否一致
                    if (version == updateInfo?.version){
                        UIUtils().toast("当前应用已经是最新版本",false)
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
                WelcomUtils().DOWNLOA_APK_SUCCESS -> {
                    UIUtils().toast("联网下载数据失败",false)
                    toMain()
                }
                WelcomUtils().DOWNLOAD_APK_FALL -> {
                    UIUtils().toast("下载应用数据成功",false)
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

                        handler.sendEmptyMessage(WelcomUtils().DOWNLOA_APK_SUCCESS)
                    }else{
                        handler.sendEmptyMessage(WelcomUtils().DOWNLOAD_APK_FALL)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateAPKFile() {

    }

    override fun toMain() {

    }

    override fun setAnimation() {

    }

    override fun isConnect() {

    }

    override fun initData() {

    }

}
