package com.example.fengxiaozheng.mynews.model;

import com.example.fengxiaozheng.mynews.entity.TouTiaoVideoResult;
import com.example.fengxiaozheng.mynews.listener.ResultListener;
import com.example.fengxiaozheng.mynews.retrofit.AppClient;
import com.example.fengxiaozheng.mynews.rxjava.ApiCallback;
import com.example.fengxiaozheng.mynews.rxjava.SubscriberCallback;

import rx.schedulers.Schedulers;

/**
 * Created by fengxiaozhengg on 2016/11/9.
 */

public class HomeListModelImp implements HomeListModel{
    private ResultListener<TouTiaoVideoResult> listener;

    public HomeListModelImp(ResultListener<TouTiaoVideoResult> listener){
        this.listener = listener;
    }

    @Override
    public void geData(String category, String as) {
        AppClient appClient = new AppClient();
        appClient.getvideoList(category, as)
                .observeOn(Schedulers.immediate())
                .subscribe(new SubscriberCallback<TouTiaoVideoResult>(new ApiCallback<TouTiaoVideoResult>() {
                            @Override
                            public void onSuccess(TouTiaoVideoResult model) {
                                listener.onSuccess(model);
                            }

                            @Override
                            public void onFailure(int code, String msg) {
                                listener.onError(msg);
                            }

                            @Override
                            public void onCompleted() {

                            }
                        })
                );

    }
}
