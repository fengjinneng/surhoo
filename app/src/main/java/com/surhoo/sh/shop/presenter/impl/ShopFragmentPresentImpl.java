package com.surhoo.sh.shop.presenter.impl;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.shop.presenter.ShopFragmentPresent;
import com.surhoo.sh.shop.view.impl.ShopFragmentView;

public class ShopFragmentPresentImpl implements ShopFragmentPresent {

    private Activity activity;
    private ShopFragmentView shopFragmentView;




    @Override
    public void bindView(Activity activity, ShopFragmentView view) {
        this.activity =activity;
        this.shopFragmentView = view;
    }

    @Override
    public void unBindView() {
        this.shopFragmentView = null;
    }



    @Override
    public void requestData(int classifyId, int sortType,int pageIndex,int pageSize) {
        HttpParams httpParams  =new HttpParams();

        httpParams.put("classifyId",classifyId);
        httpParams.put("sortType",sortType);
        httpParams.put("pageIndex",pageIndex);
        httpParams.put("pageSize",pageSize);
        NetworkReturnUtil.requestHavePageList(shopFragmentView,activity, Api.SHOPGOODS,httpParams, GoodsBean.class,pageIndex);
    }
}
