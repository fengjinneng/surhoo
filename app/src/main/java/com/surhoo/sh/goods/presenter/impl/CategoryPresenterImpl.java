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
    public void requestLevelOneCategory() {

        NetworkReturnUtil.requestList(categoryListView, activity, Api.GOODSLEVERONECATEGORY, null, CategoryBean.class);


    }

    @Override
    public void requestLevelTwoCategory(int id,int positon) {

        GetRequest<String> request = OkGo.<String>get(Api.GOODSLEVERTWOCATEGORY+"/"+id)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth));

        DialogStringCallback stringCallback = new DialogStringCallback(activity) {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("GOODSLEVERTWOCATEGORY", response.body());
                try {
                    if (response.code() == 200) {

                        if(StringUtils.isEmpty(response.body())){
                            return;
                        }
                        List beans = JSONObject.parseArray(response.body(), CategoryBean.class);
                        categoryListView.showLevelTwoCategory(beans,positon);

                    } else {
                        ToastUtils.showShort("啊哦，出现错误了！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                categoryListView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);
    }
}
