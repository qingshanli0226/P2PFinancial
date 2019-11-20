package com.example.network;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface IRetrofit {
    @GET
    Observable<ResponseBody> getGetData(@HeaderMap HashMap<String,String> header, @Url String url, @QueryMap HashMap<String,String> params);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> getPostData(@Url String url,Object postData);

    @Streaming
    @GET
    Observable<ResponseBody> downFile(@Url String url,Object postData);
}
