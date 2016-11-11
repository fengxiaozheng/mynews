package com.example.fengxiaozheng.mynews.rxjava;

import android.text.TextUtils;
import android.util.Log;

import com.example.fengxiaozheng.mynews.entity.BaseResult;

import rx.Observer;


/**
 * 网络请求返回需要的模型
 * Created by ice on 3/3/16.
 */
public abstract class BaseObserver<T extends BaseResult> implements Observer<T> {

    protected abstract void onSucceed(T result);

    protected void onFailed(String msg) {
        if (!TextUtils.isEmpty(msg)){}
         //   App.showToast(msg);
        Log.i("fail =====", msg);
    }

    @Override
    public void onCompleted() {
        Log.i("onCompleted =====", "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.i("onError====", e.toString());
        onFailed(null);
    }

    @Override
    public void onNext(T result) {
        if (result != null)
            onSucceed(result);
        else
            onFailed(null);

    }
}
