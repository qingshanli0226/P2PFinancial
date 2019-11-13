package jni.example.p2pinvest.mvp.presenter

import jni.example.base.BasePresenter
import jni.example.common.Constant
import jni.example.p2pinvest.bean.Product
import java.lang.reflect.Type

class InvestAllPresenter: BasePresenter<Product>() {
    override fun getPath(): String {
        return Constant.PRODUCT
    }

    override fun getBeanType(): Type {
        return Product::class.java
    }
}