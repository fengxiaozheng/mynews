package com.example.fengxiaozheng.mynews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.ui.activity.base.BaseActivity;
import com.example.fengxiaozheng.mynews.widget.splash.MyPagerAdapter;
import com.example.fengxiaozheng.mynews.widget.splash.ViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.main_scrolllayout)
    ViewPager mFramePager;

    private ArrayList<View> mPageViews;
    private MyPagerAdapter mPageAdapter;

    private ArrayList<View> mFramePageViews;
    private MyPagerAdapter mFramePageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initViewPager();

    }

    public void initViewPager() {
        mPageViews = new ArrayList<View>();
        mFramePageViews = new ArrayList<View>();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View frameView1 = inflater.inflate(R.layout.transparent_layer_image, null);
        initFramePagerView(frameView1, R.mipmap.frame_view1);
        View frameView2 = inflater.inflate(R.layout.transparent_layer_image, null);
        initFramePagerView(frameView2, R.mipmap.frame_view2);
        View frameView3 = inflater.inflate(R.layout.transparent_layer_image, null);
        initFramePagerView(frameView3, R.mipmap.frame_view3);
        View frameView4 = inflater.inflate(R.layout.transparent_layer_image, null);
        initFramePagerView(frameView4, R.mipmap.frame_view4);
        View frameView5 = inflater.inflate(R.layout.transparent_layer_image, null);
        initFramePagerView(frameView5, R.mipmap.frame_view5);
        mFramePageViews.add(frameView1);
        mFramePageViews.add(frameView2);
        mFramePageViews.add(frameView3);
        mFramePageViews.add(frameView4);
        mFramePageViews.add(frameView5);

        mFramePageAdapter = new MyPagerAdapter(mFramePageViews);
        mFramePager.setAdapter(mFramePageAdapter);
//      	mFramePager.setOnPageChangeListener(mFramePagerListener);
        View view1 = inflater.inflate(R.layout.transparent_layer, null);
        initPagerView(view1, getString(R.string.str_001));
        View view2 = inflater.inflate(R.layout.transparent_layer, null);
        initPagerView(view2, getString(R.string.str_002));
        View view3 = inflater.inflate(R.layout.transparent_layer, null);
        initPagerView(view3, getString(R.string.str_003));
        View view4 = inflater.inflate(R.layout.transparent_layer, null);
        initPagerView(view4, getString(R.string.str_004));
        View view5 = inflater.inflate(R.layout.transparent_layer, null);
        TextView textView5 = (TextView) view5.findViewById(R.id.text);
        textView5.setVisibility(View.GONE);
        Button btn = (Button) view5.findViewById(R.id.button);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                SplashActivity.this.finish();
            }
        });

        mPageViews.add(view1);
        mPageViews.add(view2);
        mPageViews.add(view3);
        mPageViews.add(view4);
        mPageViews.add(view5);
        mPageAdapter = new MyPagerAdapter(mPageViews);
        mPager.setAdapter(mPageAdapter);
        mPager.setFollowViewPager(mFramePager);
        // mPager.setOnPageChangeListener();

    }

    public void initFramePagerView(View frameView, int drawable) {
        ImageView image = (ImageView) frameView.findViewById(R.id.image);
        image.setImageResource(drawable);

    }

    public void initPagerView(View view, String text) {
        TextView textView1 = (TextView) view.findViewById(R.id.text);
        textView1.setText(text);
    }
}
