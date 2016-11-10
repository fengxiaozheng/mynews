package com.example.fengxiaozheng.mynews.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.fengxiaozheng.mynews.R;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class BaseActivity extends AppCompatActivity {
    private CompositeSubscription subscription;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onUnSubscriber();
    }

    public void initBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void onUnSubscriber() {
        if (subscription != null && subscription.hasSubscriptions()) {
            subscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscriptione) {
        subscription = new CompositeSubscription();
        subscription.add(subscriptione);
    }

}
