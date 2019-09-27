package com.surhoo.sh.goods.presenter.impl;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.goods.bean.CategoryBean;
import com.surhoo.sh.goods.presenter.CategoryPresenter;
import com.surhoo.sh.goods.view.CategoryListView;


public class CategoryPresenterImpl implements CategoryPresenter {

    CategoryListView categoryListView;
    Context context;


    @Override
    public void bindView(Context ctx, CategoryListView view) {
        this.context = ctx;
        this.categoryListView = view;
    }

    @Override
    public void unBindView() {
        categoryListView = null;
    }

    @Override
    public void requestLevelOneCategory() {

        NetworkReturnUtil.requestList(categoryListView, context, Api.GOODSLEVERONECATEGORY, null, CategoryBean.class);

    }

    @Override
    public void requestLevelTwoCategory(int id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id",id);
        NetworkReturnUtil.requestList(categoryListView, context, Api.GOODSLEVERTWOCATEGORY, httpParams, CategoryBean.class);

    }
}
