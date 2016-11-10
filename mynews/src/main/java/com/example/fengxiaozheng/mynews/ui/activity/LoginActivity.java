package com.example.fengxiaozheng.mynews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.fengxiaozheng.mynews.Constants;
import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.presenter.LoginPresenter;
import com.example.fengxiaozheng.mynews.presenter.LoginPresenterImp;
import com.example.fengxiaozheng.mynews.ui.activity.base.MvpActivity;
import com.example.fengxiaozheng.mynews.utils.SPUtils;
import com.example.fengxiaozheng.mynews.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.btn_login_weibo)
    Button weibo;
    @BindView(R.id.btn_login_wechat)
    Button wechat;
    @BindView(R.id.btn_login_qq)
    Button qq;
    @BindView(R.id.btn_login_share)
    Button share;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenterImp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ShareSDK.initSDK(this,"18ceed0d5ddcb");
        presenter.init();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void success() {
        SPUtils spUtils = new SPUtils(this, Constants.APP_NAME);
        if (spUtils.getBoolean(Constants.LOGIN) == false){
            spUtils.putBoolean(Constants.LOGIN, true);
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void failure() {
        Toast.makeText(this,"failure", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_login_weibo) void weiboLogin(){
        presenter.weiboLogin();
    }

    @OnClick(R.id.btn_login_wechat) void wechatLogin(){
        presenter.wechatLogin();
    }

    @OnClick(R.id.btn_login_qq) void  qqLogin(){
        presenter.qqLogin();
    }

    @OnClick(R.id.btn_login_share) void share(){
        presenter.share();
    }
}
