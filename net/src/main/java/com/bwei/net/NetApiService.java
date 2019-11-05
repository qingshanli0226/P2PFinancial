package com.bwei.net;




import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

public interface NetApiService {
//    banner图
    @GET()
    Observable<ResponseBody> getData(@QueryName String cat);
//    //    访问“全部理财”产品
//    @GET(AppNetConfig.PRODUCT)
//    Observable<ResponseBody> getProductData();
    //    登录 注册
    @GET(AppNetConfig.LOGIN)
    Observable<ResponseBody> getLoginData(@QueryName String cat, @QueryMap HashMap<String, String> params);
//    //    主页
//    @GET(AppNetConfig.USERREGISTER)
//    Observable<ResponseBody> getUserregisterData();
//    //   注册
//    @GET(AppNetConfig.FEEDBACK)
//    Observable<ResponseBody> getFeedbookData( @QueryMap HashMap<String, String> params);
}
