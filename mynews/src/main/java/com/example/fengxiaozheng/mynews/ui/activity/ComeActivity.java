package com.example.fengxiaozheng.mynews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.presenter.ComePresenter;
import com.example.fengxiaozheng.mynews.presenter.ComePresenterImp;
import com.example.fengxiaozheng.mynews.ui.activity.base.MvpActivity;
import com.example.fengxiaozheng.mynews.view.ComeView;

public class ComeActivity extends MvpActivity<ComePresenter> implements ComeView {

    @Override
    protected ComePresenter createPresenter() {
        return new ComePresenterImp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_come);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        presenter.init();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goActvity(boolean isFist) {
        if (isFist) {
            startActivity(new Intent(this, SplashActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

}
