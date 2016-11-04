package com.example.fengxiaozheng.mynews.retrofit;

import com.example.fengxiaozheng.mynews.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class AppClient {

    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

        }
        return retrofit;
    }
}
