package com.surhoo.sh.goods.presenter.impl;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.DialogStringCallback;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.goods.bean.CategoryBean;
import com.surhoo.sh.goods.presenter.CategoryPresenter;
import com.surhoo.sh.goods.view.CategoryListView;

import java.util.List;


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
    public void requestLevelOneCategory(String requestTag) {

        NetworkReturnUtil.requestNoPageList(requestTag,categoryListView, activity, Api.GOODSLEVERONECATEGORY, null, CategoryBean.class);


    }

    @Override
    public void requestLevelTwoCategory(String requestTag,int id) {

        NetworkReturnUtil.requestNoPageList(requestTag,categoryListView, activity, Api.GOODSLEVERTWOCATEGORY+"/"+id, null, CategoryBean.class);


    }
}
