package com.example.fengxiaozheng.mynews.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.presenter.SplashPresenterImp;
import com.example.fengxiaozheng.mynews.ui.activity.base.MvpActivity;
import com.example.fengxiaozheng.mynews.view.SplashView;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class SplashActivity extends MvpActivity<SplashPresenterImp> implements SplashView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected SplashPresenterImp createPresenter() {
        return new SplashPresenterImp(this);
    }

    @Override
    public void goAct() {

    }
}
