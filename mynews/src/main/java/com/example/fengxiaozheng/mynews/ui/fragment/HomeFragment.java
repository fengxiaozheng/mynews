package com.example.fengxiaozheng.mynews.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengxiaozheng.mynews.Constants;
import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.ui.adapter.MyViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.fragment_home_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.fragment_home_viewpager)
    ViewPager viewPager;
    @BindView(R.id.fragment_home_toolBar)
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        toolbar.setTitle(getString(R.string.fragment_home));
    }

    private void initView() {
        setupViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.fragment_home_all)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.fragment_home_fun)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.fragment_home_see)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.fragment_home_star)));
    //    tabLayout.setupWithViewPager(viewPager);
    //    viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void setupViewPager(ViewPager viewPager) {
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(HomeListFragment.newInstance(Constants.ONE), getString(R.string.fragment_home_all));
        adapter.addFragment(HomeListFragment.newInstance(Constants.TWO), getString(R.string.fragment_home_fun));
        adapter.addFragment(HomeListFragment.newInstance(Constants.THREE), getString(R.string.fragment_home_see));
        adapter.addFragment(HomeListFragment.newInstance(Constants.FOUR), getString(R.string.fragment_home_star));
        viewPager.setAdapter(adapter);
    }

}
