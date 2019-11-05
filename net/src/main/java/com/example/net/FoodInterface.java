package com.example.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodInterface {
    ////http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=10&page=1
    @GET("ios/cf/dish_list.php")
    Observable<FoodeBean> getfood(@Query("stage_id")String stage_id, @Query("limit")String limit, @Query("page")String page);
}
