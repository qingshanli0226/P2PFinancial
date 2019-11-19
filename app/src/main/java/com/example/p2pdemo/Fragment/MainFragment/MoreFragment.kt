package com.example.p2pdemo.Fragment.MainFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.*
import com.example.base.BaseFragment
import com.example.common.AppNetWork
import com.example.p2pdemo.Activity.HandAcitivity
import com.example.p2pdemo.Activity.RegisterActivity
import com.example.p2pdemo.Activity.WithAboutActivity
import com.example.p2pdemo.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.more.view.*
import kotlinx.android.synthetic.main.morefrag.*
import kotlinx.android.synthetic.main.morefrag.view.*
import java.util.logging.Handler

class MoreFragment: BaseFragment(){



    override fun setView(): Int {
        return R.layout.morefrag
    }

    override fun inItData() {
        val moreTitlebar = baseView.More_TitleBar
        moreTitlebar.setTitleName(resources.getString(R.string.titleBar3))


        //注册界面
        funRegister()
        //手势开关
        funToggle()
        //反馈
        funFeedBack()
        //分享
        funShare()
        //关于
        funAboutWith()

    }

    private fun funShare() {

    }

    private fun funAboutWith() {
        startActivity(Intent(activity,WithAboutActivity::class.java))
    }

    private fun funRegister() {
        startActivity(Intent(activity,RegisterActivity::class.java))
    }

    //用户反馈
    private fun funFeedBack() {
        var branch:String=""
        val feedback = baseView.More_Feedback
        feedback.setOnClickListener {

            val view = View.inflate(context, R.layout.dialog_custom, null)
            val radioGroup = view.findViewById<RadioGroup>(R.id.dialog_rg)
            val edit = view.findViewById<EditText>(R.id.dialog_edit)
            radioGroup.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                    val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
                    //获取每个部门
                    branch = radioButton.text.toString()
                }
            })
            val builder = AlertDialog.Builder(context)
            builder.setView(view)
            builder.setPositiveButton(resources.getString(R.string.Positive),object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if(edit.text.toString()!=null){
                        //要发送的反馈消息
                        val feedMessage = edit.text.toString()
                        val asyncHttpClient = AsyncHttpClient()
                        val requestParams = RequestParams()
                        requestParams.put("department",branch)
                        requestParams.put("content",feedMessage)
                        asyncHttpClient.post(AppNetWork.FEEDBACK,requestParams,object :AsyncHttpResponseHandler(){
                            override fun onSuccess(
                                statusCode: Int,
                                headers: Array<out Header>?,
                                responseBody: ByteArray?
                            ) {
                                Toast.makeText(context,"反馈成功",Toast.LENGTH_SHORT).show()
                            }

                            override fun onFailure(
                                statusCode: Int,
                                headers: Array<out Header>?,
                                responseBody: ByteArray?,
                                error: Throwable?
                            ) {
                                Toast.makeText(context,"反馈失败",Toast.LENGTH_SHORT).show()
                            }
                        })
                    }else{
                        Toast.makeText(context,"请输入您要反馈的消息",Toast.LENGTH_SHORT).show()

                    }



                }
            })

            builder.setNegativeButton(resources.getString(R.string.Negative),null)
            builder.create().show()


        }


    }

    //开关
    private fun funToggle() {
        val toggleBut = baseView.More_ToggleBut
        toggleBut.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if(isChecked==true){
                    Toast.makeText(context,"已打开",Toast.LENGTH_SHORT).show()
                    context!!.startActivity(Intent(activity,HandAcitivity::class.java))
                }else{
                    Toast.makeText(context,"已关闭",Toast.LENGTH_SHORT).show()
                }

            }
        })
    }



    override fun onConnected() {
    }

    override fun onDisConnected() {
    }


}