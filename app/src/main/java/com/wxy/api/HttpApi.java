package com.wxy.api;

import com.wxy.bean.BaseBean;
import com.wxy.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpApi {

    @GET("getDigest")
    Call<BaseBean<NewsBean>> getNewsList(@Query("pageIndex") int pageIndex,@Query("dtp") int dtp);

    @GET("getDigest")
    Observable<BaseBean<NewsBean>> getNewsListObser(@Query("pageIndex") int pageIndex, @Query("dtp") int dtp);

    @FormUrlEncoded
    @POST("getDigest")
    Call<BaseBean<NewsBean>> getNewsListPost(@Field("pageIndex") int pageIndex,@Field("dtp") int dtp);
}
