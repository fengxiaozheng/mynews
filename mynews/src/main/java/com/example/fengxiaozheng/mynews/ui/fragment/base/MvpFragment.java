package com.example.fengxiaozheng.mynews.ui.fragment.base;

import android.os.Bundle;
import android.view.View;

import com.example.fengxiaozheng.mynews.presenter.base.Presenter;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public abstract class MvpFragment<P extends Presenter> extends BaseFragment {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null){
            presenter.detachView();
        }
    }
}
