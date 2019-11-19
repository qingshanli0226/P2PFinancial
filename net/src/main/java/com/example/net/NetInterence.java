package com.example.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public  interface NetInterence {
    //http://169.254.44.116:8080/P2PInvest/index
 //   @GET("{path}")
  //  Observable<ResponseBody> getData(@HeaderMap HashMap<String, String> headers, @Path("path") String path, @QueryMap HashMap<String, String> params);
    @GET("{path}")
    Observable<ResponseBody> getData(@HeaderMap HashMap<String, String> headers, @Path("path") String path, @QueryMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("{path}")
    Observable<ResponseBody> postData(@Url String url,Object postdata);
}
