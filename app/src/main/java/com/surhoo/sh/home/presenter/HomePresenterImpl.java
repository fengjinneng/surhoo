package com.surhoo.sh.home.presenter;

import android.app.Activity;

import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.home.view.HomeView;

public class HomePresenterImpl implements HomePresenter {

    HomeView homeView;
    Activity activity;


    @Override
    public void bindView(Activity activity, HomeView view) {
        this.homeView = view;
        this.activity = activity;
    }

    @Override
    public void unBindView() {
        homeView = null;
    }

    @Override
    public void requestData() {

        NetworkReturnUtil.requestOne(homeView,activity,Api.HOMEPAGE,null,HomePageBean.class);

    }

}
