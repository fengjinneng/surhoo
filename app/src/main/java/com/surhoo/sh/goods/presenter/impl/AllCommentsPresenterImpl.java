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
import com.surhoo.sh.goods.bean.CommentBean;
import com.surhoo.sh.goods.presenter.AllCommentsPresenter;
import com.surhoo.sh.goods.view.AllCommentsView;

import java.util.List;

public class AllCommentsPresenterImpl implements AllCommentsPresenter {

    private static final String TAG ="AllCommentsPresenterImpl" ;
    AllCommentsView allCommentsView;

    private Activity activity;

    @Override
    public void bindView(Activity activity, AllCommentsView view) {
        this.activity = activity;
        allCommentsView = view;
    }

    @Override
    public void unBindView() {
        allCommentsView = null;
    }



    @Override
    public void requestData(int pageSize, int pageIndex, int goodsId) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("pageSize",pageSize);
        httpParams.put("pageIndex",pageIndex);
        httpParams.put("goodsId",goodsId);

        NetworkReturnUtil.requestHavePageList(allCommentsView,activity,Api.ALLGOODSCOMMENTS,httpParams,CommentBean.ListBean.class,pageIndex);

    }
}
