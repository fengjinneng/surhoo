package com.surhoo.sh.goods.presenter.impl;

import android.app.Activity;

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
import com.surhoo.sh.goods.presenter.GoodsDetailPresenter;
import com.surhoo.sh.goods.view.GoodsDetailView;

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

        String url = Api.GOODSDETAIL + "/" + id;

        NetworkReturnUtil.requestOne(goodsDetailView, activity, url, httpParams, GoodDetailBean.class);

    }

    @Override
    public void addToCar(int goodsId, int skuId, int goodsNum) {

        HttpParams httpParams = new HttpParams();

        String url = Api.ADDTOCART;
        httpParams.put("goodsId", goodsId);
        httpParams.put("skuId", skuId);
        httpParams.put("goodsNum", goodsNum);

        PostRequest<String> request = OkGo.<String>post(url)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth))
                .params(httpParams);

        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (!StringUtils.isEmpty(response.body())) {
                            goodsDetailView.addToCarResult(Integer.parseInt(response.body()));
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                goodsDetailView.showToastMsg(response.body());
            }
        };

        request.execute(stringCallback);

    }

    @Override
    public void getShoopingCartNumber() {

        GetRequest<String> request = OkGo.<String>get(Api.SHOPPINGCARTNUMBER)
                .tag(activity)
                .headers("Authorization", activity.getResources().getString(R.string.Auth));

        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {

                        if (!StringUtils.isEmpty(response.body())) {
                            goodsDetailView.showCarNumber(Integer.parseInt(response.body()));
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                goodsDetailView.showToastMsg(response.body());
            }
        };

        request.execute(stringCallback);

    }

}
