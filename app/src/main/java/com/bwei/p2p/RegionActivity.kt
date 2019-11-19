package com.bwei.p2p

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.bwei.base.BaseActivity
import com.bwei.base.IbaseView
import com.bwei.common.MD5Util
import com.bwei.common.P2PError
import com.bwei.p2p.presenter.RegionPresenter
import kotlinx.android.synthetic.main.activity_region.*
import kotlinx.android.synthetic.main.common_title.*

class RegionActivity :BaseActivity(),IbaseView<String>{
    override fun onGetDataSucess(data: String?) {
        val jsonObject: JSONObject = JSON.parseObject(data);
        val boolean = jsonObject.getBoolean("success");
        if(boolean){
            //解析json数据，生成User对象
            val jsonObject = JSON.parseObject(data)
            val isExist = jsonObject.getBoolean("isExist")!!
            if (isExist)
                startActivity(Intent(this@RegionActivity,MainActivity::class.java))
        }else{
            Toast.makeText(this@RegionActivity, "此用户已注册", Toast.LENGTH_SHORT).show();
        }
    }

    override fun onGetDataListSucess(data: MutableList<String>?) {
    }

    override fun onGetDataFailed(message: String?) {
        Toast.makeText(this@RegionActivity, R.string.Failed, Toast.LENGTH_SHORT).show()

    }

    override fun showLoading() {
    }

    override fun hideLoading(i: Int) {
    }

    override fun onHttpRequestDataFailed(requestCode: Int, error: P2PError?) {
        Toast.makeText(this@RegionActivity, error!!.getErrorMessenger(), Toast.LENGTH_LONG).show()

}

    lateinit var phone:String
    lateinit var user:String
    lateinit var pass:String
    lateinit var rePass:String
    lateinit var params:HashMap<String,String> ;
    lateinit var regionPresenter: RegionPresenter;
    override fun onConnected() {
        Toast.makeText(this,"网络连接成功", Toast.LENGTH_SHORT).show()
    }

    override fun onDisConnected() {
        Toast.makeText(this,"网络连接失败",Toast.LENGTH_SHORT).show()
    }

    override fun initView() {
        params= HashMap();
        regionPresenter= RegionPresenter();

    }

    override fun initDate() {
        iv_title_back.setVisibility(View.VISIBLE)
        tv_title.setText("用户注册")
        iv_title_setting.setVisibility(View.INVISIBLE)
        iv_title_back.setOnClickListener {
            finish()
        }
        region_save.setOnClickListener {
            phone=region_phone.text.toString().trim()
            user=region_name.text.toString().trim()
            pass=region_pass.text.toString().trim()
            rePass=region_rePass.text.toString().trim()
            if (!(pass.isEmpty()||phone.isEmpty()||user.isEmpty()||rePass.isEmpty())){
                if (pass.equals(rePass)){
                    params.put("name",user);
                    params.put("password",MD5Util.MD5(pass));
                    params.put("phone",phone);
                    regionPresenter.parmas.putAll(params)
                    regionPresenter.attachView(this)
                    regionPresenter.getDate();


//                    RetrofitCreate.getNetApiService().postData(hearer,AppNetConfig.USERREGISTER,params)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(object :Observer<ResponseBody>{
//                                override fun onComplete() {
//                                }
//                                override fun onSubscribe(d: Disposable) {
//                                }
//                                override fun onNext(t: ResponseBody) {
//                                    val response = t.string();
//
//                                }
//                                override fun onError(e: Throwable) {
//                                    Toast.makeText(this@RegionActivity,"联网失败",Toast.LENGTH_SHORT).show()
//                                }
//                            })
                }else{
                    region_pass.setText("")
                    region_rePass.setText("")
                    Toast.makeText(this,"两次输入密码不符",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"输入信息不能为空",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_region
    }
}