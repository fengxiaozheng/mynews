package com.example.fengxiaozheng.mynews.rxjava;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();
}
