package com.example.fengxiaozheng.mynews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengxiaozheng.mynews.Constants;
import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.entity.TouTiaoVideoResult;
import com.example.fengxiaozheng.mynews.presenter.HomeListPresenter;
import com.example.fengxiaozheng.mynews.presenter.HomeListPresenterImp;
import com.example.fengxiaozheng.mynews.ui.adapter.HomeListAdapter;
import com.example.fengxiaozheng.mynews.ui.fragment.base.MvpFragment;
import com.example.fengxiaozheng.mynews.view.HomeListView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeListFragment extends MvpFragment<HomeListPresenter> implements HomeListView
,XRecyclerView.LoadingListener{

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
    XRecyclerView recyclerView;

    List<TouTiaoVideoResult.DataBean> data = new ArrayList<>();
    private HomeListAdapter adapter;

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
        presenter.getData("video", "A135870C95A0924");
        System.out.println("1111111111111");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        adapter = new HomeListAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setRefreshing(true);
        recyclerView.setLoadingListener(this);
    }


    @Override
    public void success(TouTiaoVideoResult data) {
        adapter.getDataList().clear();

        adapter.getDataList().addAll(data.getData());

        adapter.notifyDataSetChanged();
    }

    @Override
    public void failure() {

    }

    @Override
    public void onRefresh() {
        data.clear();
        presenter.getData("video", "A135870C95A0924");
        adapter.notifyDataSetChanged();
        recyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        presenter.getData("video", "A135870C95A0924");
        recyclerView.loadMoreComplete();
        adapter.notifyDataSetChanged();
    }
}
