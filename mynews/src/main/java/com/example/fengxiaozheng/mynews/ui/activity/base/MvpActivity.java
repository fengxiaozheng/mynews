package com.example.fengxiaozheng.mynews.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.fengxiaozheng.mynews.presenter.base.BasePresenter;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity{
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
        }
    }
}
