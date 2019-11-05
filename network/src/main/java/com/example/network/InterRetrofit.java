package com.example.network;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;

public interface InterRetrofit {
    @GET("")
    Observable<RequestBody> getData();
}
