package com.example.fengxiaozheng.mynews.view;

import android.app.Activity;

import com.example.fengxiaozheng.mynews.entity.TouTiaoVideoResult;

/**
 * Created by fengxiaozheng on 2016/11/9.
 */

public interface HomeListView {
    Activity getActivity();
    void success(TouTiaoVideoResult data);
    void failure();
}
