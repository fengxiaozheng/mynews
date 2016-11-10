package com.example.fengxiaozheng.mynews.widget.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class NotouchLayout extends RelativeLayout {


    public NotouchLayout(Context context) {
        super(context);
    }

    public NotouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotouchLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

}  