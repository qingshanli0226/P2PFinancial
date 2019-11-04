package com.bw.jinrong.controller.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.bw.jinrong.controller.ui.LoadingPage
import com.loopj.android.http.RequestParams
import org.jetbrains.annotations.Nullable

abstract class BaseFragment : Fragment() {

    lateinit var loadingPage: LoadingPage

    @Nullable
    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

        loadingPage = object : LoadingPage(container?.context){
            override fun layoutId(): Int {
                return getLayoutId()
            }

            override fun onSuccess(resultState: ResultState?, view_success: View?) {
                if (view_success != null) {
                    ButterKnife.bind(this@BaseFragment, view_success)
                    initTitle()
                    initData(resultState!!.content)
                }
            }

            override fun params(): RequestParams {
                return getParams()
            }

            override fun url(): String {
                return getUrl()
            }

        }

        return loadingPage
    }

    protected abstract fun getParams(): RequestParams

    protected abstract fun getUrl(): String

    //初始化界面的数据
    protected abstract fun initData(content:String)

    //初始化title
    protected abstract fun initTitle()

    //提供布局
    abstract fun getLayoutId(): Int

    @Override
    override fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.unbind(this)
    }

    fun show(){
        loadingPage.show()
    }

}