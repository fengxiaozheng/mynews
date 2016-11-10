package com.example.fengxiaozheng.mynews.utils;

import com.example.fengxiaozheng.mynews.retrofit.ApiStore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtil {
    public  static Retrofit.Builder get(String baseUrl){
        OkHttpClient okHttpClient =new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build();

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.client(okHttpClient).baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
            RxJavaCallAdapterFactory.create());

        return builder ;

    }

    public  static ApiStore getApi (String baseuRL){

        return  get(baseuRL).build().create(ApiStore.class);
    }

}
