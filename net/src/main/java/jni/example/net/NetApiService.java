package jni.example.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NetApiService {
    @GET("{path}")
    Observable<ResponseBody> getData(@HeaderMap HashMap<String, String> headers, @Path("path") String path, @QueryMap HashMap<String, String> params);

    @POST("{path}")
    @FormUrlEncoded
    Observable<ResponseBody> postData(@FieldMap HashMap<String, String> headers, @Path("path") String path, @FieldMap HashMap<String, String> params);
}
