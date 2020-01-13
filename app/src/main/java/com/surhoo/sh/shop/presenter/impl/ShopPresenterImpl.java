package com.surhoo.sh.shop.presenter.impl;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.shop.bean.ShopDetailBean;
import com.surhoo.sh.shop.presenter.ShopPresenter;
import com.surhoo.sh.shop.view.ShopView;

public class ShopPresenterImpl implements ShopPresenter {


    ShopView shopView;
    Activity activity;

    @Override
    public void bindView(Activity activity, ShopView view) {
        this.shopView = view;
        this.activity =activity;
    }

    @Override
    public void unBindView() {
        shopView = null;
    }

    @Override
    public void requestData(int shopId) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId",shopId);

        NetworkReturnUtil.requestBeanResultUseGet(shopView,activity,Api.SHOPDETAIL,httpParams,ShopDetailBean.class);
    }
}
