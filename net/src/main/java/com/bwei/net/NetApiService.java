package com.bwei.net;


import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NetApiService {
//    banner图
    @GET("{path}")
    Observable<ResponseBody> getData(@QueryMap HashMap<String, String> hearerParmas, @Path("path") String cat, @QueryMap HashMap<String, String> params);
    //    登录 注册
    //    Post请求
    @FormUrlEncoded
    @POST("{path}")
    Observable<ResponseBody> postData(@FieldMap HashMap<String, String> hearerParmas, @Path("path") String cat, @FieldMap HashMap<String, String> params);
}
