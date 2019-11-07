package com.example.net;

import android.database.Observable;

import java.util.HashMap;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface NetApiService {
    @GET("{path}")
    io.reactivex.Observable<ResponseBody> getData(@HeaderMap HashMap<String, String> headers, @Path("path") String path, @QueryMap HashMap<String, String> params);

}
