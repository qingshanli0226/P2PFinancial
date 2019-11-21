package com.example.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetPostApiService {

    @FormUrlEncoded
    @POST("{path}")
    Observable<ResponseBody> getRegister(@Path("path") String path, @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("{path}")
    Observable<ResponseBody> getLoginData(@Path("path") String path, @FieldMap HashMap<String, String> map);
}
