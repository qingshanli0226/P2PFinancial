package com.example.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NetApiService {

    @GET("path")
    Observable<ResponseBody> getData(@HeaderMap HashMap<String,String>heades,
                                     @Path("path")String path,
                                     @QueryMap HashMap<String,String>parems);

}
