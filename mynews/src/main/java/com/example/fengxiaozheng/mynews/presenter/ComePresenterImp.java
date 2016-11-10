package com.example.fengxiaozheng.mynews.presenter;

import android.os.Handler;

import com.example.fengxiaozheng.mynews.Constants;
import com.example.fengxiaozheng.mynews.presenter.base.BasePresenter;
import com.example.fengxiaozheng.mynews.utils.SPUtils;
import com.example.fengxiaozheng.mynews.view.ComeView;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class ComePresenterImp extends BasePresenter<ComeView> implements ComePresenter {
    private boolean isFirst;
    private Handler handler = new Handler();

    public ComePresenterImp(ComeView view) {
        attachView(view);
    }


    @Override
    public void init() {
        SPUtils utils = new SPUtils(view.getContext(), Constants.APP_NAME);
        if (utils.getBoolean(Constants.INSTALL_FIRST) == false) {
            isFirst = true;
            utils.putBoolean(Constants.INSTALL_FIRST, true);
        } else {
            isFirst = false;
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.goActvity(isFirst);
            }
        }, 1000);
    }
}
