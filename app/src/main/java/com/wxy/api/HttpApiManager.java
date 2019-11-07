package com.wxy.api;

import com.wxy.base.AppConst;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpApiManager {
    private static final long TIME_OUT = 5L;
    private Retrofit retrofit;

    public HttpApiManager() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS);

        HttpCommonInterceptor.Builder interceptor = new HttpCommonInterceptor.Builder();
        interceptor.addHeaderParams("language", "cht");
        httpClient.addInterceptor(interceptor.build());
        retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(AppConst.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static class Holder {
        private static final HttpApiManager INSTANCE = new HttpApiManager();
    }

    public static HttpApiManager getInstance() {
        return Holder.INSTANCE;
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
