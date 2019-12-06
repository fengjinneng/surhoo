package com.surhoo.sh.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {


    protected Unbinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setBackgroundDrawable(null);

        setContentView(getContentView());

        mBinder = ButterKnife.bind(this);

        initView();

        initData();

        if (isFirstInLoadData()) {
            requestData();
        }

    }



    @Override
    protected void onDestroy() {
        mBinder.unbind();
        super.onDestroy();
    }


    //得到布局
    public abstract int getContentView();


    //第一次进入是否需要加载数据
    public abstract boolean isFirstInLoadData();


    //初始化视图
    public abstract void initView();

    //初始化数据
    public abstract void initData();


    //请求数据
    public abstract void requestData();


}
