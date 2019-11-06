package com.example.network;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface InterRetrofit {
    @GET(NetStringUtils.INDEX)
    Observable<ResponseBody> getData();
}
