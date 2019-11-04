package com.example.p2pfinancial

import com.example.base.BasePresenter
import com.example.net.Constant
import com.example.net.ResEntity
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.HashMap

class HomePresenter : BasePresenter<HomeBean>() {

    override fun getPath(): String {
        return "dish_list.php"
    }

    override fun getType(): Type {
        return object : TypeToken<ResEntity<List<HomeBean>>>() {}.type
    }

    override fun isList(): Boolean {
        return true
    }

    override fun getQueryMap(): HashMap<String, String> {
        val map = hashMapOf<String, String>()
        map.put("stage_id", "1")
        map.put("limit", "20")
        map.put("page", "1")
        return map
    }
}