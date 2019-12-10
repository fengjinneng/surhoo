package com.surhoo.sh.goods.presenter.impl;

import android.app.Activity;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.goods.bean.CategoryBean;
import com.surhoo.sh.goods.presenter.CategoryPresenter;
import com.surhoo.sh.goods.view.CategoryListView;


public class CategoryPresenterImpl implements CategoryPresenter {

    CategoryListView categoryListView;
    Activity activity;


    @Override
    public void bindView(Activity activity, CategoryListView view) {
        this.activity = activity;
        this.categoryListView = view;
    }

    @Override
    public void unBindView() {
        categoryListView = null;
    }

    @Override
    public void requestLevelOneCategory() {

        NetworkReturnUtil.requestList(categoryListView, activity, Api.GOODSLEVERONECATEGORY, null, CategoryBean.class);

    }

    @Override
    public void requestLevelTwoCategory(int id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id",id);
        NetworkReturnUtil.requestList(categoryListView, activity, Api.GOODSLEVERTWOCATEGORY, httpParams, CategoryBean.class);

    }
}
