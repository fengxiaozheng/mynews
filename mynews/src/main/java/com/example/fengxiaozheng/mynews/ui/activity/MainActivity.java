package com.example.fengxiaozheng.mynews.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.ui.fragment.FindFragment;
import com.example.fengxiaozheng.mynews.ui.fragment.HomeFragment;
import com.example.fengxiaozheng.mynews.ui.fragment.MyFragment;
import com.example.fengxiaozheng.mynews.widget.blurview.BlurView;
import com.example.fengxiaozheng.mynews.widget.blurview.RenderScriptBlur;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.blurView)
    BlurView blurView;
    @BindView(R.id.bottom_activity)
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 17) {
            setupBlurView();
        }
        setNavigationView();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFragment, new HomeFragment())
                .commit();

    }

    private void setupBlurView() {
        final int radius = 16;

        final View decorView = getWindow().getDecorView();
        //Activity's root View. Can also be root View of your layout
        final View rootView = decorView.findViewById(android.R.id.content);
        //set background, if your root layout doesn't have one
        final Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(this, true)) //Preferable algorithm, needs RenderScript support mode enabled
                .blurRadius(radius);
    }

    private void setNavigationView(){
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recent:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentFragment, new MyFragment())
                                .commit();
                    break;
                    case R.id.action_favorite:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentFragment, new HomeFragment())
                                .commit();
                        break;
                    case R.id.action_nearby:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentFragment, new FindFragment())
                                .commit();
                        break;
                }
                return true;
            }
        });
    }
}
