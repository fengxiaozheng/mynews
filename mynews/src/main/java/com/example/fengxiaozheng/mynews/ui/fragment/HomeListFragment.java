package com.example.fengxiaozheng.mynews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengxiaozheng.mynews.Constants;
import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.presenter.HomeListPresenter;
import com.example.fengxiaozheng.mynews.presenter.HomeListPresenterImp;
import com.example.fengxiaozheng.mynews.ui.fragment.base.MvpFragment;
import com.example.fengxiaozheng.mynews.view.HomeListView;

import butterknife.BindView;

public class HomeListFragment extends MvpFragment<HomeListPresenter> implements HomeListView {

    private int type = Constants.ONE;

    // TODO: Rename and change types and number of parameters
    public static HomeListFragment newInstance(int type) {
        HomeListFragment fragment = new HomeListFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
    }

    @BindView(R.id.fragment_home_recyclerView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_list, container, false);
    }

    @Override
    protected HomeListPresenter createPresenter() {
        return new HomeListPresenterImp(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    //    presenter.getData("video", "A135870C95A0924");
        System.out.println("1111111111111");
    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {

    }
}
