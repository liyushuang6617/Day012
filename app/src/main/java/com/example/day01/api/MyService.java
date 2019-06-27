package com.example.day01.api;

import com.example.day01.bean.ArtBean;
import com.example.day01.bean.CollcetBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MyService {

    public String arturl = "https://www.wanandroid.com/project/list/1/";

    @GET("json?cid=294")
    Observable<ArtBean> art();

    public String collceturl = "http://gank.io/api/data/";

    @GET("%E7%A6%8F%E5%88%A9/20/1")
    Observable<CollcetBean> collcet();

    String uploadUrl = "http://yun918.cn/";
    @POST("study/public/file_upload.php")
    @Multipart
    Call<ResponseBody> postImage(@Part("key") RequestBody requestBody , @Part MultipartBody.Part mu);
}
