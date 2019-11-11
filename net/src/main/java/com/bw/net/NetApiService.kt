package com.bw.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface NetApiService {

    //ResponseBody 是一个通用的返回类型，如果不清楚服务端返回的json数据，可以通过它来获取
    @GET("path")
    fun getData(@HeaderMap headerMap: HashMap<String,String>,@Path("path") path:String,@QueryMap params:HashMap<String,String>) : Observable<ResponseBody>

}