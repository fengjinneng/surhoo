package com.surhoo.sh.shop.presenter.impl;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.shop.ShopDetailBean;
import com.surhoo.sh.shop.presenter.ShopPresenter;
import com.surhoo.sh.shop.view.ShopView;

public class ShopPresenterImpl implements ShopPresenter {


    ShopView shopView;
    Context context;

    @Override
    public void bindView(Context ctx, ShopView view) {
        shopView = view;
        context =ctx;
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

        NetworkReturnUtil.requestOne(shopView,context,Api.SHOPDETAIL,httpParams,ShopDetailBean.class);
    }
}
