package com.example.fengxiaozheng.mynews.presenter.base;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
