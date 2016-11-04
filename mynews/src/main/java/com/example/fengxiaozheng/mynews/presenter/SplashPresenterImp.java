package com.example.fengxiaozheng.mynews.presenter;

import com.example.fengxiaozheng.mynews.presenter.base.BasePresenter;
import com.example.fengxiaozheng.mynews.view.SplashView;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class SplashPresenterImp extends BasePresenter<SplashView> implements SplashView{

    public SplashPresenterImp(SplashView view){
        attachView(view);
    }

    @Override
    public void goAct() {

    }
}
