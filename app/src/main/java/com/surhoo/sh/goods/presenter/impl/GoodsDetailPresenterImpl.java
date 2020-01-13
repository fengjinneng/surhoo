package com.surhoo.sh.goods.presenter.impl;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.surhoo.sh.R;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.goods.bean.GoodDetailBean;
import com.surhoo.sh.goods.bean.RequestAddToCarBean;
import com.surhoo.sh.goods.presenter.GoodsDetailPresenter;
import com.surhoo.sh.goods.view.GoodsDetailView;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GoodsDetailPresenterImpl implements GoodsDetailPresenter {

    GoodsDetailView goodsDetailView;
    Activity activity;

    private static final String TAG = "GoodsDetailPresenterImpl";


    @Override
    public void bindView(Activity activity, GoodsDetailView view) {
        this.activity = activity;
        this.goodsDetailView = view;
    }

    @Override
    public void unBindView() {
        goodsDetailView = null;
    }


    @Override
    public void requestData(int id) {

        HttpParams httpParams = new HttpParams();

        httpParams.put("goodsId",id);

        NetworkReturnUtil.requestBeanResultUseGet(goodsDetailView, activity, Api.GOODSDETAIL, httpParams, GoodDetailBean.class);

    }

    @Override
    public void addToCar(String requestTag,RequestAddToCarBean bean) {

        String s = JSONObject.toJSONString(bean);

        NetworkReturnUtil.requestStringResultUsePost(requestTag,goodsDetailView,activity,Api.ADDTOCART,s);

    }

    @Override
    public void getShoopingCartNumber(String requestTag) {
        NetworkReturnUtil.requestStringResultUseGet(requestTag,goodsDetailView,activity,Api.SHOPPINGCARTNUMBER,null);
    }

    @Override
    public void addCollect(String requestTag,int type, int typeId) {
        String s = "{\"type\":"+type+",\"typeId\":"+typeId+"}";
        NetworkReturnUtil.requestStringResultUsePost(requestTag,goodsDetailView,activity,Api.collect,s);
    }

    @Override
    public void cancelCollect(String requestTag, int type, int typeId) {
        String s = "{\"type\":"+type+",\"typeId\":"+typeId+"}";
        NetworkReturnUtil.requestStringResultUsePut(requestTag,goodsDetailView,activity,Api.collect,s);
    }
}
