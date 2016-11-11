package com.example.fengxiaozheng.mynews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.fengxiaozheng.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MyFragment extends Fragment {
    @BindView(R.id.appbar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.collasing_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.fragment_my_toolBar)
    Toolbar toolbar;
    @BindView(R.id.root_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.head_layout)
    LinearLayout linearLayout;
    @BindView(R.id.head_iv)
    ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initView(view);
    }

    private void initView(View view) {
        AppCompatActivity activiy = (AppCompatActivity) getActivity();
        activiy.setSupportActionBar(toolbar);
        activiy.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -linearLayout.getHeight() / 1.5) {
                    collapsingToolbarLayout.setTitle("风小筝");
                } else {
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });
        Glide.with(activiy).load(R.mipmap.come).bitmapTransform(new RoundedCornersTransformation(activiy,
                180, 0)).into(imageView);
        Glide.with(activiy).load(R.mipmap.come).bitmapTransform(new BlurTransformation(activiy,100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        linearLayout.setBackground(resource);
                    //    coordinatorLayout.setBackground(resource);
                    }
                });

        Glide.with(activiy).load(R.mipmap.come).bitmapTransform(new BlurTransformation(activiy, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        collapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }
}
