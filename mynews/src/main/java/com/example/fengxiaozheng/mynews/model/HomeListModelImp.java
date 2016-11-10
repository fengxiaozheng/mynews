package com.example.fengxiaozheng.mynews.model;

import com.example.fengxiaozheng.mynews.entity.TouTiaoVideoResult;
import com.example.fengxiaozheng.mynews.retrofit.AppClient;
import com.example.fengxiaozheng.mynews.rxjava.BaseObserver;

import rx.schedulers.Schedulers;

/**
 * Created by fengxiaozhengg on 2016/11/9.
 */

public class HomeListModelImp implements HomeListModel{
    @Override
    public void geData(String category, String as) {
        AppClient appClient = new AppClient();
        appClient.getvideoList(category, as)
                .observeOn(Schedulers.immediate())
                .subscribe(new BaseObserver<TouTiaoVideoResult>() {
                    @Override
                    protected void onSucceed(TouTiaoVideoResult result) {
                        System.out.println("sssssssss"+result.getData().get(0).getArticle_url());
                    }
                });
    }
}
