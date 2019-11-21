package com.example.net;

import java.util.HashMap;
import java.util.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostApiService {
    @POST("{path}")
    @FormUrlEncoded
    io.reactivex.Observable<ResponseBody> postData(@FieldMap HashMap<String, String> headers, @Path("path") String path, @FieldMap HashMap<String, String> params);

}
