package com.example.fengxiaozheng.mynews.presenter;

import com.example.fengxiaozheng.mynews.presenter.base.BasePresenter;
import com.example.fengxiaozheng.mynews.view.ComeView;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class ComePresenterImp extends BasePresenter<ComeView> implements ComePresenter{

    public ComePresenterImp(ComeView view){
        attachView(view);
    }


    @Override
    public void init() {

    }
}
