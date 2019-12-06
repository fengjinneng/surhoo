package com.surhoo.sh.shop.presenter.impl;

import android.app.Activity;
import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.shop.ShopDetailBean;
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
    public void requestData(boolean isSelf, int shopId) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("isSelf",isSelf);
        httpParams.put("shopId",shopId);

        NetworkReturnUtil.requestOne(shopView,activity,Api.SHOPDETAIL,httpParams,ShopDetailBean.class);
    }
}
