package com.example.fengxiaozheng.mynews.rxjava;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class SubscriberCallback<T> extends Subscriber<T> {
    private ApiCallback<T> apiCallback;

    public SubscriberCallback(ApiCallback<T> apiCallback){
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException){
            HttpException exception = (HttpException) e;
            int code = exception.code();
            String msg = exception.getMessage();
            apiCallback.onFailure(code, msg);
        }else {
            apiCallback.onFailure(e.hashCode(), e.getMessage());
        }
        apiCallback.onCompleted();
    }

    @Override
    public void onNext(T t) {
        apiCallback.onSuccess(t);
    }
}
