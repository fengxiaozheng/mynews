package com.example.fengxiaozheng.mynews.presenter;

import com.example.fengxiaozheng.mynews.model.HomeListModel;
import com.example.fengxiaozheng.mynews.model.HomeListModelImp;
import com.example.fengxiaozheng.mynews.presenter.base.BasePresenter;
import com.example.fengxiaozheng.mynews.view.HomeListView;

/**
 * Created by fengxiaozheng on 2016/11/9.
 */

public class HomeListPresenterImp extends BasePresenter<HomeListView> implements HomeListPresenter {

    private HomeListModel model;

    public HomeListPresenterImp(HomeListView view){
        attachView(view);
        model = new HomeListModelImp();
    }

    @Override
    public void getData(String category, String as) {
        model.geData(category, as);
    }
}
