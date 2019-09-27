package com.surhoo.sh.home.presenter;

import android.content.Context;

import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.home.view.HomeView;

public class HomePresenterImpl implements HomePresenter {

    HomeView homeView;
    Context context;


    @Override
    public void bindView(Context ctx, HomeView view) {
        homeView = view;
        context = ctx;
    }

    @Override
    public void unBindView() {
        homeView = null;
    }

    @Override
    public void requestData() {

        NetworkReturnUtil.requestOne(homeView,context,Api.HOMEPAGE,null,HomePageBean.class);

    }

}
