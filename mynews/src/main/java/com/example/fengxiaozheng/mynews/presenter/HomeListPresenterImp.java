package com.example.fengxiaozheng.mynews.presenter;

import com.example.fengxiaozheng.mynews.entity.TouTiaoVideoResult;
import com.example.fengxiaozheng.mynews.listener.ResultListener;
import com.example.fengxiaozheng.mynews.model.HomeListModel;
import com.example.fengxiaozheng.mynews.model.HomeListModelImp;
import com.example.fengxiaozheng.mynews.presenter.base.BasePresenter;
import com.example.fengxiaozheng.mynews.view.HomeListView;

/**
 * Created by fengxiaozheng on 2016/11/9.
 */

public class HomeListPresenterImp extends BasePresenter<HomeListView> implements HomeListPresenter,
        ResultListener<TouTiaoVideoResult> {

    private HomeListModel model;

    public HomeListPresenterImp(HomeListView view){
        attachView(view);
        model = new HomeListModelImp(this);
    }

    @Override
    public void getData(String category, String as) {
        model.geData(category, as);
    }

    @Override
    public void onSuccess(TouTiaoVideoResult data) {
        view.success(data);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onException(String msg) {

    }
}
