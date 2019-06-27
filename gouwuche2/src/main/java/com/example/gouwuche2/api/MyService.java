package com.example.gouwuche2.api;


import com.example.gouwuche2.bean.ArtBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyService {

    public String url = "http://www.qubaobei.com/ios/cf/";

    @GET("dish_list.php?stage_id=1&limit=20&page=1")
    Observable<ArtBean> art();

}
